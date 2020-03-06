package com.stone.dailypractice.scsample.userclient.controller;

import com.stone.dailypractice.scsample.userclient.entity.UserInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.stone.dailypractice.scsample.userclient.feign.UserInfoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MovieController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserInfoClient userInfoClient;

    //metrics.rollingStats.numBuckets：设置滚动的统计窗口被分成的桶（bucket）的数目。默认值 10。
    //注意：”metrics.rollingStats.timeInMilliseconds % metrics.rollingStats.numBuckets == 0"必须为true，否则会抛出异常。即任何能被metrics.rollingStats.timeInMilliseconds整除的值。
    @GetMapping("/user/{id}")
    @HystrixCommand(fallbackMethod = "bindByIDFallback",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
                    //, @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "100")
                    //, @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "10")
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "1"),
                    @HystrixProperty(name = "maxQueueSize", value = "2")
            }
    )
    public UserInfo findByID(@PathVariable Long id) {
        UserInfo userInfo = restTemplate.getForObject("http://provider-user/user/" + id, UserInfo.class);
        userInfo.setFrom("MovieController");
        return userInfo;
    }

    @GetMapping("/user_feign/{id}")
    public UserInfo findByID_feign(@PathVariable Long id) {
        UserInfo userInfo = userInfoClient.findByID(id);
        userInfo.setFrom("MovieController_feign");
        return userInfo;
    }

    //    @HystrixCommand(fallbackMethod = "bindByIDFallback",
//            commandProperties = {
//                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
//                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10")
//            },
//            threadPoolProperties = {
//                    @HystrixProperty(name = "coreSize", value = "5"),
//                    @HystrixProperty(name = "maxQueueSize", value = "10")
//            }
//    )
    @GetMapping("/timeout/{id}")
    @HystrixCommand(fallbackMethod = "bindByIDFallback",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
            }
    )
    public UserInfo findByIdWithTimeout(@PathVariable Long id) {
        try {
            Thread.sleep(6000);
        } catch (Exception e) {
        }
        UserInfo userInfo = findByID(id);
        userInfo.setFrom("MovieControllerTimeout");

        return userInfo;
    }

    public UserInfo bindByIDFallback(Long id) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(-1);
        userInfo.setName("调用失败时默认返回");
        return userInfo;
    }

}

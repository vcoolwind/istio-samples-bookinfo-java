package com.stone.dailypractice.bookinfo.configreload;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * get value from secret file
 * 1. create k8s secret  [mongodb-secret.yml]
 * 2. set deployment'env  with  secret  [deployment.yml]
 * 3. get value from env or config file.
 */
@Configuration
@Data
public class MongoDBConfig {
    @Value("${MONGODB_USERNAME}")
    private String username;

    @Value("${MONGODB_PASSWORD}")
    private String password;
}

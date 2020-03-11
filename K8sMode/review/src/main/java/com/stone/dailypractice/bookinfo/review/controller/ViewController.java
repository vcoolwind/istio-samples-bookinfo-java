package com.stone.dailypractice.bookinfo.review.controller;

import com.stone.dailypractice.bookinfo.review.feignclient.RatingsClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/")
public class ViewController {
    private final static Boolean ratings_enabled = Boolean.valueOf(System.getenv("ENABLE_RATINGS"));
    private final static String star_color = System.getenv("STAR_COLOR") == null ? "black" : System.getenv("STAR_COLOR");
    private final static String services_domain = System.getenv("SERVICES_DOMAIN") == null ? "" : ("." + System.getenv("SERVICES_DOMAIN"));
    private final static String ratings_hostname = System.getenv("RATINGS_HOSTNAME") == null ? "ratings" : System.getenv("RATINGS_HOSTNAME");
    private final static String ratings_service = "http://" + ratings_hostname + services_domain + ":9080/ratings";
    // HTTP headers to propagate for distributed tracing are documented at
    // https://istio.io/docs/tasks/telemetry/distributed-tracing/overview/#trace-context-propagation
    private final static String[] headers_to_proagate = {"x-request-id", "x-b3-traceid", "x-b3-spanid", "x-b3-sampled", "x-b3-flags",
            "x-ot-span-context", "x-datadog-trace-id", "x-datadog-parent-id", "x-datadog-sampled", "end-user", "user-agent"};

    @Autowired
    private DiscoveryClient discoveryClient;


    @RequestMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @RequestMapping("/services")
    public List<String> services() {
        return this.discoveryClient.getServices();
    }


    private String getJsonResponse(String productId, int starsReviewer1, int starsReviewer2) {
        String result = "{";
        result += "\"id\": \"" + productId + "\",";
        result += "\"reviews\": [";

        // reviewer 1:
        result += "{";
        result += "  \"reviewer\": \"Reviewer1\",";
        result += "  \"text\": \"An extremely entertaining play by Shakespeare. The slapstick humour is refreshing!\"";
        if (ratings_enabled) {
            if (starsReviewer1 != -1) {
                result += ", \"rating\": {\"stars\": " + starsReviewer1 + ", \"color\": \"" + star_color + "\"}";
            } else {
                result += ", \"rating\": {\"error\": \"Ratings service is currently unavailable\"}";
            }
        }
        result += "},";

        // reviewer 2:
        result += "{";
        result += "  \"reviewer\": \"Reviewer2\",";
        result += "  \"text\": \"Absolutely fun and entertaining. The play lacks thematic depth when compared to other plays by Shakespeare.\"";
        if (ratings_enabled) {
            if (starsReviewer2 != -1) {
                result += ", \"rating\": {\"stars\": " + starsReviewer2 + ", \"color\": \"" + star_color + "\"}";
            } else {
                result += ", \"rating\": {\"error\": \"Ratings service is currently unavailable\"}";
            }
        }
        result += "}";

        result += "]";
        result += "}";

        return result;
    }

    @Autowired
    private RatingsClient ratingsClient;

    private JsonObject getRatings(String productId) {
        try {
            String ratings = ratingsClient.getRatings(productId);
            JsonReader jsonReader = Json.createReader(new ByteArrayInputStream(ratings.getBytes()));
            return jsonReader.readObject();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @GetMapping("/health")
    public String health() {
        return "{\"status\": \"Reviews is healthy\"}";
    }

    @GetMapping("/reviews/{productId}")
    public String bookReviewsById(@PathVariable("productId") int productId, HttpServletRequest request) {
        int starsReviewer1 = -1;
        int starsReviewer2 = -1;

        if (ratings_enabled) {
            JsonObject ratingsResponse = getRatings(Integer.toString(productId));
            if (ratingsResponse != null) {
                if (ratingsResponse.containsKey("ratings")) {
                    JsonObject ratings = ratingsResponse.getJsonObject("ratings");
                    if (ratings.containsKey("Reviewer1")) {
                        starsReviewer1 = ratings.getInt("Reviewer1");
                    }
                    if (ratings.containsKey("Reviewer2")) {
                        starsReviewer2 = ratings.getInt("Reviewer2");
                    }
                }
            }
        }

        String jsonResStr = getJsonResponse(Integer.toString(productId), starsReviewer1, starsReviewer2);
        return jsonResStr;
    }
}

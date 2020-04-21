package com.stone.dailypractice.bookinfo.configreload.model;

import lombok.Data;

@Data
public class RemoteConfig {
    private int requestThreshold = -1;

    private String alertLevel = "unknown";

    private String mongoUserName;

    private String mongoPassword;
}

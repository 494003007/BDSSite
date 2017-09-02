package com.bdssite.modules.searchmanage;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by D on 2017/9/2.
 */
@ConfigurationProperties(prefix = "solr")
public class SolrManager {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

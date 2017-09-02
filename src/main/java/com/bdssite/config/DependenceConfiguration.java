package com.bdssite.config;

import com.bdssite.modules.searchmanage.SolrManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by D on 2017/9/2.
 */
@Configuration
public class DependenceConfiguration {
    @Bean
    public SolrManager solrManager(){
        return new SolrManager();
    }
}

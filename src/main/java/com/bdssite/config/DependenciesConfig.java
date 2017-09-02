package com.bdssite.config;

import com.bdssite.modules.searchmanage.SolrManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by D on 2017/9/3.
 */
@Configuration
public class DependenciesConfig {
    @Bean
    SolrManager solrManager(){
        return new SolrManager();
    }
}

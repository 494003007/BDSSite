package com.bdssite.config;

import com.bdssite.tool.majorandsubjectgenerator.MajorAndSubjectGeneratorMain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Ed_cc on 2017/5/23.
 */
@Configuration
public class ToolConfiguration {
    @Bean
    MajorAndSubjectGeneratorMain majorAndSubjectGeneratorMain(){
        return new MajorAndSubjectGeneratorMain();
    }
}

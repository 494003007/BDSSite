package com.bdssite;

import com.bdssite.modules.searchmanage.SolrManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by D on 2017/9/3.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SolrManagerTest {
    @Autowired
    private SolrManager solrManager;
    @Test
    public void propertyTest(){
        System.out.println(solrManager.getSolrUrl());
    }
}

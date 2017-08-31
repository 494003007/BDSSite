package com.bdssite;

import com.bdssite.tool.majorandsubjectgenerator.MajorAndSubjectGeneratorMain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by D
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class majorAndSubjectGener {
    @Autowired
    MajorAndSubjectGeneratorMain msg;
    @Test
    public void generateTest() throws Exception {
        msg.majorGenerate();
        msg.subjectGenerate();
    }
}

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.recordme;

import com.recordme.modules.usermanage.entity.LexiconCategories;
import com.recordme.modules.usermanage.services.LexiconCategoriesService;
import formgenerator.Tool.RequireProfessionalLexicon;
import java.util.Iterator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SaveLexiconCategoriesToSql {
    @Autowired
    private LexiconCategoriesService lexiconCategoriesService;

    public SaveLexiconCategoriesToSql() {
    }

    @Test
    public void saveToSql() throws Exception {
        RequireProfessionalLexicon requireProfessionalLexicon = new RequireProfessionalLexicon("https://www.liepin.com");
        Iterator var2 = requireProfessionalLexicon.getList().iterator();

        while(var2.hasNext()) {
            LexiconCategories lexiconCategories = (LexiconCategories)var2.next();
            this.lexiconCategoriesService.save(lexiconCategories);
        }

    }
}

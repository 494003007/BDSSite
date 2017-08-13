//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bdssite.tool.formgenerator.Tool;

import com.bdssite.modules.usermanage.entity.LexiconCategories;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequireProfessionalLexicon {
    List<LexiconCategories> list = new ArrayList();

    public List<LexiconCategories> getList() {
        return this.list;
    }

    public RequireProfessionalLexicon(String captureUrl) throws Exception {
        String ul = "";
        String li = "";
        String dt = "";
        String liLexicon = "";
        String dtLexicon = "";
        String ddLexicon = "";
        String dd = "";
        int times = 0;
        URL url = new URL(captureUrl);
        URLConnection con = url.openConnection();
        InputStream is = con.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

        String s;
        String content;
        for(content = reader.readLine(); (s = reader.readLine()) != null; content = content + s + "\n") {
            ;
        }

        content = content + reader.readLine() + "\n";
        Pattern patternUl = Pattern.compile("<ul class=\"hot-job float-left\" data-selector=\"hot-job\">([\\s\\S.]*?)</ul>");
        Matcher matcherUl = patternUl.matcher(content);

        label52:
        while(true) {
            do {
                if(!matcherUl.find()) {
                    System.out.println(ul);
                    return;
                }

                ul = matcherUl.group(1);
                System.out.println(ul);
            } while(ul == null);

            Pattern patternLi = Pattern.compile("<li>([\\s\\S.]*?)</li>");
            Matcher matcherLi = patternLi.matcher(ul);

            label50:
            while(true) {
                do {
                    if(!matcherLi.find()) {
                        continue label52;
                    }

                    li = matcherLi.group(1);
                } while(li == null);

                Pattern patternLiLexicon = Pattern.compile("class=\"indus-title\">([\\s\\S.]*?)</a>");
                Matcher matcherLiLexicon = patternLiLexicon.matcher(li);
                matcherLiLexicon.find();
                liLexicon = matcherLiLexicon.group(1);
                ++times;
                LexiconCategories liLexiconCategories = this.getLexiconCategories((long)times, liLexicon, new long[]{0L});
                this.list.add(liLexiconCategories);
                Pattern patternDt = Pattern.compile("target=\"_blank\" >([\\s\\S.]*?)</a>");
                Matcher matcherDt = patternDt.matcher(li);
                Pattern patternDd = Pattern.compile("<dd>([\\s\\S.]*?)</dd>");
                Matcher matcherDd = patternDd.matcher(li);

                while(true) {
                    LexiconCategories dtLexiconCategories;
                    do {
                        if(!matcherDt.find()) {
                            continue label50;
                        }

                        dt = matcherDt.group(1);
                        dtLexicon = matcherDt.group(1);
                        ++times;
                        dtLexiconCategories = this.getLexiconCategories((long)times, dtLexicon, new long[]{0L, liLexiconCategories.getId()});
                        this.list.add(dtLexiconCategories);
                    } while(!matcherDd.find());

                    dd = matcherDd.group(1);
                    Pattern patternLexicon = Pattern.compile("\">([\\s\\S.]*?)</a>");
                    Matcher matcherLexicon = patternLexicon.matcher(dd);

                    while(matcherLexicon.find()) {
                        ddLexicon = matcherLexicon.group(1);
                        ++times;
                        LexiconCategories ddLexiconCategories = this.getLexiconCategories((long)times, ddLexicon, new long[]{0L, liLexiconCategories.getId(), dtLexiconCategories.getId()});
                        this.list.add(ddLexiconCategories);
                    }
                }
            }
        }
    }

    public LexiconCategories getLexiconCategories(long id, String classification, long... ids) {
        LexiconCategories lexiconCategories = new LexiconCategories();
        String parentNode = "";
        long[] var7 = ids;
        int var8 = ids.length;

        for(int var9 = 0; var9 < var8; ++var9) {
            long id0 = var7[var9];
            parentNode = parentNode + id0 + ",";
        }

        lexiconCategories.setId(id);
        lexiconCategories.setParentNode(parentNode);
        lexiconCategories.setClassification(classification);
        lexiconCategories.setDescription(classification);
        return lexiconCategories;
    }
}

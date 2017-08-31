package com.bdssite.tool.majorandsubjectgenerator;

import com.bdssite.modules.searchmanage.entity.Major;
import com.bdssite.modules.searchmanage.entity.Subject;
import com.bdssite.modules.searchmanage.service.MajorService;
import com.bdssite.modules.searchmanage.service.SubjectService;
import com.sun.istack.internal.Nullable;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MajorAndSubjectGeneratorMain {

    @Autowired
    MajorService ms;
    @Autowired
    SubjectService ss;

    public String getUrlContent(String urlString) throws Exception {
        URL url = new URL(urlString);
        URLConnection urlConnection = url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        int a;
        while((a = br.read())!=-1){
            sb.append(Character.toChars(a));
        }
        return sb.toString();
    }

    public void majorGenerate()  throws Exception {

        HashMap<String,String> muMap = new HashMap<>();
        String content = getUrlContent("http://gkcx.eol.cn/schoolhtm/specialty/10032/list.htm");
        Pattern pat = Pattern.compile("<a target=\"_blank\" href=\"(.*?)\">&nbsp;(.*?)</a>");
        Matcher mat = pat.matcher(content);
        Pattern indexPat = Pattern.compile("recomzytype=(.*?)\"");
        Matcher indexMat = indexPat.matcher(content);
        Major indexMajor;
        boolean hasNext;
        if(indexMat.find()){
            indexMajor = createMajor(indexMat.group(1),null);
            hasNext = indexMat.find();
        }else{
            return;
        }
        while(mat.find()){
            if(!hasNext||mat.start()<indexMat.start()){
                createMajor(mat.group(2),indexMajor);
            }else{
                indexMajor = createMajor(indexMat.group(1),null);
                hasNext = indexMat.find();
            }
        }

    }

    public void subjectGenerate() throws Exception{
        String content = getUrlContent("http://gkcx.eol.cn/schoolhtm/specialty/10032/list.htm");
        Pattern pat = Pattern.compile("<a target=\"_blank\" href=\"(.*?)\">&nbsp;(.*?)</a>");
        Pattern subjectPartPat = Pattern.compile("<[pbr]*?>\\s*(主要课程|专业核心课程与主要实践环节|主干学科|专业核心能力)：(.*?)<[/pbr]*?>");
        Pattern subjectPat = Pattern.compile("([^：]*?)(?:、|。|等，|以及|，| |,|\\.)+");
        Matcher mat = pat.matcher(content);
        while(mat.find()){
            Major major = ms.findByName(mat.group(2));
            try{
                content = getUrlContent("http://gkcx.eol.cn/" + mat.group(1));
                Matcher subjectPartMatcher = subjectPartPat.matcher(content);
                while(subjectPartMatcher.find()){
                    Matcher subjectMatcher = subjectPat.matcher(subjectPartMatcher.group(0));
                    while(subjectMatcher.find()){
                        createSubject(subjectMatcher.group(1),major);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    public Major createMajor(String name,@Nullable Major parent){
        Major major = ms.findByName(name);
        if(major==null){
            major = new Major();
            major.setName(name);
            if(parent != null){
                major.setParentMajor(parent);
            }
            ms.save(major);
        }

        return major;
    }

    public Subject createSubject(String name,Major major){
        Subject sub = ss.findByName(name);
        if (sub==null){
            sub = new Subject();
            sub.setName(name);
            sub.setMajors(new ArrayList<>());
        }
        sub.getMajors().add(major);
        ss.save(sub);
        return sub;
    }
}

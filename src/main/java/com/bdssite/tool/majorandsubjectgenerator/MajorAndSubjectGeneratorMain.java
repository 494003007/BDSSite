package com.bdssite.tool.majorandsubjectgenerator;

import com.bdssite.modules.searchmanage.entity.Major;
import com.bdssite.modules.searchmanage.service.MajorService;
import com.sun.istack.internal.Nullable;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MajorAndSubjectGeneratorMain {

    @Autowired
    MajorService ms;

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

    public void gener()  throws Exception {

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

    public Major createMajor(String name,@Nullable Major parent){
        Major major = new Major();
        major.setName(name);
        if(parent != null){
            major.setParentMajor(parent);
        }
        ms.save(major);
        return major;
    }
}

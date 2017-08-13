package com.bdssite.tool.majorandsubjectgenerator;

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
    public static String getUrlContent(String urlString) throws Exception {
        URL url = new URL(urlString);
        URLConnection urlConnection = url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        int a;
        while((a = br.read())!=-1){
            sb.append(Character.toChars(a));
        }
        String content = sb.toString();
        return content;
    }

    public static void main(String[] args)  throws Exception {

        String content = getUrlContent("http://gkcx.eol.cn/schoolhtm/specialty/10032/list.htm");
        Pattern pat = Pattern.compile("<a target=\"_blank\" href=\"(.*?)\">&nbsp;(.*?)</a>");
        Matcher mat = pat.matcher(content);
        HashMap<String,String> muMap = new HashMap<>();
        while(mat.find()){
            muMap.put(mat.group(1),mat.group(2));
        }

    }


}

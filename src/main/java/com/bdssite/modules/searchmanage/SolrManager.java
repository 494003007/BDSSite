package com.bdssite.modules.searchmanage;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by D on 2017/9/2.
 */
@ConfigurationProperties(prefix = "solr")
public class SolrManager {
    private String solrUrl;


    public String getSolrUrl() {
        return solrUrl;
    }

    public void setSolrUrl(String url) {
        this.solrUrl = url;
    }

    public String excuteAndOutPut(String urlS){
        try {

            URL url = new URL(solrUrl + urlS);
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();

            httpUrlConn.setDoOutput(false);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);

            httpUrlConn.setRequestMethod("GET");
            httpUrlConn.connect();

            BufferedReader tBufferedReader = new BufferedReader(new InputStreamReader(httpUrlConn.getInputStream()));

            StringBuilder tStringBuffer = new StringBuilder();

            String sTempOneLine;

            while ((sTempOneLine = tBufferedReader.readLine()) != null){

                tStringBuffer.append(sTempOneLine);

            }

            tBufferedReader.close();
            httpUrlConn.disconnect();


            return tStringBuffer.toString();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}

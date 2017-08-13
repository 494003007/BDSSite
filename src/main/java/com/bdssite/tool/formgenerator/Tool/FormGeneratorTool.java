package com.bdssite.tool.formgenerator.Tool;

/**
 * Created by D on 2017/4/4.
 */
public class FormGeneratorTool {
    public static String toCamelCase(String underScoreCaseName){
        StringBuilder sb = new StringBuilder(underScoreCaseName);
        int index;
        while((index = sb.indexOf("_")) != -1){
            sb.replace(index,index+2,(sb.charAt(index+1)+ "").toUpperCase());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(FormGeneratorTool.toCamelCase("user_info"));
    }
}

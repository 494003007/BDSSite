package com.bdssite.tool.formgenerator;

/**
 * Created by D on 2017/4/4.
 */
public abstract class TypeGenerator {
    public String className = "";
    public String type = "";
    public String generate(String entityName,String name,int maxLength){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<label th:for=\"").append(name).append("\">").append(name).append("</label>");
        stringBuilder.append("<input ");
        stringBuilder.append("id=\"").append(name).append("\" ");
        stringBuilder.append("name=\"").append(name).append("\" ");
        stringBuilder.append("class=\"").append(className).append("\" ");
        stringBuilder.append("type=\"").append(type).append("\" ");
        stringBuilder.append("th:value=\"").append("${").append(entityName).append(".").append(name).append("}\" ");
        stringBuilder.append("/>");
        return stringBuilder.toString();
    }
    public void setParam(String className,String type){
        this.className = className;
        this.type = type;
    }
}

package com.bdssite.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by D on 2017/2/15.
 */
@Configuration
public class ShiroConfiguration {

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }



    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();

        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));

        return hashedCredentialsMatcher;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();


        shiroFilterFactoryBean.setSecurityManager(securityManager());

        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();

        filterChainDefinitionMap.put("/favicon.ico","anon");

        filterChainDefinitionMap.put("/loginI","anon");

        filterChainDefinitionMap.put("/register","anon");

        filterChainDefinitionMap.put("/AuthorizationManage/currentUser","anon");

        filterChainDefinitionMap.put("/retrieve","anon");


        filterChainDefinitionMap.put("/index","anon");

        filterChainDefinitionMap.put("/searchApi/**","anon");

        filterChainDefinitionMap.put("/img/**","anon");

        filterChainDefinitionMap.put("/assets/**","anon");

        filterChainDefinitionMap.put("/css/**","anon");

        filterChainDefinitionMap.put("/js/**","anon");

        filterChainDefinitionMap.put("/fonts/**","anon");
        //设置退出url
        filterChainDefinitionMap.put("/logout","logout");

        //所有url需登陆才可访问
        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/error/noPermission");

        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realmImpl());
        //注入缓存管理器;
        defaultWebSecurityManager.setCacheManager(ehCacheManager());
        return defaultWebSecurityManager;
    }

    @Bean
    public Realm realmImpl() {
        ShiroRealmImpl realm = new ShiroRealmImpl();
        realm.setCredentialsMatcher(hashedCredentialsMatcher());
        return realm;
    }

    @Bean
    public EhCacheManager ehCacheManager(){
        System.out.println("ShiroConfiguration.getEhCacheManager()");
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
        return cacheManager;
    }

}

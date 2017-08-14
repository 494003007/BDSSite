package com.bdssite.modules.usermanage.entity;

/**
 * Created by edward on 17-2-14.
 */



import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Date;

import java.util.List;

import javax.persistence.*;



@Entity
public class UserInfo implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id@GeneratedValue
    private long uid;//用户id;


    @Column(unique=true)
    private String username;//账号.

    private String name;//名称（昵称或者真实姓名，不同系统不同定义）
    @Column(nullable = false)
    private String password; //密码;
    @Column(nullable = false)
    private String salt;//加密密码的盐

    private String true_name;//真实姓名
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date birth_date;//出生日期

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    private String city;//国家
    @Column(nullable = false,unique = true)
    private String email;//邮件
    private String qq;
    private String phone;
    private int info_shield;//信息屏蔽设置
    private int sex;
    private String address;
    private String we_chat_account;//微信账号
    private String ip_address;//ip地址
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date new_time;//创建时间
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date last_login_time;//最后一次登陆

    private byte state;//用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定.

    //个人密保
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "user")//指向多的那方的pojo的关联外键字段
    private List<UserQuestion> userQuestions;

    //个人简历
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "user")//指向多的那方的pojo的关联外键字段
    private List<NomalUserResume> nomalUserResumes;


    //HR简历
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "user")//指向多的那方的pojo的关联外键字段
    private List<HrReleaseRecruit> hrReleaseRecruits;


    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    /*
    *
    *
    *
    *            谁删这一句，我就跟谁急
    *
    *
    *
    * */
    @JoinTable(name = "SysUserRole", joinColumns = { @JoinColumn(name = "uid") }, inverseJoinColumns ={@JoinColumn(name = "roleId") })
    private List<SysRole> roles;// 一个用户具有多个角色


//    用户 -- 收藏关系：多对多关系;
    @ManyToMany(mappedBy = "userInfos")
    @JsonIgnore
//    @JoinTable(name="SysCollectionUser",joinColumns={@JoinColumn(name="collectionId")},inverseJoinColumns={@JoinColumn(name="uid")})
    private List<CollectionProfession> collectionProfessions;

    //站内信发信人关系
    @OneToMany(mappedBy = "fromUser")
    @JsonIgnore
    private List<ShortMessage> receiveMessage;


    //站内信收信人关系
    @OneToMany(mappedBy = "toUser")
    @JsonIgnore
    private List<ShortMessage> sendMessage;


    //操作日志
    @JsonIgnore
    @OneToMany(mappedBy = "operateUser")
    private List<OperateLog> operateLogs;

    public List<ShortMessage> getReceiveMessage() {
        return receiveMessage;
    }

    public void setReceiveMessage(List<ShortMessage> receiveMessage) {
        this.receiveMessage = receiveMessage;
    }

    public List<ShortMessage> getSendMessage() {
        return sendMessage;
    }

    public void setSendMessage(List<ShortMessage> sendMessage) {
        this.sendMessage = sendMessage;
    }

    public List<OperateLog> getOperateLogs() {
        return operateLogs;
    }

    public void setOperateLogs(List<OperateLog> operateLogs) {
        this.operateLogs = operateLogs;
    }

    public List<CollectionProfession> getCollectionProfessions() {
        return collectionProfessions;
    }

    public void setCollectionProfessions(List<CollectionProfession> collectionProfessions) {
        this.collectionProfessions = collectionProfessions;
    }

    public List<UserQuestion> getUserQuestions() {
        return userQuestions;
    }

    public void setUserQuestions(List<UserQuestion> userQuestions) {
        this.userQuestions = userQuestions;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTrue_name() {
        return true_name;
    }

    public void setTrue_name(String true_name) {
        this.true_name = true_name;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getInfo_shield() {
        return info_shield;
    }

    public void setInfo_shield(int info_shield) {
        this.info_shield = info_shield;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWe_chat_account() {
        return we_chat_account;
    }

    public void setWe_chat_account(String we_chat_account) {
        this.we_chat_account = we_chat_account;
    }

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public Date getNew_time() {
        return new_time;
    }

    public void setNew_time(Date new_time) {
        this.new_time = new_time;
    }

    public Date getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(Date last_login_time) {
        this.last_login_time = last_login_time;
    }


    /**
     * 密码盐.
     * @return
     */
    public String getCredentialsSalt(){
        return this.username+this.salt;
    }

    @Override
    public String toString() {
        return "UserInfo [uid=" + uid + ", username=" + username + ", name=" + name + ", state=" + state + "]";
    }


}
package com.recordme.modules.usermanage.entity;

/**
 * Created by edward on 17-2-14.
 */

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.*;


/**
 * 系统角色实体类;
 * @author Angel(QQ:412887952)
 * @version v.0.1
 */
@Entity
public class SysRole implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id@GeneratedValue
    private Long id; // 编号

    private String role; // 角色标识程序中判断使用,如"admin",这个是唯一的:
    private String description; // 角色描述,UI界面显示使用
    private Boolean available = Boolean.FALSE; // 是否可用,如果不可用将不会添加给用户
    private String parent_role;//父角色
    private Date create_time;//创建时间
    private Long create_user;//创建者
    private String role_describe;//角色描述




    //角色 -- 权限关系：多对多关系;
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="SysRolePermission",joinColumns={@JoinColumn(name="roleId")},inverseJoinColumns={@JoinColumn(name="permissionId")})
    private List<SysPermission> permissions;

    // 用户 - 角色关系定义;
    @JsonIgnore
    @ManyToMany
    @JoinTable(name="SysUserRole",joinColumns={@JoinColumn(name="roleId")},inverseJoinColumns={@JoinColumn(name="uid")})
    private List<UserInfo> userInfos;// 一个角色对应多个用户

    public List<UserInfo> getUserInfos() {
        return userInfos;
    }

    public void setUserInfos(List<UserInfo> userInfos) {
        this.userInfos = userInfos;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getParent_role() {
        return parent_role;
    }

    public void setParent_role(String parent_role) {
        this.parent_role = parent_role;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Long getCreate_user() {
        return create_user;
    }

    public void setCreate_user(Long create_user) {
        this.create_user = create_user;
    }

    public String getRole_describe() {
        return role_describe;
    }

    public void setRole_describe(String role_describe) {
        this.role_describe = role_describe;
    }
//    public List<UserInfo> getUserInfos() {
//        return userInfos;
//    }
//    public void setUserInfos(List<UserInfo> userInfos) {
//        this.userInfos = userInfos;
//    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Boolean getAvailable() {
        return available;
    }
    public void setAvailable(Boolean available) {
        this.available = available;
    }
    public List<SysPermission> getPermissions() {
        return permissions;
    }
    public void setPermissions(List<SysPermission> permissions) {
        this.permissions = permissions;
    }
    @Override
    public String toString() {
        return "SysRole [id=" + id + ", role=" + role + ", description=" + description + ", available=" + available
                ;
    }
}
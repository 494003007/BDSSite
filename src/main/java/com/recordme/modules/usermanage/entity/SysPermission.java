package com.recordme.modules.usermanage.entity;

import javafx.scene.chart.PieChart;

import java.sql.Date;
import java.util.List;

/**
 * Created by edward on 17-2-14.
 */

import java.io.Serializable;
import java.util.List;



import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * 权限实体类;
 * @author Angel(QQ:412887952)
 * @version v.0.1
 */
@Entity
public class SysPermission implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id@GeneratedValue
    private long id;//主键.
    private String name;//名称.

    @Column(columnDefinition="enum('menu','button')")
    private String resourceType;//资源类型，[menu|button]
    private String url;//资源路径.
    private String permission; //权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view
    private Long parentId; //父编号
    private String parentIds; //父编号列表
    private Boolean available = Boolean.FALSE;

    private String parent_premission;//父权限
    private String premission_name;//权限名
    private String premission_descrip;//权限描述
    private Date creat_time;//创建时间

    public String getParent_premission() {
        return parent_premission;
    }

    public void setParent_premission(String parent_premission) {
        this.parent_premission = parent_premission;
    }

    public String getPremission_name() {
        return premission_name;
    }

    public void setPremission_name(String premission_name) {
        this.premission_name = premission_name;
    }

    public String getPremission_descrip() {
        return premission_descrip;
    }

    public void setPremission_descrip(String premission_descrip) {
        this.premission_descrip = premission_descrip;
    }

    public Date getCreat_time() {
        return creat_time;
    }

    public void setCreat_time(Date creat_time) {
        this.creat_time = creat_time;
    }

    @ManyToMany
    @JoinTable(name="SysRolePermission",joinColumns={@JoinColumn(name="permissionId")},inverseJoinColumns={@JoinColumn(name="roleId")})
    private List<SysRole> roles;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getResourceType() {
        return resourceType;
    }
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getPermission() {
        return permission;
    }
    public void setPermission(String permission) {
        this.permission = permission;
    }
    public Long getParentId() {
        return parentId;
    }
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    public String getParentIds() {
        return parentIds;
    }
    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }
    public Boolean getAvailable() {
        return available;
    }
    public void setAvailable(Boolean available) {
        this.available = available;
    }
    public List<SysRole> getRoles() {
        return roles;
    }
    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }
    @Override
    public String toString() {
        return "SysPermission [id=" + id + ", name=" + name + ", resourceType=" + resourceType + ", url=" + url
                + ", permission=" + permission + ", parentId=" + parentId + ", parentIds=" + parentIds + ", available="
                + available + ", roles=" + roles + "]";
    }

}
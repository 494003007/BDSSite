package com.recordme.modules.usermanage.entity; /***********************************************************************
 * Module:  UserRole.java
 * Author:  keben
 * Purpose: Defines the Class UserRole
 ***********************************************************************/

import org.apache.catalina.mbeans.UserMBean;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.*;

/** @pdOid f63e7854-56ee-4375-b759-3d51c153e6d3 */
public class UserRole {
   /** @pdOid b9c99a7b-5b27-41b2-9d47-979716ac4846 */
   public long id;
   /** @pdOid 52731ee7-ccf9-4b72-a0fc-870f7d1aa1f7 */
   public String parentRole;
   /** @pdOid 2fd6cd77-07d6-4b6b-9986-16e77720c8c9 */
   public String roleName;
   /** @pdOid 4f9142ea-b2a4-41da-9d35-95f2ed3298a8 */
   public Date createTime;
   /** @pdOid 9da6ae84-a5a8-491c-9609-1d39459d7d64 */
   public int createUser;
   /** @pdOid 393d7726-4879-41b8-850d-970530ce6dc8 */
   public String roleDescripe;
   public List<UserPremission> userPremission;

   public long getId() {
      return id;
   } public void setId(long id) {
      this.id = id;
   } public String getParentRole() {
      return parentRole;
   } public void setParentRole(String parentRole) {
      this.parentRole = parentRole;
   } public String getRoleName() {
      return roleName;
   } public void setRoleName(String roleName) {
      this.roleName = roleName;
   } public Date getCreateTime() {
      return createTime;
   } public void setCreateTime(Date createTime) {
      this.createTime = createTime;
   } public int getCreateUser() {
      return createUser;
   } public void setCreateUser(int createUser) {
      this.createUser = createUser;
   } public String getRoleDescripe() {
      return roleDescripe;
   } public void setRoleDescripe(String roleDescripe) {
      this.roleDescripe = roleDescripe;
   } public List<UserPremission> getUserPremission() {
      return userPremission;
   } public void setUserPremission(List<UserPremission> userPremission) {
      this.userPremission = userPremission;
   }




}
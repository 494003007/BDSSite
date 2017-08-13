package com.bdssite.modules.usermanage.entity; /***********************************************************************
 * Module:  UserQuestion.java
 * Author:  keben
 * Purpose: Defines the Class UserQuestion
 ***********************************************************************/

import javax.persistence.*;
import java.io.Serializable;

/*
* 密保问题
* */
@Entity
public class UserQuestion implements Serializable {
   private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue
   public int id;
   //问题
   public String question;
   //答案
   public String answer;


   @ManyToOne(fetch = FetchType.EAGER)//设置在“一方”pojo的外键字段上
   @JoinColumn(name = "userId", referencedColumnName = "uid")//设置对应数据表的列名和引用的数据表的列名
   private UserInfo user;

   public UserInfo getUserId() {
      return user;
   }

   public void setUserId(UserInfo userId) {
      this.user = userId;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getQuestion() {
      return question;
   }

   public void setQuestion(String question) {
      this.question = question;
   }

   public String getAnswer() {
      return answer;
   }

   public void setAnswer(String answer) {
      this.answer = answer;
   }
}
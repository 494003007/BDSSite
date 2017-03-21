package com.recordme.modules.usermanage.entity; /***********************************************************************
 * Module:  UserPremission.java
 * Author:  keben
 * Purpose: Defines the Class UserPremission
 ***********************************************************************/

import java.util.*;

/** @pdOid d81b55e2-1efc-454c-b086-c24300e0c6fe */
public class UserPremission {
   /** @pdOid aab4ffbe-85a7-4859-8107-519ab7d9bd84 */
   public long id;
   /** @pdOid 4f6a8905-3ad0-4e7c-ae07-7999c2cae9d2 */
   public String parentPremission;
   /** @pdOid 3dc7e8ce-18ad-4dc3-9092-04bbd74b6614 */
   public String premissionName;
   /** @pdOid 48b536aa-9702-4411-a04e-1d01afa45a14 */
   public Date createTime;
   /** @pdOid fb1a9dd9-3fbd-453d-843f-548800cfb39c */
   public String premissionDescripe;
      public Collection<UserRole> userRole;
   
   


}
package org.jisu.e_store_user_service.constants;

import org.jisu.e_store_user_service.entities.RoleEO;

public class RoleConstant {
    
    private static RoleEO USER_ROLE = new RoleEO(RoleIdConstant.USER_ROLE_ID, "USER");
    private static RoleEO ADMIN_ROLE = new RoleEO(RoleIdConstant.ADMIN_ROLE_ID,"ADMIN");

    public static RoleEO getUserRole(){
        return USER_ROLE;
    }

    public static RoleEO getAdminRole(){
        return ADMIN_ROLE;
    }
}

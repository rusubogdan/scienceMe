package com.scncm.model;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {

    public static final Integer ROLE_ADMIN        = 1;
    public static final Integer ROLE_MODERATOR    = 2;
    public static final Integer ROLE_USER         = 3;
    public static final Integer ROLE_RESTRICTED   = 4;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer roleId;

    @Column(name = "role")
    private String role;

    public Role() {}

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}

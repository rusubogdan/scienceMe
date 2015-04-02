package com.scncm.model;

import javax.persistence.*;
import java.util.Random;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {

    public static final Integer ROLE_ADMIN        = 1;
    public static final Integer ROLE_MODERATOR    = 2;
    public static final Integer ROLE_USER         = 3;
    public static final Integer ROLE_RESTRICTED   = 4;

    public Role(String role) {
        this.role = role;
    }

    public Role(){}

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", role='" + role + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role1 = (Role) o;

        if (role != null ? !role.equals(role1.role) : role1.role != null) return false;
        if (roleId != null ? !roleId.equals(role1.roleId) : role1.roleId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleId != null ? roleId.hashCode() : 0;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer roleId;

    @Column(name = "role")
    private String role;

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

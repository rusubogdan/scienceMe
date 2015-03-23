package com.scncm.model;

import javax.persistence.*;
import java.util.Random;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer roleId;

    @Column(name = "role")
    private String role;

    Role() {}

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "user_role",
//            joinColumns = {@JoinColumn(name = "role_id")},
//            inverseJoinColumns = {@JoinColumn(name = "user_id")}
//    )
//    private Set<User> userRoles;

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

//    public Set<User> getUserRoles() {
//        return userRoles;
//    }
//
//    public void setUserRoles(Set<User> userRoles) {
//        this.userRoles = userRoles;
//    }

}

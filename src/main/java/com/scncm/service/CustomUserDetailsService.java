package com.scncm.service;

import com.scncm.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("userDetailsService")
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) {

        com.scncm.model.User domainUser = userService.getUserByEmail(email);

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        // that freaking password may log you in :)
        if (domainUser == null) {
            return new User("anonymousUser", "5h4g534vh4bvfhsevhgovyoy",
                    true, true, true, true, getAuthorities(Role.ROLE_RESTRICTED));
        }

        return new User(
                domainUser.getEmail(),
                domainUser.getPassword(),
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                getAuthorities(domainUser.getRole().getRoleId())
        );
    }

    public Collection<? extends GrantedAuthority> getAuthorities(Integer role) {
        return getGrantedAuthorities(getRoles(role));
    }

    public List<String> getRoles(Integer role) {

        List<String> roles = new ArrayList<String>();

        if (role == 1) {
            roles.add("ROLE_MODERATOR");
            roles.add("ROLE_ADMIN");
            roles.add("ROLE_USER");
        } else if (role == 2) {
            roles.add("ROLE_MODERATOR");
            roles.add("ROLE_USER");
        } else if (role == 3) {
            roles.add("ROLE_USER");
        } else if (role == 4) {
            roles.add("ROLE_RESTRICTED");
        }

        return roles;
    }

    public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}

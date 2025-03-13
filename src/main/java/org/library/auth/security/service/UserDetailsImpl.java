package org.library.auth.security.service;

import org.library.user.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {

    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    //Permite crear un nuevo user Details
    public UserDetailsImpl(User  user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        buildAuthorities(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    private void buildAuthorities(User user) {
        this.authorities = List.of(
                new SimpleGrantedAuthority(
                        String.format("ROLE_%s", user.getRole().getName())
                )
        );
    }

}

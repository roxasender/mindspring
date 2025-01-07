package com.nexus.mindspring.security.user;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.nexus.mindspring.model.UserModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MindSpringUserDetail implements UserDetails {
    private Long Id;
    private String username;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public static MindSpringUserDetail build(UserModel user) {
        List<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
        return new MindSpringUserDetail(user.getUserId(), user.getUsername(), user.getEmail(), user.getPassword(), authorities);
    }

    // @Override
    // public Collection<? extends GrantedAuthority> getAuthorities() {
    //     return authorities;
    // }

    // @Override
    // public String getPassword() {
    //     return password;
    // }

    // @Override
    // public String getUsername() {
    //     return username;
    // }
    
    // @Override
    // public boolean isAccountNonExpired() {
    //     return true;
    // }

    // @Override
    // public boolean isAccountNonLocked() {
    //     return true;
    // }

    // @Override
    // public boolean isCredentialsNonExpired() {
    //     return true;
    // }

    // @Override
    // public boolean isEnabled() {
    //     return true;
    // }
}

package com.xqq.enrity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class LoginUser implements UserDetails {
    private User user;
    private List<String> premissions;

    @JSONField(serialize = false)
    private List<SimpleGrantedAuthority> authorities;


    public LoginUser(User user, List<String> premissions) {
        this.user = user;
        this.premissions = premissions;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
//        for (String premission : premissions) {
//            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(premission);
//            simpleGrantedAuthorities.add(simpleGrantedAuthority);
//        }
        if(authorities!=null)return authorities;
        authorities = premissions.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return authorities;
    }
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

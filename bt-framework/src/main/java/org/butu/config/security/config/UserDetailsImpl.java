package org.butu.config.security.config;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.butu.model.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: BTForum
 * @description: 用户登录数据
 * @packagename: org.butu.config.security.config
 * @author: BUTUbird
 * @date: 2022-03-15 11:33
 **/
@Data
public class UserDetailsImpl implements UserDetails {

    private  User user;

    private  List<String> permissions;



    public UserDetailsImpl(User user,List<String> permissions){
        this.user = user;
        this.permissions = permissions;
    }

    @JSONField(serialize = false)
    private List<SimpleGrantedAuthority> authorityList;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        authorityList = new ArrayList<>();
//        for (String permission : permissions){
//            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permission);
//            authorityList.add(authority);
//        }
        if (authorityList != null) {
            return authorityList;
        }
        authorityList = permissions
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return authorityList;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return user.getStatus();
    }
}

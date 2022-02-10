package com.baosight.security.distributed.uaa.service;

import com.baosight.security.distributed.uaa.dao.UserDao;
import com.baosight.security.distributed.uaa.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpringDataUserDetailsService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    //根据账号查询用户信息
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //连接数据库根据账号查询用户信息
        UserDto user = userDao.getUserByUsername(username);
        if (user == null) {
            return null;
        }
        //根据用户id查询用户权限
        List<String> permissions= userDao.findPermissionsByUserId(user.getId());
        //将permission转为数组
        String[] permissionArray = new String[permissions.size()];
        permissions.toArray(permissionArray);
        UserDetails userDetails = User.withUsername(user.getUsername()).password(user.getPassword()).authorities(permissionArray).build();
        return userDetails;
    }
}

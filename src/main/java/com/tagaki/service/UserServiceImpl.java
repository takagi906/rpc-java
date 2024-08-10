package com.tagaki.service;

import com.tagaki.pojo.User;

import java.util.Random;
import java.util.UUID;

public class UserServiceImpl implements UserService{


    @Override
    public User getUserByUserId(Integer id) {
        System.out.println("客户端查询了 "+id+" 的用户");
        Random random = new Random();
        return User.builder().userName(UUID.randomUUID().toString())
                .id(id).sex(random.nextInt(2)).build();
    }
}

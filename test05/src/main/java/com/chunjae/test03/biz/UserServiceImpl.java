package com.chunjae.test03.biz;

import com.chunjae.test03.entity.Euser;
import com.chunjae.test03.per.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Euser> getUserList() {
        return userMapper.getUserList();
    }

    @Override
    public Euser getUser(String name) {
        return userMapper.getUser(name);
    }

    @Override
    public void getWithdraw(String name) {
        userMapper.getWithdraw(name);
    }

    @Override
    public void getActivate(String name) {
        userMapper.getActivate(name);
    }

    @Override
    public void getDormant(String name) {
        userMapper.getDormant(name);
    }

    @Override
    public Euser getByEmail(String email) {
        return userMapper.getByEmail(email);
    }

    @Override
    public Euser getByName(String name) {
        return userMapper.getByName(name);
    }

    @Override
    public Euser findById(Euser user) {
        return userMapper.findById(user);
    }

    @Override
    public Euser findByPw(Euser user) {
        return userMapper.findByPw(user);
    }

    @Override
    public void userInsert(Euser user) {
        userMapper.userInsert(user);
    }

    @Override
    public void userEdit(Euser user) {
        userMapper.userEdit(user);
    }

    @Override
    public void userLevel(Euser user) {
        userMapper.userLevel(user);
    }
}

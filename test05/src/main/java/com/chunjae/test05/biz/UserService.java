package com.chunjae.test05.biz;

import com.chunjae.test05.entity.Euser;

import java.util.List;

public interface UserService {
    public List<Euser> getUserList();
    public Euser getUser(String name);
    public void getWithdraw(String name);
    public void getActivate(String name);
    public void getDormant(String name);
    public Euser getByEmail(String email);
    public Euser getByName(String name);
    public Euser findById(Euser user);
    public Euser findByPw(Euser user);
    public void userInsert(Euser user);
    public void userEdit(Euser user);
    public void userLevel(Euser user);
}

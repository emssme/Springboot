package com.chunjae.test05.per;

import com.chunjae.test05.entity.Euser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<Euser> getUserList();
    Euser getUser(String name);
    void getWithdraw(String name);
    void getActivate(String name);
    void getDormant(String name);
    Euser getByEmail(String email);
    Euser getByName(String name);
    Euser findById(Euser user);
    Euser findByPw(Euser user);
    void userInsert(Euser user);
    void userEdit(Euser user);
    void userLevel(Euser user);
}

package com.chunjae.test07.persis;

import com.chunjae.test07.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {
    User findUserByLoginId(@Param("loginId") String loginId);
    int setUserInfo(@Param("param") User param);
}

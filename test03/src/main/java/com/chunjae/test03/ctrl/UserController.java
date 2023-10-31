package com.chunjae.test03.ctrl;

import com.chunjae.test03.biz.UserService;
import com.chunjae.test03.entity.Euser;
import com.chunjae.test03.util.EmailSocket;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("list.do")
    public String userList(Model model) throws Exception {
        List<Euser> userList = userService.getUserList();
        model.addAttribute("userList", userList);
        return "/user/userList";
    }

    @GetMapping("detail.do")
    public String getUser(@RequestParam String name, Model model) throws Exception{
        Euser getUser = userService.getUser(name);
        model.addAttribute("detail", getUser);
        return "/user/getUser";
    }

    //로그인 폼
    @GetMapping("login.do")
    public String emailLoginForm(HttpServletRequest request, Model model) throws Exception {
        return "/user/login";
    }

    //이메일 로그인
    @PostMapping("emailLogin.do")
    public String emailLogin(@RequestParam String email, @RequestParam String password, HttpServletRequest request, Model model) throws Exception{


        return "/";
    }
    //아이디 로그인
    //탈퇴
    //계정 활성화
    //휴면처리
    //아이디 찾기
    //비밀번호호 찾기
    @PostMapping("/loginByEmail.do")
    public String loginByEmailPro(Euser euser, HttpSession session, Model model){
        Euser user = userService.getByEmail(euser.getEmail());
        if (user!=null){
            if (user.getPassword().equals(euser.getPassword())){
                model.addAttribute("msg","로그인 성공");
                session.setAttribute("sname", user.getName());
            } else {
                model.addAttribute("msg","비밀번호 오류");
                session.invalidate();
            }
        } else {
            model.addAttribute("msg", "해당 이메일을 가진 회원이 존재하지 않습니다.");
        }
        return "redirect:/login.do";
    }
    public void sendMail(Euser euser){
        EmailSocket dm = new EmailSocket();
        dm.sendMail(euser);
    }
    //회원가입
    @GetMapping("join.do")
    public String joinForm(){
        return "/user/join";
    }
    //회원정보수정
    //관리자 - 회원등급 변경
}

package com.task.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberViewController {

    // 회원가입 페이지 화면
    @GetMapping("/signup")
    public String signUpForm() {
        return "member/signup";
    }

    // 로그인 페이지 화면
    @GetMapping("/signin")
    public String signInForm() {
        return "member/signin";
    }
}

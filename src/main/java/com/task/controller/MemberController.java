package com.task.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.dto.request.MemberSignUpRequest;
import com.task.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    // 회원가입 API
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody MemberSignUpRequest request) {
        memberService.singUp(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원 가입 성공");
    }

}

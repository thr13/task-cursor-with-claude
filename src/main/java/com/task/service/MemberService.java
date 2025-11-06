package com.task.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.task.dto.request.MemberSignUpRequest;
import com.task.entity.Member;
import com.task.entity.Role;
import com.task.exception.ErrorCode;
import com.task.exception.MemberException;
import com.task.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    public void singUp(MemberSignUpRequest request) {
        existsByEmail(request.getEmail());

        Member newMember = new Member(
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getName(),
                Role.ROLE_MEMBER);

        memberRepository.save(newMember);
    }

    // 이메일로 회원 찾기
    @Transactional(readOnly = true)
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(
                () -> new MemberException(ErrorCode.MEMBER_NOT_FOUND));
    }

    private void existsByEmail(String email) {
        if (memberRepository.existsByEmail(email)) {
            throw new MemberException(ErrorCode.DUPLICATE_EMAIL);
        }
    }
}

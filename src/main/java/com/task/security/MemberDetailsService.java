package com.task.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.task.entity.Member;
import com.task.exception.ErrorCode;
import com.task.exception.MemberException;
import com.task.repository.MemberRepository;

@Service
@Transactional(readOnly = true)
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public MemberDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(username).orElseThrow(
                () -> new MemberException(ErrorCode.MEMBER_NOT_FOUND));

        return MemberDetails.from(member);
    }

}

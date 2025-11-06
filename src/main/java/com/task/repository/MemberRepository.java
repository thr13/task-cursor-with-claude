package com.task.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email); // 이메일로 회원 조회

    boolean existsByEmail(String email); // 이메일 중복 조회

}

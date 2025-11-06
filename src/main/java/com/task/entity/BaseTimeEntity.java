package com.task.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class BaseTimeEntity {

    @Column(name = "created_at", updatable = false)
    @CreatedDate()
    private LocalDateTime createdAt; // 생성일시

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt; // 수정일시

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt; // 삭제일시
}

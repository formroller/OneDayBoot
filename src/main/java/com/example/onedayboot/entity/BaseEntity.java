package com.example.onedayboot.entity;


import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@MappedSuperclass
@EntityListeners(value = AuditingEntityListener.class)
@Getter
abstract class BaseEntity {
//    @CreationTimestamp
//    private LocalDateTime regDate;

    @CreatedDate
    @Column(name="regdate", updatable = false)
    private LocalDateTime regDate;  // 2023-07-19 02:56:06.805994
//    private String regDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")); // 23. 7. 19. 오전 2:59
//    private String regDate;

//    @PrePersist // 엔티티 insert 이전 실행
//    public void onPrePersist(){
//        this.regDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
        // this.modDate = this.regDate;
//    }

    /*update*/
//    @LastModifiedDate
//    @Column(name="moddate")
//    private String modDate;
//
//    @PreUpdate // 엔티티 update 이전 실행
//    public void onPreUpdate(){
//        this.modDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
//    }

}

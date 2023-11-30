package com.jpastudy.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass                                           // 공통된 컬럼 작성 처리를 위해 사용
@EntityListeners(value = {AuditingEntityListener.class})    // DB에 추가되거나 변경될때 자동으로 시간 값 지정
public class BaseEntity {

    @CreatedDate                                            // Entity 가 생성된 시간
    @Column(name = "regdate", updatable = false)            // 컬럼의 이름, 업데이트 불가
    private LocalDateTime regDate;

    @LastModifiedDate                                       // Entity 가 마지막으로 수정된 시간
    @Column(name = "moddate")
    private LocalDateTime modDate;

}

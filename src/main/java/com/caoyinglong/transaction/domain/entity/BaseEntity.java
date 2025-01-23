package com.caoyinglong.transaction.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
public class BaseEntity {

    private int deleted = 0;

    private Timestamp ts;

    public BaseEntity() {
        this.ts = Timestamp.valueOf(LocalDateTime.now());
    }

}

package com.caoyinglong.transaction.domain.entity;

import com.caoyinglong.transaction.enums.BusinessType;
import com.caoyinglong.utils.SnowflakeUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transaction extends BaseEntity{

    @Id
    @Column(nullable = false)
    private String id;

    private String accountId;

    private BusinessType bussinessType;

    private BigDecimal amount;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    public void setPrimaryKey(){
        this.id = SnowflakeUtils.generateId();
    }

    public String getPrimaryKey(){
        return this.id;
    }

    public void setDefaultValue() {
        if(getCreateTime() == null){
            this.setCreateTime(LocalDateTime.now());
        }
        if(getUpdateTime() == null){
            this.setUpdateTime(LocalDateTime.now());
        }
    }

    public void setUpdateValue(){
        this.setUpdateTime(LocalDateTime.now());
        if(getCreateTime() == null){
            this.setCreateTime(LocalDateTime.now());
        }
    }

}

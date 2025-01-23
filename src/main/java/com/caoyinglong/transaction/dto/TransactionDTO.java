package com.caoyinglong.transaction.dto;

import com.caoyinglong.transaction.enums.BusinessType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@Getter
@Setter
public class TransactionDTO {

    private String id;
    @NotBlank(message = "account cannot be blank")
    private String accountId;

    @NotNull(message = "bussinessType cannot be null")
    private BusinessType bussinessType;

    @NotBlank(message = "amount cannot be blank")
    private BigDecimal amount;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}

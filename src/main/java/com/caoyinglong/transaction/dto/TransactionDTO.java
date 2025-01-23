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

    @NotBlank(message = "bussinessType cannot be null")
    private String bussinessType;

    @NotNull(message = "amount cannot be null")
    private BigDecimal amount;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}

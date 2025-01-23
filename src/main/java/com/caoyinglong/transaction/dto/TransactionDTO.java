package com.caoyinglong.transaction.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

package com.caoyinglong.transaction.assembler;

import com.caoyinglong.transaction.domain.entity.Transaction;
import com.caoyinglong.transaction.dto.TransactionDTO;
import com.caoyinglong.transaction.enums.BusinessType;

import java.util.List;

public class TransactionAssembler {

    public static TransactionDTO entityToDto(Transaction entity) {
        if (entity != null) {
            return TransactionDTO.builder()
                    .id(entity.getId())
                    .accountId(entity.getAccountId())
                    .bussinessType(entity.getBussinessType().name())
                    .amount(entity.getAmount())
                    .createTime(entity.getCreateTime())
                    .updateTime(entity.getUpdateTime())
                    .build();
        }
        return null;
    }
    public static Transaction dtoToEntity(TransactionDTO dto) {
        if (dto!= null) {
            return Transaction.builder()
                    .id(dto.getId())
                    .accountId(dto.getAccountId())
                    .bussinessType(BusinessType.getType(dto.getBussinessType()))
                    .amount(dto.getAmount())
                    .createTime(dto.getCreateTime())
                   .updateTime(dto.getUpdateTime())
                   .build();
        }
        return null;
    }
    public static List<Transaction> dtoToEntity4List(List<TransactionDTO> dtoList) {
        if (dtoList!= null) {
            return dtoList.stream().map(TransactionAssembler::dtoToEntity).toList();
        }
        return null;
    }
    public static List<TransactionDTO> entityToDto4List(List<Transaction> entityList) {
        if (entityList!= null) {
            return entityList.stream().map(TransactionAssembler::entityToDto).toList();
        }
        return null;
    }
}

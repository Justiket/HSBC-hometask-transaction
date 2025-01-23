package com.caoyinglong.transaction.application;

import com.caoyinglong.transaction.assembler.TransactionAssembler;
import com.caoyinglong.transaction.domain.entity.Transaction;
import com.caoyinglong.transaction.domain.service.TransactionCommandService;
import com.caoyinglong.transaction.domain.service.TransactionQueryService;
import com.caoyinglong.transaction.dto.TransactionDTO;
import com.caoyinglong.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author caoyinglong
 * @description 交易应用服务
 * @since 2025-01-22 17:15:19
 */
@Service
public class TransactionApplicationService {
    @Autowired
    private TransactionCommandService cmdService;

    @Autowired
    private TransactionQueryService queryService;

    public String createTransaction(TransactionDTO dto) {
        return cmdService.create(TransactionAssembler.dtoToEntity(dto));
    }

    public String deleteTransaction(String id) {
        return cmdService.delete(id);
    }

    public String updateTransaction(TransactionDTO dto) {
        return cmdService.update(TransactionAssembler.dtoToEntity(dto));
    }

    public TransactionDTO findById(String id) {
        return TransactionAssembler.entityToDto(queryService.findById(id));
    }

    public Page<TransactionDTO> findAll() {
        List<Transaction> transactions = queryService.findAll();
        List<TransactionDTO> dtos = TransactionAssembler.entityToDto4List(transactions);
        return new Page<TransactionDTO>(dtos.size(),dtos);
    }


}

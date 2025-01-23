package com.caoyinglong.transaction.application;

import com.caoyinglong.exceptions.ExceptionUtils;
import com.caoyinglong.transaction.assembler.TransactionAssembler;
import com.caoyinglong.transaction.domain.entity.Transaction;
import com.caoyinglong.transaction.domain.service.TransactionCommandService;
import com.caoyinglong.transaction.domain.service.TransactionQueryService;
import com.caoyinglong.transaction.dto.TransactionDTO;
import com.caoyinglong.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
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

    @CachePut(value = "transaction", key = "#dto.id")
    public TransactionDTO createTransaction(TransactionDTO dto) {
        return TransactionAssembler.entityToDto(cmdService.create(TransactionAssembler.dtoToEntity(dto)));
    }

    @CacheEvict(value = "transaction",key = "#id")
    public void deleteTransaction(String id) {
        cmdService.delete(id);
    }

    @CachePut(value = "transaction",key = "#dto.id")
    public String updateTransaction(TransactionDTO dto) {
        return cmdService.update(TransactionAssembler.dtoToEntity(dto)).getPrimaryKey();
    }
    @Cacheable(value = "transaction",key = "#id")
    public TransactionDTO findById(String id) {
        return TransactionAssembler.entityToDto(queryService.findById(id));
    }

    public PageResult<TransactionDTO> findAll(Integer pageNum, Integer pageSize) {
        if(!PageResult.isValid(pageNum,pageSize)){
            ExceptionUtils.wrappBusinessException("分页参数不合法！");
        }
        PageResult<TransactionDTO> pageResult= PageResult.of(pageNum,pageSize);
        Page<Transaction> page = queryService.findAll(pageNum-1,pageSize);
        List<TransactionDTO> dtos = TransactionAssembler.entityToDto4List(page.getContent());
        pageResult.setDatas(dtos);
        pageResult.setTotal((int)page.getTotalElements());
        pageResult.setTotalPage(page.getTotalPages());
        return pageResult;
    }


}

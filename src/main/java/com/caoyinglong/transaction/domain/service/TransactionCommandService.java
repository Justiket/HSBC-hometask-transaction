package com.caoyinglong.transaction.domain.service;

import com.caoyinglong.statusenums.ApiStatus;
import com.caoyinglong.transaction.domain.entity.Transaction;
import com.caoyinglong.transaction.domain.repository.TransactionMemRepository;
import com.caoyinglong.exceptions.ExceptionUtils;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author caoyinglong
 * @description 交易增删改操作服务
 * @since 2025-01-22 14:17:19
 */
@Service
public class TransactionCommandService {

    @Autowired
    private TransactionMemRepository repository;

    @Autowired
    private TransactionQueryService queryService;

    @Transactional
    public Transaction create(Transaction transaction) {
        transaction.setPrimaryKey();
        transaction.setDefaultValue();
        return repository.save(transaction);
    }

    @Transactional
    public Transaction update(Transaction transaction) {
        if(Objects.isNull(transaction) || StringUtils.isBlank(transaction.getPrimaryKey())){
            ExceptionUtils.wrappBusinessException("更新对象参数有误！",ApiStatus.REQ_PARAM_INVALID_ERROR);
        }
        Transaction oldTransaction = queryService.findById(transaction.getPrimaryKey());
        if(oldTransaction == null){
            ExceptionUtils.wrappBusinessException("更新对象不存在！",ApiStatus.REQ_PARAM_INVALID_ERROR);
        }
        transaction.setUpdateValue();
        return repository.save(transaction);
    }

    @Transactional
    public void delete(String id) {
        Transaction oldTransaction = queryService.findById(id);
        if(oldTransaction == null){
            return;
        }
        repository.deleteById(id);
    }

}

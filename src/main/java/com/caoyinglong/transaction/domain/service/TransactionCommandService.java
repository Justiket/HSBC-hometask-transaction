package com.caoyinglong.transaction.domain.service;

import com.caoyinglong.transaction.domain.entity.Transaction;
import com.caoyinglong.transaction.domain.repository.TransactionMemRepository;
import com.caoyinglong.exceptionUtils.ExceptionUtils;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public String create(Transaction transaction) {
        if(StringUtils.isNotBlank(transaction.getPrimaryKey())){
            ExceptionUtils.wrappBusinessException("新增时主键不为空！");
        }
        transaction.setPrimaryKey();
        transaction.setDefaultValue();
        return repository.create(transaction);
    }
    public String update(Transaction transaction) {
        if(StringUtils.isBlank(transaction.getPrimaryKey())){
            ExceptionUtils.wrappBusinessException("更新时主键为空！");
        }
        Transaction oldTransaction = queryService.findById(transaction.getPrimaryKey());
        if(oldTransaction == null){
            ExceptionUtils.wrappBusinessException("更新对象不存在！");
        }
        transaction.setUpdateValue();
        return repository.update(transaction);
    }
    public String delete(String id) {
        Transaction oldTransaction = queryService.findById(id);
        if(oldTransaction == null){
            ExceptionUtils.wrappBusinessException("删除对象不存在！");
        }
        return repository.delete(id);
    }

}

package com.caoyinglong.transaction.domain.service;

import com.caoyinglong.transaction.domain.entity.Transaction;
import com.caoyinglong.transaction.domain.repository.TransactionMemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author caoyinglong
 * @description 交易查询服务
 * @since 2025-01-22 14:17:19
 */
@Service
public class TransactionQueryService {
    @Autowired
    private TransactionMemRepository repository;

    public Transaction findById(String transactionId) {
        return repository.findById(transactionId);
    }

    public List<Transaction> findAll() {
        return repository.findAll();
    }

}

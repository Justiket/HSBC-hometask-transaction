package com.caoyinglong.transaction.domain.service;

import com.caoyinglong.transaction.domain.entity.Transaction;
import com.caoyinglong.transaction.domain.repository.TransactionMemRepository;
import com.caoyinglong.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author caoyinglong
 * @description 交易查询服务
 * @since 2025-01-22 14:17:19
 */
@Service
public class TransactionQueryService {
    @Autowired
    private TransactionMemRepository repository;

    public Transaction findById(String id) {
        return repository.findById(id).orElse(null);
    }

    public Page<Transaction> findAll(Integer pageNum, Integer pageSize) {
       // 创建 Pageable 对象，指定页码、每页数量和排序方式
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "createTime"));
        return repository.findAll(pageable);
    }

}

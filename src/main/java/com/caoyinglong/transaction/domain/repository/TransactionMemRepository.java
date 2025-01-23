package com.caoyinglong.transaction.domain.repository;

import com.caoyinglong.transaction.domain.entity.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface TransactionMemRepository {

    String create(Transaction transaction);

    String update(Transaction transaction);

    String delete(String transaction);

    Transaction findById(String id);

    List<Transaction> findAll();
}

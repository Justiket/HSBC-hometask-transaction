package com.caoyinglong.transaction.domain.repository;

import com.caoyinglong.transaction.domain.entity.Transaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TransactionMemRepositoryImpl implements TransactionMemRepository{

    private Map<String, Transaction> transactionMap = new ConcurrentHashMap<>();
    private Map<String, Transaction> deletedTransactionMap = new ConcurrentHashMap<>();

    @Override
    public String create(Transaction transaction) {
        transactionMap.put(transaction.getPrimaryKey(),transaction);
        return transaction.getPrimaryKey();
    }

    @Override
    public String update(Transaction transaction) {
        transactionMap.put(transaction.getPrimaryKey(),transaction);
        return transaction.getPrimaryKey();
    }

    @Override
    public String delete(String id) {
        Transaction transaction = transactionMap.get(id);
        transactionMap.remove(transaction.getPrimaryKey());
        deletedTransactionMap.put(transaction.getPrimaryKey(),transaction);
        return transaction.getPrimaryKey();
    }

    @Override
    public Transaction findById(String id) {
        return transactionMap.get(id);
    }

    @Override
    public List<Transaction> findAll() {
        return new ArrayList<>(transactionMap.values());
    }
}

package com.caoyinglong.transaction.domain.repository;

import com.caoyinglong.transaction.domain.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionMemRepository extends JpaRepository<Transaction,String> {

    Page<Transaction> findAll(Pageable pageable);

}

package com.caoyinglong.transaction.controller;

import com.caoyinglong.transaction.application.TransactionApplicationService;
import com.caoyinglong.transaction.dto.TransactionDTO;
import com.caoyinglong.utils.Page;
import com.caoyinglong.utils.Result;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author caoyinglong
 * @description 交易控制层
 * @since 2025-01-22 15:13:19
 */

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionApplicationService appService;

    @PostMapping
    public Result<TransactionDTO> createTransaction(@Valid @RequestBody TransactionDTO dto) {
        appService.createTransaction(dto);
        return Result.success(dto);
    }

    @DeleteMapping("/{id}")
    public Result<String> deleteTransaction(@PathVariable String id) {
        appService.deleteTransaction(id);
        return Result.success(id);
    }

    @PutMapping("/{id}")
    public Result<String> modifyTransaction(@PathVariable String id, @RequestBody TransactionDTO dto) {
        appService.updateTransaction(dto);
        return Result.success(id);
    }

    @GetMapping("/{id}")
    public Result<TransactionDTO> getTransaction(@PathVariable String id) {
        TransactionDTO transaction = appService.findById(id);
        return  Result.success(transaction);
    }

    @GetMapping
    public Result<Page<TransactionDTO>> getAllTransaction() {
        Page<TransactionDTO> transaction = appService.findAll();
        return  Result.success(transaction);
    }

}

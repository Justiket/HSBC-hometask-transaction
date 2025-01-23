package com.caoyinglong.transaction.controller;

import com.caoyinglong.transaction.application.TransactionApplicationService;
import com.caoyinglong.transaction.dto.TransactionDTO;
import com.caoyinglong.utils.PageResult;
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

    /**
     * 创建单个交易记录
     *
     * @param dto 包含交易信息的交易数据传输对象
     * @return 返回包含创建后交易记录的结果对象
     */
    @PostMapping
    public Result<TransactionDTO> createTransaction(@Valid @RequestBody TransactionDTO dto) {
        // 调用应用服务层的createTransaction方法，根据交易信息创建交易记录
        TransactionDTO result = appService.createTransaction(dto);
        return Result.success(result);
    }

    /**
     * 删除单个交易记录
     *
     * @param id 交易ID
     * @return 返回成功结果
     */
    @DeleteMapping("/{id}")
    public Result<Void> deleteTransaction(@PathVariable String id) {
        // 调用应用服务层的deleteTransaction方法，根据交易ID删除交易记录
        appService.deleteTransaction(id);
        return Result.success();
    }



    /**
     * 更新单个交易记录
     *
     * @param dto 包含更新信息的交易数据传输对象
     * @return 返回包含更新后交易记录主键的结果对象
     */
    @PutMapping
    public Result<String> updateTransaction(@Valid @RequestBody TransactionDTO dto) {
        // 调用应用服务层的updateTransaction方法，根据DTO更新信息更新交易记录
        String key = appService.updateTransaction(dto);
        return Result.success(key);
    }

    /**
     * 根据交易ID获取单个交易记录
     *
     * @param id 交易ID
     * @return 返回包含单个交易记录的结果对象
     */
    @GetMapping("/{id}")
    public Result<TransactionDTO> getTransaction(@PathVariable String id) {
        // 调用应用服务层的findById方法，根据交易ID获取交易记录
        TransactionDTO transaction = appService.findById(id);
        return Result.success(transaction);
    }


    /**
     * 获取所有交易记录的分页数据
     *
     * @param pageNum  当前页码
     * @param pageSize 每页显示的记录数
     * @return 返回包含分页交易记录的结果对象
     */
    @GetMapping
    public Result<PageResult<TransactionDTO>> getAllTransaction(@RequestParam Integer pageNum,
                                                                @RequestParam Integer pageSize) {
        // 调用应用服务层的findAll方法，获取分页交易记录
        PageResult<TransactionDTO> transaction = appService.findAll(pageNum, pageSize);
        return Result.success(transaction);
    }


}

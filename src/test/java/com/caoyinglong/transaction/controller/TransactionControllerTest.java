//package com.caoyinglong.transaction.controller;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//
//import com.caoyinglong.transaction.application.TransactionApplicationService;
//import com.caoyinglong.transaction.dto.TransactionDTO;
//import com.caoyinglong.utils.PageResult;
//import com.caoyinglong.utils.Result;
//
//@SpringBootTest
//class TransactionControllerTest {
//
//    @Autowired
//    private TransactionController controller;
//
//    @MockBean
//    private TransactionApplicationService appService;
//
//    @Test
//    void createTransaction_shouldReturnSuccess() {
//        // 模拟创建交易的 DTO
//        TransactionDTO dto = new TransactionDTO();
//
//        // 模拟应用服务层的创建交易方法返回结果
//        TransactionDTO createdDto = new TransactionDTO();
//        when(appService.createTransaction(dto)).thenReturn(createdDto);
//
//        // 执行创建交易的请求
//        Result<TransactionDTO> result = controller.createTransaction(dto);
//
//        // 验证结果
//        assertNotNull(result);
//        assertEquals(HttpStatus.OK, result.getHttpStatus());
//        assertEquals(createdDto, result.getData());
//    }
//
//    @Test
//    void deleteTransaction_shouldReturnSuccess() {
//        // 模拟交易 ID
//        String id = "123";
//
//        // 执行删除交易的请求
//        Result<Void> result = controller.deleteTransaction(id);
//
//        // 验证结果
//        assertNotNull(result);
//        assertEquals(HttpStatus.OK, result.getHttpStatus());
//    }
//
//    @Test
//    void updateTransaction_shouldReturnSuccess() {
//        // 模拟更新交易的 DTO
//        TransactionDTO dto = new TransactionDTO();
//
//        // 模拟应用服务层的更新交易方法返回结果
//        String key = "updatedKey";
//        when(appService.updateTransaction(dto)).thenReturn(key);
//
//        // 执行更新交易的请求
//        Result<String> result = controller.updateTransaction(dto);
//
//        // 验证结果
//        assertNotNull(result);
//        assertEquals(HttpStatus.OK, result.getHttpStatus());
//        assertEquals(key, result.getData());
//    }
//
//    @Test
//    void getTransaction_shouldReturnSuccess() {
//        // 模拟交易 ID
//        String id = "123";
//
//        // 模拟应用服务层根据 ID 获取交易的结果
//        TransactionDTO transaction = new TransactionDTO();
//        when(appService.findById(id)).thenReturn(transaction);
//
//        // 执行获取交易的请求
//        Result<TransactionDTO> result = controller.getTransaction(id);
//
//        // 验证结果
//        assertNotNull(result);
//        assertEquals(HttpStatus.OK, result.getHttpStatus());
//        assertEquals(transaction, result.getData());
//    }
//
//    @Test
//    void getAllTransaction_shouldReturnSuccess() {
//        // 模拟页码和每页大小
//        int pageNum = 1;
//        int pageSize = 10;
//
//        // 模拟应用服务层获取所有交易的分页结果
//        PageResult<TransactionDTO> pageResult = new PageResult<>();
//        when(appService.findAll(pageNum, pageSize)).thenReturn(pageResult);
//
//        // 执行获取所有交易的请求
//        Result<PageResult<TransactionDTO>> result = controller.getAllTransaction(pageNum, pageSize);
//
//        // 验证结果
//        assertNotNull(result);
//        assertEquals(HttpStatus.OK, result.getHttpStatus());
//        assertEquals(pageResult, result.getData());
//    }
//}
//

package com.caoyinglong.transaction.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import cn.hutool.json.JSONUtil;
import com.caoyinglong.statusenums.ApiStatus;
import com.caoyinglong.transaction.application.TransactionApplicationService;
import com.caoyinglong.transaction.dto.TransactionDTO;
import com.caoyinglong.transaction.domain.enums.BusinessType;
import com.caoyinglong.utils.PageResult;
import com.caoyinglong.utils.Result;
import com.caoyinglong.utils.SnowflakeUtils;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;

@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionApplicationService appService;

    @Test
    public void createSuccess() throws Exception {
        // 创建交易 DTO 对象
        TransactionDTO dto = initDTO();

        // 模拟应用服务层的方法调用，返回创建成功的交易 DTO
        when(appService.createTransaction(dto)).then(new Answer<TransactionDTO>() {
            @Override
            public TransactionDTO answer(InvocationOnMock invocation) throws Throwable {
                dto.setId(SnowflakeUtils.generateId());
                return dto;
            }
        }).thenReturn(dto);

        // 发送 POST 请求创建交易
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/transaction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andReturn();

        Result result = getResultForMvcResult(mvcResult);
        assertNotNull(result);
        // 验证响应状态码为 200
        assertEquals(ApiStatus.SUCCESS.getStatus(), result.getStatus());
    }

    @Test
    public void createInvalid() throws Exception {
        // 创建无效的交易 DTO 对象（缺少必要字段）
        TransactionDTO dto = new TransactionDTO();

        // 发送 POST 请求创建交易
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/transaction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andReturn();


        Result result = getResultForMvcResult(mvcResult);
        assertNotNull(result);
        //目前所有参数检查均为非空检查，因此错误统一使用ReqParamMissingError,后续根据情况修改
        assertEquals(ApiStatus.REQ_PARAM_MISSING_ERROR.getStatus(), result.getStatus());
    }

    @Test
    public void deleteSuccess() throws Exception {
        // 模拟应用服务层的方法调用，返回删除成功的结果
        doNothing().when(appService).deleteTransaction("123");
        // 发送 DELETE 请求删除交易
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/transaction/123"))
               .andReturn();

        Result result = getResultForMvcResult(mvcResult);
        assertNotNull(result);
        // 验证响应状态码为 200
        assertEquals(ApiStatus.SUCCESS.getStatus(), result.getStatus());
    }

    @Test
    public void updateSuccess() throws Exception {
        // 创建交易 DTO 对象
        TransactionDTO dto = initDTO();
        // 模拟应用服务层的方法调用，返回更新成功的交易 DTO
        when(appService.updateTransaction(dto)).thenReturn(dto.getId());
        // 发送 PUT 请求更新交易
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/transaction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andReturn();

        Result result = getResultForMvcResult(mvcResult);
        assertNotNull(result);
        // 验证响应状态码为 200
        assertEquals(ApiStatus.SUCCESS.getStatus(), result.getStatus());
    }

    @Test
    public void findByIdSuccess() throws Exception {
        // 创建交易 DTO 对象
        TransactionDTO dto = initDTO();
        // 模拟应用服务层的方法调用，返回更新成功的交易 DTO
        when(appService.findById(dto.getId())).thenReturn(dto);
        // 发送 GET 请求更新交易
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/transaction/"+dto.getId()))
               .andReturn();

        Result result = getResultForMvcResult(mvcResult);
        assertNotNull(result);
        // 验证响应状态码为 200
        assertEquals(ApiStatus.SUCCESS.getStatus(), result.getStatus());
    }

    @Test
    public void findPageSuccess() throws Exception {
        // 创建交易 DTO 对象
        TransactionDTO dto = initDTO();
        PageResult<TransactionDTO> pageResult = PageResult.of(1,1,1,10,(List.of(dto)));
        // 模拟应用服务层的方法调用，返回更新成功的交易 DTO
        when(appService.findPage(1,10)).thenReturn(pageResult);
        // 发送 GET 请求更新交易
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/transaction/findPage")
                       .param("pageNum","1")
                       .param("pageSize","10"))
               .andReturn();
        Result result = getResultForMvcResult(mvcResult);
        assertNotNull(result);
        // 验证响应状态码为 200
        assertEquals(ApiStatus.SUCCESS.getStatus(), result.getStatus());
    }

    private static Result getResultForMvcResult(MvcResult mvcResult) throws UnsupportedEncodingException {
        if (mvcResult == null) {
            return Result.error();
        }
        return JSONUtil.parse(mvcResult.getResponse().getContentAsString()).toBean(Result.class);
    }

    private TransactionDTO initDTO(){
        return TransactionDTO.builder()
                .id(SnowflakeUtils.generateId())
                .amount(new BigDecimal("100.0"))
                .accountId("123")
                .bussinessType(BusinessType.DEPOSIT.name())
                .build();
    }



}

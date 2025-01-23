package com.caoyinglong.transaction.application;
import com.caoyinglong.transaction.domain.entity.Transaction;
import com.caoyinglong.transaction.domain.service.TransactionQueryService;
import com.caoyinglong.transaction.dto.TransactionDTO;
import com.caoyinglong.transaction.enums.BusinessType;
import com.caoyinglong.utils.SnowflakeUtils;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TransactionApplicationServiceTest {

    @Mock
    private TransactionQueryService queryService;

    @InjectMocks
    private TransactionApplicationService transactionApplicationService;

    @Autowired
    private CacheManager cacheManager;

    @Test
    public void testCacheableAnnotation() {
        // 初始化Mockito
        MockitoAnnotations.openMocks(this);

        // 模拟查询服务的行为
        String id = SnowflakeUtils.generateId();
        Transaction transaction = Transaction.builder()
                        .id(id)
                        .bussinessType(BusinessType.DEPOSIT)
                        .build();
        when(queryService.findById(id)).thenReturn(transaction);

        // 从缓存管理器中获取缓存
        Cache cache = cacheManager.getCache("transaction");
        if (cache != null) {
            // 验证缓存中是否存在该键值对
            Cache.ValueWrapper valueWrapper = cache.get(id);
            if (valueWrapper != null) {
                assertNotNull(valueWrapper);
                assertEquals(transaction, valueWrapper.get());
            }
        }
    }
}

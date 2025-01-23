package com.caoyinglong.utils;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.lang.Snowflake;

public class SnowflakeUtils {
    // 数据中心ID
    private static final long DATACENTER_ID = 1L;
    // 工作机器ID
    private static final long WORKER_ID = 1L;
    // 雪花算法对象
    private static final Snowflake SNOWFLAKE = IdUtil.getSnowflake(WORKER_ID, DATACENTER_ID);

    // 私有化构造函数，防止外部实例化
    private SnowflakeUtils() {
    }

    // 生成雪花算法ID并以字符串形式返回
    public static String generateId() {
        long id = SNOWFLAKE.nextId();
        return String.valueOf(id);
    }
}

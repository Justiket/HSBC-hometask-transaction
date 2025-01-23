package com.caoyinglong.filter;

import com.caoyinglong.statusenums.ApiStatus;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class IpFilter implements Filter {

    // 存储每个 IP 地址的请求计数
    private final Map<String, AtomicInteger> ipCounts = new HashMap<>();
    // 最大请求次数
    private static final int MAX_REQUESTS = 100;
    // 时间窗口（毫秒）
    private static final long TIME_WINDOW = 60*1000;
    // 上次清理时间
    private long lastCleanupTime = System.currentTimeMillis();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String clientIp = getClientIpAddress(httpRequest);

        // 清理过期的请求计数
        cleanUpOldRequests();

        // 获取当前 IP 的请求计数
        AtomicInteger count = ipCounts.computeIfAbsent(clientIp, k -> new AtomicInteger(0));
        int currentCount = count.incrementAndGet();

        if (currentCount > MAX_REQUESTS) {
            // 请求频率超过限制，返回 429 状态码
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(ApiStatus.TOO_MANY_REQUESTS.getMessage());
            ((HttpServletResponse) response).setStatus(ApiStatus.TOO_MANY_REQUESTS.getStatus());
            return;
        }

        // 请求频率未超过限制，继续处理请求
        chain.doFilter(request, response);
    }

    private String getClientIpAddress(HttpServletRequest request) {
        String xffHeader = request.getHeader("X-Forwarded-For");
        if (xffHeader == null) {
            return request.getRemoteAddr();
        }
        return xffHeader.split(",")[0];
    }

    private void cleanUpOldRequests() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastCleanupTime > TIME_WINDOW) {
            ipCounts.clear();
            lastCleanupTime = currentTime;
        }
    }
}

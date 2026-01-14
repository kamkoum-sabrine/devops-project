package com.enicarthage.devops_project.metrics;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class MetricsService {

    private final AtomicLong requestCount = new AtomicLong(0);
    private final AtomicLong totalResponseTimeMs = new AtomicLong(0);

    public void recordRequest(long responseTimeMs) {
        requestCount.incrementAndGet();
        totalResponseTimeMs.addAndGet(responseTimeMs);
    }

    public long getRequestCount() {
        return requestCount.get();
    }

    public long getAvgResponseTime() {
        long count = requestCount.get();
        return count == 0 ? 0 : totalResponseTimeMs.get() / count;
    }
}

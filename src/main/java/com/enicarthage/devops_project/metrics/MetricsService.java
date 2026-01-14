package com.enicarthage.devops_project.metrics;


import org.springframework.stereotype.Service;

@Service
public class MetricsService {

    private long requestCount = 0;
    private long totalResponseTimeMs = 0;

    public synchronized void record(long durationMs) {
        requestCount++;
        totalResponseTimeMs += durationMs;
    }

    public long getRequestCount() {
        return requestCount;
    }

    public long getAvgResponseTime() {
        return requestCount == 0 ? 0 : totalResponseTimeMs / requestCount;
    }
}


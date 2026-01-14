package com.enicarthage.devops_project.filter;

import com.enicarthage.devops_project.metrics.MetricsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

    private final MetricsService metricsService;

    public RequestLoggingFilter(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws IOException, jakarta.servlet.ServletException {

        long startTime = System.currentTimeMillis();
        String traceId = UUID.randomUUID().toString();

        request.setAttribute("traceId", traceId);

        filterChain.doFilter(request, response);

        long duration = System.currentTimeMillis() - startTime;

        metricsService.record(duration);

        System.out.println(
                "traceId=" + traceId +
                        " method=" + request.getMethod() +
                        " path=" + request.getRequestURI() +
                        " status=" + response.getStatus() +
                        " durationMs=" + duration
        );
    }
}

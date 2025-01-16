package com.docker.ApiDockerTEST.service;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

@Service
public class CustomMetricService {
    private final MeterRegistry meterRegistry;

    public CustomMetricService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    public void trackCustomMetric() {
        meterRegistry.counter("metric_counter-natan", "status", "success").increment();
    }
}

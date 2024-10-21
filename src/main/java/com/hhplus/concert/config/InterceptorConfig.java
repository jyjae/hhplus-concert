package com.hhplus.concert.config;

import com.hhplus.concert.interfaces.interceptor.QueueTokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private final QueueTokenInterceptor queueTokenInterceptor;

    public InterceptorConfig(QueueTokenInterceptor queueTokenInterceptor) {
        this.queueTokenInterceptor = queueTokenInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(queueTokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/tokens"); // 특정 경로에 인터셉터 적용
    }
}

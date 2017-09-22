package com.jaya.retry.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.RetryListener;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import com.jaya.retry.listener.SampleRetryListener;
import com.jaya.retry.service.RetryAndRecoverService;
import com.jaya.retry.service.RetryService;

@Configuration
public class RetryExampleApplication {
    @Bean
    public RetryTemplate retryTemplate() {
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(5);

        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(1500); // 1.5 seconds

        RetryTemplate template = new RetryTemplate();
        template.setRetryPolicy(retryPolicy);
        template.setBackOffPolicy(backOffPolicy);
        
        RetryListener[] listeners = new RetryListener[2];
        listeners[0] = new SampleRetryListener();
		template.setListeners(listeners);
		
        return template;
    }
    
    @Bean
    public RetryAndRecoverService getRetryAdnRecoverService()
    {
    	return new RetryAndRecoverService();
    }
    
    @Bean
    public RetryService getRetryService()
    {
    	return new RetryService();
    }
}
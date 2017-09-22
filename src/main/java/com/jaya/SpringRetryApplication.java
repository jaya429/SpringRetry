package com.jaya;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.retry.annotation.EnableRetry;

import com.jaya.retry.config.RetryExampleApplication;
import com.jaya.retry.service.RetryAndRecoverService;
import com.jaya.retry.service.RetryService;

@SpringBootApplication
@EnableRetry
@Import(value=RetryExampleApplication.class)
public class SpringRetryApplication implements CommandLineRunner{

	@Autowired
    private RetryService retryService;
    @Autowired
    private  RetryAndRecoverService retryAndRecoverService;
    
    public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringRetryApplication.class, args);
	}

    public void retryWithException() {
        retryAndRecoverService.retryWithException();
    }

	@Override
	public void run(String... arg0) throws Exception {
		retryAndRecoverService.retryWithException();
		//retryService.simpleRetry();
		//retryService.withTemplate();
	}
}

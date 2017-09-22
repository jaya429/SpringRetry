package com.jaya.retry.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

import com.jaya.retry.exception.SampleException;

@Service
public class RetryService {

    @Autowired
    private RetryTemplate retryTemplate;


    @Retryable
    public void simpleRetry() {
        System.out.println("simpleRetry");
        throw new SampleException("Something went wrong");
    }

    public void withTemplate() {
        retryTemplate.execute(new RetryCallback<Void, SampleException>() {
			@Override
			public Void doWithRetry(RetryContext context) throws SampleException {
				System.out.println("calling retry callback");
		        throw new SampleException("Something went wrong");
			}        	
		},new RecoveryCallback<Void>() {
			@Override
			public Void recover(RetryContext context) throws Exception {
				System.out.println("calling recovery callback");
				return null;
			}			
		});
    }

}

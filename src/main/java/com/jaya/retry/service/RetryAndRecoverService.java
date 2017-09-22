package com.jaya.retry.service;

import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import com.jaya.retry.exception.SampleException;
import com.jaya.retry.exception.SimpleException;

@Service
public class RetryAndRecoverService {

    @Retryable(exclude = {SampleException.class, SimpleException.class}, maxAttempts = 5)
    public void retryWithException() {
        System.out.println("retryWithException");
        throw new SampleException("exception in retry annotate method");
    }


    @Recover
    public void recover(SampleException exception) {
        System.out.println("recovering from SampleException ->" + exception.getMessage());
    }

    @Recover
    public void recover(SimpleException exception) {
        System.out.println("recovering from SimpleException ->" + exception);
    }
}

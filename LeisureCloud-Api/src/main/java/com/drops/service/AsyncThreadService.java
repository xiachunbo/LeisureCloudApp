package com.drops.service;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

public interface AsyncThreadService {
    public Future<String> doReturn(int i);
}

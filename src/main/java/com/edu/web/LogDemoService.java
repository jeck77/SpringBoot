package com.edu.web;

import com.edu.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

    private final MyLogger myLogger;
    //private final ObjectProvider<MyLogger> myLoggerProvider;
    public void logic(String id) {
        //MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.log("service id = " + id );
    }
}

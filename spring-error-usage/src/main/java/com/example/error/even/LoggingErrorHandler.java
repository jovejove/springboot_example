package com.example.error.even;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.ErrorHandler;

/**
 * @author: ljj
 * @date: 2022/6/30
 * @description:
 */
public class LoggingErrorHandler implements ErrorHandler {

    public static final ErrorHandler LOG_AND_SUPPRESS_ERROR_HANDLER = new LoggingErrorHandler();

    private final Log logger = LogFactory.getLog(LoggingErrorHandler.class);
    @Override
    public void handleError(Throwable t) {
        logger.error("Unexpected error occurred in scheduled task", t);
    }
}

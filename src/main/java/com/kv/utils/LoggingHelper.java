package com.kv.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggingHelper {

  public static Logger getConfiguredLogger(Class clazz) {
    Logger logger = Logger.getLogger(clazz);
    PropertyConfigurator.configure("log4j.properties");

    return logger;
  }
}

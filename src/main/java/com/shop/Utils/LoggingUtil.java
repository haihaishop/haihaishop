package com.shop.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by 15764 on 2017-07-17.
 */
public class LoggingUtil {
    static private Logger logger ;
    static public void log(Object info ){
        logger = LoggerFactory.getLogger(LoggingUtil.class);
        logger.info(info.toString());
    }
}

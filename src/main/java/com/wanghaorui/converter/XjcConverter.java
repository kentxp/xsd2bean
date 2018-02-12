package com.wanghaorui.converter;

import com.wanghaorui.App;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wangfei on 2018/2/12.
 */
public class XjcConverter {
    public static final Logger log = LoggerFactory.getLogger(XjcConverter.class);

    public static void xjc(String directory,String packageName){
        final Runtime runtime = Runtime.getRuntime();
        Process process = null;
        try {
            String cmd = "xjc " + directory + " -d "+directory+" -p " + packageName + " -encoding UTF-8 -no-header";
            log.info("run cmd :{}",cmd);
            process = runtime.exec(cmd);
        } catch (final Exception e) {
            log.error("{}",e);
            System.out.println("Error exec!");
        }
    }
}

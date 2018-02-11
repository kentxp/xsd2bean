package com.wanghaorui.file;

import com.wanghaorui.App;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DisposeIncludeXsd {
    public static final Logger log = LoggerFactory.getLogger(App.class);

    public static boolean containFile(String fileName, List<String> commonFiles, boolean copyFlag, String directory ) throws IOException {
        for(String commonFile : commonFiles){
            String[] tmp = commonFile.split("\\"+ File.separator);
            if(fileName.equals(tmp[tmp.length-1])){
                log.debug(fileName+" match "+ tmp[tmp.length-1] );
                if(copyFlag){
                    FileUtils.copyFile(commonFile,directory+File.separator+fileName);
                    log.debug("copy file to {}..",directory+File.separator+fileName );

                }
                return true;
            }
        }
        return false;
    }
}

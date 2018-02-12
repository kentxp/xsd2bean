package com.wanghaorui.file;

import com.wanghaorui.App;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DisposeIncludeXsd {
    public static final Logger log = LoggerFactory.getLogger(App.class);

    public static boolean containFile(String fileName, List<String> commonFiles, boolean copyFlag, String directory ) throws IOException {
        for(String commonFile : commonFiles){
            String[] tmp = commonFile.split("\\"+ File.separator);
            if(fileName.equals(tmp[tmp.length-1])){
                log.debug(fileName+" match "+ tmp[tmp.length-1] );
                if(copyFlag){
                    //查下一个这个目录是否已经有这个文件了 如果已经有了 则不进行复制  因为有循环依赖的问题
                    File file2Copy = new File(directory + File.separator + fileName );
                    if(file2Copy.exists()){
                        continue;
                    }
                    FileUtils.copyFile(commonFile,directory+File.separator+fileName);
                    log.debug("copy file to {}..",directory+File.separator+fileName );
                    //继续分析该文件是否有依赖
                    String source = FileUtils.readFile(commonFile);
                    String rgex = "schemaLocation=\"(.*?)\"/>";
                    List<String> depenyFiles = StrSub.getSubUtil(source,rgex);
                    for(String depenyFile: depenyFiles)
                    {
                        containFile(depenyFile,commonFiles,true,directory);
                    }
                }
                return true;
            }
        }
        return false;
    }

    public static void disposeXsdDirectory(String directory, List<String> commonFiles) throws IOException {
        List<String> files = new ArrayList<String>();
        FileUtils.listFiles(new File(directory),files);
        for(String file:files){
            String source = FileUtils.readFile(file);
            String rgex = "schemaLocation=\"(.*?)\"/>";
            List<String> depenyFiles = StrSub.getSubUtil(source,rgex);
            log.debug("{} 依赖 {}",file,depenyFiles,directory);
            disposeDepenyXsd(depenyFiles,files,commonFiles,directory);

        }
    }

    public static void disposeDepenyXsd(List<String> depenyFiles,List<String> localFiles, List<String> commonFiles,String directory) throws IOException {
        //判断依赖文件是否已经存在
        for(String depenyFile: depenyFiles)
        {
            //先检查本地是否有这个文件
            if(containFile(depenyFile,localFiles,false,directory)){
                continue;
            }
            //再检查公共是否有这个文件
            containFile(depenyFile,commonFiles,true,directory);
        }
    }
}

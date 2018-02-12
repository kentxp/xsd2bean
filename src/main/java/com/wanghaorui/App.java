package com.wanghaorui;

import com.wanghaorui.converter.XjcConverter;
import com.wanghaorui.file.DisposeIncludeXsd;
import com.wanghaorui.file.StrSub;
import com.wanghaorui.file.DirectoryNode;
import com.wanghaorui.file.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static final Logger log = LoggerFactory.getLogger(App.class);

    public static final String XSD_LOACTION = "D:\\logs\\v2.0\\xsd";
    public static final String COMMON_XSD_LOACTION = "D:\\logs\\v2.0\\commonxsd";
    public static void main( String[] args ) throws IOException {
        log.info("start app...");
        //FileUtils.traverseFolder1(XSD_LOACTION);

        //获取所有末级目录 即为需要处理的文件目录
        DirectoryNode directoryNode  = new DirectoryNode(XSD_LOACTION);
        FileUtils.initDirectoryNode(new File(XSD_LOACTION),directoryNode);
        List<String> leafDirectory = new ArrayList<String>();
        FileUtils.getLeafDirectory(directoryNode,leafDirectory );

        //获取所有的公共文件
        List<String> commonFiles = new ArrayList<String>();
        FileUtils.listFiles(new File(COMMON_XSD_LOACTION),commonFiles);
        System.out.println(commonFiles);

        //补全文件
        for(String directory : leafDirectory){

            DisposeIncludeXsd.disposeXsdDirectory(directory, commonFiles);
            String[] tmp = directory.split("\\" + File.separator);
            String packageName = "com.wanghaorui."+tmp[tmp.length-1];
            XjcConverter.xjc(directory, packageName);
//                List<String> files = new ArrayList<String>();
//            FileUtils.listFiles(new File(directory),files);
//            for(String file:files){
//                String source = FileUtils.readFile(file);
//                String rgex = "schemaLocation=\"(.*?)\"/>";
//                List<String> depenyFiles = StrSub.getSubUtil(source,rgex);
//                log.debug("{} 依赖 {}",file,depenyFiles);
//
//                //判断依赖文件是否已经存在
//                for(String depenyFile: depenyFiles)
//                {
//                    //先检查本地是否有这个文件
//                    if(containFile(depenyFile,files,false,directory)){
//                        continue;
//                    }
//                    //再检查公共是否有这个文件
//                    if(containFile(depenyFile,commonFiles,true,directory)){
//
//                    }
//                }
//            }
        }


    }

}

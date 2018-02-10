package com.wanghaorui;

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

    public static final String XSD_LOACTION = "D://logs";
    public static final String COMMON_XSD_LOACTION = "D:\\GT3-ZJ-金税三期标准服务清册_V2.23\\附录报文\\涉税服务管理\\公共XSD";
    public static void main( String[] args ) throws IOException {
        log.info("start app...");
        //FileUtils.traverseFolder1(XSD_LOACTION);


        DirectoryNode directoryNode  = new DirectoryNode(XSD_LOACTION);
        FileUtils.initDirectoryNode(new File(XSD_LOACTION),directoryNode);
        List<String> leafDirectory = new ArrayList<String>();
        FileUtils.getLeafDirectory(directoryNode,leafDirectory );

        List<String> commonFiles = new ArrayList<String>();
        FileUtils.listFiles(new File(COMMON_XSD_LOACTION),commonFiles);
        System.out.println(commonFiles);

       // FileUtils.initDirectoryNode(new File(XSD_LOACTION));
        String source = FileUtils.readFile("D:\\logs\\123123\\esw\\jj\\HXZG_SF_00024\\TaxMLBw_SSFWGL_SF_00024_Request_V1.0.xsd");
        String rgex = "schemaLocation=\"(.*?)\"/>";
        System.out.println(StrSub.getSubUtil(source,rgex));

    }
}

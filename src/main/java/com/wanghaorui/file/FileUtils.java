package com.wanghaorui.file;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class FileUtils {


    public static void initDirectoryNode(File file, DirectoryNode parentNode){
        if(file!=null){
            if(file.isDirectory()){
                File f[]=file.listFiles();
                if(f!=null){
                    List<DirectoryNode> directorySonNodes = new ArrayList();
                    for(int i=0;i<f.length;i++){
                        if(f[i].isDirectory()){
                            DirectoryNode directoryNode  = new DirectoryNode(f[i].getAbsolutePath());
                            directorySonNodes.add(directoryNode);
                            initDirectoryNode(f[i],directoryNode); // 递归调用
                        }
                    }
                    if(directorySonNodes.size() != 0){
                        parentNode.setChildNode(directorySonNodes);
                    }
                }
                //System.out.println(file);
            }
        }
    }

    public static void listFiles(File file,List<String> files ){
        if(file!=null){
            if(file.isDirectory()){
                File f[]=file.listFiles();
                if(f!=null){
                    for(int i=0;i<f.length;i++){
                        listFiles(f[i],files); // 递归调用
                    }
                }
            }else{
                files.add(file.getAbsolutePath()+System.lineSeparator()+file.getName());
            }
        }
    }

    public static void getLeafDirectory(DirectoryNode n, List<String> leafDirectory)
    {
        List childlist=n.getChildNode();
        if(childlist==null)//没有孩子 说明是叶子结点
        {
            leafDirectory.add(n.getText());
            return;
        }else{
            Iterator it=childlist.iterator();
            while(it.hasNext())
            {
                DirectoryNode child=(DirectoryNode)it.next();
                getLeafDirectory(child,leafDirectory);//深度优先 进入递归
            }
        }
    }

    public static void traverseFolder1(String path) {
        int fileNum = 0, folderNum = 0;
        File file = new File(path);
        LinkedList<File> list = new LinkedList<File>();
        list.add(file);
        File temp_file;
        while (!list.isEmpty()) {
            temp_file = list.removeFirst();
            File[] files = temp_file.listFiles();
            for (File file2 : files) {
                if (file2.isDirectory()) {
                    System.out.println("文件夹:" + file2.getAbsolutePath());
                    list.add(file2);
                    folderNum++;
                } else {
                    //System.out.println("文件:" + file2.getAbsolutePath());
                    fileNum++;
                }
            }
        }
        System.out.println("文件夹共有:" + folderNum + ",文件共有:" + fileNum);

    }

    public static void copyFile(String source,String destination) throws IOException {
        InputStream is=new FileInputStream(source);
        OutputStream os=new FileOutputStream(destination);
        int b=0;
        while((b=is.read())!=-1){
            os.write(b);
        }
        is.close();
        os.close();
    }

    public static String readFile(String source) throws IOException {
        File file = new File(source);
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }
}

package com.hut;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;


import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;


public class FileSystemCat {
    static int foldeNum=0;

    public static void main(String[] args){

//        ListItem();
//        upFile();

     //   downFile();

        upLostsFile();
    }

    private static void  ListItem(){
        try {

            Configuration config = new Configuration();
            Path dfs = new Path("hdfs://172.22.66.245:9090/input");
            FileSystem fileSystem = dfs.getFileSystem(config);
            FileStatus[] status = fileSystem.listStatus(dfs);
            for (int i = 0; i < status.length; i++) {
                System.out.println(status[i].getPath().toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void upFile(){
        try{
            Configuration conf = new Configuration();
            Path path = new Path("hdfs://172.22.66.245:9090/input");
            Path localPath = new Path("D:\\2017-09-25_093127.jpg");
            FileSystem fs = path.getFileSystem(conf);
            fs.copyFromLocalFile(localPath,path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void upLostsFile(){
//        Scanner cin = new Scanner(System.in);
//        String path = cin.next();
//        File file = new File("D:\\intellijIDEA");
//        if(file.exists()){
//            File[] files = file.listFiles();
//
//            for (int i = files.length - 1; i >= 0; i--) {
//                System.out.println(files[i]);
//            }
//        }

        traverseFolder1("H:\\books");

    }


    private static void downFile(){
        try{
            Configuration conf = new Configuration();
            Path path = new Path("hdfs://172.22.66.245:9090/input/haha.txt");
            FileSystem fs = path.getFileSystem(conf);
            FSDataInputStream open = fs.open(path);
            OutputStream output = new FileOutputStream("D://hello.txt");
            IOUtils.copyBytes(open,output,4096,true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void  traverseFolder1(String path) {
        int fileNum = 0, folderNum = 0;
        File file = new File(path);
        if (file.exists()) {
            LinkedList<File> list = new LinkedList<>();
            File[] files = file.listFiles();
            for (File file2 : files) {
                if (file2.isDirectory()) {
                    System.out.println("文件夹:" + file2.getAbsolutePath());
                    list.add(file2);

                    foldeNum++;
                } else {
                    System.out.println("文件:" + file2.getAbsolutePath());
                    fileNum++;
                }
            }
            File temp_file;
            while (!list.isEmpty()) {
                temp_file = list.removeFirst();
                files = temp_file.listFiles();
                if (files!=null)
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        list.add(file2);
                        folderNum++;
                    } else {
                        System.out.println("文件:" + file2.getAbsolutePath());
                        fileNum++;
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
        System.out.println("文件夹共有:" + folderNum + ",文件共有:" + fileNum);

    }

}

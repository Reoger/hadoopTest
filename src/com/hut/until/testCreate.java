package com.hut.until;

import com.alibaba.fastjson.JSON;
import com.hut.bean.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.hwpf.extractor.WordExtractor;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Time;
import java.util.Date;
import java.util.LinkedList;

public class testCreate {


    private static BeanForDoc2 beanForDoc;
    private static BeanForPaper2 beanForPaper;

    private static String baseUrl ="http://10.12.198.100:9200/" ;


    public static void main(String args[]) {
     //   baseUrl= "http://127.0.0.1:9200/";

        beanForDoc = new BeanForDoc2();
        beanForPaper = new BeanForPaper2();
        traverseFolder1("H:\\index");

//        traverseFolder1("G:\\test");

    }

    public static void traverseFolder1(String path) {
        int fileNum = 0, folderNum = 0;
        File file = new File(path);
        if (file.exists()) {
            LinkedList<File> list = new LinkedList<>();
            File[] files = file.listFiles();
            for (File file2 : files) {
                if (file2.isDirectory()) {
                    System.out.println("根目录文件夹:" + file2.getName());
//                    if(file2.getName().equals("document"))
                        //公文
                    createEmptryIndex( file2.getName());
                    list.add(file2);

                    folderNum++;
                } else {
                    System.out.println("一般这里我不会放文件，所以这里暂时不处理");
                    fileNum++;
                }
            }
            File temp_file;
            while (!list.isEmpty()) {
                temp_file = list.removeFirst();
                files = temp_file.listFiles();
                if (files != null)
                    for (File file2 : files) {
                        if (file2.isDirectory()) {
                            System.out.println("文件夹-:" + file2.getAbsolutePath());
                            if(file2.getParentFile().getName().equals("document"))
                                createStructIndex(file2.getAbsolutePath(),CreateFromIndex.JSON_DOC);
                            else if(file2.getParentFile().getName().equals("paper"))
                                createStructIndex(file2.getAbsolutePath(),CreateFromIndex.JSON_PAPER);
                            list.add(file2);
                            folderNum++;
                        } else {
                            System.out.println("文件:" + file2.getAbsolutePath());
                            String pathS = file2.getParentFile().getParent();
                            String res = pathS.substring(pathS.lastIndexOf("\\")+1);

                            if("document".equals(res))
                                createDocData(file2.getAbsolutePath(),file2.getName(),file2.length());
                            else if("paper".equals(res))
                                createPaperDate(file2.getAbsolutePath(),file2.getName(),file2.length());
                            fileNum++;
                        }
                    }
            }
        } else {
            System.out.println("文件不存在!");
        }
        System.out.println("文件夹共有:" + folderNum + ",文件共有:" + fileNum);

    }

    private static void createPaperDate(String absolutePath, String fileName, long totalSpace) {


        beanForPaper.setUpdate_time(new Date());
        beanForPaper.setUpload_time(new Date());
        beanForPaper.setSize((int) totalSpace);
        beanForPaper.setUploader("reoger");
        beanForPaper.setName(fileName);

        int length = fileName.indexOf(".");
        String author = fileName.substring(0,length==-1?fileName.length():length);
        beanForPaper.setAuthor(author);

        String content = null;
        if(fileName.endsWith("pdf")||fileName.endsWith("PDF"))
            content = ReadPDF(absolutePath);
        else if(fileName.endsWith("doc")||fileName.endsWith("DOC"))
            content = ReadWord(absolutePath);
        beanForPaper.setContent(content!=null?content:"无法识别");

       // String downLink = upFile(fileName,absolutePath);
        String downLink = "http://reoger.tk";
        beanForPaper.setDown_link(downLink);

        String jsonString = JSON.toJSONString(beanForPaper);
        String[] splits = absolutePath.split("\\\\");

        try {
            String temp = URLEncoder.encode(splits[2].toLowerCase(), "UTF-8");
            String file = URLEncoder.encode(splits[3].toLowerCase(), "UTF-8");
             String url = baseUrl + temp + "/" + file;


            HttpRequest.sendPost(url,jsonString);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }


    private static void createDocData(String absolutePath, String fileName, long totalSpace) {

        int index = fileName.indexOf("字");
        char key = 0;
        System.out.println(index);
        if(index >= 2){

            key= fileName.charAt(index-1);
            String zhuzhi = fileName.substring(0,index-1);
            int indexTwo = fileName.indexOf("【");
            if(indexTwo == -1)
                indexTwo = fileName.indexOf("[");
            String time = fileName.substring(indexTwo+1,indexTwo+5);
            beanForDoc.setKey_word(key+"");
            beanForDoc.setOrganization(zhuzhi);
            beanForDoc.setTime(Integer.valueOf(time));
        }
        String content = null;

        if(fileName.endsWith("pdf"))
            content = ReadPDF(absolutePath);
        else if(fileName.endsWith("doc"))
            content = ReadWord(absolutePath);
//        String downLink = upFile(fileName,absolutePath);
        String downLink = "http://reoger.tk";
        beanForDoc.setContent(content);
        beanForDoc.setSize((int) totalSpace);
        beanForDoc.setDown_link(downLink);
        beanForDoc.setName(fileName);

        beanForDoc.setUpload_time(new Date());
        beanForDoc.setUpdate_time(new Date());
        String jsonString = JSON.toJSONString(beanForDoc);
        String[] splits = absolutePath.split("\\\\");

        try {
            String temp = URLEncoder.encode(splits[2].toLowerCase(), "UTF-8");
            String file = URLEncoder.encode(splits[3].toLowerCase(), "UTF-8");
            String url = baseUrl + temp + "/" + file;

            HttpRequest.sendPost(url,jsonString);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }

    private static String ReadWord(String absolutePath) {
        File file = new File(absolutePath);
        String result;
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(file);
            WordExtractor wordExtractor = new WordExtractor(fis);
            result = wordExtractor.getText();
            result = result.replaceAll("\\s*","");
            wordExtractor.close();
            return result;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            try {
                fis.close();
             } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static String ReadPDF(String absolutePath) {

        File file = new File(absolutePath);
        PDDocument document = null;
        try {
            document = PDDocument.load(file);
            // 获取页码
            int pages = document.getNumberOfPages();
            // 读文本内容
            PDFTextStripper stripper = new PDFTextStripper();
            // 设置按顺序输出
            stripper.setSortByPosition(true);
            stripper.setStartPage(1);
            stripper.setEndPage(pages);
            String content = stripper.getText(document);
            document.close();
            return content;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {


        }
    }

    private static String createStructIndex(String absolutePath,String str) {
        String[] splits = absolutePath.split("\\\\");

        try {

            String temp = URLEncoder.encode(splits[2].toLowerCase(), "UTF-8");
            String file = URLEncoder.encode(splits[3].toLowerCase(), "UTF-8");
            String url = baseUrl + temp + "/" + file + "/_mappings";

            String res = str.replace("reoger", splits[3].toLowerCase());
        //    System.out.println(res);

            String sh = HttpRequest.sendPost(url, res);

            return sh;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }


    private static String createEmptryIndex(String IndexName) {
        IndexSettings indexName = new IndexSettings();
        IndexSettings.SettingsEntity settingsEntity = new IndexSettings.SettingsEntity();
        settingsEntity.setNumber_of_replicas(1);
        settingsEntity.setNumber_of_shards(4);
        indexName.setSettings(settingsEntity);

        String str = JSON.toJSONString(indexName);

        try {
            String temp = URLEncoder.encode(IndexName.toLowerCase(), "UTF-8");
            String url = baseUrl + temp;

        //    System.out.println(url);
            return HttpRequest.sendPut(url, str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;

    }


    private static String  upFile(String name, String absolutePath){

        try{
            Configuration conf = new Configuration();
            Path path = new Path("hdfs://172.22.66.245:9090/input");
            System.out.println("upload file --- "+absolutePath);

//            Path localPath = new Path(absolutePath);
//            FileSystem fs = path.getFileSystem(conf);
//            fs.copyFromLocalFile(localPath,path);
            System.out.println("end upload +++ "+absolutePath);
            String realPath = URLEncoder.encode(name,"utf-8");
            realPath = realPath.replace("++","%20%20");
            return "172.22.66.245:50075/webhdfs/v1/input/xiaoxiaoxiaoxiao?op=OPEN".replace("xiaoxiaoxiaoxiao", realPath);
            //这里没有上传完整的路径，需要加上参数：&namenoderpcaddress=master:9090

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

package com.hut.until;

import com.alibaba.fastjson.JSON;
import com.hut.bean.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.hwpf.extractor.WordExtractor;
import sun.text.resources.FormatData;


import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.LinkedList;

public class testCreate {


    private static BeanForDoc beanForDoc;
    private static BeanForPaper beanForPaper;


    public static void main(String args[]) {
        beanForDoc = new BeanForDoc();
        beanForPaper = new BeanForPaper();
//        traverseFolder1("H:\\index");

        traverseFolder1("G:\\test");

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
                    if(file2.getName().equals("document")){
                        //公文
                        createEmptryIndex( file2.getName());
                    }

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
                            createStructIndex(file2.getAbsolutePath());
                            list.add(file2);
                            folderNum++;
                        } else {
                            System.out.println("文件:" + file2.getAbsolutePath());
                            String pathS = file2.getParentFile().getParent();
                            String res = pathS.substring(pathS.lastIndexOf("\\")+1);

                            if("document".equals(res))
                                createDocData(file2.getAbsolutePath(),file2.getName(),file2.length());
                            else if("bachelor".equals(res))
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

        String content = null;
        if(fileName.endsWith("pdf"))
            content = ReadPDF(absolutePath);
        else if(fileName.endsWith("doc"))
            content = ReadWord(absolutePath);
        beanForPaper.setContent(content!=null?content:"无法识别");



        System.out.println(absolutePath);
        String downLink = upFile(fileName,absolutePath);
        beanForPaper.setDown_link(downLink);

        String jsonString = JSON.toJSONString(beanForPaper);
        String[] splits = absolutePath.split("\\\\");

        try {
            String temp = URLEncoder.encode(splits[2].toLowerCase(), "UTF-8");
            String file = URLEncoder.encode(splits[3].toLowerCase(), "UTF-8");
             String url = "http://172.22.66.245:9200/" + temp + "/" + file;


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
        String downLink = upFile(fileName,absolutePath);
        beanForDoc.setContent(content);
        beanForDoc.setInteger((int) totalSpace);
        beanForDoc.setDown_link(downLink);
        beanForDoc.setName(fileName);
        beanForDoc.setUpload_time(new Date());
        String jsonString = JSON.toJSONString(beanForDoc);
        String[] splits = absolutePath.split("\\\\");

        try {
            String temp = URLEncoder.encode(splits[2].toLowerCase(), "UTF-8");
            String file = URLEncoder.encode(splits[3].toLowerCase(), "UTF-8");
            String url = "http://172.22.66.245:9200/" + temp + "/" + file;

            HttpRequest.sendPost(url,jsonString);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }

    private static String ReadWord(String absolutePath) {
        File file = new File(absolutePath);
        String result;
        FileInputStream fis;
        try{
            fis = new FileInputStream(file);
            WordExtractor wordExtractor = new WordExtractor(fis);
            result = wordExtractor.getText();
            result = result.replaceAll("\\s*","");
            return result;

        }catch (Exception e){
            e.printStackTrace();
            return null;
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
            return content;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String createStructIndex(String absolutePath) {
        String[] splits = absolutePath.split("\\\\");

        try {

            String temp = URLEncoder.encode(splits[2].toLowerCase(), "UTF-8");
            String file = URLEncoder.encode(splits[3].toLowerCase(), "UTF-8");
            String url = "http://172.22.66.245:9200/" + temp + "/" + file + "/_mappings";

            String res = CreateFromIndex.JSON_DOC.replace("reoger", splits[3].toLowerCase());
        //    System.out.println(res);

            String sh = HttpRequest.sendPost(url, res);

            System.out.println(sh);
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
            String url = "http://172.22.66.245:9200/" + temp;

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

            Path localPath = new Path(absolutePath);

            FileSystem fs = path.getFileSystem(conf);
            fs.copyFromLocalFile(localPath,path);
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

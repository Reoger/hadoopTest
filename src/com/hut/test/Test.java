package com.hut.test;


import org.apache.poi.hwpf.extractor.WordExtractor;

import java.io.File;
import java.io.FileInputStream;

public class Test {

    public static void main(String args[]){

        String result = null;
        File file = new File("F:\\My Documents\\my wold\\test.doc");
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(file);
            WordExtractor wordExtractor = new WordExtractor(fis);
            result = wordExtractor.getText();

            System.out.println(result.trim());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

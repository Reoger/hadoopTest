package com.hut;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hut.bean.JsonBean;
import com.hut.until.HttpRequest;



import java.util.Date;

public class ClientTest {

    public static void main(String args[]){
//        //发送 GET 请求
//        String s= HttpRequest.sendGet("http://localhost:9100", "key=123&v=456");
//        System.out.println(s);


        String jsonObject = "{\n" +
                "  \"name\": \"ppppp\",\n" +
                "  \"country\": \"english\",\n" +
                "  \"age\": 10,\n" +
                "  \"date\": \"1999-03-05\"\n" +
                "}";

        JsonBean bean = new JsonBean();
        bean.setAge(18);
        bean.setDate(new Date());
        bean.setCountry("China");
        bean.setName("helllo");
        String   str  = JSON.toJSONString(bean);
        System.out.println(str);

//        //发送 POST 请求
//        String sr=HttpRequest.sendPost("http://localhost:9200/people/man", str);
//        System.out.println(sr);



        String str2 = HttpRequest.sendPut("http://localhost:9200/",jsonObject);
        System.out.println(str2);
    }


}

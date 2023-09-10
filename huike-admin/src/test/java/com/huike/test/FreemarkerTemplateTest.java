package com.huike.test;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class FreemarkerTemplateTest {

    @Autowired
    private Configuration configuration;


    //使用Freemarker生成一个文件 .
    @Test
    public void testGenerateFile() throws Exception {
        //1. 准备数据模型
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("title", "Hello Freemarker");
        dataModel.put("message", "我是一个消息");

        //2. 获取模板
        Template template = configuration.getTemplate("demo.ftl");

        //3. 生成文件
        FileWriter fileWriter = new FileWriter("E:/1.html");
        template.process(dataModel, fileWriter);

        fileWriter.close();
    }


    //使用Freemarker生成一个文本字符串.
//    @Test
//    public void testGenerateText() throws Exception {
//        //1. 准备数据模型
//        Map<String, Object> dataModel = new HashMap<>();
//        dataModel.put("title", "Hello Freemarker");
//        dataModel.put("message", "我是一个消息");
//
//        //2. 获取模板
//        Template template = configuration.getTemplate("demo.ftl");
//
//        //3. 生成文件
//        String result = FreeMarkerTemplateUtils.processTemplateIntoString(template, dataModel);
//        System.out.println(result);
//    }


    //使用Freemarker生成一个文本字符串.
//    @Test
//    public void testGenerateText2() throws Exception {
//        //1. 准备数据模型
//        Map<String, Object> dataModel = new HashMap<>();
//        dataModel.put("title", "Hello Freemarker");
//        dataModel.put("message", "我是一个消息");
//        dataModel.put("user", new User(1L, "Tom", 20));
//        dataModel.put("name", "Rose");
//
//        List<User> userList = new ArrayList<>();
//        userList.add(new User(1L, "Rose1", 10));
//        userList.add(new User(2L, "Rose2", 11));
//        userList.add(new User(3L, "Rose3", 12));
//        userList.add(new User(4L, "Rose4", 13));
//        userList.add(new User(5L, "Rose5", 14));
//        userList.add(new User(6L, "Rose6", 15));
//        userList.add(new User(7L, "Rose7", 16));
//        userList.add(new User(8L, "Rose8", 17));
//
//        dataModel.put("dataList", userList);
//
//
//        //2. 获取模板
//        Template template = configuration.getTemplate("demo.ftl");
//
//        //3. 生成文件
//        String result = FreeMarkerTemplateUtils.processTemplateIntoString(template, dataModel);
//        System.out.println(result);
//    }

}

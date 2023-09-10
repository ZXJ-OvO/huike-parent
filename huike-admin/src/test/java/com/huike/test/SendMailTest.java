package com.huike.test;


import com.huike.domain.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zxj
 * @mail zxjOvO@gmail.com
 * @date 2023/09/09 23:42
 */
@SpringBootTest
public class SendMailTest {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailProperties mailProperties;

    @Test
    public void testSendSimpleMail() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(mailProperties.getUsername());
        simpleMailMessage.setCc(mailProperties.getUsername(), mailProperties.getUsername()); //抄送
        simpleMailMessage.setBcc(mailProperties.getUsername()); //密送
        simpleMailMessage.setSubject("Mail测试邮箱");
        simpleMailMessage.setText("这是一封测试邮件 .....");

        javaMailSender.send(simpleMailMessage);
    }


    @Test
    public void testSendMail() throws Exception {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(mailProperties.getUsername());
        mimeMessageHelper.setTo(mailProperties.getUsername());
        mimeMessageHelper.setCc(mailProperties.getUsername()); //抄送
        mimeMessageHelper.setBcc(mailProperties.getUsername()); //密送
        mimeMessageHelper.setSubject(mailProperties.getUsername());

        mimeMessageHelper.setText("<h1>这是一封测试邮件 .....<h1>", true);
        mimeMessageHelper.addAttachment("1.png", new File("C:\\Users\\root\\Desktop\\04-项目一：苍穹外卖 - 15d\\day07：微信登录、商品浏览\\资料\\微信登录流程.png"));
        mimeMessageHelper.addAttachment("2.pdf", new File("C:\\Users\\root\\Desktop\\午间面试题库(仅供参考).pdf"));
        javaMailSender.send(mimeMessage);
    }

    @Autowired
    private Configuration configuration;

    //使用Freemarker生成一个文本字符串.
    @Test
    @Scheduled(cron = "* 1 * * * ?")
    public void testGenerateText2() throws Exception {
        //1. 准备数据模型
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("title", "运营数据报告");
        dataModel.put("adminName", "超级管理员");

        List<User> userList = new ArrayList<>();
        userList.add(new User("2021-09-01", 156, 125, 91, 609050));
        userList.add(new User("2021-09-02", 129, 123, 92, 609350));
        userList.add(new User("2021-09-03", 154, 122, 93, 639050));
        userList.add(new User("2021-09-04", 152, 125, 93, 649050));
        userList.add(new User("2021-09-05", 153, 123, 95, 609050));
        userList.add(new User("2021-09-06", 155, 122, 94, 605050));
        userList.add(new User("2021-09-07", 156, 111, 92, 606050));
        userList.add(new User("2021-09-08", 157, 122, 94, 607050));
        userList.add(new User("2021-09-09", 153, 130, 92, 608050));
        userList.add(new User("2021-09-10", 154, 150, 94, 609950));

        dataModel.put("dataRows", userList);

        //2. 获取模板
        Template template = configuration.getTemplate("mail.ftl");

        //3. 生成文件
        String result = FreeMarkerTemplateUtils.processTemplateIntoString(template, dataModel);
        System.out.println(result);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(mailProperties.getUsername());
        mimeMessageHelper.setTo("zxjOvO@gmail.com");
        // mimeMessageHelper.setCc(mailProperties.getUsername()); //抄送
        // mimeMessageHelper.setBcc(mailProperties.getUsername()); //密送
        mimeMessageHelper.setSubject("测试邮件"); // 设置邮件标题

        mimeMessageHelper.setText(result, true);
        javaMailSender.send(mimeMessage);

    }
}

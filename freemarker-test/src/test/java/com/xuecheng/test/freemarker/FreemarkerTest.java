package com.xuecheng.test.freemarker;

import com.xuecheng.test.freemarker.model.Student;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import sun.nio.ch.IOUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FreemarkerTest {

   // 测试静态化，基于ftl生成html
    @Test
    public  void  generateHtml() throws IOException, TemplateException {
        // 定义配置类
        Configuration configuration = new Configuration(Configuration.getVersion());
        // 得到classPath路径
        String classPath = this.getClass().getResource("/").getFile();
        // 定义模板路径
        configuration.setDirectoryForTemplateLoading(new File(classPath+"/templates/"));
        // 获取模板文件的内容
        Template template = configuration.getTemplate("/test1.ftl");
        // 定义数据模型
        Map map = getMap();
        // 静态化
        String s = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);

        InputStream inputStream = IOUtils.toInputStream(s);
        FileOutputStream outputStream = new FileOutputStream("D:/test.html");
        IOUtils.copy(inputStream,outputStream);
        inputStream.close();
        outputStream.close();


    }

    /**
     * 基于模板文件字符串生成模板
     */
    @Test
    public  void  generateStringToHtml() throws IOException, TemplateException {
        // 定义配置类
        Configuration configuration = new Configuration(Configuration.getVersion());
            //模板内容，这里测试时使用简单的字符串作为模板
        String templateString= ""+ "<html>\n" +
                "    <head></head>\n" +
            " <body>\n" +
                 "    名称：${name}\n" +
             " </body>\n" +
                 "</html>";
        // 使用一个模板加载器变为模板
        StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
        stringTemplateLoader.putTemplate("template",templateString);
        //在配置中设置模板加载器
       configuration.setTemplateLoader(stringTemplateLoader);
        Template template = configuration.getTemplate("template", "utf-8");
        //定义数据模型
        Map map = getMap();

        // 静态化
        String s = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);

        InputStream inputStream = IOUtils.toInputStream(s);
        FileOutputStream outputStream = new FileOutputStream("D:/test.html");
        IOUtils.copy(inputStream,outputStream);
        inputStream.close();
        outputStream.close();

    }

    public Map getMap() {
        Map map = new HashMap();
        map.put("name", "黑马程序员");
        Student stu1 = new Student();
        stu1.setName("小明");
        stu1.setAge(18);
//        stu1.setBirthday(new Date());
        Student stu2 = new Student();
        stu2.setName("小红");
        stu2.setAge(19);
//  stu2.setBirthday(new   Date());
        List<Student> friends = new ArrayList<>();
        friends.add(stu1);
        stu2.setFriends(friends);
        stu2.setBestFriend(stu1);
        List<Student> stus = new ArrayList<>();
        stus.add(stu1);
        stus.add(stu2);
//        //向数据模型放数据
        map.put("stus", stus);
//        //准备map数据
        HashMap<String, Student> stuMap = new HashMap<>();
        stuMap.put("stu1", stu1);
        stuMap.put("stu2", stu2);
//        //向数据模型放数据
        map.put("stu1", stu1);
//        //向数据模型放数据
        map.put("stuMap", stuMap);
//        //返回模板文件名称


      return  map;
    }

}

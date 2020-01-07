package com.xuecheng.manage_cms.test;

import com.xuecheng.manage_cms.service.CmsPageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestPageService {

    @Autowired
    private CmsPageService cmsPageService;


    /**
     * 测试页面静态化
      * @param model
     * @param template
     */
    @Test
    public void testGetPageHtml(){
        String pageHtml = cmsPageService.getPageHtml("5dc6ba63f2a84f39306d0c9f");

        System.out.println(pageHtml);


    }
}

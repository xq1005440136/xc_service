package com.xuecheng.manage_cms.controller;

import com.xuecheng.framework.web.BaseController;
import com.xuecheng.manage_cms.service.CmsPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import java.io.IOException;

/**
 * 页面预览的controller
 */
@Controller
public class CmsPagePrieviewController extends BaseController {
    @Autowired
    private CmsPageService cmsPageService;

    @GetMapping(value = "/cms/prieview/{pageId}")
    public void prieview(@PathVariable("pageId") String pageId) throws IOException {
        String html = cmsPageService.getPageHtml(pageId);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(html.getBytes("utf-8"));


    }
}

package com.xuecheng.manage_cms.controller;

import com.xuecheng.api.cms.CmsConfigControllerApi;
import com.xuecheng.framework.domain.cms.CmsConfig;
import com.xuecheng.manage_cms.service.CmsPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class CmsConfigController implements CmsConfigControllerApi {

    @Autowired
    private CmsPageService cmsPageService;



    @Override
    @GetMapping("/getModel/{id}")
    public CmsConfig getmodel(@PathVariable("id") String id) {
        CmsConfig cmsConfig = cmsPageService.findByCmsConfigId(id);

        return cmsConfig;
    }
}

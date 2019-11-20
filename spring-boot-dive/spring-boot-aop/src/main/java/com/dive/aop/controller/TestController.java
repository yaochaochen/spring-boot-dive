package com.dive.aop.controller;

import com.dive.aop.annotation.Log;
import com.dive.aop.domain.SysLog;
import com.dive.aop.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Properties;

@Controller
public class TestController {

    @Autowired
    private SysLogService sysLogService;
    @Log("执行方法一")
    @GetMapping("/one")
    public void methodOne(String name) {
        //sysDao.save(new SysLog());

    }

    @Log("执行方法二")
    @GetMapping("/two")
    public String  methodTwo() throws InterruptedException {
        Thread.sleep(2000);
        return "Test";
    }

    @Log("查询列表")
    @GetMapping("/three")
    public Page<SysLog> methodThree(String name, Integer page, Integer size) {
        return  sysLogService.findList(name, size, page);

    }

    @GetMapping(value = "test", consumes = "text/properties")
    public Properties getUser(@RequestBody Properties properties) {
        return properties;
    }

    
}

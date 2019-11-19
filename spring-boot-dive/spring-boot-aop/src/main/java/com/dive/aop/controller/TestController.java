package com.dive.aop.controller;

import com.dive.aop.annotation.Log;
import com.dive.aop.dao.SysDao;
import com.dive.aop.dao.impl.SysLogRepository;
import com.dive.aop.domain.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private SysLogRepository sysDao;
    @Log("执行方法一")
    @GetMapping("/one")
    public void methodOne(String name) {
        sysDao.save(new SysLog());

    }

    @Log("执行方法二")
    @GetMapping("/two")
    public String  methodTwo() throws InterruptedException {
        Thread.sleep(2000);
        return "Test";
    }

    @Log("执行方法三")
    @GetMapping("/three")
    public void methodThree(String name, String age) {

    }
}

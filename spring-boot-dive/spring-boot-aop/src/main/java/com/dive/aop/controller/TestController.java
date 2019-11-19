package com.dive.aop.controller;

import com.dive.aop.annotation.Log;
import com.dive.aop.dao.SysDao;
import com.dive.aop.dao.impl.SysLogRepository;
import com.dive.aop.domain.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
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
    public Page<SysLog> methodThree(String name) {
        Sort sort =Sort.by("username").ascending();
        Pageable pageable = PageRequest.of(0, 20, sort);
        Page page = sysDao.findByUsername(name, pageable);
        page.getContent();
        return page;
    }
}

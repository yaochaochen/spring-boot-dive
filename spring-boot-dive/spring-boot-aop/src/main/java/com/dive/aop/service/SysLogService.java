package com.dive.aop.service;


import com.dive.aop.dao.impl.SysLogRepository;
import com.dive.aop.domain.SysLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class SysLogService {

    @Resource
    SysLogRepository sysLogRepository;


    public Page<SysLog> findList(String username, Integer page, Integer size) {
        Sort sort = Sort.by("username").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return sysLogRepository.findByUsername(username, pageable);
    }
}

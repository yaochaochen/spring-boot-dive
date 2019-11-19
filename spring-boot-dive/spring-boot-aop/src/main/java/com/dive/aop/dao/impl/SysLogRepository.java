package com.dive.aop.dao.impl;


import com.dive.aop.domain.SysLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface SysLogRepository extends CrudRepository<SysLog, Long> {


    Page<SysLog> findByUsername(String userName, Pageable pageable);

    Slice<SysLog> findAllByUsername(String userName, Sort sort);



}

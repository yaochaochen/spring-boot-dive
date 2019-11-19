package com.dive.domain;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Bird implements InitializingBean, DisposableBean {

    public Bird() {
        System.out.println("调用无参构造器创建 Bird");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("销毁Bird");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化Bird");
    }
}

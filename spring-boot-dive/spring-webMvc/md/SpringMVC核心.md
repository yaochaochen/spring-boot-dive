---
typora-copy-images-to: ./image
---

# WEB-MVC核心

## 理解Spring Web Mvc架构

### 基础架构servlet

![](/Users/yaochaochen/Desktop/WX20191122-150005.png)

-  	特点
  - ​		请求/响应（Request/Response）
  - 屏蔽网络通讯的细节
- API特性
  - 面向HTTP协议
  - 完整的声明周期
- 职责
  - 处理请求
  - 资源处理
  - 视图渲染

### 核心架构

[前端控制器](http://www.corej2eepatterns.com/FrontController.htm)

![image-20191122150619894](/Users/yaochaochen/Desktop/image-20191122150619894.png)

- 资源：[FrontController](http://www.corej2eepatterns.com/FrontController.htm)
- 实现 Spring Web Mvc [DispatcherServlet](https://docs.spring.io/spring/docs/1.0.0/javadoc-api/org/springframework/web/servlet/DispatcherServlet.html)

### 认识Spring Web MVC

#### spring Framework时代的一般认识

##### 实现Controller

```java
@Controller
public class HelloWorldController {
    @RequestMapping("")
    public String index() {
        return "index";
    }
```

##### 配置Web MVC组件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">

    <context:component-scan base-package="com.imooc.web"/>

    <bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping"/>

    <bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter"/>

    <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    <property name="viewClass" value="org.springframework.web.servlet.view.JstlView"/>
    <property name="prefix" value="/WEB-INF/jsp/"/>
    <property name="suffix" value=".jsp"/>
    </bean>

</beans>
```

##### 部署DispatcherServlet

```xml
<web-app>

    <servlet>
    <servlet-name>app</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <load-on-startup>1</load-on-startup>
    <init-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>/WEB-INF/app-context.xml</param-value>
    </init-param>
    </servlet>

    <servlet-mapping>
    <servlet-name>app</servlet-name>
    <url-pattern>/</url-pattern>
    </servlet-mapping>

</web-app>
```

### SpringFrameWork时代的重新认识



|                         组件Bean类型                         | 说明                                                         |
| :----------------------------------------------------------: | ------------------------------------------------------------ |
| [HandlerMapping](https://docs.spring.io/spring/docs/5.0.2.RELEASE/spring-framework-reference/web.html%23mvc-handlermapping) | 映射请求(Request)到处理器(Handler)加上其关联的拦截器 (HandlerInterceptor)列表，其映射关系基于不同的 HandlerMapping 实现的一些 标准细节。其中两种主要 HandlerMapping 实现， RequestMappingHandlerMapping 支持标注 @RequestMapping 的方法， SimpleUrlHandlerMapping 维护精确的URI 路径与处理器的映射 |
|                        HandlerAdapter                        | 帮助 DispatcherServlet 调用请求处理器(Handler)，无需关注其中实际的调用 细节。比如，调用注解实现的 Controller 需要解析其关联的注解. HandlerAdapter 的主要目的是为了屏蔽与 DispatcherServlet 之间的诸多细节。 |
| [HandlerExceptionResolver](https://docs.spring.io/spring/docs/5.0.6.RELEASE/spring-framework-reference/web.html%23mvc-exceptionhandlers) | 解析异常，可能策略是将异常处理映射到其他处理器(Handlers) 、或到某个 HTML 错误页面，或者其他。 |
| [ViewResolver](https://docs.spring.io/spring/docs/5.0.6.RELEASE/spring-framework-reference/web.html%23mvc-viewresolver) | 从处理器(Handler)返回字符类型的逻辑视图名称解析出实际的 View 对象，该对 象将渲染后的内容输出到HTTP 响应中。 |
|            LocaleResolver, LocaleContextResolver             | 从客户端解析出 Locale ，为其实现国际化视图。                 |
|                      MultipartResolver                       | 解析多部分请求(如 Web 浏览器文件上传)的抽象实现              |

#### 交互流程

![image-20191122152456951](/Users/yaochaochen/project/spring-boot/spring-boot-dive/spring-webMvc/md/image/image-20191122152456951.png)
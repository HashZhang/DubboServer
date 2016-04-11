package com.cn.hash.server.zk;

import com.alibaba.dubbo.container.Main;
import org.apache.naming.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 862911 on 2016/4/7.
 */
public class App {
    public static void main(String[] args) {
//        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:/META-INF/spring/spring-zk-config.xml");
//        applicationContext.start();
        Main.main(args);
    }
}

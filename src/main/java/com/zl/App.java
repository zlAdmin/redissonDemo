package com.zl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 启动类
 *
 */

@RestController
@SpringBootApplication
@EntityScan("com.zl")
public class App {
    public static void main( String[] args ) {
        SpringApplication.run(App.class, args);
    }

    @RequestMapping("hello")
    String sayHello(){
        return "hello wold";
    }
}

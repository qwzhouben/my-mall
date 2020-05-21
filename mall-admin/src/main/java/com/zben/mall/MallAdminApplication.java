package com.zben.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @DESC:应用启动入口
 * @author: zhouben
 * @date: 2020/5/21 0021 13:47
 */
@SpringBootApplication
@MapperScan(basePackages = "com.zben.mall.mapper")
public class MallAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallAdminApplication.class, args);
    }

}

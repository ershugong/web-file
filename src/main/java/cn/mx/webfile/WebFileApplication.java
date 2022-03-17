package cn.mx.webfile;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.mx.webfile.mapper")
public class WebFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebFileApplication.class, args);
    }

}

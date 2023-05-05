package mtak.dev.helloSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // springboot의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동으로
public class Application {
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args); //내장 WAS 실행
        System.out.println("hi");
    }
}

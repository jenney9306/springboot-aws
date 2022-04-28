package com.jiyea.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
// 스프링부트의 자동설정, 스프링 bean 읽기와 생성 모두 자동으로 설정
// 항상 프로젝트의 최상단에 위치
public class Application { //main class
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
        // 내장 aws(톰켓) 으로 실행
    }
}

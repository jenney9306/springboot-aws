package com.jiyea.springboot.config.auth;

import java.lang.annotation.*;

@Target(ElementType.ANNOTATION_TYPE.PARAMETER) //이 어노테이션이 생성될 수 있는 위치를 지정
@Retention(RetentionPolicy.RUNTIME)//이파일을 어노테이션 클래스로 지정합니다.
public @interface LoginUser {
}

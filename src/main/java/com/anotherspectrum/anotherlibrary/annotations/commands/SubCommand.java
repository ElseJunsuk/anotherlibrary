package com.anotherspectrum.anotherlibrary.annotations.commands;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 커맨드 생성을 도와주는 Annotation
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface SubCommand {

    /**
     * 커맨드의 args 를 생성합니다.
     * @return 추가할 커맨드의 args
     */
    String[] args();

}

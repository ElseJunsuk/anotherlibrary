package com.anotherspectrum.anotherlibrary.annotations.commands;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 커맨드 도움말 Annotation
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface Help {

    /**
     * 해당 커맨드에 대한 도움말을 작성할 수 있습니다.
     * @return 커맨드에 대한 상세한 도움말
     */
    String commandHelp();

    /**
     * 해당 커맨드에 대한 짧은 도움말을 작성할 수 있습니다.
     * @return 커맨드에 대한 짧은 도움말
     */
    String shortCommandHelp() default "";

}

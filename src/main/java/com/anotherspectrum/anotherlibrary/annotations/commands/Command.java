package com.anotherspectrum.anotherlibrary.annotations.commands;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 커맨드 생성을 도와주는 Annotation
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface Command {

    /**
     * 첫번째 인수에는 커맨드의 이름이 들어갑니다. 두번째 인수부터는 Alias 로 판단합니다.
     * 커맨드의 이름, Alias 에는 공백이 포함될 수 없습니다.
     * 설정된 이름과 동일한 커맨드가 생성되며, Alias 는 null 값을 가질 수 있습니다.
     * @example @Command(commandNameAndAlias = {"test", "commandtest", "ct", "te"})
     * @return 공백 없는 커맨드의 이름과 Alias
     */
    String[] commandNameAndAlias();

}

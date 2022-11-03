package com.anotherspectrum.anotherlibrary.annotations.commands;

import com.google.common.base.Preconditions;

import javax.lang.model.element.Element;
import java.util.ArrayList;
import java.util.List;

public class CommandManager {

    public String emitCommandHelp(Element element) {
        Preconditions.checkNotNull(element.getAnnotation(Help.class), "Command Alias 클래스 데이터를 찾을 수 없습니다.");
        return element.getAnnotation(Help.class).commandHelp();
    }

    public String emitCommandName(Element element) {
        Preconditions.checkNotNull(element.getAnnotation(Command.class), "Command Annotation 클래스 데이터를 찾을 수 없습니다.");
        return element.getAnnotation(Command.class).commandNameAndAlias()[0];
    }

    public List<String> emitCommandAlias(Element element) {
        Preconditions.checkNotNull(element.getAnnotation(Command.class), "Command Annotation 클래스 데이터를 찾을 수 없습니다.");
        List<String> lists = new ArrayList<>();
        for (int i = 1; i < element.getAnnotation(Command.class).commandNameAndAlias().length; i++)
            for (String keys : element.getAnnotation(Command.class).commandNameAndAlias())
                lists.add(i, keys);
        return lists;
    }

    public String[] emitSubCommand(Element element) {
        Preconditions.checkNotNull(element.getAnnotation(SubCommand.class), "SubCommand Annotation 클래스 데이터를 찾을 수 없습니다.");
        return element.getAnnotation(SubCommand.class).args();
    }


}

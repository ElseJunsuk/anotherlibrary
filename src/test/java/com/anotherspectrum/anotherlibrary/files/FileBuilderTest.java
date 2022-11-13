package com.anotherspectrum.anotherlibrary.files;

import org.jetbrains.annotations.TestOnly;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

@TestOnly
class FileBuilderTest {

    @Test
    void test() {
        FileBuilder.FileRegister builder =
                new FileBuilder("/Volumes/JunSAnother/Samples/Splices/test/test_two")
                .setFile("ang_gimotti.yml")
                .build();
    }

}
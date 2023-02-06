package com.anotherspectrum.anotherlibrary.details;

import org.jetbrains.annotations.TestOnly;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoreboardManagerTest {

    @Test
    void collectionsReverseTestMethod() {
        List<String> list = new ArrayList<>(Arrays.asList("T", "e", "s", "t"));
        System.out.println(list);

        Collections.reverse(list);
        System.out.println(list);
    }

}
package com.cydeo.apiTests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class P20_MethodSource {


    @ParameterizedTest
    @MethodSource("getPracticeData")
    public void test1(Map<String, String> allData){


    }
}

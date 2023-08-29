package com.cydeo.day11;

import com.cydeo.utilities.ExcelUtil;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;

public class P08_BookitLoginDDT {



    //create a method to read bookitqa3 excel file information (QA3 Sheet)
    //use those information as an email and password to send a get request to /sign endpoint
    //verify you got 200 for each request
    //print accessToken for each request

    @ParameterizedTest
    @MethodSource("getBookItData")
    public void test1(Map<String, String> spartans){

        System.out.println(spartans.get("email"));

        System.out.println(spartans.get("password"));


    }

    public static List<Map<String, String>> getBookItData(){

        ExcelUtil excelUtil =new ExcelUtil("src/test/resources/BookItQa3.xlsx", "QA3");

        return excelUtil.getDataList();
    }
}

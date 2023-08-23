package com.cydeo.utilities;

import java.util.LinkedHashMap;
import java.util.Map;

public class SpartanFlow {

    Map<String, Object> map;
    public static void getPOST(String name, String gender, Long phone){

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("name", name);
        map.put("gender", gender);
        map.put("phone", phone);

    }

}

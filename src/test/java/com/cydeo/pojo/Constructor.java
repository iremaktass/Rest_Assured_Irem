package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class Constructor {

    private int total;

    private int limit;

    private List<String> constructorId;

    private List<String> name;

}

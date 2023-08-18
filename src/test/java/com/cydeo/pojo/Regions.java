package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Regions {


    @JsonProperty("region_id")
    private int regionId;

    @JsonProperty("region_name")
    private String regionName;

}

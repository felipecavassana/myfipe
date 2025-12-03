package com.felipecavassana.myfipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CodeNameDTO(
        @JsonAlias("code") String code,
        @JsonAlias("name") String name) {

    @Override
    public String code() {
        return code;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return "CODE: "+ code+ "\tNAME: "+name;
    }
}

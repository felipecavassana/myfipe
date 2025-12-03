package com.felipecavassana.myfipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VehicleDetailsDTO(
        @JsonAlias("price") String price,
        @JsonAlias("model") String model,
        @JsonAlias("codeFipe") String codeFipe,
        @JsonAlias("modelYear") Integer year) {
}

package com.felipecavassana.myfipe.service;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public interface MapperService {
    <T> T mapperObject(String json, Class<T> tClass);
    <T> T mapperObject(String json, TypeReference<T> tClass);
    <T> List<T> mapperList(String json, Class<T> tClass);
}

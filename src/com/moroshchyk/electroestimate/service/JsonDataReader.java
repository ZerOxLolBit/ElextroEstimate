package com.moroshchyk.electroestimate.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Клас для зчитування даних з JSON файлів.
 */
public class JsonDataReader {

    /**
     * Зчитує дані з JSON файлу та повертає список об'єктів відповідного типу.
     *
     * @param filePath шлях до JSON файлу
     * @param clazz    клас типу об'єктів
     * @param <T>      тип об'єктів
     * @return список об'єктів типу T
     */
    public static <T> List<T> modelDataJsonReader(String filePath, Class<T[]> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<T> dataList = new ArrayList<>();

        try {
            T[] data = objectMapper.readValue(new File(filePath), clazz);
            dataList.addAll(Arrays.asList(data));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataList;
    }
}

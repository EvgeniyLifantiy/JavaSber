package com.BankApi.Tools;

import com.sun.net.httpserver.HttpExchange;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Евгений
 * @project Bank-Api-Application
 */
public class Mapper {

    private final ObjectMapper mapper = new ObjectMapper();

    public Map<String, String> getParamValue(String query) {
        Map<String, String> result = new HashMap<>();
        if (query == null) return result;
        for (String param : query.split("&")) {
            String[] entry = param.split("=");
            if (entry.length > 1) {
                result.put(entry[0], entry[1]);
            } else {
                result.put(entry[0], "");
            }
        }
        return result;
    }

    public <T> T JsonToEntity(HttpExchange exchange,Class<T> entity) {
        try {
            return mapper.readValue(exchange.getRequestBody(), entity);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> String EntityListToJson(List<T> list) {
        try {
            return mapper.writeValueAsString(list);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public String EntityToJson(Object o)   {
        try {
            return mapper.writeValueAsString(o);
        } catch (IOException e) {
            System.out.println("Json processing error");
            return null;
        }
    }
}

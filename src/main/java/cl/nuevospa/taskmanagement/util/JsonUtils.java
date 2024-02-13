package cl.nuevospa.taskmanagement.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


public class JsonUtils {

    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
    }


    public static final String objectToJsonString(Object object) {
        String salida = "";
        try {
            salida = objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return salida;
    }

    public static final String objectToJsonEncodeString(Object object, String encoding) {
        String salida = "";
        try {
            String json = objectMapper.writeValueAsString(object);
            salida = URLEncoder.encode(json, encoding);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return salida;
    }

    public static final Object jsonStringToObject(Class<?> type, String value) {
        Object salida = "";
        try {
            Reader reader = new StringReader(value);
            salida = objectMapper.readValue(reader, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return salida;
    }

    public static final String sampleObjectToJSONString(Object input) {
        String salida = "";
        try {
            salida = objectMapper.writeValueAsString(input);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return salida;
    }


    public static String convertHashMapToJsonForRequest(Map<String, Object> hashMap) {
        String salida = "";
        try {
            salida = objectMapper.writeValueAsString(hashMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return salida;
    }


    public static Map<String, Object> convertJsonToHashMap(String json) {
        Map<String, Object> hash = null;
        try {
            hash = objectMapper.readValue(json, HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hash;
    }

}

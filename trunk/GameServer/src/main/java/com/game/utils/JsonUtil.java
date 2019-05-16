package com.game.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.game.item.Equip;
import com.game.item.Item;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addKeySerializer(Item.class, new MyKeySerializer());
        //simpleModule.addKeyDeserializer(Equip.class, new MyKeyDeserializer() );
        objectMapper.registerModule(simpleModule);
    }

    public static byte[] object2bytes(Object object){
        try {
            return objectMapper.writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T bytes2Object(byte[] bytes, Class<T> clazz){
        try {
            return objectMapper.readValue(bytes, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T json2Object(String json, Class<T> clazz){
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String object2json(Object object){
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    //将Map中的 key序列化成 json对象(String), 默认根据 toString()序列化
    public static class MyKeySerializer extends JsonSerializer<Object> {
        @Override
        public void serialize(Object object, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            StringWriter writer = new StringWriter();
            objectMapper.writeValue(writer, object);
            jsonGenerator.writeFieldName(writer.toString());
        }
    }

    //要反序列化的key类型, 在readValue()中指定, 好像无效果
    public static class MyKeyDeserializer extends KeyDeserializer {
        @Override
        public Object deserializeKey(String key, DeserializationContext deserializationContext) throws IOException {
            return objectMapper.readValue(key, Equip.class);
        }
    }
}

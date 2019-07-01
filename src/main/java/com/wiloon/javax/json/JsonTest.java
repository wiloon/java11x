package com.wiloon.javax.json;

import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

public class JsonTest {
    public static void main(String[] args) throws UnsupportedEncodingException {
        Map<String, Object> map = new HashMap<>();
        byte[] bar = "bar".getBytes(); //fastjson默认做了base64
        map.put("foo", bar);
        System.out.println("map: " + map);
        String text = JSON.toJSONString(map);
        System.out.println("json: " + text);

        Map mapOut = JSON.parseObject(text, Map.class);
        System.out.println("map out: " + mapOut);
        String value = (String) mapOut.get("foo");
        System.out.println("value: " + value);

        Base64 base64 = new Base64();
        byte[] byteArray = base64.decodeBase64(value.getBytes());
        String base64Decode = new String(byteArray, "UTF8");

        System.out.println("base64 decode: " + base64Decode);

    }
}

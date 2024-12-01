package com.kigi.https.util;

import android.text.TextUtils;

import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * created by chow on 2019/08/10
 */
public class IntegerTypeAdapter extends TypeAdapter<Integer> {
    @Override
    public void write(JsonWriter out, Integer value) throws IOException {
        if (value == null) {
            out.nullValue();
        } else {
            out.value(value);
        }
    }

    @Override
    public Integer read(JsonReader in) throws IOException {
        JsonToken peek = in.peek();
        switch (peek) {
            case NULL:
                in.nextNull();
                return null;
            case NUMBER:
                return in.nextInt();
            case STRING:
                return toInteger(in.nextString());
            case BOOLEAN:
                return in.nextBoolean() ? 1 : 0;
            default:
                throw new JsonParseException("Expected BOOLEAN or NUMBER but was " + peek);
        }
    }

    /**
     * true  TURE 都为true
     * "0" 为 false
     *
     * @param name
     * @return
     */
    public static Integer toInteger(String name) {
        try {
            if (!TextUtils.isEmpty(name)) {
                return Integer.parseInt(name);
            } else {
                return -10086;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -10086;
        }
    }

}

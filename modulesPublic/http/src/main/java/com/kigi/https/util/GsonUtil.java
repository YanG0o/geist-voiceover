package com.kigi.https.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by chow on 2019/8/10
 */
public class GsonUtil {

    private static Gson gson;

    static {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(TypeAdapters.newFactory(Boolean.TYPE, Boolean.class, new BooleanTypeAdapter()));
        gsonBuilder.registerTypeAdapterFactory(TypeAdapters.newFactory(Integer.TYPE, Integer.class, new IntegerTypeAdapter()));
        gson = gsonBuilder.create();
    }

    private GsonUtil() {
    }


    /**
     * 将object对象转成json字符串
     *
     * @param object
     * @return
     */
    public static String gsonToString(Object object) {
        String gsonString = null;
        try {
            if (gson != null) {
                gsonString = gson.toJson(object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gsonString;
    }


    /**
     * 将gsonString转成泛型bean
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> T gsonToBean(String gsonString, Class<T> cls) {
        T t = null;
        try {
            if (gson != null) {
                t = gson.fromJson(gsonString, cls);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 将gsonString转成泛型bean
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> T gsonToBeanEException(String gsonString, Class<T> cls) throws Exception {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(gsonString, cls);
        }
        return t;
    }

    /**
     * 标准json转成list
     * 泛型在编译期类型被擦除导致报错
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> ArrayList<T> gsonToList(String gsonString, Class<T> cls) {
        ArrayList<T> list = null;
        if (gson != null) {
            list = gson.fromJson(gsonString, new TypeToken<ArrayList<T>>() {
            }.getType());
        }
        return list;
    }

    public static <T> Set<T> gsonToSet(String gsonString, Class<T> cls) {
        Set<T> set = null;
        if (gson != null) {
            set = gson.fromJson(gsonString, new TypeToken<Set<T>>() {
            }.getType());
        }
        return set;
    }


    /**
     * 转成list中有map的
     *
     * @param gsonString
     * @return
     */
    public static <T> List<Map<String, T>> gsonToListMaps(String gsonString) {
        List<Map<String, T>> list = null;
        if (gson != null) {
            list = gson.fromJson(gsonString,
                    new TypeToken<List<Map<String, T>>>() {
                    }.getType());
        }
        return list;
    }

    /**
     * 转成map的
     *
     * @param gsonString
     * @return
     */
    public static <T> Map<String, T> gsonToMaps(String gsonString) {
        Map<String, T> map = null;
        if (gson != null) {
            map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }

    public static Gson getGson() {
        if (gson == null) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapterFactory(TypeAdapters.newFactory(Boolean.TYPE, Boolean.class, new BooleanTypeAdapter()));
            gsonBuilder.registerTypeAdapterFactory(TypeAdapters.newFactory(Integer.TYPE, Integer.class, new IntegerTypeAdapter()));
            gson = gsonBuilder.create();
        }
        return gson;
    }

    public static long getValueByKey(String json, String key) {
        JsonParser parser = new JsonParser();
        // 2.获得 根节点元素
        JsonElement element = parser.parse(json);
        // 3.根据 文档判断根节点属于 什么类型的 Gson节点对象
        JsonObject root = element.getAsJsonObject();
        long value = 0;
        try {
            value = root.get(key).getAsLong();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static <T> ArrayList<T> json2ArrayList(String json, Class<T> cls) {

        try {
            ArrayList<T> arrayList = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                arrayList.add(new Gson().fromJson(jsonArray.getString(i), cls));
            }
            return arrayList;
        } catch (JSONException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

    }
}
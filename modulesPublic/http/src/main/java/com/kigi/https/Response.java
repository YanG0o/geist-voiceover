package com.kigi.https;

import androidx.annotation.Keep;

import com.google.gson.annotations.SerializedName;

public class Response<T> {

    @Keep
    @SerializedName("data")
    private T data;
    @Keep
    @SerializedName("code")
    private int code;
    @Keep
    @SerializedName("message")
    private String message;

    public boolean isSuccess(){
        return code == 200;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

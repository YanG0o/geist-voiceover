package com.kigi.baseview.exception;

public interface IErrorCode {
    //ui层处理数据错误
    int STATE_ERROR_UI_HANDLER = 10000;
    // 无网络
    int STATE_ERROR_NO_NET = 10001;
    //空数据
    int STATE_EMPTY_DATA = 10002;
    //401 token认证失败
    int STATE_TOKEN_ERROR = 401;


    int STATE_ERROR_THROW_BY_USER = 10004;


    int STATE_ERROR_TIME_OUT = 10005;

    int STATE_ERROR_CANCEL = 10006;

    int STATE_ERROR_OOM = 10007;
    //识别域名错误
    int STATE_ERROR_UNKNOWN_HOST = 10008;



}

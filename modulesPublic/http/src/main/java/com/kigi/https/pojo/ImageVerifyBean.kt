package com.kigi.https.pojo

//session_cookie_name	string
//必须
//回传Cookie的name
//session_cookie_value	string
//必须
//回传Cookie的value
//raw_captcha	string
//必须
//图片二进制base64
data class ImageVerifyBean(var session_cookie_name:String?,var session_cookie_value:String?,var raw_captcha:String?)
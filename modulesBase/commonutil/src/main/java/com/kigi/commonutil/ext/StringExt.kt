package com.kigi.commonutil.ext

fun String?.isEmpty():Boolean{
    return this == "" || this == null ||  "null" == this
}

fun String?.getNoNullValue(default: String):String{
    if(this == null || this == "" || this == "null"){
        return default
    }
    return this
}

fun String?.noNullValue(default:String):String{
    if(this == null || this == "" || "null" == this){
        return default
    }
    return this
}
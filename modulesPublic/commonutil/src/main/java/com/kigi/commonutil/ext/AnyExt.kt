package com.kigi.commonutil.ext

inline fun <reified T> Any.saveAs() : T{
    return this as T
}

@Suppress("UNCHECKED_CAST")
fun <T> Any.saveAsUnChecked() : T{
    return this as T
}

inline fun <reified T> Any.isEqualType() : Boolean{
    return this is T
}

inline fun <reified T> Any.mockListData(dataSize:Int = 10,produceMethod:(Int)->T):MutableList<T>{
    val data = mutableListOf<T>()
    for(i in 0 until dataSize){
        data.add(produceMethod(i))
    }
    return data
}




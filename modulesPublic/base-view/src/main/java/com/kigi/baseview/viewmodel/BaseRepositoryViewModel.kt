package com.kigi.baseview.viewmodel

import com.kigi.commonutil.ext.saveAs
import com.kigi.baseview.viewmodel.repository.BaseRepository
import java.lang.reflect.ParameterizedType

/**
 * @Author: HeYan
 * @CreateTime： 2023/1/8 23:01
 * @Describe： （）
 */
open class BaseRepositoryViewModel<R : BaseRepository> : BaseViewModel(){

    protected val repository: R
        get() = _repository

    private val _repository: R by lazy {
        val type = javaClass.genericSuperclass
        val vbClass: Class<R> = type?.saveAs<ParameterizedType>()!!.actualTypeArguments[0].saveAs()
        vbClass.getDeclaredConstructor().newInstance()
    }

}
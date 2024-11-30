package com.kigi.baseview.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kigi.baseview.exception.AppException
import com.kigi.baseview.exception.IErrorCode
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * @Author : HeYan
 * @Time : 2022/12/30 10:46
 * @Description : BaseViewModel
 */
open class BaseViewModel : ViewModel() {

    private val _dialogLoading = MutableLiveData<Boolean>()
    private val _toastMessage = MutableLiveData<String>()
    private val _showErrorView = MutableLiveData<String>()
//    open val dialogLoading: LiveData<Boolean> = _dialogLoading
    val toastMessage: LiveData<String> = _toastMessage
    val showErrorView: LiveData<String> = _showErrorView

    var isShowLoading by mutableStateOf(false)

    val toastMessageRes = MutableLiveData<Int>()

    fun showLoading() {
        isShowLoading = true;
//        _dialogLoading.postValue(true)
    }

    open fun hideLoading() {
        _dialogLoading.postValue(false)
    }


    open fun toast(message: String? = "") {
        message?.let {
            _toastMessage.postValue(it)
        }
    }

    open fun toastRes(messageRes: Int) {
        toastMessageRes.postValue(messageRes)
    }

    private val handler = CoroutineExceptionHandler { _, ex ->
        toast(AppException(ex).message)
    }

    fun launch(
        context: CoroutineContext = handler,
        scope: CoroutineScope = viewModelScope,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        return scope.launch(context, block = block)
    }

    suspend fun <T> call(
        loading: Boolean = false,
        toastType: ToastType = ToastType.ERROR,
        func: suspend () -> T
    ): Promise<T> {
        if (loading) {
            showLoading()
        }
        return try {
            val data = func()
            if (loading) {
                hideLoading()
            }
            Promise(data, null)
        } catch (e: Throwable) {
            e.printStackTrace()
            hideLoading()
            val exception = if (e is AppException) e else AppException(e)
            when (toastType) {
                ToastType.ALL, ToastType.ERROR -> {
                    toast(exception.message)
                }
                else -> {
                }
            }

            Promise(null, exception)

        }
    }

    /**
     * 异常唤起，网络错误展示
     */
    fun callNetworkError(e: AppException, block: () -> Unit) {
        when (e.errorCode) {
            IErrorCode.STATE_ERROR_NO_NET, IErrorCode.STATE_ERROR_TIME_OUT, IErrorCode.STATE_ERROR_UNKNOWN_HOST -> {
                _showErrorView.postValue(e.message)
            }
            else -> block.invoke()
        }
    }

}


class Promise<T>(var data: T?, private val error: AppException? = null) {
    fun <K> then(func: (data: T?) -> K): Promise<K> {
        if (error != null) {
            return Promise(null, error)
        }
        return try {
            Promise(func(data), null)
//            if (data != null) Promise(func(data!!), null)
//            else Promise(null, AppException(IErrorCode.STATE_EMPTY_DATA))
        } catch (e: Throwable) {
            Promise(null, AppException(e))
        }
    }


    fun catch(handlerError: ((e: AppException) -> Unit)? = null) {
        error?.let {
            handlerError?.invoke(it)
        }

    }

}

enum class ToastType {
    NONE, ERROR, ALL
}
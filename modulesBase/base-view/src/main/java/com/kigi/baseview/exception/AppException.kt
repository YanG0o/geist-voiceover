package com.kigi.baseview.exception

import android.text.TextUtils
import com.kigi.baseview.BuildConfig
import com.kigi.baseview.R
import com.kigi.commonutil.CommonBaseApp
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.CancellationException

open class AppException : RuntimeException, IErrorCode {
    constructor(throwable: Throwable?) : super(throwable) {
        errorCode = if (throwable == null) {
            -1
        } else {
            when (throwable) {
                is ConnectException -> IErrorCode.STATE_ERROR_NO_NET
                is SocketTimeoutException -> IErrorCode.STATE_ERROR_TIME_OUT
                is UnknownHostException -> IErrorCode.STATE_ERROR_UNKNOWN_HOST
                is CancellationException -> IErrorCode.STATE_ERROR_CANCEL
                is OutOfMemoryError -> IErrorCode.STATE_ERROR_OOM
                is AppException -> throwable.errorCode
                else -> -1
            }
        }

    }

    var url = ""
        private set

    var errorCode = 0
        private set

    constructor(errorCode: Int,message: String?) : super(message) {
        this.errorCode = errorCode
    }

    constructor(errorCode: Int) : super() {
        this.errorCode = errorCode
    }

    constructor(message: String?) : super(message) {
        errorCode = IErrorCode.STATE_ERROR_THROW_BY_USER
    }


    override val message: String?
        get() {
            return when (errorCode) {
                IErrorCode.STATE_ERROR_NO_NET -> CommonBaseApp.context.getString(R.string.tips_check_network)
                IErrorCode.STATE_EMPTY_DATA -> CommonBaseApp.context.getString(R.string.tips_no_data)
//                IErrorCode.STATE_ERROR_FAILED -> "识别失败"
                IErrorCode.STATE_ERROR_TIME_OUT -> CommonBaseApp.context.getString(R.string.tips_network_timeout)
                IErrorCode.STATE_ERROR_OOM -> CommonBaseApp.context.getString(R.string.tips_oom)
                IErrorCode.STATE_TOKEN_ERROR -> "verify error"
                IErrorCode.STATE_ERROR_CANCEL -> ""
                else -> {
                    var msg = if (BuildConfig.DEBUG) "$errorCode : ${super.message}" else ""
                    if (TextUtils.isEmpty(msg)) {
//                        msg = getContext().resources.getString(R.string.better_error_serer)
                        msg = CommonBaseApp.context.getString(R.string.tips_server_error)
                    }
                    return msg
                }
            }
        }

}
package com.abdullah.univeraproject.utils

import android.telecom.Call


sealed class RemoteResponse<out T>(var status: Status, val _data: T?, val message: String?) {

    data class Success<out R>(val data: R) : RemoteResponse<R>(
        status = Status.SUCCESS,
        _data = data,
        message = null
    )

    data class Loading(val isLoading: Boolean) : RemoteResponse<Nothing>(
        status = Status.LOADING,
        _data = null,
        message = null
    )

    data class Error(val exception: String) : RemoteResponse<Nothing>(
        status = Status.ERROR,
        _data = null,
        message = exception
    )

}
package com.abdullah.univeraproject.viewmodels

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abdullah.univeraproject.di.retrofit.RetrofitRepository
import com.abdullah.univeraproject.utils.RemoteResponse
import kotlin.reflect.KFunction2

interface IBaseViewModel {
    fun LoadData()
}

open class BaseViewModel<T>(val repository: RetrofitRepository) : ViewModel(),
    IBaseViewModel {
    var liveData: MutableLiveData<RemoteResponse<List<T>>> = MutableLiveData()

    private fun getObserver(): MutableLiveData<RemoteResponse<List<T>>> {
        return liveData
    }


    fun observe(
        owner: LifecycleOwner,
        run: KFunction2<Boolean, RemoteResponse<List<T>>?, Unit>
    ) {
        getObserver().observe(
            owner
        ) { t ->
            run(t != null, t)
        }
    }

    override fun LoadData() {

    }
}
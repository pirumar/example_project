package com.abdullah.univeraproject.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.abdullah.univeraproject.databinding.CustomRecyclerViewBinding
import com.abdullah.univeraproject.utils.RemoteResponse
import com.abdullah.univeraproject.utils.Status

class CustomRecyclerView(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    var binding: CustomRecyclerViewBinding
    var liveData: MutableLiveData<RemoteResponse<List<Any>>>

    init {
        liveData = MutableLiveData(RemoteResponse.Loading(true))
        binding = CustomRecyclerViewBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )


    }


    fun recycylerView(): RecyclerView {
        return binding.recycler
    }

    fun SetData(
        data: RemoteResponse<List<Any>>,

    ) {
        liveData.observe(findViewTreeLifecycleOwner()!!) {
            if (it.status == Status.LOADING) {
                binding.progressBar.visibility =
                    VISIBLE;

            } else if (it.status == Status.ERROR) {
                binding.tvError.text = it.message

                binding.progressBar.visibility =
                    GONE
            } else {
                binding.progressBar.visibility =
                    GONE
            }
        }
        this.liveData.postValue(data)
    }


}
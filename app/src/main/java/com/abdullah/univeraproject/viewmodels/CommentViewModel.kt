package com.abdullah.univeraproject.viewmodels

import androidx.lifecycle.MutableLiveData
import com.abdullah.univeraproject.di.retrofit.RetrofitRepository
import com.abdullah.univeraproject.models.Comment
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(repositorys: RetrofitRepository) :
    BaseViewModel<Comment>(repositorys) {

    private var start: Int = 0
    private val limit: Int = 20


    fun LoadData(photoId: Int) {

        repository.getCommentByPhoto(
            photoId, start, limit, liveData
        )
        start += limit
    }
}
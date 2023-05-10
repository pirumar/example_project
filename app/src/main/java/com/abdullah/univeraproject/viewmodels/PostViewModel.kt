package com.abdullah.univeraproject.viewmodels

import com.abdullah.univeraproject.di.retrofit.RetrofitRepository
import com.abdullah.univeraproject.models.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(repositorys: RetrofitRepository) :
    BaseViewModel<Post>(repositorys) {

    override fun LoadData() {
        super.LoadData()
        repository.getPosts(liveData)
    }
}
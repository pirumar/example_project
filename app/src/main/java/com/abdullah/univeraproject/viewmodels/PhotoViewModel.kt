package com.abdullah.univeraproject.viewmodels

import com.abdullah.univeraproject.di.retrofit.RetrofitRepository
import com.abdullah.univeraproject.models.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(repositorys: RetrofitRepository) :
    BaseViewModel<Photo>(repositorys) {

    fun loadData(id: Int) {
        super.LoadData()
        repository.getPhotoByAlbum(id, liveData)
    }

}
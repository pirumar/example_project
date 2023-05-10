package com.abdullah.univeraproject.viewmodels

import com.abdullah.univeraproject.di.retrofit.RetrofitRepository
import com.abdullah.univeraproject.models.Album
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(repositorys: RetrofitRepository) :
    BaseViewModel<Album>(repositorys) {

    override fun LoadData() {
        super.LoadData()
        repository.getAlbums(liveData)
    }
}
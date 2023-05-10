package com.abdullah.univeraproject.di.retrofit

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.abdullah.univeraproject.models.Album
import com.abdullah.univeraproject.models.Comment
import com.abdullah.univeraproject.models.Photo
import com.abdullah.univeraproject.models.Post
import com.abdullah.univeraproject.utils.RemoteResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.UnknownHostException
import javax.inject.Inject
import javax.net.ssl.SSLEngineResult.Status


class RetrofitRepository @Inject constructor(
    private val retrofitServiceInstance: RetrofitServiceInstance, private val context: Context
) {


    fun getPosts(liveData: MutableLiveData<RemoteResponse<List<Post>>>) {
        retrofitServiceInstance.getPost().enqueue(object : Callback<List<Post>> {
            override fun onResponse(
                call: Call<List<Post>>,
                response: Response<List<Post>>
            ) {
                liveData.postValue(RemoteResponse.Success(response.body()!!))
            }


            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                t.let {
                    if (t is UnknownHostException)
                        liveData.postValue(RemoteResponse.Error("İnternet bağlantınızı kontrol ediniz"))
                }
            }

        })
    }

    fun getAlbums(livedata: MutableLiveData<RemoteResponse<List<Album>>>) {
        livedata.postValue(RemoteResponse.Loading(true))

        retrofitServiceInstance.getAlbum().enqueue(object : Callback<List<Album>> {
            override fun onResponse(
                call: Call<List<Album>>,
                response: Response<List<Album>>
            ) {
                livedata.postValue(RemoteResponse.Success(response.body()!!))
            }

            override fun onFailure(call: Call<List<Album>>, t: Throwable) {

                t.let {
                    if (t is UnknownHostException) {
                        livedata.postValue(
                            RemoteResponse.Error(
                                "İnternet bağlantınızı kontrol ediniz"
                            )
                        )

                    }
                }

            }

        })
    }

    fun getPhotoByAlbum(albumId: Int, liveData: MutableLiveData<RemoteResponse<List<Photo>>>) {

        liveData.postValue(RemoteResponse.Loading(true))
        retrofitServiceInstance.getPhoto(albumId).enqueue(object : Callback<List<Photo>> {
            override fun onResponse(
                call: Call<List<Photo>>,
                response: Response<List<Photo>>
            ) {
                liveData.postValue(RemoteResponse.Success(response.body()!!))
            }

            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {

                t.let {
                    if (t is UnknownHostException)
                        liveData.postValue(RemoteResponse.Error("İnternet bağlantınızı kontrol ediniz"))
                }
            }

        })
    }

    fun getCommentByPhoto(
        photoId: Int,
        start: Int,
        limit: Int,
        liveData: MutableLiveData<RemoteResponse<List<Comment>>>
    ) {
        liveData.value?.status = com.abdullah.univeraproject.utils.Status.LOADING

        retrofitServiceInstance.getComment(photoId, start, limit)
            .enqueue(object : Callback<List<Comment>> {
                override fun onResponse(
                    call: Call<List<Comment>>,
                    response: Response<List<Comment>>
                ) {
                    Thread.sleep(300)
                    val list = liveData.value?._data
                    if (list != null) {
                        (list as ArrayList<Comment>).addAll(response.body()!!)
                        liveData.postValue(
                            RemoteResponse.Success(list)
                        )
                    } else {
                        liveData.postValue(RemoteResponse.Success(response.body()!!))
                    }


                }

                override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                    t.let {
                        liveData.postValue(RemoteResponse.Error("İnternet bağlantınızı kontrol ediniz"))
                    }
                }

            })
    }


}


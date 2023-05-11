package com.abdullah.univeraproject.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.abdullah.univeraproject.MainActivity
import com.abdullah.univeraproject.R
import com.abdullah.univeraproject.databinding.FragmentPhotoBinding
import com.abdullah.univeraproject.databinding.PhotoItemBinding
import com.abdullah.univeraproject.di.adapter.BaseViewHolder
import com.abdullah.univeraproject.di.adapter.RecAdapter
import com.abdullah.univeraproject.di.retrofit.RetrofitServiceInstance
import com.abdullah.univeraproject.models.Photo
import com.abdullah.univeraproject.utils.RemoteResponse
import com.abdullah.univeraproject.viewholder.PhotoViewHolder
import com.abdullah.univeraproject.viewmodels.PhotoViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PhotoFragment : Fragment() {
    val viewModel by viewModels<PhotoViewModel>()

    @set:Inject
    internal var mSharedPreferences: SharedPreferences? = null

    @set:Inject
    internal var retrofitServiceInstance: RetrofitServiceInstance? = null

    lateinit var binding: FragmentPhotoBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhotoBinding.inflate(inflater, container, false)
        binding.customRec.SetOnRetryConnection {
            LoadData()
        }
        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.observe(this, this::OnChanged)
        LoadData()
    }

    fun LoadData() {
        val id = mSharedPreferences?.getInt("albumId", 0)
        if (id != null) {
            viewModel.loadData(id)
        }
    }

    private fun createViewHolder(parent: ViewGroup): BaseViewHolder<Photo> {
        return PhotoViewHolder(
            PhotoItemBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }


    private fun OnChanged(success: Boolean, data: RemoteResponse<List<Photo>>?) {
        data?.let { binding.customRec.SetData(it) }
        binding.customRec.recycylerView().adapter =
            RecAdapter(
                data?._data,
                context,
                this::createViewHolder,
                this::ItemClick
            )
    }


    private fun ItemClick(photo: Photo) {
        activity?.let { MainActivity.loadFragment(CommentFragment.newInstance(photo.id), it) }
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            PhotoFragment()
    }
}
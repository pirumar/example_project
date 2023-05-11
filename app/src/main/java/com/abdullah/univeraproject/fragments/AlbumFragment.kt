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
import com.abdullah.univeraproject.databinding.AlbumItemBinding
import com.abdullah.univeraproject.databinding.CustomRecyclerViewBinding
import com.abdullah.univeraproject.databinding.FragmentAlbumBinding
import com.abdullah.univeraproject.di.adapter.BaseViewHolder
import com.abdullah.univeraproject.di.adapter.RecAdapter
import com.abdullah.univeraproject.di.retrofit.RetrofitServiceInstance
import com.abdullah.univeraproject.models.Album
import com.abdullah.univeraproject.utils.RemoteResponse
import com.abdullah.univeraproject.viewholder.AlbumViewHolder
import com.abdullah.univeraproject.viewmodels.AlbumViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AlbumFragment : Fragment() {
    val viewModel by viewModels<AlbumViewModel>()

    @set:Inject
    internal var mSharedPreferences: SharedPreferences? = null

    @set:Inject
    internal lateinit var retrofitServiceInstance: RetrofitServiceInstance


    lateinit var binding: FragmentAlbumBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAlbumBinding.inflate(inflater, container, false)
        binding.customRec.SetOnRetryConnection {
            viewModel.LoadData()

        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.observe(this, this::OnChanged)
        viewModel.LoadData()
    }


    private fun createViewHolder(parent: ViewGroup): BaseViewHolder<Album> {
        return AlbumViewHolder(
            AlbumItemBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }


    fun OnChanged(success: Boolean, data: RemoteResponse<List<Album>>?) {

        data?.let { binding.customRec.SetData(it) }
        binding.customRec.recycylerView().adapter =
            RecAdapter(
                data?._data,
                context,
                this::createViewHolder,
                this::ItemClick
            )
        binding.customRec.recycylerView().adapter?.notifyDataSetChanged()


    }

    fun ItemClick(album: Album) {
        mSharedPreferences?.edit()?.putInt("albumId", album.id)?.apply()
        val fragment = PhotoFragment()
        activity?.let { MainActivity.loadFragment(fragment, it) }
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            AlbumFragment()
    }
}
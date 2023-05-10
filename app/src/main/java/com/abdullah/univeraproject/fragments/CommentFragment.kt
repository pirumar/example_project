package com.abdullah.univeraproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.abdullah.univeraproject.R
import com.abdullah.univeraproject.databinding.CommentItemBinding
import com.abdullah.univeraproject.databinding.FragmentCommentBinding
import com.abdullah.univeraproject.di.adapter.BaseViewHolder
import com.abdullah.univeraproject.di.adapter.RecAdapter
import com.abdullah.univeraproject.models.Comment
import com.abdullah.univeraproject.utils.RemoteResponse
import com.abdullah.univeraproject.utils.Status
import com.abdullah.univeraproject.viewholder.CommentViewHolder
import com.abdullah.univeraproject.viewmodels.CommentViewModel
import dagger.hilt.android.AndroidEntryPoint


private const val ARG_PHOTOID = "photoId"

@AndroidEntryPoint
class CommentFragment : Fragment() {
    private var photoId: Int = 0
    val viewModel by viewModels<CommentViewModel>()
    var adapter: RecAdapter<Comment>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.observe(this, this::OnChanged)
        arguments?.let {
            photoId = it.getInt(ARG_PHOTOID)
            viewModel.LoadData(photoId)

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.customRec.recycylerView()
            .addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1)) {
                        viewModel.LoadData(photoId)
                    }
                }
            })
    }

    lateinit var binding: FragmentCommentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCommentBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun createViewHolder(parent: ViewGroup): BaseViewHolder<Comment> {
        return CommentViewHolder(
            CommentItemBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }


    private fun OnChanged(success: Boolean, data: RemoteResponse<List<Comment>>?) {
        data?.let { binding.customRec.SetData(it) }
        if (adapter == null) {
            adapter = RecAdapter(
                data?._data,
                context,
                this::createViewHolder,
                {

                }
            )
            binding.customRec.recycylerView().adapter = adapter
        } else {
            data?._data?.let { adapter!!.setItems(it) }
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(photoId: Int) =
            CommentFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PHOTOID, photoId)
                }
            }
    }
}
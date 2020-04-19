package com.rascarlo.astroeye.recents

import android.content.res.Resources
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.rascarlo.astroeye.databinding.FragmentRecentImagesBinding


/**
 * fragment to hold the [RecentImageViewModel]
 * recycler view makes use of [RecentImagesAdapter]
 * observe the [RecentImageViewModel] to submit list to [RecentImagesAdapter]
 */
class RecentImagesFragment : Fragment() {

    /**
     * initialize [RecentImageViewModel]
     */
    private val viewModel: RecentImageViewModel by lazy {
        ViewModelProvider(this).get(RecentImageViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity!!).application
        // data binding
        val binding = FragmentRecentImagesBinding.inflate(inflater)
        // binding lifecycle owner
        binding.lifecycleOwner = this
        // binding access to view model
        binding.viewModel = viewModel
        // adapter
        val adapter = RecentImagesAdapter()
        // layout manager
        val layoutManager = LinearLayoutManager(
            application.applicationContext,
            LinearLayoutManager.VERTICAL,
            false
        )
        /**
         * not to use if using card view
         */
        val dividerItemDecoration = DividerItemDecoration(
            binding.recentImageRecyclerView.context,
            layoutManager.orientation
        )
        // binding.recentImageRecyclerView.addItemDecoration(dividerItemDecoration)
        // spacing item decoration
        val spacingItemDecoration = object : ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                if (parent.getChildAdapterPosition(view) != 0) {
                    outRect.top = (8 * Resources.getSystem().displayMetrics.density).toInt()
                }
                outRect.left = (0 * Resources.getSystem().displayMetrics.density).toInt()
                outRect.right = (0 * Resources.getSystem().displayMetrics.density).toInt()
                outRect.bottom = (8 * Resources.getSystem().displayMetrics.density).toInt()
            }
        }
        binding.recentImageRecyclerView.addItemDecoration(spacingItemDecoration)
        // recycler view
        binding.recentImageRecyclerView.layoutManager = layoutManager
        binding.recentImageRecyclerView.adapter = adapter
        // observe live data
        viewModel.recentImages.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                adapter.submitList(it)
            }
        })
        return binding.root
    }
}
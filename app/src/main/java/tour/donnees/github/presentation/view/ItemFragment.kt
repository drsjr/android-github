package tour.donnees.github.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import tour.donnees.github.databinding.FragmentItemListBinding
import tour.donnees.github.presentation.viewmodel.MainViewModel

class ItemFragment : Fragment() {

    private val viewModel by activityViewModel<MainViewModel>()
    private lateinit var binding: FragmentItemListBinding
    private val adapterItem by lazy { ItemViewAdapter(viewModel) }

    private var columnCount = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemListBinding.inflate(inflater)


        // Set the adapter
        if (binding.list is RecyclerView) {
            endlessScrolling(binding.list)
                with(binding.list) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = adapterItem
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        viewModel.getRepositories()
    }

    private fun initObserver() {
        viewModel.list.observe(this@ItemFragment.viewLifecycleOwner) {
            adapterItem.updateAdapter()
        }
        viewModel.isLoading.observe(this@ItemFragment.viewLifecycleOwner, ::showLoading)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.showIf(isLoading)
    }

    private fun endlessScrolling(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!viewModel.isLoading()) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == adapterItem.itemCount - 1) {
                        viewModel.getRepositories()
                    }
                }
            }
        })
    }
}

fun View.showIf(boo: Boolean) {
    this.visibility = if (boo) View.VISIBLE else View.GONE
}
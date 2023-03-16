package tour.donnees.github.presentation.view

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
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

    private var columnCount = 2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemListBinding.inflate(inflater)


        // Set the adapter
        when (resources.configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                with(binding.list) {
                    layoutManager = GridLayoutManager(context, columnCount)
                    adapter = adapterItem
                }
            }
            else -> {
                with(binding.list) {
                    layoutManager = LinearLayoutManager(context)
                    adapter = adapterItem
                }
            }

        }
        binding.list.apply {
            endlessScrolling(this)
            addDivider(this)
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            viewModel.getRepositories()
        } else {
            adapterItem.updateAdapter()
        }
        initObserver()
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

    private fun addDivider(recyclerView: RecyclerView) {
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))
    }
}

fun View.showIf(boo: Boolean) {
    this.visibility = if (boo) View.VISIBLE else View.GONE
}
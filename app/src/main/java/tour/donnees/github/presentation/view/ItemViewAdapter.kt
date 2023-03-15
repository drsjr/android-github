package tour.donnees.github.presentation.view

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import tour.donnees.github.databinding.FragmentItemBinding
import tour.donnees.github.domain.model.RepositoryModel
import tour.donnees.github.presentation.viewmodel.MainViewModel

class ItemViewAdapter(private val viewModel: MainViewModel) : RecyclerView.Adapter<ItemViewAdapter.ViewHolder>() {

    private val values = mutableListOf<RepositoryModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.repositoryName
        holder.contentView.text = item.profileName
    }

    override fun getItemCount(): Int = values.size

    fun updateAdapter() {
        values.clear()
        values.addAll(viewModel.getRepositoryList())
        notifyDataSetChanged()
    }

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemTitle
        val contentView: TextView = binding.itemSubTitle

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}
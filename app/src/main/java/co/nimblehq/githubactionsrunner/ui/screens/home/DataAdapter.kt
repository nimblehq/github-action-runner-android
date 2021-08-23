package co.nimblehq.githubactionsrunner.ui.screens.home

import android.view.*
import androidx.recyclerview.widget.RecyclerView
import co.nimblehq.githubactionsrunner.databinding.ItemDataBinding
import co.nimblehq.githubactionsrunner.domain.data.Data
import co.nimblehq.githubactionsrunner.extension.loadImage
import co.nimblehq.githubactionsrunner.ui.common.ItemClickable
import co.nimblehq.githubactionsrunner.ui.common.ItemClickableImpl
import kotlinx.android.extensions.LayoutContainer

internal class DataAdapter :
    RecyclerView.Adapter<DataAdapter.ViewHolder>(),
    ItemClickable<DataAdapter.OnItemClick> by ItemClickableImpl() {

    var items = listOf<Data>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    internal inner class ViewHolder(
        private val binding: ItemDataBinding
    ) : RecyclerView.ViewHolder(binding.root), LayoutContainer {

        override val containerView: View
            get() = itemView

        init {
            itemView.setOnClickListener {
                notifyItemClick(OnItemClick.Item(items[adapterPosition]))
            }
        }

        fun bind(model: Data) {
            with(model) {
                with(binding) {
                    tvDataTitle.text = title
                    tvDataAuthor.text = author
                    ivDataThumbnail.loadImage(thumbnail)
                }
            }
        }
    }

    sealed class OnItemClick {

        data class Item(val data: Data) : OnItemClick()
    }
}

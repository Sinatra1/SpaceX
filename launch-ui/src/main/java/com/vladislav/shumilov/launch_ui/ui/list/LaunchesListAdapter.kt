package com.vladislav.shumilov.launch_ui.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vladislav.shumilov.launch_domain.model.local.LaunchForList
import com.vladislav.shumilov.launch_ui.R
import com.vladislav.shumilov.launch_ui.databinding.LaunchesListRowBinding

internal class LaunchesListAdapter(
    private val onItemClickListener: (launch: LaunchForList) -> Unit,
) : RecyclerView.Adapter<LaunchesListViewHolder>() {

    private val items = mutableListOf<LaunchForList>()
    private lateinit var binding: LaunchesListRowBinding

    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(true)
    }

    override fun getItemId(position: Int) = items[position].launch.flightNumber.toLong()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchesListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.launches_list_row, parent, false)
        return LaunchesListViewHolder(binding, onItemClickListener)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: LaunchesListViewHolder, position: Int) {
        val item = items[position]

        holder.bind(item)
    }

    override fun getItemViewType(position: Int) =
        LAUNCHES_LIST_VIEW_HOLDER_TYPE

    fun setItems(items: List<LaunchForList>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun selectItem(itemId: String) {
        items.apply {
            forEach {
                it.selected = false
            }

            firstOrNull {
                it.launch.id == itemId
            }?.selected = true
        }

        notifyDataSetChanged()
    }
}
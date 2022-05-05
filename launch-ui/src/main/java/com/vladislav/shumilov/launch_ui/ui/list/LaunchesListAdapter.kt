package com.vladislav.shumilov.launch_ui.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.vladislav.shumilov.core_ui.utils.OnClickViewHolder
import com.vladislav.shumilov.launch_domain.model.local.LaunchForList
import com.vladislav.shumilov.launch_ui.R
import com.vladislav.shumilov.launch_ui.databinding.LaunchesListRowBinding

internal class LaunchesListAdapter(context: Context) :
    RecyclerView.Adapter<LaunchesListViewHolder>(), OnClickViewHolder<Pair<View, LaunchForList>> {

    private val viewHolderClickEvent = MutableLiveData<Pair<View, LaunchForList>>()

    private val items = mutableListOf<LaunchForList>()
    private val layoutInflater = LayoutInflater.from(context)
    private lateinit var binding: LaunchesListRowBinding

    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(true)
    }

    override fun getItemId(position: Int) = items[position].launch.flightNumber.toLong()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchesListViewHolder {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.launches_list_row, parent, false)
        return LaunchesListViewHolder(binding, viewHolderClickEvent)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: LaunchesListViewHolder, position: Int) {
        val item = items[position]

        holder.bind(item)
    }

    override fun getItemViewType(position: Int) =
        LAUNCHES_LIST_VIEW_HOLDER_TYPE

    override fun getViewHolderClickEvent(): LiveData<Pair<View, LaunchForList>> =
        viewHolderClickEvent

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
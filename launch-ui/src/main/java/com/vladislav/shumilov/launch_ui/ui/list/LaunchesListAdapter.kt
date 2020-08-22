package com.vladislav.shumilov.launch_ui.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.launch_ui.R
import com.example.launch_ui.databinding.LaunchesListRowBinding
import com.vladislav.shumilov.core_ui.utils.OnClickViewHolder
import com.vladislav.shumilov.launch_domain.model.local.LaunchForList
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject


internal class LaunchesListAdapter(context: Context) :
    RecyclerView.Adapter<LaunchesListViewHolder>(), OnClickViewHolder<LaunchForList> {

    override var onClickViewHolderCallback: Subject<LaunchForList> = PublishSubject.create()

    private val items: ArrayList<LaunchForList> = ArrayList()
    private val layoutInflater = LayoutInflater.from(context)
    private lateinit var binding: LaunchesListRowBinding

    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(true)
    }

    override fun getItemId(position: Int) = items[position].launch.flightNumber.toLong()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchesListViewHolder {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.launches_list_row, parent, false)
        return LaunchesListViewHolder(binding, onClickViewHolderCallback)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: LaunchesListViewHolder, position: Int) {
        val item = items[position]

        holder.bind(item)
    }

    override fun getItemViewType(position: Int) =
        LAUNCHES_LIST_VIEW_HOLDER_TYPE



    fun addItems(items: List<LaunchForList>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}
package com.vladislav.shumilov.core_ui.ui.list_with_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.vladislav.shumilov.core_ui.R

abstract class BaseListWithDetailFragment<ListFragment : Fragment, DetailFragment : Fragment> :
    Fragment(), BaseListWithDetail {

    companion object {
        private const val SELECTED_ITEM_ID = "selected_item_id"

        fun getBundle(itemId: String?): Bundle = Bundle().apply {
            putString(SELECTED_ITEM_ID, itemId)
        }
    }

    protected open val layoutId = R.layout.core_base_list_with_detail

    private var commonDetailContainerView: FrameLayout? = null
    private var selectedItemId: String? = null
    private var canShowDetailFragment = true

    init {
        retainInstance = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        selectedItemId = arguments?.getString(SELECTED_ITEM_ID)

        setListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(layoutId, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        commonDetailContainerView = view.findViewById(R.id.commonDetailContainer)

        if (canShowDetailFragment || commonDetailContainerView != null) {
            setDetailFragment()
        } else {
            canShowDetailFragment = true
        }
    }

    override fun initializeSelectedItemId(itemId: String) {
        if (selectedItemId == null && commonDetailContainerView != null) {
            transmitSelectedItemId(itemId)
        }
    }

    override fun transmitSelectedItemId(itemId: String) {
        selectedItemId = itemId
        setDetailFragment()
    }

    override fun getSelectedItemId(): String? = selectedItemId

    protected abstract fun getListFragment(): ListFragment

    protected abstract fun getDetailFragmentTag(itemId: String): DetailFragment

    protected abstract fun getListFragmentTag(): String

    protected abstract fun getDetailFragmentTag(): String

    private fun setDetailFragment() {
        selectedItemId?.let {
            selectListRow(it)

            if (commonDetailContainerView != null) {
                childFragmentManager.beginTransaction()
                    .replace(
                        R.id.commonDetailContainer,
                        getDetailFragmentTag(it),
                        getDetailFragmentTag()
                    ).commit()
                canShowDetailFragment = true
            } else {
                showDetailFragment(it)
                canShowDetailFragment = false
            }
        }
    }

    private fun setListFragment() {
        childFragmentManager.beginTransaction()
            .replace(R.id.commonListContainer, getListFragment(), getListFragmentTag())
            .commit()
    }

    private fun showDetailFragment(itemId: String) {
        (childFragmentManager.findFragmentByTag(getListFragmentTag()) as? BaseListFragment)
            ?.showDetailFragment(itemId)
    }

    private fun selectListRow(itemId: String) {
        (childFragmentManager.findFragmentByTag(getListFragmentTag()) as? BaseListFragment)?.selectListRow(
            itemId
        )
    }
}
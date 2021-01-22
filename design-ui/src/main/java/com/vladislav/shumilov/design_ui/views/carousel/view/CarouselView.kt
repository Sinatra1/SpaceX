package com.vladislav.shumilov.design_ui.views.carousel.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.view.ContextThemeWrapper
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager2.widget.ViewPager2
import com.vladislav.shumilov.core_ui.utils.getDataFromAttrOrNull
import com.vladislav.shumilov.design_ui.R
import com.vladislav.shumilov.design_ui.views.carousel.model.CarouselItemModel
import kotlinx.android.synthetic.main.design_carousel_view.view.*

private const val OFF_SCREEN_PAGE_LIMIT = 1

class CarouselView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.designCarouselViewTheme,
    private val adapter: CarouselAdapterAbstract = CarouselAdapter(context)
) : ConstraintLayout(
    ContextThemeWrapper(
        context,
        context.getDataFromAttrOrNull(defStyleAttr) ?: R.style.DesignCarouselView
    ), attrs, defStyleAttr
) {
    private lateinit var onPageSelectedListener: (position: Int) -> Unit

    init {
        View.inflate(context, R.layout.design_carousel_view, this)

        designCarouseViewPager.adapter = adapter

        designCarouseViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                onPageSelectedListener(position)
            }
        })

        val attributes =
            context.theme.obtainStyledAttributes(attrs, R.styleable.DesignCarouselView, 0, 0)

        designCarouseViewPager.offscreenPageLimit = attributes.getInt(
            R.styleable.DesignCarouselView_DesignCarouselView_off_screen_page_limit,
            OFF_SCREEN_PAGE_LIMIT
        )

        attributes.recycle()
    }

    fun setItems(items: List<CarouselItemModel>?) {
        items?.let(adapter::setItems)
    }

    fun setCurrentItem(itemIndex: Int) {
        designCarouseViewPager.setCurrentItem(itemIndex, true)
    }

    fun setOnPageSelectedListener(listener: (position: Int) -> Unit) {
        onPageSelectedListener = listener
    }
}
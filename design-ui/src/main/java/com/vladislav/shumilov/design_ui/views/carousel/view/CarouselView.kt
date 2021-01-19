package com.vladislav.shumilov.design_ui.views.carousel.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.view.ContextThemeWrapper
import androidx.constraintlayout.widget.ConstraintLayout
import com.vladislav.shumilov.core_ui.utils.getDataFromAttrOrNull
import com.vladislav.shumilov.design_ui.R
import com.vladislav.shumilov.design_ui.views.carousel.model.CarouselItemModel
import kotlinx.android.synthetic.main.design_carousel_view.view.*

private const val DEFAULT_CAROUSEL_DELAY = 0

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
    init {
        View.inflate(context, R.layout.design_carousel_view, this)

        designCarouselList.adapter = adapter

        val attributes =
            context.theme.obtainStyledAttributes(attrs, R.styleable.DesignCarouselView, 0, 0)

        val delay = attributes.getInt(R.styleable.DesignCarouselView_DesignCarouselView_design_carousel_delay, DEFAULT_CAROUSEL_DELAY)

        attributes.recycle()
    }

    fun setItems(items: List<CarouselItemModel>?) {
        items?.let(adapter::setItems)
    }
}
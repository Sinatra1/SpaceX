package com.vladislav.shumilov.core_ui.utils

import io.reactivex.subjects.Subject

interface OnClickViewHolder<T> {
    var onClickViewHolderCallback: Subject<T>
}
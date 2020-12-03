package com.vladislav.shumilov.core_data.util

import io.reactivex.disposables.Disposable
import io.reactivex.internal.disposables.DisposableContainer

operator fun DisposableContainer.plusAssign(d: Disposable) {
    add(d)
}
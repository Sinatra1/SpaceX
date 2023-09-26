package com.vladislav.shumilov.launch_ui.ui.work

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

internal class OneTimeWork(
    context: Context,
    workerParams: WorkerParameters,
): Worker(context, workerParams) {

    override fun doWork(): Result {
        Log.e("OneTimeWork", "OneTimeWork ${Thread.currentThread().name}")

        for (i in 1..10) {
            Thread.sleep(1000)
            Log.e("OneTimeWork", "OneTimeWork $i")
        }

        return Result.success()
    }
}
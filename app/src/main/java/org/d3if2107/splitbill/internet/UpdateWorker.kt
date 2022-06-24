package org.d3if2107.splitbill.internet

import android.content.Context
import android.content.ContextParams
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class UpdateWorker (context: Context,
    workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        Log.d("Worker", "Proses background dijalankan...")
        return Result.success()
    }
}
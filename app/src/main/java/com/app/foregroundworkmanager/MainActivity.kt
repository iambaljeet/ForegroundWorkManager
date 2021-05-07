package com.app.foregroundworkmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        WorkManager.getInstance(this)
            .beginUniqueWork("ForegroundWorker", ExistingWorkPolicy.APPEND_OR_REPLACE,
            OneTimeWorkRequest.from(ForegroundWorker::class.java)).enqueue().state
            .observe(this) { state ->
                Log.d(TAG, "ForegroundWorker: $state")
            }
    }
}
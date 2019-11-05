package com.sunil.androidjetpackcomponent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.*
import com.sunil.androidjetpackcomponent.work.NotifyWorker
import kotlinx.android.synthetic.main.work_activity.*
import java.util.concurrent.TimeUnit
import javax.xml.datatype.DatatypeConstants.DAYS



class WorkerActivity : AppCompatActivity() {

    private lateinit var worker: WorkManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.work_activity)

        worker = WorkManager.getInstance()

        val powerConstraint = Constraints.Builder().setRequiresCharging(true).build()

        val taskData = Data.Builder().putString(MESSAGE_STATUS, "Notify Done.").build()

        val request = OneTimeWorkRequest.Builder(NotifyWorker::class.java)
            .setConstraints(powerConstraint).setInputData(taskData).build()

        btn_send.setOnClickListener {
            worker.enqueue(request)
        }

        worker.getWorkInfoByIdLiveData(request.id).observe(this, Observer { workInfo ->
            workInfo.let {
                if (it.state.isFinished) {
                    val outputData = it.outputData
                    val taskResult = outputData.getString(NotifyWorker.WORK_RESULT)
                    txt_input.text = taskResult
                } else {
                    val workStatus = workInfo.state
                    txt_input.text = workStatus.toString()
                }
            }
        })

    }

    companion object {
        const val MESSAGE_STATUS = "message_status"
    }

    fun scheduleWork(tag: String) {
        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .build()
        val work = PeriodicWorkRequestBuilder<NotifyWorker>(1, TimeUnit.HOURS).setConstraints(constraints)
            .build()
        worker.enqueueUniquePeriodicWork(tag, ExistingPeriodicWorkPolicy.KEEP , work)
    }
}
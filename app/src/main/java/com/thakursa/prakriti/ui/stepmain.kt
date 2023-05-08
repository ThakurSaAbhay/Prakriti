package com.thakursa.prakriti

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thakursa.prakriti.callback.stepsCallback
import com.thakursa.prakriti.helper.GeneralHelper
import com.thakursa.prakriti.service.StepDetectorService
import kotlinx.android.synthetic.main.step.TV_CALORIES
import kotlinx.android.synthetic.main.step.TV_DISTANCE
import kotlinx.android.synthetic.main.step.TV_STEPS

class stepmain : AppCompatActivity(), stepsCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.step)

        val intent = Intent(this, StepDetectorService::class.java)
        startService(intent)

        StepDetectorService.subscribe.register(this)
    }

    override fun subscribeSteps(steps: Int) {
        TV_STEPS.setText(steps.toString())
        TV_CALORIES.setText(GeneralHelper.getCalories(steps))
        TV_DISTANCE.setText(GeneralHelper.getDistanceCovered(steps))
    }
}
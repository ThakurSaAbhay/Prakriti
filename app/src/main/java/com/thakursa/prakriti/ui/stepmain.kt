package com.thakursa.prakriti

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thakursa.prakriti.callback.stepsCallback
import com.thakursa.prakriti.databinding.StepBinding
import com.thakursa.prakriti.helper.GeneralHelper
import com.thakursa.prakriti.service.StepDetectorService

class stepmain : AppCompatActivity(), stepsCallback {
    private lateinit var binding: StepBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = StepBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this, StepDetectorService::class.java)
        startService(intent)

        StepDetectorService.subscribe.register(this)
    }

    override fun subscribeSteps(steps: Int) {
        binding.TVSTEPS.text = steps.toString()
        binding.TVCALORIES.text = GeneralHelper.getCalories(steps)
        binding.TVDISTANCE.text = GeneralHelper.getDistanceCovered(steps)
    }
}

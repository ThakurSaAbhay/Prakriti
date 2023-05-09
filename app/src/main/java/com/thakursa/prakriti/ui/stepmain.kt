package com.thakursa.prakriti

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thakursa.prakriti.callback.stepsCallback
import com.thakursa.prakriti.databinding.StepBinding
import com.thakursa.prakriti.helper.GeneralHelper
import com.thakursa.prakriti.service.StepDetectorService

class stepmain : AppCompatActivity(), stepsCallback {
    private lateinit var binding: StepBinding
    private val sharedPrefFile = "kotlinsharedpreference"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = StepBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this, StepDetectorService::class.java)
        startService(intent)
        StepDetectorService.subscribe.register(this)



    }

    override fun subscribeSteps(steps: Int) {
        val sharedPreferences: SharedPreferences=this.getSharedPreferences("steps",0)
        val editor:SharedPreferences.Editor=sharedPreferences.edit()
        editor.putInt("steps",steps)
        editor.apply()
        editor.commit()
        binding.TVSTEPS.text = steps.toString()
        binding.TVCALORIES.text = GeneralHelper.getCalories(steps)
        binding.TVDISTANCE.text = GeneralHelper.getDistanceCovered(steps)
    }
}

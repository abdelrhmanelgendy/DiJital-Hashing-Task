package com.tasks.dijitalgarajihashingtask.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import com.emmanuel.dijitalgaraj.quiz.SearchHash
import com.tasks.dijitalgarajihashingtask.R
import com.tasks.dijitalgarajihashingtask.databinding.ActivityHashSolvingBinding
import com.tasks.dijitalgarajihashingtask.view.HashRetrievingActivity.Companion.EMAIL
import com.tasks.dijitalgarajihashingtask.view.HashRetrievingActivity.Companion.HASHED_VALUE
import kotlinx.coroutines.*

class HashSolvingActivity : AppCompatActivity() {
    private lateinit var activityBinding: ActivityHashSolvingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityHashSolvingBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)

        val email = intent?.getStringExtra(EMAIL)
        val hashedValue = intent?.getStringExtra(HASHED_VALUE)


        CoroutineScope(Dispatchers.Default)
            .launch {
                val decodedEmail = SearchHash()(email = email!!, hashedValue = hashedValue!!)
                Log.d("TAG", "onCreate: ${email} ${hashedValue}\n" + decodedEmail)
                withContext(Dispatchers.Main)
                {
                    activityBinding.layoutLoadingData.root.isVisible = false
                    activityBinding.txtSolvedEmail.isVisible = true
                    activityBinding.txtSolvedEmail.text = "${
                        resources.getString(R.string.cracked_email)
                    }\n${decodedEmail}"
                }
            }
    }
}
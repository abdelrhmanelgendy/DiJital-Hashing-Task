package com.tasks.dijitalgarajihashingtask.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.emmanuel.dijitalgaraj.quiz.SearchHashUseCase
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

        crackingTheEmail(email, hashedValue)


        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);

    }


//solving the hashed email
    @SuppressLint("SetTextI18n")
    private fun crackingTheEmail(email: String?, hashedValue: String?) {
        CoroutineScope(Dispatchers.Default)
            .launch {
                val decodedEmail = SearchHashUseCase()(email = email!!, hashedValue = hashedValue!!)
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

    //navigation up overriding
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
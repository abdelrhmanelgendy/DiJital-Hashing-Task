package com.tasks.dijitalgarajihashingtask.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.info.tasks.domain.model.HashDataRequest
import com.tasks.dijitalgarajihashingtask.HashedValuesViewModel
import com.tasks.dijitalgarajihashingtask.R
import com.tasks.dijitalgarajihashingtask.databinding.ActivityHashRetrievingBinding
import com.tasks.domain.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class HashRetrievingActivity : AppCompatActivity() {

    companion object
    {
        val EMAIL ="email"
        val HASHED_VALUE ="hashed_value"
    }
    lateinit var activityBinding: ActivityHashRetrievingBinding
    private lateinit var email: String
    private lateinit var hashedValue: String


    val hashedValuesViewModel: HashedValuesViewModel by lazy {
        ViewModelProvider(this).get(HashedValuesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityBinding = ActivityHashRetrievingBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)

        activityBinding.btnGetHashValue.setOnClickListener {
            getTheHashEmail()

        }
        activityBinding.btnOpenHashSolvingActivity.setOnClickListener {
            val hashSolvingIntent = Intent(this,HashSolvingActivity::class.java)
            hashSolvingIntent.putExtra(EMAIL,email)
            hashSolvingIntent.putExtra(HASHED_VALUE,hashedValue)
            startActivity(hashSolvingIntent)
        }
    }

    private fun getTheHashEmail() {
        changeButtonVisibility(false)
        changePreogressBarVisibility(true)
        hashedValuesViewModel.getHashedEmail(
            HashDataRequest(getStringResourceById(R.string.guid)),
            slug = getStringResourceById(R.string.slug)
        )

        lifecycleScope.launchWhenStarted {
            hashedValuesViewModel.hashValueStateFlow.collect {

                when (it) {
                    is Resource.Idle -> {
                    }
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        makeALog("this is Success ${it.data.toString()}")
                        changePreogressBarVisibility(false)
                        email = it.data?.email!!
                        hashedValue = it.data?.hash!!
                        activityBinding.txtHashResult.setText("${getStringResourceById(R.string.hash_place_holder)}\n${it.data?.hash}")
                        activityBinding.txtEmailResult.setText("${getStringResourceById(R.string.email_place_holder)} ${it.data?.email}")
                        activityBinding.btnOpenHashSolvingActivity.isVisible = true
                    }
                    is Resource.Error -> {

                    }
                }

            }
        }
    }

    private fun getStringResourceById(resourceId: Int): String {
        return baseContext.getString(resourceId)
    }

    fun makeALog(logMsg: String) {
        Log.d("TAG121", "makeALog: " + logMsg)
    }

    private fun changeButtonVisibility(isVisible: Boolean) {
        activityBinding.btnGetHashValue.isVisible = isVisible
    }

    private fun changePreogressBarVisibility(isVisible: Boolean) {
        activityBinding.progressLoading.isVisible = isVisible
    }

}
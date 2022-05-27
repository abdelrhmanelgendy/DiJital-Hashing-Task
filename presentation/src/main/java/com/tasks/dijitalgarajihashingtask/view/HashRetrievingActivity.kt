package com.tasks.dijitalgarajihashingtask.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
    lateinit var activityBinding: ActivityHashRetrievingBinding
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
                        activityBinding.txtHashResult.setText("${getStringResourceById(R.string.hash_place_holder)}\n${it.data?.hash}")
                        activityBinding.txtEmailResult.setText("${getStringResourceById(R.string.email_place_holder)} ${it.data?.email}")


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

    fun changeButtonVisibility(isVisible: Boolean) {
        activityBinding.btnGetHashValue.isVisible = isVisible
    }

    fun changePreogressBarVisibility(isVisible: Boolean) {
        activityBinding.progressLoading.isVisible = isVisible
    }

}
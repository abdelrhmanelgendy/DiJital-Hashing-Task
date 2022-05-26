package com.tasks.dijitalgarajihashingtask.view

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.info.tasks.domain.model.HashDataRequest
import com.tasks.dijitalgarajihashingtask.HashedValuesViewModel
import com.tasks.dijitalgarajihashingtask.databinding.ActivityHashRetrieverBinding
import com.tasks.domain.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class HashRetrieverActivity : AppCompatActivity() {
    private   val TAG = "HashRetrieverActivity"
    lateinit var activityBinding: ActivityHashRetrieverBinding
    val hashedValuesViewModel: HashedValuesViewModel by lazy {
        ViewModelProvider(this).get(HashedValuesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        //creating the view of the activity by activity binding instead of the normal XML Binding
        activityBinding = ActivityHashRetrieverBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)

        activityBinding.btnGetHashValue.setOnClickListener {
            getTheHashEmail()
        }


    }

    private fun getTheHashEmail() {
        hashedValuesViewModel.getHashedEmail(
            HashDataRequest("80e2c70f-996b-445d-95f5-2f1822145397"),
            slug = "emmanuel-nwokoma-40866"
        )

        lifecycleScope.launchWhenStarted {
            hashedValuesViewModel.hashValueStateFlow.collect {

                when (it)
                {
                    is  Resource.Idle->{
                        makeALog("this is Idel")
                    }
                    is  Resource.Loading->{
                        makeALog("this is Loading")

                    }
                    is  Resource.Success->{
                        makeALog("this is Success ${it.data.toString()}")

                    }
                    is  Resource.Error->{
                        makeALog("this is Error")

                    }
                }

            }
        }
    }
    fun makeALog(logMsg:String)
    {
        Log.d(TAG, "makeALog: "+logMsg)
    }
}
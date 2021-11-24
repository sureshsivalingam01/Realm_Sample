package com.mrright.realm_example.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.mrright.realm_example.R
import com.mrright.realm_example.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var bind: ActivityMainBinding

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        collectors()

        bind.btnSave.setOnClickListener {
            viewModel.insert(
                bind.inputName.editText?.text.toString(),
                bind.inputStd.editText?.text.toString(),
                bind.inputAddress.editText?.text.toString(),
            )
        }


    }

    private fun collectors() {

        lifecycleScope.launchWhenStarted {
            viewModel.state.collect {
                when (it) {
                    is State.Toast -> Toast.makeText(this@MainActivity, it.msg, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        viewModel.nameErr.observe(this) {
            bind.inputName.error = it
            bind.inputName.isErrorEnabled = it != null
        }

        viewModel.stdErr.observe(this){
            bind.inputStd.error = it
            bind.inputStd.isErrorEnabled = it != null
        }

        viewModel.addressErr.observe(this){
            bind.inputAddress.error = it
            bind.inputAddress.isErrorEnabled = it != null
        }

    }


}
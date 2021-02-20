package com.example.binlist.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.binlist.R
import com.example.binlist.databinding.ActivityMainBinding
import com.example.binlist.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.cardInputEditText.doOnTextChanged { text, start, before, count ->

            if (text?.length!! < 6) {
                binding.cardNumberTextInputLayout.error = resources.getString(R.string.error_string)

            } else {

                binding.cardNumberTextInputLayout.isErrorEnabled = false

                val testNumber = 45717360
                viewModel.getBin(testNumber).observe(this, Observer {
                    when (it.status) {
                        Resource.Status.SUCCESS -> {
                            /*bindCharacter(it.data!!)
                            binding.progressBar.visibility = View.GONE
                            binding.characterCl.visibility = View.VISIBLE*/
                            Timber.d("Response successful")
                            Timber.d(it.data?.first()?.responseResource?.type)
                        }

                        Resource.Status.ERROR ->
                            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()

                        Resource.Status.LOADING -> {
                            /*binding.progressBar.visibility = View.VISIBLE
                            binding.characterCl.visibility = View.GONE*/
                            Timber.d("Currently loading")
                        }
                    }
                })

            }

        }

    }


}
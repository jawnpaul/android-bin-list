package com.example.binlist.ui

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.binlist.R
import com.example.binlist.data.entities.BinModel
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

            if (isOnline(this)) {

                if (text?.length!! < 6) {
                    binding.cardNumberTextInputLayout.error = resources.getString(R.string.error_string)
                    //Hide card
                } else {
                    //Show card
                    viewModel.deleteBins()
                    binding.cardNumberTextInputLayout.isErrorEnabled = false

                    val testNumber = 4
                    viewModel.getBin(text.toString().toInt()).observe(this, Observer {
                        when (it.status) {
                            Resource.Status.SUCCESS -> {
                                Timber.d("Response successful")
                                if (it.data?.isNotEmpty() == true) {
                                    Timber.d("Database is not empty")
                                    Timber.d(it.data.size.toString())
                                    //I use last here to the most recent element in the database. This is because of when user clears the edit text
                                    //and input becomes less than 6
                                    setValues(it.data.last())
                                } else {
                                    Timber.d("Database is empty")
                                }
                            }

                            Resource.Status.ERROR -> {
                                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                            }

                            Resource.Status.LOADING -> {
                                Timber.d("Currently loading")

                            }
                        }
                    })
                }

            } else {

                Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show()
            }


        }

    }

    private fun setValues(binModel: BinModel) {

        if (!binModel.responseResource.scheme.isNullOrEmpty()) {
            binding.cardSchemeTextView.text = binModel.responseResource.scheme
        }

        if (!binModel.responseResource.type.isNullOrEmpty()) {
            binding.cardTypeTextView.text = binModel.responseResource.type
        }

        if (!binModel.responseResource.bank?.name.isNullOrEmpty()) {
            binding.bankTextView.text = binModel.responseResource.bank?.name
        }

        if (!binModel.responseResource.country?.name.isNullOrEmpty()) {
            binding.countryTextView.text = binModel.responseResource.country?.name
        }

        //This is a special scenario where we have length but is equal to "null"
        if (binModel.responseResource.number?.length.toString().isNotEmpty() && binModel.responseResource.number?.length.toString() != "null") {
            binding.cardLengthTextView.text = binModel.responseResource.number?.length.toString()
        }

        when {
            binModel.responseResource.prepaid?.equals(false) == true -> {
                binding.prepaidTextView.text = resources.getString(R.string.no)
            }
            binModel.responseResource.prepaid?.equals(false) == false -> {
                binding.prepaidTextView.text = resources.getString(R.string.yes)
            }
            else -> {
                binding.prepaidTextView.text = resources.getString(R.string.unavailable)
            }
        }


    }

    private fun isOnline(context: Context): Boolean {
        val cm =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null &&
                cm.activeNetworkInfo!!.isConnectedOrConnecting
    }
}
package com.keneitec.theylonf.organizzeclone.view.signin

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.keneitec.theylonf.organizzeclone.databinding.ActivitySignInBinding
import com.keneitec.theylonf.organizzeclone.model.User
import com.keneitec.theylonf.organizzeclone.util.forceHideKeyboard
import com.keneitec.theylonf.organizzeclone.util.showSnackBar

class SignInActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySignInBinding.inflate(layoutInflater) }
    private val viewModel: SignInViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getCallbackMsg()
        loginAccountIsSuccesful()
        uiAnimation()
        setupButtons()
    }

    private fun setupButtons() {
        binding.btnSignIn.setOnClickListener {
            val email = binding.editEmailSignIn.editText?.text.toString()
            val password = binding.editPasswordSignIn.editText?.text.toString()

            validateFields(email, password)
        }
    }

    private fun uiAnimation() {
        viewModel.loading.observe(this) {
            if (it) {
                binding.SignInProgressBar.visibility = View.VISIBLE
                binding.btnSignIn.isEnabled = false
            } else {
                binding.SignInProgressBar.visibility = View.GONE
                binding.btnSignIn.isEnabled = true
            }
        }
    }

    private fun getCallbackMsg() {
        viewModel.msg.observe(this) {
            if (it != null) {
                binding.root.showSnackBar(it, binding.SignInContainer.id)
            }
        }
    }

    private fun loginAccountIsSuccesful() {
        viewModel.firebaseUserResult.observe(this) { result ->
            when (result) {
                is com.keneitec.theylonf.organizzeclone.data.ResultFirebase.Success -> finish()
                else -> {}
            }
        }
    }

    private fun validateFields(email: String, password: String) {
        if (email.isNotBlank()) {
            if (password.isNotBlank()) {
                val user = User(email = email, password = password)
                viewModel.signInUser(user)
            } else binding.editPasswordSignIn.error = "Campo obrigatorio"
        } else binding.editEmailSignIn.error = "Campo obrigatorio"
        binding.root.forceHideKeyboard()
    }
}

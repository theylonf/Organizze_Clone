package com.keneitec.theylonf.organizzeclone.view.signup

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.keneitec.theylonf.organizzeclone.data.ResultFirebase
import com.keneitec.theylonf.organizzeclone.data.remote.FirebaseAuthSource
import com.keneitec.theylonf.organizzeclone.data.remote.FirebaseDatabaseSource
import com.keneitec.theylonf.organizzeclone.databinding.ActivitySignUpBinding
import com.keneitec.theylonf.organizzeclone.helper.Base64Custom
import com.keneitec.theylonf.organizzeclone.model.User
import com.keneitec.theylonf.organizzeclone.util.showSnackBar

class SignUpActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }
    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        FirebaseAuthSource.getFirebaseAuth()
        FirebaseDatabaseSource.getFirebaseDatabase()

        getCallbackMsg()
        createAccountIsSuccessful()
        uiAnimation()
        setupButtons()
    }

    private fun setupButtons() {
        binding.btnSignUp.setOnClickListener {
            val name = binding.editName.editText?.text.toString()
            val email = binding.editEmail.editText?.text.toString()
            val password = binding.editPassword.editText?.text.toString()

            validateFields(name, email, password)
        }
    }

    private fun uiAnimation() {
        viewModel.loading.observe(this) {
            if (it) {
                binding.createAccountProgressBar.visibility = View.VISIBLE
                binding.btnSignUp.isEnabled = false
            } else {
                binding.createAccountProgressBar.visibility = View.GONE
                binding.btnSignUp.isEnabled = true
            }
        }
    }

    private fun getCallbackMsg() {
        viewModel.msg.observe(this) {
            if (it != null) {
                binding.root.showSnackBar(it, binding.signUpContainer.id)
            }
        }
    }

    private fun createAccountIsSuccessful() {
        viewModel.user.observe(this) { result ->
            when (result) {
                is ResultFirebase.Success -> {
                    finish()
                }
                else -> {}
            }
        }
    }

    private fun validateFields(name: String, email: String, password: String) {
        if (name.isNotBlank()) {
            if (email.isNotBlank()) {
                if (password.isNotBlank()) {
                    val user = User(
                        idUser = Base64Custom.codifyBase64(email),
                        name = name,
                        email = email,
                        password = password
                    )
                    createUser(user)
                } else binding.editPassword.error = "Campo obrigatorio"
            } else binding.editEmail.error = "Campo obrigatorio"
        } else binding.editName.error = "Campo obrigatorio"
    }

    private fun createUser(user: User) {
        viewModel.createAccount(user)
    }
}

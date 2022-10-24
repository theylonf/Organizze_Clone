package com.keneitec.theylonf.organizzeclone.view.intro

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.heinrichreimersoftware.materialintro.app.IntroActivity
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide
import com.keneitec.theylonf.organizzeclone.R
import com.keneitec.theylonf.organizzeclone.view.main.MainActivity
import com.keneitec.theylonf.organizzeclone.view.signin.SignInActivity
import com.keneitec.theylonf.organizzeclone.view.signup.SignUpActivity

class IntroActivity : IntroActivity() {

    val viewModel: IntroViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        isButtonBackVisible = false
        isButtonNextVisible = false

        addSlide(
            FragmentSlide.Builder()
                .fragment(R.layout.intro_1)
                .background(android.R.color.holo_orange_light)
                .build()
        )

        addSlide(
            FragmentSlide.Builder()
                .fragment(R.layout.intro_2)
                .background(android.R.color.holo_orange_light)
                .build()
        )
        addSlide(
            FragmentSlide.Builder()
                .fragment(R.layout.intro_3)
                .background(android.R.color.holo_orange_light)
                .build()
        )
        addSlide(
            FragmentSlide.Builder()
                .fragment(R.layout.intro_4)
                .background(android.R.color.holo_orange_light)
                .build()
        )
        addSlide(
            FragmentSlide.Builder()
                .fragment(R.layout.intro_cadastro)
                .background(android.R.color.holo_orange_light)
                .canGoForward(false)
                .build()
        )

//        addSlide(
//            SimpleSlide.Builder()
//                .title("Titulo")
//                .description("Descrição")
//                .image(R.drawable.um)
//                .background(android.R.color.holo_orange_light)
//                .build()
//        )
//
//        addSlide(
//            SimpleSlide.Builder()
//                .title("Titulo 2")
//                .description("Descrição 2")
//                .image(R.drawable.dois)
//                .background(android.R.color.holo_orange_light)
//                .build()
//        )
//
//        addSlide(
//            SimpleSlide.Builder()
//                .title("Titulo 3")
//                .description("Descrição 3")
//                .image(R.drawable.tres)
//                .background(android.R.color.holo_orange_light)
//                .build()
//        )
//        addSlide(
//            SimpleSlide.Builder()
//                .title("Titulo 4")
//                .description("Descrição 4")
//                .image(R.drawable.quatro)
//                .background(android.R.color.holo_orange_light)
//                .build()
//        )
    }

    private fun verifyIfUserIsLogged() {
        val user = viewModel.getCurrentUser()
        if (user != null) {
            openMainActivity()
        }
    }

    override fun onStart() {
        super.onStart()
        verifyIfUserIsLogged()
    }

    fun bntSignUp(view: View) {
        startActivity(Intent(applicationContext, SignUpActivity::class.java))
    }

    fun bntSignIn(view: View) {
        startActivity(Intent(applicationContext, SignInActivity::class.java))
    }

    private fun openMainActivity() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
        finish()
    }
}

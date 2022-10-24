package com.keneitec.theylonf.organizzeclone.view.receitas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.keneitec.theylonf.organizzeclone.databinding.ActivityReceitasBinding

class ReceitasActivity : AppCompatActivity() {
    private val binding: ActivityReceitasBinding by lazy { ActivityReceitasBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}

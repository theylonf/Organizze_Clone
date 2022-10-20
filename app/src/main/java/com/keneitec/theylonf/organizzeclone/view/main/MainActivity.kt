package com.keneitec.theylonf.organizzeclone.view.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.snackbar.Snackbar
import com.keneitec.theylonf.organizzeclone.R
import com.keneitec.theylonf.organizzeclone.databinding.ActivityMainBinding
import com.keneitec.theylonf.organizzeclone.view.despesas.DespesasActivity
import com.keneitec.theylonf.organizzeclone.view.receitas.ReceitasActivity

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupButtons()

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_principal)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fabMenu.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    private fun setupButtons() {
        binding.fabDespesa.setOnClickListener {
            startActivity(Intent(applicationContext, DespesasActivity::class.java))
        }

        binding.fabReceita.setOnClickListener {
            startActivity(Intent(applicationContext, ReceitasActivity::class.java))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_principal)
        return navController.navigateUp(appBarConfiguration) ||
            super.onSupportNavigateUp()
    }
}

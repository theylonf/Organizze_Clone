package com.keneitec.theylonf.organizzeclone.di

import com.keneitec.theylonf.organizzeclone.data.repository.FirebaseAuthRepository
import com.keneitec.theylonf.organizzeclone.view.intro.IntroViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    /* single ira durar durante toodo o ciclo de vida da aplicação,
    * enquanto o factory só ira ser instanciado uma unica vez
    * quando for chamado atravez do comando get()
    * */

    single { // Ira persistir durante toda a aplicação
        FirebaseAuthRepository()
    }

    viewModel {
        IntroViewModel()
    }
}

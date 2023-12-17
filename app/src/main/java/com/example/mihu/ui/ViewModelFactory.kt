package com.example.mihu.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mihu.data.local.repository.MihuRepository
import com.example.mihu.data.local.repository.WorkerRepository
import com.example.mihu.di.Injection
import com.example.mihu.ui.home.HomeViewModel
import com.example.mihu.ui.login.LoginViewModel
import com.example.mihu.ui.profile.ProfileViewModel
import com.example.mihu.ui.register.RegisterViewModel
import com.example.mihu.ui.worker.login.LoginWorkerViewModel
import com.example.mihu.ui.worker.register.RegisterWorkerViewModel

class ViewModelFactory(
    private val mihuRepository: MihuRepository,
    private val workerRepository: WorkerRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(mihuRepository) as T
        } else if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(mihuRepository) as T
        } else if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(mihuRepository) as T
        } else if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(mihuRepository) as T
        } else if (modelClass.isAssignableFrom(RegisterWorkerViewModel::class.java)) {
            return RegisterWorkerViewModel(workerRepository) as T
        } else if (modelClass.isAssignableFrom(LoginWorkerViewModel::class.java)) {
            return LoginWorkerViewModel(workerRepository) as T
        }
        throw UnsupportedOperationException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    Injection.providedMihuRepository(context),
                    Injection.providedWorkerRepository(context)
                )
            }.also { instance = it }
    }
}

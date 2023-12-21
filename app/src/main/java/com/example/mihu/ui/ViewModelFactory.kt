package com.example.mihu.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mihu.data.local.pref.UserPreferences
import com.example.mihu.data.local.pref.dataStore
import com.example.mihu.data.local.repository.MihuRepository
import com.example.mihu.data.local.repository.WorkerRepository
import com.example.mihu.di.Injection
import com.example.mihu.ui.history.HistoryViewModel
import com.example.mihu.ui.home.HomeViewModel
import com.example.mihu.ui.login.LoginViewModel
import com.example.mihu.ui.order.OrderViewModel
import com.example.mihu.ui.profile.ProfileViewModel
import com.example.mihu.ui.register.RegisterViewModel
import com.example.mihu.ui.splash.SplashViewModel
import com.example.mihu.ui.worker.detail.DetailJobWorkerViewModel
import com.example.mihu.ui.worker.history.HistoryWorkerViewModel
import com.example.mihu.ui.worker.login.LoginWorkerViewModel
import com.example.mihu.ui.worker.register.RegisterWorkerViewModel
import com.example.mihu.ui.worker.task.TaskViewModel

class ViewModelFactory(
    private val mihuRepository: MihuRepository,
    private val workerRepository: WorkerRepository,
    private val userPreferences: UserPreferences
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(mihuRepository) as T
        } else if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(mihuRepository) as T
        } else if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
            return SplashViewModel(mihuRepository) as T
        } else if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(mihuRepository, userPreferences) as T
        } else if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(mihuRepository, userPreferences) as T
        } else if (modelClass.isAssignableFrom(RegisterWorkerViewModel::class.java)) {
            return RegisterWorkerViewModel(workerRepository) as T
        } else if (modelClass.isAssignableFrom(LoginWorkerViewModel::class.java)) {
            return LoginWorkerViewModel(workerRepository) as T
        } else if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            return TaskViewModel(workerRepository, userPreferences) as T
        } else if (modelClass.isAssignableFrom(DetailJobWorkerViewModel::class.java)) {
            return DetailJobWorkerViewModel(workerRepository, userPreferences) as T
        } else if (modelClass.isAssignableFrom(HistoryWorkerViewModel::class.java)) {
            return HistoryWorkerViewModel(workerRepository, userPreferences) as T
        } else if (modelClass.isAssignableFrom(OrderViewModel::class.java)) {
            return OrderViewModel(mihuRepository, userPreferences) as T
        } else if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            return HistoryViewModel(mihuRepository, userPreferences) as T
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
                    Injection.providedWorkerRepository(context),
                    UserPreferences.getInstance(context.dataStore)
                )
            }.also { instance = it }
    }
}

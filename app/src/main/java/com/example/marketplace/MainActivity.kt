package com.example.marketplace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation3.runtime.rememberNavBackStack
import com.example.marketplace.di.LoginViewModelFactory
import com.example.marketplace.presentation.ObserveAsEvents
import com.example.marketplace.presentation.SnackbarController
import com.example.marketplace.presentation.navigation.LoginScreen
import com.example.marketplace.presentation.navigation.NavigationRoot
import com.example.marketplace.presentation.viewmodels.loginmvi.LoginViewModel
import com.example.marketplace.ui.theme.MarketplaceTheme
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val snackbarHostState = remember {
                SnackbarHostState()
            }
            MarketplaceTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = {
                        SnackbarHost(snackbarHostState)
                    }
                ) { innerPadding ->
                    ObserveAsEvents(SnackbarController.events) { event ->
                        snackbarHostState.currentSnackbarData?.dismiss()

                        val result = snackbarHostState.showSnackbar(
                            message = event.message,
                            actionLabel = event.action?.name,
                            duration = SnackbarDuration.Long
                        )
                        if(result == SnackbarResult.ActionPerformed) {
                            event.action?.action?.invoke()
                        }
                    }

                    NavigationRoot(
                        modifier = Modifier.fillMaxSize().padding(innerPadding)
                    )
                }
            }
        }
    }
}
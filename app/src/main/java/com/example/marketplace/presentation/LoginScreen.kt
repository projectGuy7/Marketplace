package com.example.marketplace.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marketplace.presentation.viewmodels.basemvipattern.ViewIntent
import com.example.marketplace.presentation.viewmodels.loginmvi.LoginIntent
import com.example.marketplace.presentation.viewmodels.loginmvi.LoginState

@Composable
fun LogIn(
    modifier: Modifier = Modifier,
    state: LoginState,
    onEvent: (LoginIntent) -> Unit
) {
    val defaultModifier = Modifier.padding(10.dp)
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            fontSize = 30.sp,
            fontWeight = FontWeight.Light,
            modifier = defaultModifier,
            text = "Log in"
        )
        TextField(
            value = state.loginField,
            onValueChange = { newValue: String -> onEvent(LoginIntent.TypeInLoginField(newValue))},
            placeholder = { Text("Type in your login") },
            singleLine = true,
            modifier = defaultModifier
        )
        TextField(
            value = state.emailField,
            onValueChange = { newValue: String -> onEvent(LoginIntent.TypeInEmailField(newValue))},
            placeholder = { Text("Type in your email address") },
            singleLine = true,
            modifier = defaultModifier
        )
        TextField(
            value = state.passwordField,
            onValueChange = { newValue: String -> onEvent(LoginIntent.TypeInPasswordField(newValue))},
            placeholder = { Text("Type in your password") },
            singleLine = true,
            modifier = defaultModifier
        )
        Box(
            contentAlignment = Alignment.Center
        ) {
            Button(
                modifier = defaultModifier,
                onClick = {
                    onEvent(LoginIntent.SendCredentials)
                },
                content = {
                    Box(
                        modifier = Modifier.width(80.dp).height(40.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        if(state.loading) {
                            Loading(
                                circleSize = 10.dp,
                                travelDistance = 5.dp,
                                spaceBetween = 2.dp,
                                circleColor = MaterialTheme.colorScheme.onPrimary
                            )
                        } else {
                            Text(
                                "Confirm",
                                fontSize = 18.sp
                            )
                        }
                    }
                },
                shape = RoundedCornerShape(15.dp)
            )
        }
    }
}

@Preview(
    heightDp = 732,
    widthDp = 412,
    showBackground = true
)
@Composable
fun LogInPreview() {
    LogIn(
        Modifier,
        LoginState(),
        {}
    )
}
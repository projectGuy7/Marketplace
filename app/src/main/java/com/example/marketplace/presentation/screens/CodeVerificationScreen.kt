package com.example.marketplace.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marketplace.presentation.viewmodels.loginmvi.LoginIntent
import com.example.marketplace.presentation.viewmodels.loginmvi.LoginState

@Composable
fun CodeVerification(
    modifier: Modifier = Modifier,
    state: LoginState,
    onEvent: (LoginIntent) -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            fontSize = 30.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier.padding(10.dp),
            text = "Code verification"
        )
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(top = 10.dp),
                text = "We've sent you the confirmation code\non your email"
            )
            TextField(
                value = state.verificationCode,
                onValueChange = { newValue: String -> onEvent(LoginIntent.UpdateCodeVerificationField(newValue))},
                placeholder = { Text("Type in your verification code") },
                singleLine = true,
                modifier = Modifier.padding(start = 0.dp, top = 10.dp, end = 10.dp, bottom = 10.dp)
            )
        }
        Button(
            modifier = Modifier.padding(10.dp),
            onClick = {
                onEvent(LoginIntent.SendVerificationCode)
            },
            content = {
                Box(
                    modifier = Modifier.width(80.dp).height(40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Confirm",
                        fontSize = 18.sp
                    )
                }
            },
            shape = RoundedCornerShape(15.dp)
        )
    }
}


@Preview(
    heightDp = 732,
    widthDp = 412,
    showBackground = true
)
@Composable
fun CodeVerificationPreview() {
    CodeVerification(
        Modifier,
        LoginState(),
        {}
    )
}
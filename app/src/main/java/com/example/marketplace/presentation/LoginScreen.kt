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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LogIn(
    onLogInPressed: (String, String) -> Unit
) {
    var loginValue: String by rememberSaveable { mutableStateOf("") }
    var passwordValue : String by rememberSaveable { mutableStateOf("") }

    val defaultModifier = Modifier.padding(10.dp)
    Column(
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
            value = loginValue,
            onValueChange = { newValue: String -> loginValue = newValue },
            placeholder = { Text("Type in your login") },
            singleLine = true,
            modifier = defaultModifier
        )
        TextField(
            value = passwordValue,
            onValueChange = { newValue: String -> passwordValue = newValue },
            placeholder = { Text("Type in your password") },
            singleLine = true,
            modifier = defaultModifier
        )
        Button(
            modifier = defaultModifier,
            onClick = {
                onLogInPressed(loginValue, passwordValue)
            },
            content =
                {
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
fun LogInPreview() {
    LogIn({a, b -> })
}
package com.example.iphonecalculator

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.example.iphonecalculator.common.ActionButton
import com.example.iphonecalculator.common.Button
import com.example.iphonecalculator.common.Button2
import com.example.iphonecalculator.ui.theme.DarkGray
import com.example.iphonecalculator.ui.theme.LightGray
import com.example.iphonecalculator.ui.theme.Yellow
import kotlin.math.roundToLong

@Composable
fun CalculatorScreen() {
    val darkTheme: Boolean = isSystemInDarkTheme()
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color.Black.toArgb()
            window.navigationBarColor = Color.Black.toArgb()
            if (darkTheme) {
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
                    !darkTheme
                WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars =
                    !darkTheme
            } else {
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
                    darkTheme
                WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars =
                    darkTheme
            }
        }
    }

    var strNumber by remember {
        mutableStateOf("0")
    }
    var isActionActive by remember {
        mutableStateOf(false)
    }
    var result by remember {
        mutableStateOf(0.0)
    }
    var operation by remember {
        mutableStateOf("")
    }

    fun updateTitleLength(newTitle : String): String{
        return if (newTitle.length < 9)
            strNumber
        else
            strNumber.slice(listOf(0,1,2,3,4,5,6,7,8))
    }
    var textColor = Color.White
    var backgroundColor = Yellow

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)
        .statusBarsPadding()) {
        Box(modifier = Modifier.weight(3f))
        Text(text = updateTitleLength(strNumber),
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f),
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.displaySmall,
            color = Color.White,
            fontSize = 80.sp,
            maxLines = 1
            )
        Column(modifier = Modifier.weight(8f),
            verticalArrangement = Arrangement.SpaceAround) {
            Row(horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()) {
                Button(text = if (strNumber == "0") "AC" else "C",
                    backgroundColor = LightGray,
                    textColor = Color.Black,
                    onClick = {
                        if (strNumber != "0"){
                            if (!isActionActive) {
                                result = strNumber.toDouble()
                                isActionActive = true
                                operation = "cancel"
                            }
                            strNumber = "0"
                        } else {
                            isActionActive = false
                        }
                    })
                Button(text = "+/-",
                    backgroundColor = LightGray,
                    textColor = Color.Black,
                    onClick = {
                        result = strNumber.toDouble()
                        strNumber = (result * -1).toString()
                        strNumber = if (strNumber.endsWith(".0")) strNumber.substring(startIndex = 0, endIndex = strNumber.length-2) else strNumber
                    })
                Button(text = "%",
                    backgroundColor = LightGray,
                    textColor = Color.Black,
                    onClick = {
                        result = strNumber.toDouble()
                        strNumber = ((result / 1000f)*10).toString()
                        strNumber = if (strNumber.endsWith(".0")) strNumber.substring(startIndex = 0, endIndex = strNumber.length-2) else strNumber
                    })
                ActionButton(text = "÷",
                    textColor = if (operation == "divide") Yellow else textColor,
                    backgroundColor = if (operation == "divide") Color.White else backgroundColor,
                    onClick = {
                        if (!isActionActive) {
                            result = strNumber.toDouble()
                            isActionActive = true
                            operation = "divide"
                        }
                    })
            }
            Row(horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()) {
                Button(text = "7",
                    backgroundColor = DarkGray,
                    textColor = Color.White,
                    onClick = {
                        if (strNumber == "0"){
                            strNumber = "7"
                        } else {
                            if (isActionActive){
                                strNumber = "7"
                                isActionActive = false
                            } else {
                                strNumber += "7"
                            }
                        }
                    })
                Button(text = "8",
                    backgroundColor = DarkGray,
                    textColor = Color.White,
                    onClick = {
                        if (strNumber == "0"){
                            strNumber = "8"
                        } else {
                            if (isActionActive){
                                strNumber = "8"
                                isActionActive = false
                            } else {
                                strNumber += "8"
                            }
                        }
                    })
                Button(text = "9",
                    backgroundColor = DarkGray,
                    textColor = Color.White,
                    onClick = {
                        if (strNumber == "0"){
                            strNumber = "9"
                        } else {
                            if (isActionActive){
                                strNumber = "9"
                                isActionActive = false
                            } else {
                                strNumber += "9"
                            }
                        }
                    })
                ActionButton(text = "x",
                    textColor = if (operation == "multiply") Yellow else textColor,
                    backgroundColor = if (operation == "multiply") Color.White else backgroundColor,
                    onClick = {
                        if (!isActionActive) {
                            result = strNumber.toDouble()
                            isActionActive = true
                            operation = "multiply"
                        }
                    })
            }
            Row(horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()) {
                Button(text = "4",
                    backgroundColor = DarkGray,
                    textColor = Color.White,
                    onClick = {
                        if (strNumber == "0"){
                            strNumber = "4"
                        } else {
                            if (isActionActive){
                                strNumber = "4"
                                isActionActive = false
                            } else {
                                strNumber += "4"
                            }
                        }
                    })
                Button(text = "5",
                    backgroundColor = DarkGray,
                    textColor = Color.White,
                    onClick = {
                        if (strNumber == "0"){
                            strNumber = "5"
                        } else {
                            if (isActionActive){
                                strNumber = "5"
                                isActionActive = false
                            } else {
                                strNumber += "5"
                            }
                        }
                    })
                Button(text = "6",
                    backgroundColor = DarkGray,
                    textColor = Color.White,
                    onClick = {
                        if (strNumber == "0"){
                            strNumber = "6"
                        } else {
                            if (isActionActive){
                                strNumber = "6"
                                isActionActive = false
                            } else {
                                strNumber += "6"
                            }
                        }
                    })
                ActionButton(text = "-",
                    textColor = if (operation == "subtract") Yellow else textColor,
                    backgroundColor = if (operation == "subtract") Color.White else backgroundColor,
                    onClick = {
                        if (!isActionActive) {
                            result = strNumber.toDouble()
                            isActionActive = true
                            operation = "subtract"
                        }
                    })
            }
            Row(horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()) {
                Button(text = "1",
                    backgroundColor = DarkGray,
                    textColor = Color.White,
                    onClick = {
                        if (strNumber == "0"){
                            strNumber = "1"
                        } else {
                            if (isActionActive){
                                strNumber = "1"
                                isActionActive = false
                            } else {
                                strNumber += "1"
                            }
                        }
                    })
                Button(text = "2",
                    backgroundColor = DarkGray,
                    textColor = Color.White,
                    onClick = {
                        if (strNumber == "0"){
                            strNumber = "2"
                        } else {
                            if (isActionActive){
                                strNumber = "2"
                                isActionActive = false
                            } else {
                                strNumber += "2"
                            }
                        }
                    })
                Button(text = "3",
                    backgroundColor = DarkGray,
                    textColor = Color.White,
                    onClick = {
                        if (strNumber == "0"){
                            strNumber = "3"
                        } else {
                            if (isActionActive){
                                strNumber = "3"
                                isActionActive = false
                            } else {
                                strNumber += "3"
                            }
                        }
                    })
                ActionButton(text = "+",
                    textColor = if (operation == "add") Yellow else textColor,
                    backgroundColor = if (operation == "add") Color.White else backgroundColor,
                ) {
                    if (!isActionActive) {
                        result = strNumber.toDouble()
                        isActionActive = true
                        operation = "add"
                    }
                }
            }
            Row(horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()) {
                Button2(text = "0",
                    backgroundColor = DarkGray,
                    textColor = Color.White,
                    onClick = {
                        if (strNumber == "0"){
                            strNumber = "0"
                        } else {
                            if (isActionActive){
                                strNumber = "0"
                                isActionActive = false
                            } else {
                                strNumber += "0"
                            }
                        }
                    })
                Button(text = ".",
                    backgroundColor = DarkGray,
                    textColor = Color.White,
                    onClick = {})
                ActionButton(text = "=",
                    textColor = textColor,
                    backgroundColor = backgroundColor,
                    onClick = {
                        if(operation == "add") {
                            strNumber = (result + strNumber.toDouble()).toString()
                        }
                        if(operation == "subtract") {
                            strNumber = (result - strNumber.toDouble()).toString()
                        }
                        if(operation == "multiply") {
                            strNumber = (result * strNumber.toDouble()).toString()
                        }
                        if(operation == "divide") {
                            strNumber = (result / strNumber.toDouble()).toString()
                        }
                        strNumber = if (strNumber.endsWith(".0")) strNumber.substring(startIndex = 0, endIndex = strNumber.length-2) else strNumber
                        operation = ""
                    })
            }
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CalculatorScreenPrev() {
    CalculatorScreen()
}
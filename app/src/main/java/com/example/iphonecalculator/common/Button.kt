package com.example.iphonecalculator.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iphonecalculator.ui.theme.DarkGray
@Composable
fun Button(
    text: String,
    backgroundColor: Color,
    textColor: Color,
    onClick:()->Unit
) {
    Button(shape = RoundedCornerShape(100),
        modifier = Modifier.size(90.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        ),
        onClick = { onClick() }) {
        Text(
            text = text,
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            color = textColor
        )
    }
}

@Composable
fun Button2(
    text: String,
    backgroundColor: Color,
    textColor: Color,
    onClick:()->Unit
) {
    Card(shape = RoundedCornerShape(100),
        modifier = Modifier
            .size(width = 190.dp, height = 90.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
        )) {
        Text(
            text = text,
            fontSize = 30.sp,
            modifier = Modifier
                .padding(
                    PaddingValues(
                        top = 23.dp,
                        start = 40.dp
                    )
                )
                .align(Alignment.Start),
            color = textColor
        )
    }
}

@Composable
fun ActionButton(
    text: String,
    backgroundColor: Color,
    textColor: Color,
    onClick: () -> Unit
) {
    Button(shape = RoundedCornerShape(100),
        modifier = Modifier.size(90.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        ),
        onClick = {onClick()}) {
        Text(
            text = text,
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            color = textColor
        )
    }
}

@Preview
@Composable
fun ButtonPrev() {
    Column {
        Button(text = "AC",
            backgroundColor = DarkGray,
            textColor = Color.White,
            onClick = {})
        Button2(text = "0",
            backgroundColor = DarkGray,
            textColor = Color.White,
            onClick = {})
    }
}
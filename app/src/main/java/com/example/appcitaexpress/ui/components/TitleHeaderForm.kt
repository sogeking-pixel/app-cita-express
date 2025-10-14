package com.example.appcitaexpress.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcitaexpress.ui.theme.GreenMain

@Composable
fun TitleHeaderForm(
    modifier: Modifier = Modifier,
    title: String = "Agenda",
    color: Color = GreenMain,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    numberStep: Int = 1,
){
    Row(modifier = modifier.padding(vertical = 10.dp), verticalAlignment = Alignment.CenterVertically){
        Box(
            modifier = Modifier
                .size(22.dp)
                .background(color, shape = CircleShape),
            contentAlignment = Alignment.Center
        ){
            Text(text = "$numberStep", color = backgroundColor, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.width(8.dp))

        Text(text = title, color = color, fontSize = 18.sp, fontWeight = FontWeight.Bold,)
    }

}
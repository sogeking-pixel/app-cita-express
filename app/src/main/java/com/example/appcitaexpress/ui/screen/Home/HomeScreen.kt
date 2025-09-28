package com.example.appcitaexpress.ui.screen.Home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcitaexpress.ui.theme.AppCitaExpressTheme
import com.example.appcitaexpress.ui.theme.GreenMain
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

val list_options = listOf(
    "hola","como","estas"
)

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.background,
){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(GreenMain) // Tu verde
            .statusBarsPadding()
    ){
        Column(
            modifier = Modifier.background(color),
        ) {
            LogoHeader(Modifier.padding(bottom = 40.dp))
            FormAgenda()

        }

    }

}

@Composable
fun LogoHeader(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.background,
    backgroundColor: Color = GreenMain,
){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, start = 20.dp, end = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "CitaExpress",
                color = color,
                fontSize =48.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Todas tus citas médicas en una sola plataforma",
                color = color,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 10.dp)
            )
        }

    }
}

@Composable
fun FormAgenda(
    modifier: Modifier = Modifier,
    title: String = "Agenda",
){
    Column (modifier = modifier.padding(horizontal = 20.dp))
    {
        TitleHeaderForm(
            title = "Agendar Citas Medicas",
            numberStep = 1,
            color = GreenMain,
            backgroundColor = MaterialTheme.colorScheme.background,
        )
        HorizontalDivider(thickness = 1.dp)
        BodyFormAgenda()

    }
}

@Composable
fun TitleHeaderForm(
    modifier: Modifier = Modifier,
    title: String = "Agenda",
    color: Color = MaterialTheme.colorScheme.background,
    backgroundColor: Color = GreenMain,
    numberStep: Int = 1,
){
    Row(modifier = modifier.padding(vertical = 10.dp)){
        Box(
            modifier = Modifier.size(22.dp).background(color, shape = CircleShape),
            contentAlignment = Alignment.Center
        ){
            Text(text = "$numberStep", color = backgroundColor, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.width(8.dp))

        Text(text = title, color = color, fontSize = 18.sp, fontWeight = FontWeight.Bold,)
    }

}

@Composable
fun BodyFormAgenda(
    modifier: Modifier = Modifier,
){
    val optionText = list_options[0]
    var selectedCategory by remember { mutableStateOf<String>(optionText) }
    Column (modifier = modifier){
        NavList(
            options = list_options,
            modifier = Modifier.padding(vertical = 30.dp),
            onSelectedCategory = {selectedCategory = it}
        )
        DateInputWithPicker()
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavList(
    options: List<String>,
    modifier: Modifier = Modifier,
    onSelectedCategory: (String) -> Unit = {}
){
    var optionSelect by remember { mutableStateOf(options[0]) }
    var isExpanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { isExpanded = !isExpanded },
        modifier = modifier
    ) {
        TextField(
            value = optionSelect,
            onValueChange = {},
            readOnly = true,
            label = { Text("Prueba") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            modifier = Modifier.menuAnchor().fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            options.forEach { option ->
                val optionText = option
                DropdownMenuItem(
                    text = { Text(optionText) },
                    onClick = {
                        optionSelect = option
                        isExpanded = false
                        onSelectedCategory(optionText)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateInputWithPicker() {
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val dateFormatter = remember { SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()) }

    val selectedDateText by remember {
        derivedStateOf {
            datePickerState.selectedDateMillis?.let {
                dateFormatter.format(Date(it))
            } ?: "Selecciona una fecha"
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = selectedDateText,
            onValueChange = {},
            label = { Text("Fecha") },
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Seleccionar fecha",
                    modifier = Modifier.clickable { showDatePicker = true }
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { showDatePicker = true }
        )
    }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = {
                showDatePicker = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDatePicker = false
                    }
                ) {
                    Text("Aceptar")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDatePicker = false
                    }
                ) {
                    Text("Cancelar")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 350,
    heightDp = 700
)
@Composable
fun GreetingPreview() {
    AppCitaExpressTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) {innerPadding ->
            HomeScreen(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
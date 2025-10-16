package com.example.appcitaexpress.ui.screen.homeui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appcitaexpress.data.model.Specialty
import com.example.appcitaexpress.ui.components.ErrorScreen
import com.example.appcitaexpress.ui.components.LoadingScreen
import com.example.appcitaexpress.ui.components.LogoHeader
import com.example.appcitaexpress.ui.components.TitleHeaderForm
import com.example.appcitaexpress.ui.theme.AppCitaExpressTheme
import com.example.appcitaexpress.ui.theme.GreenMain
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.background,
    homeViewModel: HomeViewModel = viewModel()
) {
    val homeUiState by homeViewModel.homeUiState.collectAsState()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(GreenMain)
            .statusBarsPadding()
    ) {
        Column(
            modifier = Modifier.background(color),
        ) {
            LogoHeader(Modifier.padding(bottom = 40.dp))
            FormAgenda(homeUiState, homeViewModel)

        }

    }

}


@Composable
fun FormAgenda(
    homeUiState: HomeUiState,
    homeViewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(horizontal = 20.dp)) {

        when (homeUiState) {
            is HomeUiState.Loading -> {
                LoadingScreen()
            }
            is HomeUiState.Success -> {
                TitleHeaderForm(
                    title = "Buscar Citas Medicas",
                    numberStep = 1,
                    color = GreenMain,
                    backgroundColor = MaterialTheme.colorScheme.background,
                )
                HorizontalDivider(thickness = 1.dp)
                BodyFormAgenda(
                    specialties =  homeUiState.dataHome.specialties,
                    enabled =  (homeUiState.dataHome.selectedSpecialty != null && homeUiState.dataHome.selectedDate != null),
                    homeViewModel= homeViewModel
                )
            }
            is HomeUiState.Error -> {
                ErrorScreen({homeViewModel.loadSpecialties()})
            }
        }
    }
}

@Composable
fun BodyFormAgenda(
    specialties: List<Specialty>,
    enabled: Boolean,
    homeViewModel: HomeViewModel,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        NavList(
            specialties = specialties,
            modifier = Modifier.padding(vertical = 30.dp),
            onSpecialtySelected = { homeViewModel.onSpecialtySelected(it) }
        )
        DateInputWithPicker(onDateSelected = { homeViewModel.onDateSelected(it) })
        ButtonSearchDoctor(
            modifier = Modifier.padding(top = 30.dp),
            onClick = { homeViewModel.searchDoctors() },
            enabled = enabled
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavList(
    specialties: List<Specialty>,
    modifier: Modifier = Modifier,
    onSpecialtySelected: (Specialty) -> Unit
) {
    var selectedSpecialty by remember { mutableStateOf<Specialty?>(null) }
    var isExpanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { isExpanded = !isExpanded },
        modifier = modifier
    ) {
        TextField(
            value = selectedSpecialty?.nombre ?: "",
            onValueChange = {},
            readOnly = true,
            label = { Text("Especialidad") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            modifier = Modifier.fillMaxWidth().menuAnchor(),
        )
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            specialties.forEach { specialty ->
                DropdownMenuItem(
                    text = { Text(specialty.nombre) },
                    onClick = {
                        selectedSpecialty = specialty
                        isExpanded = false
                        onSpecialtySelected(specialty)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateInputWithPicker(onDateSelected: (String) -> Unit) {
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val dateFormatter = remember { SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()) }

    val selectedDateText by remember {
        derivedStateOf {
            datePickerState.selectedDateMillis?.let {
                val formattedDate = dateFormatter.format(Date(it))
                onDateSelected(formattedDate)
                formattedDate
            } ?: "Selecciona una fecha"
        }
    }

    Column(modifier = Modifier) {
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


@Composable
fun ButtonSearchDoctor(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = GreenMain,
            contentColor = Color.White
        )
    ) {
        Text(text = "Buscar Doctor", fontWeight = FontWeight.Bold)
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
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            HomeScreen(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
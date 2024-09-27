package com.example.ceiitApp.loans

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ceiitApp.models.*
import com.example.ceiitApp.themes.MainTheme

@Composable
fun ConfirmacionPrestamoScreen(
    obj: Objeto,
    isForStudent: Boolean, // To switch between student and CEIIT manager
    onNextClick: () -> Unit
) {
    val loanPeriod = remember { mutableStateOf("") }
    val acceptedTerms = remember { mutableStateOf(false) }
MainTheme {
    Column(modifier = Modifier.padding(16.dp)) {
        // Display object details
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth(),
            elevation = 4.dp
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Objeto: ${obj.nombre}", style = MaterialTheme.typography.h6)
                Text("Descripción: ${obj.descripcion}")
                Text("Estado: ${obj.estado}")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Loan period selector (for students only)
        if (isForStudent) {
            Text("Plazo del préstamo")
            TextField(
                value = loanPeriod.value,
                onValueChange = { loanPeriod.value = it },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Terms and Conditions (switch for student or manager)
        Text(
            text = if (isForStudent) {
                "Términos y Condiciones de préstamo para el alumno"
            } else {
                "Términos y Condiciones de préstamo para el encargado CEIIT"
            }
        )
        Text(
            text = "Aquí se muestran los términos y condiciones del préstamo."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Accept terms and conditions checkbox
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = acceptedTerms.value,
                onCheckedChange = { acceptedTerms.value = it }
            )
            Text("Acepto Términos y Condiciones")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Next button (disabled until terms are accepted)
        Button(
            onClick = { onNextClick() },
            enabled = acceptedTerms.value && (if (isForStudent) loanPeriod.value.isNotEmpty() else true),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Siguiente")
        }
    }
}

}

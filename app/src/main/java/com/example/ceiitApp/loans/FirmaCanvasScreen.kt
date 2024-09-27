package com.example.ceiitApp.loans

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.ceiitApp.themes.MainTheme

@Composable
fun FirmaCanvasScreen(onSignatureSave: () -> Unit) {
    MainTheme {
        var isSigned by remember { mutableStateOf(false) }

        Column(modifier = Modifier.padding(16.dp)) {
            Text("Firma aquí:", style = MaterialTheme.typography.h6)

            // Canvas for signature (you can replace with a proper drawing canvas implementation)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(Color.Gray)
            ) {
                // Placeholder for signature drawing logic
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                // Clear button to reset the signature
                Button(onClick = { isSigned = false }) {
                    Text("Limpiar")
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Save button to confirm the signature
                Button(onClick = { isSigned = true; onSignatureSave() }) {
                    Text("Guardar")
                }
            }
        }
    }
}
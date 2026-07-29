package com.example.enmanuel_gomez_ap2_p2.presentation.gasto

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.enmanuel_gomez_ap2_p2.domain.model.Gasto
import com.example.enmanuel_gomez_ap2_p2.ui.theme.Enmanuel_Gomez_Ap2_p2Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    onNavigateToEdit: (Int?) -> Unit,
    viewModel: ListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ListScreenContent(uiState = uiState, onNavigateToEdit = onNavigateToEdit)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreenContent(
    uiState: ListUiState,
    onNavigateToEdit: (Int?) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gastos") },
                actions = {
                    Column(
                        modifier = Modifier.padding(end = 16.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = "Registros: ${uiState.totalRegistros}",
                            style = MaterialTheme.typography.labelSmall
                        )
                        Text(
                            text = "Total: ${"%.2f".format(uiState.totalMonto)}",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onNavigateToEdit(null) }) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Gasto")
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                uiState.isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                uiState.error != null -> Text(
                    text = uiState.error,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp)
                )
                else -> LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(uiState.gastos) { gasto ->
                        GastoItem(gasto = gasto, onClick = { onNavigateToEdit(gasto.gastoId) })
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}

@Composable
fun GastoItem(gasto: Gasto, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "ID: ${gasto.gastoId}",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.outline
            )
            Text(
                text = gasto.suplidor,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = gasto.fecha,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.outline
            )
        }
        Text(
            text = "${"%.2f".format(gasto.monto)}",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ListScreenPreview() {
    Enmanuel_Gomez_Ap2_p2Theme {
        ListScreenContent(
            uiState = ListUiState(
                gastos = listOf(
                    Gasto(1, "2026-07-22T00:00:00", "Suplidor A", "B0100000001", 18.0, 100.0),
                    Gasto(2, "2026-07-23T00:00:00", "Suplidor B", "B0100000002", 36.0, 200.0),
                    Gasto(3, "2026-07-24T00:00:00", "Suplidor C", "B0100000003", 0.0, 500.0)
                )
            ),
            onNavigateToEdit = {}
        )
    }
}

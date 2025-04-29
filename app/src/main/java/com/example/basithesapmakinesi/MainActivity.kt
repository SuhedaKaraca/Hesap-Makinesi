package com.example.basithesapmakinesi


import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basithesapmakinesi.ui.theme.BasitHesapMakinesiTheme




class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasitHesapMakinesiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        CalculatorScreen(modifier = Modifier.padding(innerPadding))
                        }
                }

            }
        }
    }



@Composable
fun CalculatorScreen(modifier: Modifier = Modifier) {
    var input by remember { mutableStateOf("") }


    //Arayüz Tasarımı
    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ){
        //Ekran Kısmı (yazılacak kısım)
        Text(text = input,
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.padding(bottom = 16.dp))

        // 1, 2, 3
        Row (horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = { input += "1"}) { Text("1")   }
            Button(onClick = { input += "2"}) { Text(text ="2" ) }
            Button(onClick = { input += "3"}) { Text(text ="3" ) }
            Button(onClick = { input += "+"}) { Text(text = "+") }
            }

        //4,5,6

        Row (horizontalArrangement = Arrangement.spacedBy(16.dp)){
                Button(onClick = { input += "4" }) { Text("4") }
                Button(onClick = { input += "5" }) { Text("5") }
                Button(onClick = { input += "6" }) { Text("6") }
                Button(onClick = { input += "-"}) { Text(text = "-") }
            

        }

       // 7,8,9
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = { input += "7" }) { Text("7") }
            Button(onClick = { input += "8" }) { Text("8") }
            Button(onClick = { input += "9" }) { Text("9") }
            Button(onClick = {input += "x" }) { Text(text = "x") }
        }

        // 0 - Temizle - Eşittir
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = { input += "0" }) { Text("0") }
            Button(onClick = { input = "" }) { Text("C") }
            Button(onClick = {val fixedInput = input.replace("x","*").replace("÷","/");
                input = evaluateExpression(fixedInput) }) { Text(text = "=") }
            Button(onClick = { input += "÷"}) {Text(text = "÷") }

        }
    }
}

fun evaluateExpression(expression: String): String {
    return try {
        val result = expression.replace("×", "*").replace("÷", "/")
        val answer = result.toDoubleOrNull() ?: evalSimple(result)
        answer.toString()
    } catch (e: Exception) {
        "Hata"
    }
}

fun evalSimple(expr: String): Double {
    val ops = listOf("+", "-", "*", "/")
    for (op in ops) {
        val parts = expr.split(op)
        if (parts.size == 2) {
            val a = parts[0].toDoubleOrNull()
            val b = parts[1].toDoubleOrNull()
            if (a != null && b != null) {
                return when (op) {
                    "+" -> a + b
                    "-" -> a - b
                    "*" -> a * b
                    "/" -> a / b
                    else -> 0.0
                }
            }
        }
    }
    return 0.0
}







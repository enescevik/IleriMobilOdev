package com.halic.ilerimobilodev

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.halic.ilerimobilodev.ui.theme.IleriMobilOdevTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IleriMobilOdevTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Box(
                            modifier = Modifier
                                .background(color = Color.Green.copy(alpha = 0.2f))
                                .padding(8.dp)
                                .weight(0.5f)
                        ) {
                            CalculateLengthView()
                        }

                        Box(
                            modifier = Modifier
                                .background(color = Color.Blue.copy(alpha = 0.2f))
                                .padding(8.dp)
                                .weight(1f)
                        ) {
                            CalculateLetterView()
                        }
                    }
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CalculateLengthView() {
    var input by remember {
        mutableStateOf("")
    }

    Column {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Metin Uzunluğu Hesaplama", fontWeight = FontWeight.Bold)
        }

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = input,
            onValueChange = { input = it },
            maxLines = 6,
            label = {
                Text(text = "Bir şeyler yazın...")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = "Uzunluk"
                )
            }
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Girilen metnin uzunluğu = %,d".format(input.length))
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CalculateLetterView() {
    var inputMidterm by remember {
        mutableStateOf("")
    }
    var inputFinal by remember {
        mutableStateOf("")
    }

    Column {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Vize ve Final Harf Notu Hesaplama", fontWeight = FontWeight.Bold)
        }

        Row {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(0.5f),
                value = inputMidterm,
                onValueChange = { inputMidterm = it },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                label = {
                    Text(text = "Vize Notu")
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.padding(vertical = 20.dp)
            ) {
                Text(text = "Vize Ağırlığı: %40")
            }
        }

        Row {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(0.5f),
                value = inputFinal,
                onValueChange = { inputFinal = it },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                label = {
                    Text(text = "Final Notu")
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.padding(vertical = 20.dp)
            ) {
                Text(text = "Final Ağırlığı: %60")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var midtermPoint = inputMidterm.toIntOrNull()
            var finalPoint = inputFinal.toIntOrNull()

            if (midtermPoint != null && finalPoint != null
                && midtermPoint >= 0 && midtermPoint <= 100
                && finalPoint >= 0 && finalPoint <= 100
            ) {
                var total = (midtermPoint * 0.4) + (finalPoint * 0.6)

                Text(
                    text = "Toplam Puan = ${total.roundToInt()}",
                    fontSize = 20.sp
                )

                var letter = when (total) {
                    in 90.0..100.0 -> "AA"
                    in 80.0..89.0 -> "BA"
                    in 70.0..79.0 -> "BB"
                    in 65.0..69.0 -> "CB"
                    in 60.0..64.0 -> "CC"
                    in 55.0..59.0 -> "DC"
                    in 50.0..54.0 -> "DD"
                    in 0.0..49.0 -> "FF"
                    else -> ""
                }

                Text(text = "Harf = $letter",
                    fontWeight = FontWeight.Bold,
                    color = Color.Blue,
                    fontSize = 24.sp,
                )
            } else {
                Text(
                    text = "Vize ve Final notuna 0 ile 100 arası değer giriniz!",
                    fontWeight = FontWeight.Bold,
                    color = Color.Red
                )
            }
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)) {
            Divider(
                thickness = 2.dp,
                color = Color.DarkGray
            )
        }

        Row(
            modifier = Modifier.scale(0.9f)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Başarı Notu", fontWeight = FontWeight.Bold)
                Text(text = "4.00")
                Text(text = "3.50")
                Text(text = "3.00")
                Text(text = "2.50")
                Text(text = "2.00")
                Text(text = "1.50")
                Text(text = "1.00")
                Text(text = "0.00")
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Harf Notu", fontWeight = FontWeight.Bold)
                Text(text = "AA")
                Text(text = "BA")
                Text(text = "BB")
                Text(text = "CB")
                Text(text = "CC")
                Text(text = "DC")
                Text(text = "DD")
                Text(text = "FF")
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Anlamı", fontWeight = FontWeight.Bold)
                Text(text = "Mükemmel")
                Text(text = "Çok İyi")
                Text(text = "İyi")
                Text(text = "Orta")
                Text(text = "Yeterli")
                Text(text = "Koşullu Başarılı")
                Text(text = "Koşullu Başarılı")
                Text(text = "Başarısız")
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Aralık", fontWeight = FontWeight.Bold)
                Text(text = "90-100")
                Text(text = "80-89")
                Text(text = "70-79")
                Text(text = "65-69")
                Text(text = "60-64")
                Text(text = "55-59")
                Text(text = "50-54")
                Text(text = "0-49")
            }
        }
    }
}
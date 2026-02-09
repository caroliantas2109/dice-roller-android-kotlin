package com.example.diceroller

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diceroller.ui.theme.DiceRollerTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge() // Makes the UI extend behind system bars (modern full-screen look)

        setContent {
            DiceRollerTheme {
                DiceRollerUI() // Calls the main UI Composable
            }
        }
    }
}

// Generates a random dice number between 1 and 6
fun GetARandomDice() : Int {
    return Random.nextInt(1, 7) // 7 is excluded, so range is 1–6
}

// Maps dice number to the correct image resource
fun getDicePainterResource(diceNo: Int): Int {
    return when (diceNo) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
}

@Composable
fun DiceRollerUI() {

    // Stores current dice number and remembers it across recompositions
    // mutableStateOf allows UI to refresh automatically when value changes

    var DiceNo by remember { mutableStateOf(6) }
    // NOTE: This should store the NUMBER (1–6), not drawable ID

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Row for dice image
            Row {
                Image(
                    painter = painterResource(getDicePainterResource(DiceNo)), // Converts dice number to image
                    contentDescription = "Dice Image"
                )
            }

            Spacer(modifier = Modifier.height(56.dp)) // Adds space between image and button

            // Row for button
            Row {
                Button(
                    onClick = {
                        DiceNo = GetARandomDice() // Generate new dice number
                        // Log.v("Dice is rolled", "Roll No: $DiceNo") // Debug log (optional)
                    }
                ) {
                    Text(
                        text = "Roll",
                        fontSize = 26.sp
                    )
                }
            }
        }
    }
}

package com.bsuir.tserashkevich.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bsuir.tserashkevich.R
import com.bsuir.tserashkevich.ui.theme.DarkGrey
import com.bsuir.tserashkevich.viewModels.FilmViewModel
import com.bsuir.tserashkevich.MaskVisualTransformation
import com.bsuir.tserashkevich.YearDefaults
import com.bsuir.tserashkevich.navigation.Screens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(navController: NavController, viewModel: FilmViewModel, coroutineScope: CoroutineScope, snackbarHostState: SnackbarHostState) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGrey)
            .padding(5.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(11.dp)
        ) {
            Text(
                text = stringResource(id = R.string.add_film),
                color = Color.White,
                fontSize = 28.sp,
                modifier = Modifier.padding(top = 5.dp, bottom = 10.dp)
            )

            var name by remember { mutableStateOf("") }

            OutlinedTextField(
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color(0xFFFCACFC),
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.White,
                ),
                value = name,
                onValueChange = { newText ->
                    name = newText
                },
                label = { Text(text = stringResource(id = R.string.name), color = Color.White) },
                maxLines = 1,
            )

            var director by remember { mutableStateOf("") }

            OutlinedTextField(
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color(0xFFFCACFC),
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.White,
                ),
                value = director,
                onValueChange = { newText ->
                    director = newText
                },
                label = { Text(text = stringResource(id = R.string.director), color = Color.White) },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            var rating by remember { mutableStateOf("") }

            OutlinedTextField(
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color(0xFFFCACFC),
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.White,
                ),
                value = rating,
                onValueChange = { newText ->
                    if (newText.length <= 1) {
                        rating = newText
                    }
                },
                label = { Text(text = stringResource(id = R.string.rating_en), color = Color.White) },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            var year by remember { mutableStateOf("") }

            OutlinedTextField(
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color(0xFFFCACFC),
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.White,
                ),
                value = year,
                onValueChange = { newText ->
                    if (newText.length <= YearDefaults.YEAR_LENGTH) {
                        year = newText
                    }
                },
                visualTransformation = MaskVisualTransformation(YearDefaults.YEAR_MASK),
                label = { Text(text = stringResource(id = R.string.year), color = Color.White) },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Button(onClick = {
                viewModel.onClickAddFilm(name, director, rating.toInt(), year.toInt())
                coroutineScope.launch {
                    val snackbarResult = snackbarHostState.showSnackbar(
                        message = "New film added",
                        actionLabel = "Ok"
                    )
                }
                navController.navigate(Screens.HomeScreen.route)

            }, enabled = name != "" && director != "" && year != "" && rating != "" && year.length == 4,
                modifier = Modifier.padding(vertical = 16.dp).background(Color(0xFF960000))
            ){
                Text(text = stringResource(id = R.string.add_film), fontSize = 20.sp, color = Color.White)
            }
        }
    }
}


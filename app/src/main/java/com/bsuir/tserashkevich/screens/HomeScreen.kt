package com.bsuir.tserashkevich.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bsuir.tserashkevich.R
import com.bsuir.tserashkevich.navigation.Screens
import com.bsuir.tserashkevich.ui.theme.White
import com.bsuir.tserashkevich.viewModels.Film
import com.bsuir.tserashkevich.viewModels.FilmViewModel
import com.bsuir.tserashkevich.ui.theme.DarkGrey


@Composable
fun HomeScreen(navController: NavController, viewModel: FilmViewModel){
    Box(modifier = Modifier.background(DarkGrey)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp, bottom = 30.dp)
        ) {
            Button(
                modifier = Modifier.padding(vertical = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF960000),
                    contentColor = Color.White
                ),
                onClick = {
                    navController.navigate(Screens.AddScreen.route)
                }

            ) {
                Text(text = stringResource(id = R.string.add_film), fontSize = 20.sp)
            }
            HomeScreenContent(
                items = viewModel.items,
                onEdit = { navController.navigate("edit_film") },
                onRemove = viewModel::onClickRemoveFilm,
                navController = navController
            )
        }
    }
}



@Composable
private fun HomeScreenContent(
    items: SnapshotStateList<Film>,
    onRemove: (Film) -> Unit,
    onEdit: () -> Unit,
    navController: NavController
) {
    LazyColumn(modifier = Modifier.padding(bottom = 50.dp)){
        itemsIndexed(items = items) { index, film ->
            FilmItem(film = film, onRemove = onRemove, navController = navController, index = index)
        }
    }
}

@Composable
private fun FilmItem(
    film: Film,
    onRemove: (Film) -> Unit,
    modifier: Modifier = Modifier,
    navController: NavController,
    index: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .height(85.dp),
        shape = RoundedCornerShape(10.dp)

    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth().background(Color(0xFFFCACFC))
        ) {
            Column(modifier = Modifier.padding(start = 10.dp, top = 5.dp, bottom = 5.dp)) {
                Text(text = "Name: " + film.filmName)
                Text(text = "Director: " + film.director)
                Text(text = "Raiting: " + film.rating)
                Text(text = "Year: " + film.releaseDate)
            }

            Row (horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.CenterVertically ,modifier = Modifier.fillMaxHeight()) {
                IconButton( onClick = { navController.navigate(Screens.EditScreen.withArgs(index.toString())) }
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = stringResource(R.string.edit),
                        modifier = Modifier.fillMaxHeight()
                    )
                }

                IconButton( onClick = { onRemove(film) }
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = stringResource(R.string.delete),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}
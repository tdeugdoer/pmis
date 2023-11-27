package com.bsuir.tserashkevich.viewModels


import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import java.util.UUID

data class Film(
    var filmName: String,
    var director: String,
    var rating: Int,
    var releaseDate: Int,
    val id: UUID = UUID.randomUUID()
)


class FilmViewModel(): ViewModel() {
    val items: SnapshotStateList<Film> = DefaultNotes.toMutableStateList()

    fun onClickRemoveFilm(film: Film) = items.remove(film)

    fun onClickAddFilm(filmName: String, director: String, rating: Int, releaseDate: Int){
        val newNote =  Film(filmName, director, rating, releaseDate)
        items.add(newNote)
    }

    fun onClickEditFilm(filmName: String, director: String, rating: Int, releaseDate: Int, index : Int){
        val film = items.get(index)
        film.filmName = filmName
        film.director = director
        film.rating = rating
        film.releaseDate = releaseDate
    }

    fun getItem(id: Int): Film{
        return items.get(id)
    }

    private companion object {

        private val DefaultNotes = listOf(
            Film("Napoleon", "Ridley Scott", 7, 2023),
            Film("The Thing", "John Carpenter", 8, 1982),
            Film("2001: A Space Odyssey", "Stanley Kubrick", 8, 1968),
            Film("Горько", "Zhora Kryzhovnikov", 6, 2013),
            Film("Pledge This!", "William Heins, Strathford Hamilton", 2, 2006),
            Film("Napoleon", "Ridley Scott", 7, 2023),
            Film("The Thing", "John Carpenter", 8, 1982),
            Film("2001: A Space Odyssey", "Stanley Kubrick", 8, 1968)
        )
    }
}
package com.ipiecole.films;

import com.ipiecole.films.films.Films;
import com.ipiecole.films.films.FilmsData;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class FilmsTest {

    @Test
    public void testFilms(){
        //Given
        Films films = new Films();

        //When
        FilmsData filmsData = films.getFilms();

        //Then
        //Créer une classe avec toutes les infos des films
        //Object créé avec la bonne valeur dans les attributs
        /*
        *       {
            "categorie":"Animation",
            "description":"<p>À la suite d’une chute lors de la cueillette du gui,...",
            "titre":"Astérix - Le Secret de la Potion Magique"
        }
        * */

        Assertions.assertThat(filmsData.getCategorie().equalsIgnoreCase("action"));


    }
}

package com.example.prototipomusicapp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    Button play_pause;
    MediaPlayer mp;
    int posicion = 0;
    MediaPlayer vectormp [] = new MediaPlayer[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.perfilFragment, R.id.favoritosFragment)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        play_pause = (Button) findViewById(R.id.btn_play);
        vectormp[0] = MediaPlayer.create(this, R.raw.kali_uchis_telepatia);
        vectormp[1] = MediaPlayer.create(this, R.raw.the_neighbourhood_daddy_issues);
        vectormp[2] = MediaPlayer.create(this, R.raw.mark_ronson_miley_cyrus_nothing_breaks_like_a_heart);
    }

// Boton para reproducir la canción
    public void playPause(View view) {
        if (vectormp[posicion].isPlaying()) {
            vectormp[posicion].pause();
            play_pause.setBackgroundResource(R.drawable.play);
            Toast.makeText(this, "Pausa", Toast.LENGTH_SHORT).show();
        } else {
            vectormp[posicion].start();
            play_pause.setBackgroundResource(R.drawable.pausa);
            Toast.makeText(this, "Play", Toast.LENGTH_SHORT).show();
        }
    }

// Boton para ir a la siguiente canción
    public void siguiente(View view) {
        if (posicion < vectormp.length - 1) {

            if (vectormp[posicion].isPlaying()) {
                vectormp[posicion].stop();
                posicion++;
                vectormp[posicion].start();
            } else {
                posicion++;
            }

        } else {
            Toast.makeText(this, "No hay más canciones", Toast.LENGTH_SHORT).show();
        }
    }

// Boton para ir a la anterior canción
    public void atras(View view) {
        if (posicion >= 1) {

            if (vectormp[posicion].isPlaying()) {
                vectormp[posicion].stop();
                vectormp[0] = MediaPlayer.create(this, R.raw.kali_uchis_telepatia);
                vectormp[1] = MediaPlayer.create(this, R.raw.the_neighbourhood_daddy_issues);
                vectormp[2] = MediaPlayer.create(this, R.raw.mark_ronson_miley_cyrus_nothing_breaks_like_a_heart);
                posicion--;

                vectormp[posicion].start();

            } else {
                posicion--;
            }

        } else {
            Toast.makeText(this, "No hay más canciones", Toast.LENGTH_SHORT).show();
        }
    }

}
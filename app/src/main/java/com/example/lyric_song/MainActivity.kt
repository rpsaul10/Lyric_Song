package com.example.lyric_song

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    var lyric: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        lyric = findViewById(R.id.lyric)

        findViewById<Button>(R.id.go).setOnClickListener {
            val song = findViewById<EditText>(R.id.song_name).text.toString()
            val artist = findViewById<EditText>(R.id.artist_name).text.toString()
            val lyricSong = LyricSong(song, artist)
            DownloadLyric(this, lyricSong).start()
        }

    }
}
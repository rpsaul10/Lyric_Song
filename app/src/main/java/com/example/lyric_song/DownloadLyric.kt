package com.example.lyric_song

import android.widget.Toast

class DownloadLyric(private var mainActivity: MainActivity, private var lyricSong: LyricSong): Thread() {
    override fun run() {
        val lyric = lyricSong.getTrackLyric()

        mainActivity.runOnUiThread{
            when (lyric) {
                null -> {
                    mainActivity.lyric?.text = ""
                    Toast.makeText(mainActivity, mainActivity.getString(R.string.lyricsNotAvailable), Toast.LENGTH_SHORT).show()
                }
                "" -> {
                    mainActivity.lyric?.text = ""
                    Toast.makeText(mainActivity, mainActivity.getString(R.string.forbiddenLyrics), Toast.LENGTH_SHORT).show()
                }
                else -> {
                    mainActivity.lyric?.text = lyric
                }
            }
        }
    }
}
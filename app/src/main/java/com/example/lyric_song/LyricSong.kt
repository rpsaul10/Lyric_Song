package com.example.lyric_song

import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class LyricSong(var track_name: String, var artist_name: String) {
    private val API_KEY_LABEL = "apikey="
    private val API_KEY = "29aeabb2b877f655784dbbf82592d33d"
    private val METHOD = "matcher.lyrics.get"
    private val BASE_URL = "https://api.musixmatch.com/ws/1.1/"
    private val FORMAT = "?format=json&callback=callback"
    private val ARTIST_LABEL = "q_artist="
    private val TRACK_LABEL = "q_track="

    private fun buildFinalUrl(): String {
        return BASE_URL + METHOD + FORMAT + String.format(
            "&%s%s",
            TRACK_LABEL,
            track_name.replace(" ", "%20")
        ) + String.format("&%s%s", ARTIST_LABEL, artist_name.replace(" ", "%20")) + String.format(
            "&%s%s",
            API_KEY_LABEL,
            API_KEY
        )
    }

    fun getTrackLyric(): String? {
        return try {
            val url = URL(buildFinalUrl())
            val httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.requestMethod = "GET"
            val isr = InputStreamReader(httpURLConnection.inputStream)
            val br = BufferedReader(isr)
            var line: String?
            val stringBuilder = StringBuilder()
            while (br.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }
            val `object` = JSONObject(stringBuilder.toString())
            val message = `object`.getJSONObject("message")
            if (message.getJSONObject("header").getInt("status_code") != 200) {
                // System.out.println("Error");
                return null
            }
            val object2 = message.getJSONObject("body").getJSONObject("lyrics")
            object2.getString("lyrics_body")
        } catch (e: IOException) {
            null
        }
    }

}
package com.example.tareafragmentos.Modelo

import android.content.Context
import com.example.tareafragmentos.R
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class GestorGson {
    var gson : Gson = Gson()

    fun readGson(context: Context): JsonRecibido{
        val stringBuilder: StringBuilder = StringBuilder()
        try {
            val bufferedReader =
                BufferedReader(InputStreamReader(context.resources.openRawResource(R.raw.datos)))
            var text: String? = null
            while ({ text = bufferedReader.readLine(); text }() != null) {
                System.out.println(text)
                stringBuilder.append(text)
            }
            bufferedReader.close()

        } catch (e: IOException) {
            throw e
        }
        System.out.println(stringBuilder.toString())
        var GSON = gson.fromJson(stringBuilder.toString(), JsonRecibido::class.java)
        System.out.println(GSON.toString())
        return gson.fromJson(stringBuilder.toString(), JsonRecibido::class.java)
    }
}
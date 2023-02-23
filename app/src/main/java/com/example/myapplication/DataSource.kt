package dev.mvillasenor.evaluation

import android.content.Context
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream


class DataSource(private val context: Context) {

    fun getCountries(): List<Country> {
        val countries = mutableListOf<Country>()
        try {
            loadJSONFromAsset("countries.json")?.let {
                val countriesJson = JSONObject(it)

                countriesJson.keys().forEach { code ->
                    val countryJson = countriesJson.getJSONObject(code)
                    countries.add(
                        Country(
                            code = code,
                            name = countryJson.getString("name"),
                            nativeName = countryJson.getString("native"),
                            phone = countryJson.getJSONArray("phone").getInts(),
                            continent = countryJson.getString("continent"),
                            capital = countryJson.getString("capital"),
                            currency = countryJson.getJSONArray("currency").getStrings(),
                            languages = countryJson.getJSONArray("languages").getStrings(),
                        )
                    )
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return countries
    }

    fun getContinents(): Map<String, String> {
        val continents = mutableMapOf<String, String>()
        try {
            loadJSONFromAsset("continents.json")?.let {
                val continentsJson = JSONObject(it)
                continentsJson.keys().forEach { code ->
                    val continentName = continentsJson.getString(code)
                    continents[code] = continentName
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return continents
    }


    private fun JSONArray.getInts(): List<Int> {
        val ints = mutableListOf<Int>()
        for (i in 0 until this.length()) {
            ints.add(this.getInt(i))
        }
        return ints
    }

    private fun JSONArray.getStrings(): List<String> {
        val ints = mutableListOf<String>()
        for (i in 0 until this.length()) {
            ints.add(this.getString(i))
        }
        return ints
    }

    private fun loadJSONFromAsset(file: String): String? {
        return try {
            val stream: InputStream = context.assets.open(file)
            val size: Int = stream.available()
            val buffer = ByteArray(size)
            stream.read(buffer)
            stream.close()
            String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
    }
}
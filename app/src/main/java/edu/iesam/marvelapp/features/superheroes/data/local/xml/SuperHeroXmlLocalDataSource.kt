package edu.iesam.marvelapp.features.superheroes.data.local.xml

import android.content.Context
import edu.iesam.marvelapp.R
import edu.iesam.marvelapp.core.serialization.JsonSerializer
import edu.iesam.marvelapp.features.superheroes.domain.SuperHero
import androidx.core.content.edit
import edu.iesam.marvelapp.core.cache.AppCache

/*
Conector para gestionar datos en un fichero XML a través de SharedPreferences.
 */

class SuperHeroXmlLocalDataSource (
    private val context: Context,
    private val serializer: JsonSerializer<List<SuperHero>>,
    private val appCache: AppCache
) {

    private val keySuperHero: String = "superhero"
    private val ttl: Long = 5 * 60 * 1000

    // Me creo la fuente de datos xml: SharedPreferences
    val sharedPref = context.getSharedPreferences(
        context.getString(R.string.local_source_superhero), Context.MODE_PRIVATE)

    // Metodo para guardar un listado de superheroes
    fun save(superHeroes: List<SuperHero>) {

        // apply { } Scope Function
        sharedPref.edit().apply {
            putString(keySuperHero, serializer.toJson(superHeroes))
            apply()
        }
        appCache.updateTime(keySuperHero)

    }

    // Metodo para obtener el listado de superheroes
    fun findAll(): List<SuperHero> {

        if (!appCache.isExpired(keySuperHero, ttl)) {

            val jsonList = sharedPref.getString(keySuperHero, null)

            // let { } Scope Function
            jsonList?.let {
                return serializer.fromJson(it)
            }

        }
         return emptyList()

    }

    // Metodo para obtener un superheroe por su id
    fun findById(id: String): SuperHero? {

        // find { } Scope Function
        return findAll().find { it.id == id }

    }

    // Metodo para eliminar todos los superheroes
    fun deleteAll() {

        sharedPref.edit { remove(keySuperHero) }

    }

    // Metodo para eliminar un superheroe por su id
    fun deleteById(id: String) {

        save(findAll().filterNot { it.id == id })

    }

}
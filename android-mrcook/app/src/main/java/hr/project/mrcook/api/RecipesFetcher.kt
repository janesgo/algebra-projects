package hr.project.mrcook.api

import android.content.ContentValues
import android.content.Context
import android.util.Log
import hr.project.mrcook.DATA_IMPORTED
import hr.project.mrcook.MrCookReceiver
import hr.project.mrcook.RECIPES_PROVIDER_URI
import hr.project.mrcook.framework.sendBroadcast
import hr.project.mrcook.framework.setBooleanPreference
import hr.project.mrcook.handler.downloadImage
import hr.project.mrcook.model.Recipe
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.full.memberProperties

class RecipesFetcher(private val context: Context) {
    private var recipesApi: RecipesApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        recipesApi = retrofit.create(RecipesApi::class.java)
    }

    fun getRecipes(query: String) {
        val request = recipesApi.fetchRecipes(query)
        request.enqueue(object : Callback<RecipesRootApi> {
            override fun onResponse(call: Call<RecipesRootApi>, response: Response<RecipesRootApi>) {
                response.body()?.let {
                    populateRecipes(it)
                }
            }

            override fun onFailure(call: Call<RecipesRootApi>, t: Throwable) {
                Log.d(javaClass.name, t.message, t)
            }
        })

    }

    private fun populateRecipes(recipesRootApi: RecipesRootApi) {
        GlobalScope.launch {
            val mealsMutable = mutableListOf<Recipe>()
            recipesRootApi.recipes.forEach {
                val imagePath = downloadImage(context, it.strMeal.replace(" ", "_"), it.strMealThumb)

                val values = ContentValues().apply {
                    put(Recipe::title.name, it.strMeal)
                    put(Recipe::category.name, it.strCategory)
                    put(Recipe::area.name, it.strArea)
                    put(Recipe::instructions.name, it.strInstructions)
                    put(Recipe::imagePath.name, imagePath ?: "")
                    put(Recipe::ingredients.name, extractValues(it, "Ingredient").joinToString())
                    put(Recipe::measures.name, extractValues(it, "Measure").joinToString())
                    put(Recipe::fav.name, false)
                }
                context.contentResolver.insert(RECIPES_PROVIDER_URI, values)
            }

            context.setBooleanPreference(DATA_IMPORTED, true)
            context.sendBroadcast<MrCookReceiver>()
        }
    }

    private fun extractValues(it: RecipeApi, type: String): List<String> {
        val list = mutableListOf<String>()
        it.javaClass.kotlin.memberProperties.forEach { m ->
            if (m.name.contains(type)) {
                if (m.get(it) != null && (m.get(it) as String).isNotBlank() ) {
                    list.add(m.get(it) as String)
                }
            }
        }
        return list
    }
}
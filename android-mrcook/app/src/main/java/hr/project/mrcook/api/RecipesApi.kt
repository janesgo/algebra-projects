package hr.project.mrcook.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val API_URL = "https://www.themealdb.com/api/json/v1/1/"

interface RecipesApi {
    @GET("search.php")
    fun fetchRecipes(@Query("s") s: String ): Call<RecipesRootApi>
}
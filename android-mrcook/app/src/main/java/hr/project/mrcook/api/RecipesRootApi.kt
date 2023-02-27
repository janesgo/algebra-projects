package hr.project.mrcook.api

import com.google.gson.annotations.SerializedName

data class RecipesRootApi(@SerializedName("meals") val recipes: List<RecipeApi>)

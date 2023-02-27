package hr.project.mrcook.model

data class Recipe(
    var _id: Long?,
    val title: String,
    val category: String,
    val area: String,
    val instructions: String,
    val imagePath: String,
    val ingredients: String,
    val measures: String,
    var fav: Boolean
)

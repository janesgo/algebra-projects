package hr.project.mrcook

import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hr.project.mrcook.model.Recipe
import java.io.File

class RecipePagerAdapter(private val context: Context, private val items: MutableList<Recipe>) :
    RecyclerView.Adapter<RecipePagerAdapter.ViewHolder>() {
    class ViewHolder(recipeView: View) : RecyclerView.ViewHolder(recipeView) {
        val ivFav = itemView.findViewById<ImageView>(R.id.ivFavourite)
        private val ivPagerRecipe = recipeView.findViewById<ImageView>(R.id.ivPagerRecipe)
        private val tvPagerTitle = recipeView.findViewById<TextView>(R.id.tvPagerTitle)
        private val tvPagerCategory = recipeView.findViewById<TextView>(R.id.tvPagerCategory)
        private val tvPagerArea = recipeView.findViewById<TextView>(R.id.tvPagerArea)
        private val tvPagerIngredients = recipeView.findViewById<TextView>(R.id.tvPagerIngredients)
        private val tvPagerInstructions =
            recipeView.findViewById<TextView>(R.id.tvPagerInstructions)

        fun bind(recipe: Recipe) {
            Picasso.get().load(File(recipe.imagePath)).error(R.drawable.splash_logo)
                .into(ivPagerRecipe)
            ivFav.setImageResource(if (recipe.fav) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24)
            tvPagerTitle.text = recipe.title
            tvPagerCategory.text = recipe.category
            tvPagerArea.text = recipe.area
            tvPagerInstructions.text = recipe.instructions
            mergeIngredients(recipe)
        }

        private fun mergeIngredients(recipe: Recipe) {
            val listIngredients = recipe.ingredients.split(", ")
            val listMeasures = recipe.measures.split(", ")
            var sb = StringBuilder()

            for (i in listIngredients.indices) {
                sb.append(listMeasures[i]).append(" ").append(listIngredients[i]).append(", ")
            }
            tvPagerIngredients.text = sb.toString().removeSuffix(", ")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        recipeView
        = LayoutInflater.from(parent.context).inflate(R.layout.recipe_pager, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.ivFav.setOnClickListener {
            item.fav = !item.fav
            val uri = ContentUris.withAppendedId(RECIPES_PROVIDER_URI, item._id!!)
            val values = ContentValues().apply {
                put(Recipe::fav.name, item.fav)
            }
            context.contentResolver.update(uri, values, null, null)
            notifyItemChanged(position)
        }
        holder.bind(item)
    }

    override fun getItemCount() = items.size
}
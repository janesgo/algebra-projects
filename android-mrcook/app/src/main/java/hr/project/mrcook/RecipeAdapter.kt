package hr.project.mrcook

import android.app.AlertDialog
import android.content.ContentUris
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hr.project.mrcook.framework.startActivity
import hr.project.mrcook.model.Recipe
import java.io.File
import java.util.*


class RecipeAdapter(private val context: Context, private val items: ArrayList<Recipe>, fragment: RecipesFragment) :
    RecyclerView.Adapter<RecipeAdapter.ViewHolder>(), Filterable {
    private val fr = fragment
    private var recipes: ArrayList<Recipe> = items
    private var recipesOrg: ArrayList<Recipe> = items

    class ViewHolder(recipeView: View) : RecyclerView.ViewHolder(recipeView) {
        private val ivRecipe = recipeView.findViewById<ImageView>(R.id.ivRecipe)
        private val tvRecipe = recipeView.findViewById<TextView>(R.id.tvRecipe)
        fun bind(recipe: Recipe) {
            Picasso.get().load(File(recipe.imagePath)).error(R.drawable.splash_logo).into(ivRecipe)
            tvRecipe.text = recipe.title
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    recipes = items
                } else {
                    val filtered = ArrayList<Recipe>()
                    for (i in items) {
                        if (i.title.lowercase().contains(charSearch.lowercase())) {
                            filtered.add(i)
                        }
                    }
                    recipes = filtered
                }
                val filterResults = FilterResults()
                filterResults.values = recipes
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                recipes = results?.values as ArrayList<Recipe>
                notifyDataSetChanged()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        recipeView
        = LayoutInflater.from(parent.context).inflate(R.layout.recipe, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            context.startActivity<RecipePagerActivity>(RECIPE_POSITION, recipesOrg.indexOf(recipes[position]))
        }
        holder.itemView.setOnLongClickListener {
            AlertDialog.Builder(context).apply {
                setTitle(R.string.delete)
                setMessage(context.getString(R.string.sure) + " '${recipes[position].title}'?")
                setCancelable(true)
                setNegativeButton(R.string.cancel, null)
                setPositiveButton("OK") { _, _ -> deleteRecipe(position) }
                show()
            }
            true
        }
        holder.bind(recipes[position])
    }

    private fun deleteRecipe(position: Int) {
        val recipe = recipes[position]
        context.contentResolver.delete(
            ContentUris.withAppendedId(
                RECIPES_PROVIDER_URI,
                recipe._id!!
            ), null, null
        )
        File(recipe.imagePath).delete()
        recipesOrg.remove(recipe)
        notifyDataSetChanged()
        fr.binding.searchRecipes.setQuery("", false)
        fr.binding.searchRecipes.clearFocus()
    }

    override fun getItemCount() = recipes.size
}
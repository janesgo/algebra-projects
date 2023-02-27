package hr.project.mrcook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import hr.project.mrcook.databinding.ActivityRecipePagerBinding
import hr.project.mrcook.framework.fetchRecipes
import hr.project.mrcook.model.Recipe

const val RECIPE_POSITION = "h.project.mrcook.recipe_position"

class RecipePagerActivity : AppCompatActivity() {
    private var recipePosition = 0
    private lateinit var recipes: MutableList<Recipe>
    private lateinit var binding: ActivityRecipePagerBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipePagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initPager()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun initPager() {
        recipes = fetchRecipes()
        recipePosition = intent.getIntExtra(RECIPE_POSITION, 0)
        binding.viewPager.adapter = RecipePagerAdapter(this, recipes)
        binding.viewPager.currentItem = recipePosition
        title = recipes[recipePosition].title

        var pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                title = recipes[position].title
            }
        }

        binding.viewPager.registerOnPageChangeCallback(pageChangeCallback)
    }
}
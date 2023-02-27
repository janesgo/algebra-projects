package hr.project.mrcook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import hr.project.mrcook.databinding.FragmentRecipesBinding
import hr.project.mrcook.framework.fetchRecipes
import hr.project.mrcook.model.Recipe

class RecipesFragment : Fragment() {
    lateinit var binding: FragmentRecipesBinding
    private lateinit var mainAdapter: RecipeAdapter
    private lateinit var recipes: ArrayList<Recipe>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipesBinding.inflate(inflater, container, false)
        recipes = ArrayList(requireContext().fetchRecipes())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = getString(R.string.fragment_recipes_title)
        binding.searchRecipes.setOnQueryTextListener(object : OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query != null) {
                    mainAdapter.filter.filter(query)
                }
                return false
            }
        })

        binding.rvRecipes.apply {
            layoutManager = LinearLayoutManager(context)
            mainAdapter = RecipeAdapter(context, recipes, this@RecipesFragment)
            adapter = mainAdapter
        }
    }
}
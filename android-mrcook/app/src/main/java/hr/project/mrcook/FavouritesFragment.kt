package hr.project.mrcook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import hr.project.mrcook.databinding.FragmentFavouritesBinding
import hr.project.mrcook.framework.fetchRecipes
import hr.project.mrcook.model.Recipe


class FavouritesFragment : Fragment() {
    private lateinit var binding: FragmentFavouritesBinding
    private lateinit var recipes: MutableList<Recipe>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        recipes = ArrayList(requireContext().fetchRecipes())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = getString(R.string.fragment_favourites_title)

        val fav = ArrayList<String>(recipes.filter { it.fav }.map { it.title }.toMutableList())
        binding.favList.adapter = ArrayAdapter(this.requireContext(),
            android.R.layout.simple_list_item_1,
            fav)
    }
}
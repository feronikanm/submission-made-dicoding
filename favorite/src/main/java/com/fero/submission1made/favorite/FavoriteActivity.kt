package com.fero.submission1made.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.fero.submission1made.core.ui.MealAdapter
import com.fero.submission1made.detail.DetailMealActivity
import com.fero.submission1made.favorite.databinding.ActivityFavoriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        supportActionBar?.title = "Favorite Meal"

        getFavData()

    }

    private fun getFavData() {

        val mealAdapter = MealAdapter()
        mealAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailMealActivity::class.java)
            intent.putExtra(DetailMealActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        favoriteViewModel.favoriteMeal.observe(this, { favMeal ->
            mealAdapter.setData(favMeal)
            binding.viewEmpty.visibility = if (favMeal.isNotEmpty()) View.GONE else View.VISIBLE
        })

        binding.rvMeal.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mealAdapter
        }

    }
}
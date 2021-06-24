package com.fero.submission1made.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.fero.submission1made.R
import com.fero.submission1made.core.domain.model.Meal
import com.fero.submission1made.databinding.ActivityDetailMealBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailMealActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val  detailMealViewModel: DetailMealViewModel by viewModel()
    private lateinit var binding: ActivityDetailMealBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val detailMeal = intent.getParcelableExtra<Meal>(EXTRA_DATA)
        showDetailMeal(detailMeal)

    }

    private fun showDetailMeal(detailMeal: Meal?) {
        detailMeal?.let {
            supportActionBar?.title = detailMeal.name
            binding.content.tvDetailDescription.text = detailMeal.description
            Glide.with(this)
                .load(detailMeal.image)
                .into(binding.ivDetailImage)

            var statusFavorite = detailMeal.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailMealViewModel.setFavoriteMeal(detailMeal, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite_white))
        }
    }
}
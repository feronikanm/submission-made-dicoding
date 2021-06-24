package com.fero.submission1made.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.fero.submission1made.R
import com.fero.submission1made.core.data.Resource
import com.fero.submission1made.core.ui.MealAdapter
import com.fero.submission1made.databinding.FragmentHomeBinding
import com.fero.submission1made.detail.DetailMealActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val mealAdapter = MealAdapter()
            mealAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailMealActivity::class.java)
                intent.putExtra(DetailMealActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            homeViewModel.meal.observe(viewLifecycleOwner, { meal ->
                if (meal != null) {
                    when (meal) {
                        is Resource.Loading -> binding?.progressBar?.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding?.progressBar?.visibility = View.GONE
                            mealAdapter.setData(meal.data)
                        }
                        is Resource.Error -> {
                            binding?.progressBar?.visibility = View.GONE
                            binding?.viewError?.root?.visibility = View.VISIBLE
                            binding?.viewError?.tvError?.text = meal.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            })

            binding?.let {
                with(it.rvMeal) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = mealAdapter
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
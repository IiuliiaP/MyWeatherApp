package com.example.myweatherapp.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.myweatherapp.databinding.FragmentWeatherDetailsBinding
import com.example.myweatherapp.viewmodel.AppState
import com.example.myweatherapp.viewmodel.WeatherDetailsViewModel

class WeatherDetails : Fragment() {
    lateinit var binding: FragmentWeatherDetailsBinding

    companion object {
        fun newInstance() = WeatherDetails()
    }

    private lateinit var viewModel: WeatherDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this).get(WeatherDetailsViewModel::class.java)
        val observer = Observer<AppState>{renderData(it)}
        viewModel.getData().observe(viewLifecycleOwner, observer)

        viewModel.getWeather()
    }
    private fun renderData(data: AppState){
        when(data){
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                binding.message.text = "ошибка!"
            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Success -> {
                binding.loadingLayout.visibility = View.GONE
                binding.message.visibility = View.GONE
                binding.cityName.text = data.weatherData.city.toString()
                binding.temperature.text = data.weatherData.temperature.toString()
                Toast.makeText(requireContext(),"успешная загрузка",Toast.LENGTH_LONG).show()
            }
        }

    }

}
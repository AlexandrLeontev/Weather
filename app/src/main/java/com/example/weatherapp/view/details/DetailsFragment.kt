package com.example.weatherapp.view.details


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentDetailsBinding
import com.example.weatherapp.model.Weather

class       DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val weather = arguments?.getParcelable<Weather>(BUNDLE_EXTRA)
//        if (weather != null) {
//            val city = weather.city
//            binding.cityName.text = city.city
//            binding.cityCoordinates.text = String.format(
//                getString(R.string.city_coordinates),

        arguments?.getParcelable<Weather>(BUNDLE_EXTRA)?.let { weather ->
            weather.city.also { city ->
                binding.cityName.text = city.city
                binding.cityCoordinates.text = String.format(
                        getString(R.string.city_coordinates),
                        city.lat.toString(),
                        city.lon.toString()
                )
                binding.temperatureValue.text = weather.temperature.toString()
                binding.feelsLikeValue.text = weather.feelsLike.toString()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        const val BUNDLE_EXTRA = "weather"

        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}

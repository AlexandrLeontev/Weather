package com.example.weatherapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.weatherapp.AppState
import com.example.weatherapp.R
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
//                              savedInstanceState: Bundle?): View {
//        return inflater.inflate(R.layout.main_fragment, container, false)
//    }

        private var _binding: ResultProfileBinding? = null  // Чтобы создать Биндинг-класс во Фрагменте
    //Обратите внимание, что эта переменная существует только между методами onCreateView и onDestroyView. Можете объяснить почему?
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ResultProfileBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
//        val observer = Observer<Any> { renderData(it) } // Здесь мы создали инстанс Observer’а, который
//        // выполняет метод renderData, как только LiveData обновляет данные, которые она хранит
//        // В качестве аргумента renderData принимает объект, возвращаемый лайвдатой.
//        viewModel.getData().observe(viewLifecycleOwner, observer) // Далее мы вызываем у созданной ViewModel метод getData,
//    // который возвращает нам LiveData, и вызываем у LiveData метод observe, который и передаём в жизненный цикл вместе с Observer’ом
//    //Теперь, если данные, которые хранит LiveData, изменятся, Observer сразу об этом узнает и вызовет метод renderData,
//    // в который передаст новые данные
//    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getWeather()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val weatherData = appState.weatherData
                loadingLayout.visibility = View.GONE
                Snackbar.make(mainView, "Success", Snackbar.LENGTH_LONG).show()
            }
            is AppState.Loading -> {
                loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                loadingLayout.visibility = View.GONE
                Snackbar
                    .make(mainView, "Error", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Reload") { viewModel.getWeather() }
                    .show()
            }
        }
    }


//    private fun renderData(data: Any) {
//        Toast.makeText(context, "data", Toast.LENGTH_LONG).show()
//    }


}
package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weatherapp.databinding.MainActivityBinding
import com.example.weatherapp.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.main_activity)
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                    .replace(R.id.container, MainFragment.newInstance())
//                    .commitNow()
//        }
//    }

    private lateinit var binding: ResultProfileBinding // Чтобы получить Биндинг-класс в Активити

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ResultProfileBinding.inflate(getLayoutInflater())
        val view = binding.getRoot()
        setContentView(view)
    }

    binding.name.setText(viewModel.getName())  // Теперь можно обращаться к вашим вью
    binding.button.setOnClickListener { viewModel.userClicked() }


}
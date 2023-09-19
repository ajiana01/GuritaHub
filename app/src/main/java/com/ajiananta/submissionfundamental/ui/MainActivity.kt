package com.ajiananta.submissionfundamental.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajiananta.submissionfundamental.data.User
import com.ajiananta.submissionfundamental.databinding.ActivityMainBinding
import com.ajiananta.submissionfundamental.ui.adapter.CardAdapter
import com.ajiananta.submissionfundamental.ui.detail.DetailUserActivity


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: CardAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = CardAdapter()
        adapter.notifyDataSetChanged()

        adapter.setOnItemClickCallback(object : CardAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                Intent(this@MainActivity, DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME, data.login)
                    startActivity(it)
                }
            }
        })
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            MainViewModel::class.java
        )
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerView.setHasFixedSize(false)
            recyclerView.adapter = adapter
            with(binding) {
                pencarianView.setupWithSearchBar(pencarianBar)
                pencarianView
                    .editText
                    .setOnEditorActionListener { textView, actionId, event ->
                        pencarianBar.text = pencarianView.text
                        pencarianView.hide()
                        viewModel.getTotalCount()
                        searchUser()
                        false
                    }
            }
            if (savedInstanceState == null) {
                viewModel.setSearchUsers("a")
            }
        }
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL)
        )
        viewModel.getSearchUsers().observe(this) {
            if (it != null) {
                adapter.setList(it)
                showLoading(false)
            }
        }
        viewModel.getTotalCount().observe(this) {totalCount ->
            Toast.makeText(
                this@MainActivity,
                "Hasil ditemukan: $totalCount",
                Toast.LENGTH_SHORT
            ).show()
        }

    }
    private fun searchUser() {
        binding.apply {
            val query = pencarianBar.text.toString()
            if (query.isEmpty()) return
            showLoading(true)
            viewModel.setSearchUsers(query)
        }
    }
    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}
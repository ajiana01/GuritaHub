package com.ajiananta.submissionfundamental.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.ajiananta.submissionfundamental.R
import com.ajiananta.submissionfundamental.databinding.ActivityDetailUserBinding
import com.ajiananta.submissionfundamental.ui.adapter.PagerAdapter
import com.bumptech.glide.Glide

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: DetailUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val username = intent.getStringExtra(EXTRA_USERNAME)
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)

        showLoading(true)
        viewModel = ViewModelProvider(this )[DetailUserViewModel::class.java]
        viewModel.setUserDetail(username.toString())
        viewModel.getUserDetail().observe(this) {
                binding.apply {
                    showLoading(false)
                    textViewName.text = it.name
                    textViewUsername.text = it.login
                    textViewBio.text = it.bio
                    textViewLocation.text = it.location
                    textViewFollowers.text = resources.getString(R.string.data_follower, it.followers)
                    textViewFollowing.text = resources.getString(R.string.data_following, it.following)
                    Glide.with(this@DetailUserActivity)
                        .load(it.avatarUrl)
                        .into(fotoProfile)
                    Log.d("GlideDebug", "URL Gambar: ${it.avatarUrl}")
                }
        }
        val pagerAdapter = PagerAdapter(this, supportFragmentManager, bundle)
        binding.apply {
            viewPager.adapter = pagerAdapter
            tabLayout.setupWithViewPager(viewPager)
        }
    }
    private fun showLoading(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }
    companion object {
        const val EXTRA_USERNAME = "extra_username"
    }
}
package com.ajiananta.submissionfundamental.ui.detail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajiananta.submissionfundamental.R
import com.ajiananta.submissionfundamental.data.User
import com.ajiananta.submissionfundamental.databinding.FragmentFollowBinding
import com.ajiananta.submissionfundamental.ui.adapter.CardAdapter

class FollowingFragment: Fragment(R.layout.fragment_follow) {

    private var _binding : FragmentFollowBinding? = null
    private val binding by lazy {
        FragmentFollowBinding.bind(requireView())
    }
    private lateinit var viewModel: FollowingViewModel
    private lateinit var adapter: CardAdapter
    private lateinit var username: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments
        username = args?.getString(DetailUserActivity.EXTRA_USERNAME).toString()
        _binding = FragmentFollowBinding.bind(view)

        adapter = CardAdapter()
        adapter.notifyDataSetChanged()

        adapter.setOnItemClickCallback(object : CardAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                Intent(requireActivity(), DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME, data.login)
                    startActivity(it)
                }
            }
        })

        binding.apply {
            recyclerViewFollow.setHasFixedSize(true)
            recyclerViewFollow.layoutManager = LinearLayoutManager(activity)
            recyclerViewFollow.adapter = adapter
        }

        showLoading(true)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[FollowingViewModel::class.java]
        viewModel.setlistFollowers(username)
        viewModel.getListFollowing().observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.setList(it)
                showLoading(false)
            } else {
                Log.e("FollowingFragment", "Following data is null")
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}

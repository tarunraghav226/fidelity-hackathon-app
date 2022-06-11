package com.example.fidelityhackathon.ui.community

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fidelityhackathon.data.models.Members
import com.example.fidelityhackathon.databinding.CommunityMembersFragmentBinding
import com.example.fidelityhackathon.presentation.adapters.CommunityMembersAdapter
import com.example.fidelityhackathon.presentation.viewmodels.CommunityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CommunityMembersFragment: Fragment() {
    private lateinit var binding: CommunityMembersFragmentBinding

    private val communityViewModel: CommunityViewModel by viewModels()
    private val args: CommunityMembersFragmentArgs by navArgs()

    private lateinit var communityMembersAdapter: CommunityMembersAdapter

    private var membersDataList: List<Members> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CommunityMembersFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getMembersData()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun getMembersData() {
        viewLifecycleOwner.lifecycleScope.launch {
            communityViewModel.doShowCommunityMembers(args.accessToken).let {
                if(!it.isSuccessful) return@let
                handleLoaderVisibility(View.GONE)
                membersDataList = it.body()?.members?: listOf()
                initRecyclerView()
            }
        }
    }

    private fun handleLoaderVisibility(visibility: Int) {
        binding.loader.visibility = visibility
    }

    private fun initRecyclerView() {
        communityMembersAdapter = CommunityMembersAdapter(membersDataList)
        binding.communityMembersRecyclerview.adapter = communityMembersAdapter
        binding.communityMembersRecyclerview.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }
}
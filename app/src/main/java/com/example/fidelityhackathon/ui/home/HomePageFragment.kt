package com.example.fidelityhackathon.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fidelityhackathon.BuildConfig
import com.example.fidelityhackathon.R
import com.example.fidelityhackathon.databinding.HomePageFragmentBinding
import com.example.fidelityhackathon.presentation.viewmodels.AuthenticationViewModel
import com.example.fidelityhackathon.presentation.viewmodels.CommunityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomePageFragment: Fragment() {

    private lateinit var binding: HomePageFragmentBinding

    private val authenticationViewModel: AuthenticationViewModel by viewModels()
    private val communityViewModel: CommunityViewModel by viewModels()

    private val args: HomePageFragmentArgs by navArgs()

    private lateinit var authenticationToken: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomePageFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        authenticationToken = args.accessToken

        initClickListeners()
        updateUI()
    }

    private fun updateUI() {
        updateAppVersion()
        updateUserInCommunityStatus()
    }

    private fun updateUserInCommunityStatus(){
        viewLifecycleOwner.lifecycleScope.launch {
            communityViewModel.doCheckUserInCommunity(authenticationToken).let {
                if (!it.isSuccessful) {
                    if(it.code() == 403){
                        findNavController().popBackStack()
                    } else {
                        return@let
                    }
                }
                binding.userInCommunityText.text = if (it.body()?.isInCommunity == true)
                    getString(R.string.community_joined)
                else getString(R.string.not_in_community)
                val color = if (it.body()?.isInCommunity == true)
                    R.color.green
                else R.color.red
                binding.userInCommunityText.setTextColor(ContextCompat.getColor(requireContext(),color))
            }
        }
    }

    private fun updateAppVersion() {
        binding.appVersion.text = getString(R.string.app_version, BuildConfig.APP_VERSION)
    }

    private fun initClickListeners() {
        binding.logout.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                authenticationViewModel.clearStoreData()
                findNavController().popBackStack()
            }
        }

        binding.button2.setOnClickListener {
            moveToMemberListingPage()
        }

        binding.button.setOnClickListener {
            addUserInCommunity()
        }

        binding.carbonGuidelines.setOnClickListener {
            val action = HomePageFragmentDirections.actionHomePageFragmentToGuidelinesFragment()
            findNavController().navigate(action)
        }
    }

    private fun moveToMemberListingPage() {
        val action = HomePageFragmentDirections.actionHomePageFragmentToCommunityMembersFragment(args.accessToken)
        findNavController().navigate(action)
    }

    private fun addUserInCommunity() {
        viewLifecycleOwner.lifecycleScope.launch{
            communityViewModel.doAddUserInCommunity(args.accessToken).let{
                if(!it.isSuccessful) return@let
                moveToMemberListingPage()
            }
        }
    }
}
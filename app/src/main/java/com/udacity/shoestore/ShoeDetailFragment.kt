package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.viewmodels.ShoesViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [ShoeDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoeDetailFragment : Fragment() {

    private val sharedShoesViewModel : ShoesViewModel by activityViewModels()

    private var binding: FragmentShoeDetailBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            shoesViewModel = sharedShoesViewModel
            shoeInfoFragment = this@ShoeDetailFragment
        }

        return binding!!.root
    }

    fun saveShoeItem() {
        sharedShoesViewModel.saveShoeItem()
        goToShoeListFragment()
    }

    fun goToShoeListFragment() = findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
}
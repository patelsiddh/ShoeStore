package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentShoeListBinding

/**
 * A simple [Fragment] subclass.
 * Use the [ShoeListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoeListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //inflater.inflate(R.layout.fragment_shoe_list, container, false)
        // Inflate the layout for this fragment
        val binding: FragmentShoeListBinding = DataBindingUtil
                                .inflate(inflater, R.layout.fragment_shoe_list, container, false)

        binding.addShoeFloatingButton.setOnClickListener {view ->
            view.findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }
        return binding.root
    }
}
package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoeItemBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodels.ShoesViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [ShoeListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoeListFragment : Fragment() {

    private var binding : FragmentShoeListBinding? = null

    private val sharedShoesViewModel : ShoesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)

        binding!!.addShoeFloatingButton.setOnClickListener {view ->
            view.findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }

        binding?.lifecycleOwner = viewLifecycleOwner

        // inflating a layout that independently holds data for a single shoe item and
        // add that inside fragment_shoe_list.xml -> LinearLayout (id : shoe_list_layout)
        sharedShoesViewModel.shoesList.observe(viewLifecycleOwner, Observer { shoes ->
            addShoeListView(shoes)
        })

        return binding!!.root
    }

    private fun addShoeListView(shoes: List<Shoe>) {
        shoes.forEach {
            val shoeBinding = ShoeItemBinding.inflate(LayoutInflater.from(requireContext()), binding!!.shoeListLayout, false)

            shoeBinding.shoeNameCardText.text = it.name
            shoeBinding.shoeCompanyCardText.text = it.company
            shoeBinding.shoeSizeCardText.text =
                getString(R.string.shoe_size_card_text, it.size.toString())
            shoeBinding.shoeDescriptionCardText.text = it.description

            binding!!.shoeListLayout.addView(shoeBinding.root)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentShoeListBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)

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

    // Manually generating a view that adds shoe details value in a single cardview
    private fun addShoeListView(shoes: List<Shoe>) {
        shoes.forEach {
            val shoeBinding = ShoeItemBinding.inflate(LayoutInflater.from(requireContext()), binding!!.shoeListLayout, false)

            with(shoeBinding){
                shoeNameCardText.text = it.name
                shoeCompanyCardText.text = it.company
                shoeSizeCardText.text = getString(R.string.shoe_size_card_text, it.size.toString())
                shoeDescriptionCardText.text = it.description
            }

            binding!!.shoeListLayout.addView(shoeBinding.root)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController()) ||
                super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
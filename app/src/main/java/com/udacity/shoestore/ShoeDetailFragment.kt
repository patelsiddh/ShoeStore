package com.udacity.shoestore

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodels.ShoesViewModel
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 * Use the [ShoeDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoeDetailFragment : Fragment() {

    private val sharedShoesViewModel : ShoesViewModel by activityViewModels()

    private lateinit var binding: FragmentShoeDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        binding = FragmentShoeDetailBinding.inflate(inflater, container, false)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            shoeInfoFragment = this@ShoeDetailFragment
            shoe = sharedShoesViewModel.shoeItem
        }

        return binding.root
    }

    // Save the details of shoe entered by user when clicked on "SAVE" button
    fun saveShoeItem() {
        val shoeBinder = binding.shoe
        val name = shoeBinder?.name
        val company = shoeBinder?.company
        val size = shoeBinder?.size
        val info = shoeBinder?.description

        if (name!!.isEmpty() || company!!.isEmpty() || size!!.isNaN() || info!!.isEmpty()) {
            setErrorTextField(true)
        }
        else {
            sharedShoesViewModel.saveShoeItem(name, size, company, info)
            goToShoeListFragment()
        }

    }

    fun goToShoeListFragment()
    {
        findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
        sharedShoesViewModel.resetShoeItem()
    }

    private fun setErrorTextField(error: Boolean)
    {
        if (error){
            binding.errorText.text = getString(R.string.error_text)
            binding.errorText.visibility = View.VISIBLE
        }
        else {
            binding.errorText.text = null
            binding.errorText.visibility = View.GONE
        }
    }
}
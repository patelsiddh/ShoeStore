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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            shoesViewModel = sharedShoesViewModel
            shoeInfoFragment = this@ShoeDetailFragment
        }

        return binding.root
    }

    fun saveShoeItem() {
        val name = binding.shoeNameEdit.text.toString()
        val company = binding.shoeCompanyEdit.text.toString()
        val size = binding.shoeSizeEdit.text.toString()
        val info = binding.shoeDetailEdit.text.toString()

        if (name.isEmpty() || company.isEmpty() || size.isEmpty() || info.isEmpty()) {
            setErrorTextField(true)
        }
        else {
            Timber.i("Entered Shoe Name: ${sharedShoesViewModel.shoeName} ")
            sharedShoesViewModel.saveShoeItem(name, size, company, info)
            goToShoeListFragment()
        }

    }

    fun goToShoeListFragment() = findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())

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
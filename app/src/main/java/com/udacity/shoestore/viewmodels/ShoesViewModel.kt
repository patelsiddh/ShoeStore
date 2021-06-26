package com.udacity.shoestore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoesViewModel() : ViewModel(){

    private lateinit var shoesItemList : MutableList<Shoe>

    private val _shoesList = MutableLiveData<List<Shoe>>()
    val shoesList: LiveData<List<Shoe>> = _shoesList

    //private val _shoeName = MutableLiveData<String>()
    private lateinit var shoeName: String

    //private val _shoeSize = MutableLiveData<String>()
    private lateinit var shoeSize: String

    //private val _shoeCompany = MutableLiveData<String>()
    private lateinit var shoeCompany: String

    //private val _shoeInfo = MutableLiveData<String>()
    private lateinit var shoeInfo: String

    init {
        loadDefaultShoes()
    }

    private fun loadDefaultShoes() {
        shoesItemList = mutableListOf<Shoe>(
            Shoe("Running Comforter", 10.5, "Puma", "Nice running shoes for reliable price"),
            Shoe("Long-Walker", 8.0, "Nike", "comfortable pair for whole day standing tasks"),
            Shoe("Traditional Boot", 10.0, "ALDO", "Old-School tradition maintained")
        )
        _shoesList.value = shoesItemList
    }

    fun saveShoeItem(name: String, size: String, company: String, info: String) {

        shoeName= name
        shoeSize = size
        shoeCompany = company
        shoeInfo = info

        val shoeSizeNumeric = shoeSize.toDouble()

        val newShoe = Shoe(shoeName, shoeSizeNumeric, shoeCompany, shoeInfo)

        val tempShoesList = shoesItemList.toMutableList()
        shoesItemList.clear()
        tempShoesList.add(newShoe)
        shoesItemList = tempShoesList

        _shoesList.value = shoesItemList

    }

}


package com.udacity.shoestore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoesViewModel() : ViewModel(){

    private lateinit var shoesItemList : MutableList<Shoe>

    private val _shoesList = MutableLiveData<List<Shoe>>()
    val shoesList: LiveData<List<Shoe>> = _shoesList

    private val _shoeName = MutableLiveData<String>()
    val shoeName : LiveData<String>
    get() = _shoeName

    private val _shoeSize = MutableLiveData<String>()
    val shoeSize : LiveData<String>
        get() = _shoeSize

    private val shoeSizeNumeric = (shoeSize.value?.toDouble()) ?: 0.0

    private val _shoeCompany = MutableLiveData<String>()
    val shoeCompany : LiveData<String>
        get() = _shoeCompany

    private val _shoeInfo = MutableLiveData<String>()
    val shoeInfo : LiveData<String>
        get() = _shoeInfo

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

    fun saveShoeItem() {
        val newShoe = Shoe(
            shoeName.value ?: "Sample Shoe Name",
            shoeSizeNumeric, shoeCompany.value ?: "Sample Shoe Company",
            shoeInfo.value ?: "Sample Shoe Description"
        )

        val tempShoesList = shoesItemList.toMutableList()
        shoesItemList.clear()
        tempShoesList.add(newShoe)
        shoesItemList = tempShoesList

        _shoesList.value = shoesItemList

    }

}


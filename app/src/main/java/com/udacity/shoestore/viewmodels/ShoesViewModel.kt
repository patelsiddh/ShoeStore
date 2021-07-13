package com.udacity.shoestore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoesViewModel() : ViewModel(){

    private lateinit var shoesItemList : MutableList<Shoe>

    private val _shoesList = MutableLiveData<List<Shoe>>()
    val shoesList: LiveData<List<Shoe>> = _shoesList

    lateinit var shoeItem: Shoe

    init {
        loadDefaultShoes()
        resetShoeItem()
    }

    fun resetShoeItem() {
        shoeItem = Shoe("",0.0,"","")
    }

    private fun loadDefaultShoes() {
        shoesItemList = mutableListOf<Shoe>(
            Shoe("Running Comforter", 10.5, "Puma", "Nice running shoes for reliable price"),
            Shoe("Long-Walker", 8.0, "Nike", "comfortable pair for whole day standing tasks"),
            Shoe("Traditional Boot", 10.0, "ALDO", "Old-School tradition maintained")
        )
        _shoesList.value = shoesItemList
    }

    fun saveShoeItem(name: String, size: Double, company: String, info: String) {
        val newShoe = Shoe(name, size, company, info)

        val tempShoesList = shoesItemList.toMutableList()
        shoesItemList.clear()
        tempShoesList.add(newShoe)
        shoesItemList = tempShoesList

        _shoesList.value = shoesItemList
        resetShoeItem()

    }

}


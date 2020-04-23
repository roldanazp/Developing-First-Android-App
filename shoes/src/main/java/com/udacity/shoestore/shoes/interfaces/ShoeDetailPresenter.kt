package com.udacity.shoestore.shoes.interfaces

import com.udacity.shoestore.global.models.Shoe

/**
 * Represents all actions that can be perform on the shoe detail view
 */
interface ShoeDetailPresenter{

    /**
     * Saves or update the shoe
     */
    fun onSaveSelected(shoe: Shoe)

    /**
     * Loads image from gallery or camera
     */
    fun loadImage()

    /**
     * Go back in navigation
     */
    fun onBack()

}


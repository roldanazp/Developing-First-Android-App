package com.udacity.shoestore.modules.shoes.interfaces

import com.udacity.shoestore.models.Shoe

/**
 * Represents all actions that can be perform on the shoes list view
 */
interface ShoeListPresenter{

    /**
     * Performs select shoe event from the shoe list view
     */
    fun onItemSelected(shoe: Shoe)

    /**
     * Performs logout event from the shoe list view
     */
    fun onLogout()

    /**
     * Performs new shoe event from the shoe list view
     */
    fun onCreateShoe()

}
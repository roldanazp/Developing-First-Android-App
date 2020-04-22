package com.udacity.shoestore.modules.shoes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.FragmentShoeListItemBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.modules.shoes.interfaces.ShoeListPresenter
import com.udacity.shoestore.modules.shoes.viewmodel.ShoeViewModel
import com.udacity.shoestore.sharedpreferences.PreferencesHelper

/**
 * Fragment responsible for displaing in memory shoes on the activity [ShoeViewModel.shoesMutableLiveData]
 */
class ShoeListFragment : Fragment(), ShoeListPresenter {

    /**
     * Holds the view biding object for in instance access
     */
    private lateinit var binding: FragmentShoeListBinding

    /**
     * Activity [ShoeViewModel] that holds in memory shoes and the new shoes added in [MutableLiveData]
     * variables
     */
    private val shoeViewModel: ShoeViewModel by activityViewModels()

    /**
     * A list of Binding objects [FragmentShoeListItemBinding] that represents every shoe in memory
     */
    private val shoeItemBindingList = mutableListOf<FragmentShoeListItemBinding>()


    /**
     * Inflates and link the view to the [ShoeListPresenter] and set the list of shoes as binding,
     * also initialize listeners for observable fields on [shoeViewModel] to maintain in zinc the shoes
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShoeListBinding.inflate(layoutInflater, container, false)
        binding.presenter = this
        addInMemoryItems()
        initViewModelObserver()
        return binding.root
    }

    /**
     * For every shoe in memory viewmodel [ShoeViewModel] calls [addItem]
     */
    private fun addInMemoryItems() {
        shoeViewModel.shoesMutableLiveData.value?.let { shoeList ->
            shoeList.forEach { addItem(it) } }
    }

    /**
     * Every time [ShoeViewModel.shoeUpdate] is set update or add the shoe to the list
     */
    private fun initViewModelObserver() {
        shoeViewModel.shoeUpdate.observe(viewLifecycleOwner, Observer { shoe ->
            val shoeBinding = shoeItemBindingList.find { it.shoe?.equals(shoe) ?: false }
            shoeBinding?.let {
                it.shoe = shoe
            } ?: run {
                shoe?.let { addItem(it) }
            }
        })
    }

    /**
     * Create [FragmentShoeListItemBinding] and linked to a [Shoe] model, after add the binding
     * to [shoeItemBindingList] and add the view to layout
     */
    private fun addItem( shoe: Shoe) {
        val newShoeBinding = FragmentShoeListItemBinding.inflate(layoutInflater,
            binding.llShoeListContainer, false)
        newShoeBinding.shoe = shoe
        newShoeBinding.presenter = this
        shoeItemBindingList.add(newShoeBinding)
        binding.llShoeListContainer.addView(newShoeBinding.root)
    }

    /**
     * [ShoeListPresenter.onItemSelected] event navigates to
     * [ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment] action with
     * [shoe] "id" meaning shoe update
     */
    override fun onItemSelected(shoe: Shoe) {
        val action = ShoeListFragmentDirections
            .actionShoeListFragmentToShoeDetailFragment(shoe.hashCode())
        findNavController().navigate(action)
    }

    /**
     * [ShoeListPresenter.onLogout] clear in [shoeViewModel] data and navigates to
     * [ShoeListFragmentDirections.actionShoeListFragmentToSplashScreenFragment]
     */
    override fun onLogout() {
        PreferencesHelper.isTutorialCompleted = false
        PreferencesHelper.isUserLoggedIn = false
        shoeViewModel.clearShoes()
        shoeViewModel.shoeUpdate.value = null
        val action = ShoeListFragmentDirections.actionShoeListFragmentToSplashScreenFragment()
        findNavController().navigate(action)
    }

    /**
     * [ShoeListPresenter.onItemSelected] event navigates to
     * [ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment] action
     */
    override fun onCreateShoe() {
        val action = ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment()
        findNavController().navigate(action)
    }

}

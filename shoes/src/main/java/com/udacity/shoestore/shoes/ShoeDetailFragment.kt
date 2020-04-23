package com.udacity.shoestore.shoes

import android.app.Activity.RESULT_CANCELED
import android.content.Intent
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.udacity.shoestore.global.shoes.ShoeDetailFragmentArgs
import com.udacity.shoestore.global.models.Shoe
import com.udacity.shoestore.shoes.viewmodel.ShoeViewModel
import com.udacity.shoestore.shoes.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.shoes.interfaces.ShoeDetailPresenter
import timber.log.Timber

const val NEW_SHOE_ID = 0
const val GALLERY_IMAGE = 52346

/**
 * Show details of a show, enables user to update or create a shoe, please note that every field si bind
 * two ways, any change it will be reflected immediately
 */
class ShoeDetailFragment : Fragment(), ShoeDetailPresenter {

    /**
     * Holds the view biding class for in instance access
     */
    private lateinit var binding: FragmentShoeDetailBinding

    /**
     * Holds the id of the shoe
     */
    private val args: ShoeDetailFragmentArgs by navArgs()

    /**
     * If there are is an update for the shoe or is a new shoe
     */
    private val isNewShoe:Boolean by lazy{ args.shoeId==NEW_SHOE_ID }

    /**
     * Activity [ShoeViewModel] that holds in memory shoes and the new shoes added in [MutableLiveData]
     * variables
     */
    private val shoeViewModel: ShoeViewModel by activityViewModels()

    /**
     * Inflates and link the view to the [FragmentShoeDetailBinding] and set the list of shoes as binding,
     * also initialize listeners for observable fields on [shoeViewModel] to maintain in zinc the shoes
     *
     * [shoeViewModel] is an activity shared viewmodel
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShoeDetailBinding.inflate(layoutInflater, container, false)
        binding.presenter = this
        binding.shoe = if(isNewShoe) Shoe() else shoeViewModel.getShoe(args.shoeId)
        return binding.root
    }

    /**
     * [ShoeDetailPresenter.onSaveSelected] event add the shoe to the list in the viewmodel and navigates up
     */
    override fun onSaveSelected(shoe: Shoe) {
        if(isNewShoe) shoeViewModel.addShoe(shoe)
        findNavController().navigateUp()
        Timber.i(if(isNewShoe) "User create a shoe" else "User update a shoe")
    }

    /**
     * [ShoeDetailPresenter.loadImage] event attempts to load an image from the gallery
     */
    override fun loadImage() {
        val galleryIntent = Intent(Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, GALLERY_IMAGE)
    }

    /**
     * [ShoeDetailPresenter.loadImage] if the image was selected successfully is added to de shoe and print it on screen
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_CANCELED) { return }
        if (requestCode == GALLERY_IMAGE) {
            if (data != null) {
                val contentURI = data.data
                contentURI?.let {uri->
                    activity?.let {activity->
                        val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                            val imageSource = ImageDecoder.createSource(activity.contentResolver, uri)
                            ImageDecoder.decodeBitmap(imageSource)
                        }else{
                            @Suppress("DEPRECATION")//When the min api level >= 28 remove else
                            MediaStore.Images.Media.getBitmap(activity.contentResolver, uri)
                        }
                        val imageView = ImageView(context)
                        imageView.setImageBitmap(bitmap)
                        binding.shoe?.images?.add(bitmap)
                        binding.shoe = binding.shoe
                    }
                }
            }
        }
    }

    /**
     * [ShoeDetailPresenter.onBack] go back to the shoe list
     */
    override fun onBack() {
        findNavController().navigateUp()
    }

}

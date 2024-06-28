package com.example.termproject

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

private const val ARG_IMAGE = "image"

class ImageFragment : Fragment() {

    private var image: ByteArray? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            image = it.getByteArray(ARG_IMAGE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_image, container, false)
        val imageView: ImageView = view.findViewById(R.id.imageView)

        image?.let {
            val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
            imageView.setImageBitmap(bitmap)
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(image: ByteArray) =
            ImageFragment().apply {
                arguments = Bundle().apply {
                    putByteArray(ARG_IMAGE, image)
                }
            }
    }
}

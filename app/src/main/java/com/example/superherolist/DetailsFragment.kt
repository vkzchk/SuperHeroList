package com.example.superherolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

class DetailsFragment : Fragment() {

    private lateinit var rootView: View
    private lateinit var itemImage: ImageView
    private lateinit var detailItemTitle: TextView
    private lateinit var detailItemGender: TextView
    private lateinit var detailItemAlterEgos: TextView
    private lateinit var detailItemGroupAffiliation: TextView
    private lateinit var detailItemRelatives: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.details_fragment_layout, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews()
    }

    private fun initializeViews() {
        itemImage = rootView.findViewById(R.id.itemImage)
        detailItemTitle = rootView.findViewById(R.id.detailItemTitle)
        detailItemGender = rootView.findViewById(R.id.detailItemGender)
        detailItemAlterEgos = rootView.findViewById(R.id.detailItemAlterEgos)
        detailItemGroupAffiliation = rootView.findViewById(R.id.detailItemGroupAffiliation)
        detailItemRelatives = rootView.findViewById(R.id.detailItemRelatives)
    }

    fun setDetails(details: Details) {
        Glide.with(this)
            .load(details.imageUrl)
            .into(itemImage)
        detailItemTitle.text = details.fullName
        detailItemGender.text = details.gender
        detailItemAlterEgos.text = details.alterEgos
        detailItemGroupAffiliation.text = details.groupAffiliation
        detailItemRelatives.text = details.relatives
    }
}

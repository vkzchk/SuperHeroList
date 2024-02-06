package com.example.superherolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ListFragment : Fragment() {

    private lateinit var myAdapter: RecyclerViewAdapter

    private var onItemClick: (String, Details) -> Unit = { _, _ -> }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_item_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        myAdapter = RecyclerViewAdapter(mutableListOf()) { name, details ->
            onItemClick(name, details)
        }
        recyclerView.adapter = myAdapter
    }

    fun setSuperHeroes(superHeroes: List<SuperHeroTransformResponse>) {
        myAdapter.setItems(superHeroes)
    }

    fun setOnItemClickListener(listener: (String, Details) -> Unit) {
        onItemClick = listener
    }
}


package com.example.superherolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val apiClient = ApiClient.client.create(ApiInterface::class.java)

        apiClient.getSuperHeroes()
            .subscribeOn(Schedulers.io())
            .map { res -> transformResponse(res) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val myAdapter = RecyclerViewAdapter(it) {
                    Toast.makeText(this, "${it} clicked", Toast.LENGTH_SHORT).show()
                }
                recyclerView.adapter = myAdapter
            }, {
                Toast.makeText(this, "Fetch error ${it.message}", Toast.LENGTH_LONG).show()
            })
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}

fun transformResponse(res: SuperHeroResponse): MutableList<SuperHeroTransformResponse> {
    val items: MutableList<SuperHeroTransformResponse> = mutableListOf()
    for (hero in res.heroes) {
        val name = hero.name
        val work = hero.work.occupation
        val image = hero.images.sm
        items.add(SuperHeroTransformResponse(name, work, image))
    }

    return items
}
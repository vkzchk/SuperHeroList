package com.example.superherolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listFragment = supportFragmentManager.findFragmentById(R.id.recyclerView) as ListFragment
        val detailsFragment = supportFragmentManager.findFragmentById(R.id.detailsFragmentContainer) as? DetailsFragment
        val apiClient = ApiClient.client.create(ApiInterface::class.java)

        apiClient.getSuperHeroes()
            .subscribeOn(Schedulers.io())
            .map { res -> transformResponse(res) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                listFragment.setSuperHeroes(it)
            }, {
                Toast.makeText(this, "Fetch error ${it.message}", Toast.LENGTH_LONG).show()
            })
        listFragment.setOnItemClickListener{_, details ->
            if (detailsFragment!= null) {
                detailsFragment.setDetails(details)
            } else {
                val newDetailsFragment = DetailsFragment()
                newDetailsFragment.setDetails(details)
                supportFragmentManager.beginTransaction()
                    .add(R.id.detailsFragmentContainer, newDetailsFragment)
                    .addToBackStack("details_fragment")
                    .commit()
            }
        }
    }
}


fun transformResponse(res: List<SuperHeroResponse>): MutableList<SuperHeroTransformResponse> {
    val items: MutableList<SuperHeroTransformResponse> = mutableListOf()
    res.forEach {
        val name = it.name
        val work = it.work.occupation
        val image = it.images.sm
        val fullName = it.biography.fullName
        val gender = it.appearance.gender
        val alterEgos = it.biography.alterEgos
        val imageUrl = it.images.lg
        val groupAffiliation = it.connections.groupAffiliation
        val relatives = it.connections.relatives
        val details = Details(fullName, gender,alterEgos,imageUrl,groupAffiliation,relatives)
        items.add(SuperHeroTransformResponse(name, work, image,details))
    }

    return items
}
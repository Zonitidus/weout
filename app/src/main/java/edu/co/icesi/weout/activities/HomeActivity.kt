package edu.co.icesi.weout.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.co.icesi.weout.databinding.ActivityHomeBinding
import edu.co.icesi.weout.recycler.categoria.CategoriaAdapter
import edu.co.icesi.weout.recycler.expanded.ExpandedAdapter
import edu.co.icesi.weout.recycler.promociones.PromocionesAdapter
import edu.co.icesi.weout.recycler.recomendado.RecomendadoAdapter
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.view.*

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding



    private lateinit var layoutManagerVertical: LinearLayoutManager
    private lateinit var layoutManagerHorizontal: LinearLayoutManager

    private lateinit var promocionesAdapter: PromocionesAdapter
    private lateinit var categoriaAdapter: CategoriaAdapter
    private lateinit var recomendadoAdapter: RecomendadoAdapter
    private lateinit var expandedAdapter: ExpandedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        layoutManagerHorizontal = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        layoutManagerVertical = LinearLayoutManager(this)

        promocionesRecycler.layoutManager = layoutManagerHorizontal
        promocionesRecycler.setHasFixedSize(true)
        categoriasRecycler.layoutManager = layoutManagerHorizontal
        categoriasRecycler.setHasFixedSize(true)
        recomendadoRecycler.layoutManager = layoutManagerVertical
        recomendadoRecycler.setHasFixedSize(true)
        expandedRecycler.layoutManager = layoutManagerVertical
        expandedRecycler.setHasFixedSize(false)

        promocionesAdapter = PromocionesAdapter()
        categoriaAdapter = CategoriaAdapter()
        recomendadoAdapter = RecomendadoAdapter()
        expandedAdapter = ExpandedAdapter()

        promocionesRecycler.adapter = promocionesAdapter
        categoriasRecycler.adapter = categoriaAdapter
        recomendadoRecycler.adapter = recomendadoAdapter
        expandedRecycler.adapter = expandedAdapter



    }
}
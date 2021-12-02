package edu.co.icesi.weout.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import edu.co.icesi.weout.R
import edu.co.icesi.weout.recycler.categoria.CategoriaAdapter
import edu.co.icesi.weout.recycler.expanded.ExpandedAdapter
import edu.co.icesi.weout.recycler.promociones.PromocionesAdapter
import edu.co.icesi.weout.recycler.recomendado.RecomendadoAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var layoutManagerVertical: LinearLayoutManager
    private lateinit var layoutManagerHorizontal: LinearLayoutManager

    private lateinit var promocionesAdapter: PromocionesAdapter
    private lateinit var categoriaAdapter: CategoriaAdapter
    private lateinit var recomendadoAdapter: RecomendadoAdapter
    private lateinit var expandedAdapter: ExpandedAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var vista = inflater.inflate(R.layout.fragment_home, container, false)

        layoutManagerHorizontal = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        layoutManagerVertical = LinearLayoutManager(activity)

        promocionesAdapter = PromocionesAdapter()
        categoriaAdapter = CategoriaAdapter()
        recomendadoAdapter = RecomendadoAdapter()
        expandedAdapter = ExpandedAdapter()

        promocionesRecycler.adapter = promocionesAdapter
        categoriasRecycler.adapter = categoriaAdapter
        recomendadoRecycler.adapter = recomendadoAdapter
        expandedRecycler.adapter = expandedAdapter

        promocionesRecycler.layoutManager = layoutManagerHorizontal
        promocionesRecycler.setHasFixedSize(true)
        categoriasRecycler.layoutManager = layoutManagerHorizontal
        categoriasRecycler.setHasFixedSize(true)
        recomendadoRecycler.layoutManager = layoutManagerVertical
        recomendadoRecycler.setHasFixedSize(true)
        expandedRecycler.layoutManager = layoutManagerVertical
        expandedRecycler.setHasFixedSize(false)

        return vista
        //return super.onCreateView(inflater, container, savedInstanceState)
    }


    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}
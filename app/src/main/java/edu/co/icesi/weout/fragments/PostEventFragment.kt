package edu.co.icesi.weout.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.co.icesi.weout.R
import edu.co.icesi.weout.databinding.FragmentPostEventBinding

class PostEventFragment : Fragment() {

    private var _binding: FragmentPostEventBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPostEventBinding.inflate(inflater, container, false)
        val view = binding.root


        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = PostEventFragment()
    }
}
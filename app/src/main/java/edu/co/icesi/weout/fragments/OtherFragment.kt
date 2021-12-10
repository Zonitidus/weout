package edu.co.icesi.weout.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import edu.co.icesi.weout.R
import edu.co.icesi.weout.activities.AdvertiseActivity
import edu.co.icesi.weout.activities.PostInfoActivity
import edu.co.icesi.weout.activities.SupportActivity
import edu.co.icesi.weout.databinding.FragmentOtherBinding
import edu.co.icesi.weout.databinding.FragmentPostEventBinding

class OtherFragment : Fragment() {

    private var _binding: FragmentOtherBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOtherBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.adminPostBttn.setOnClickListener{

        }

        binding.logoutBttn.setOnClickListener{

        }

        binding.pautaBttn.setOnClickListener{
            val intent = Intent(it.context, AdvertiseActivity::class.java).apply {
            }
            ContextCompat.startActivity(it.context, intent, null)
        }

        binding.supportBttn.setOnClickListener{
            val intent = Intent(it.context, SupportActivity::class.java).apply {
            }
            ContextCompat.startActivity(it.context, intent, null)
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = OtherFragment()
    }
}
package edu.co.icesi.weout.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.facebook.login.LoginManager
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import edu.co.icesi.weout.R
import edu.co.icesi.weout.activities.*
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


        binding.logoutBttn.setOnClickListener{

            val provider = (activity as HomeActivity).getUserProvider().name

            if(provider.equals(ProviderType.FACEBOOK.name)){
                LoginManager.getInstance().logOut()
            }else if (provider.equals(ProviderType.BASIC.name)){
                Firebase.auth.signOut()
            }

            val sp = (activity as HomeActivity).getSharedPreferences("user", Context.MODE_PRIVATE)
            sp.edit().clear().apply()

            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)

            (activity as HomeActivity).finish()

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
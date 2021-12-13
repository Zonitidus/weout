package edu.co.icesi.weout.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.co.icesi.weout.R
import edu.co.icesi.weout.activities.LikedPostsActivity
import edu.co.icesi.weout.activities.MyPostListActivity
import edu.co.icesi.weout.activities.PostInfoActivity
import edu.co.icesi.weout.databinding.FragmentNotificationBinding
import edu.co.icesi.weout.databinding.FragmentPerfilBinding
import edu.co.icesi.weout.model.Notification
import edu.co.icesi.weout.model.User

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class PerfilFragment : Fragment() {

    private lateinit var binding : FragmentPerfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentPerfilBinding.inflate(inflater, container, false)

        val sharedPreferences = requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getString("userId", "")

        val query = Firebase.firestore.collection("users").document(userId!!).get()

        query.addOnCompleteListener {

            val user : User = it.result!!.toObject(User::class.java)!!

            binding.perfilNameTV.text = "${user.name} ${user.lastName}"
            binding.perfilEmailTV.text = "${user.email}"
            binding.perfilPhoneTV.text = "${user.phone}"

        }

        binding.publicacionesBtn.setOnClickListener {

            val intent = Intent(it.context, MyPostListActivity::class.java).apply {
                putExtra("userid", userId)
            }
            ContextCompat.startActivity(it.context, intent, null)

        }

        binding.likedBtn.setOnClickListener {

            val intent = Intent(it.context, LikedPostsActivity::class.java).apply {
                putExtra("userid", userId)
            }
            ContextCompat.startActivity(it.context, intent, null)

        }

        return binding.root
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PerfilFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
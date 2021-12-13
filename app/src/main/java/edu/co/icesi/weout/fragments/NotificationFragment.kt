package edu.co.icesi.weout.fragments

import android.app.DatePickerDialog
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
import edu.co.icesi.weout.activities.EventMapActivity
import edu.co.icesi.weout.activities.HomeActivity
import edu.co.icesi.weout.databinding.FragmentNotificationBinding
import edu.co.icesi.weout.databinding.FragmentPostEventBinding
import edu.co.icesi.weout.databinding.FragmentPostsListBinding
import edu.co.icesi.weout.model.Notification
import edu.co.icesi.weout.model.Post
import edu.co.icesi.weout.notification.NotificationAdapter
import edu.co.icesi.weout.post.PostAdapter

class NotificationFragment : Fragment() {

    private lateinit var binding : FragmentNotificationBinding

    private val adapter = NotificationAdapter()

    private lateinit var notifications : ArrayList<Notification>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentNotificationBinding.inflate(inflater, container, false)

        val sharedPreferences = requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getString("userId", "")

        val recycler = binding.notificationsRV

        recycler.setHasFixedSize(false)
        recycler.layoutManager = LinearLayoutManager(this.context)
        recycler.adapter = adapter
        recycler.smoothScrollToPosition(0)

        notifications = ArrayList()

        val query = Firebase.firestore.collection("users").document(userId!!).collection("notifications")
            .get()

        query.addOnCompleteListener {

            if (it.result?.size() != 0) {

                for (document in it.result!!) {

                    var notification = document.toObject(Notification::class.java)
                    adapter.addNotification(notification)
                    adapter.notifyDataSetChanged()

                }

            }

        }


        return binding.root
    }

    override fun onResume() {
        super.onResume()

        val sharedPreferences = requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getString("userId", "")

        val query = Firebase.firestore.collection("users").document(userId!!).collection("notifications")
            .get()

        query.addOnCompleteListener {



            if (it.result?.size() != 0) {

                adapter.clear()

                for (document in it.result!!) {

                    var notification = document.toObject(Notification::class.java)
                    adapter.addNotification(notification)
                    adapter.notifyDataSetChanged()

                }

            }

        }

    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PostsListFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }


}
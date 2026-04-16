package com.example.notingapp.fragments

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.notingapp.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RequestsFragment : Fragment() {

    private lateinit var container: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        container = LinearLayout(requireContext())
        container.orientation = LinearLayout.VERTICAL

        return container
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val repo = NoteRepository(requireContext())

        lifecycleScope.launch(Dispatchers.IO) {

            val requests = repo.getRequests()

            launch(Dispatchers.Main) {

                requests.forEach { req ->

                    val text = TextView(requireContext())
                    text.text = "${req.fromUserId} wants to share note"

                    val accept = TextView(requireContext())
                    accept.text = "Accept"
                    accept.setOnClickListener {
                        lifecycleScope.launch {
                            repo.acceptRequest(req._id!!)
                        }
                    }

                    val reject = TextView(requireContext())
                    reject.text = "Reject"
                    reject.setOnClickListener {
                        lifecycleScope.launch {
                            repo.rejectRequest(req._id!!)
                        }
                    }

                    container.addView(text)
                    container.addView(accept)
                    container.addView(reject)
                }
            }
        }
    }
}
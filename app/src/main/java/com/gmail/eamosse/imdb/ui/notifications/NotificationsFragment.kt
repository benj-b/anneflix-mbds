package com.gmail.eamosse.imdb.ui.notifications

import android.os.Bundle
import android.text.util.Linkify
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.gmail.eamosse.imdb.R

class NotificationsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)

        val profilesTextView: TextView = root.findViewById(R.id.text_team_profiles)

        Linkify.addLinks(profilesTextView, Linkify.WEB_URLS)

        return root
    }
}

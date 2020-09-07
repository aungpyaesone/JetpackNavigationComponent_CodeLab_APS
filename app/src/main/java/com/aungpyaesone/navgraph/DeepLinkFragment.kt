package com.aungpyaesone.navgraph

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_deep_link.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DeepLinkFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DeepLinkFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_deep_link, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DeepLinkFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DeepLinkFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            showText.text = arguments?.getString("myarg")

        send_notification_button.setOnClickListener {
        val args = Bundle()
        args.putString("myargs",args_edit_text.text.toString())

            val deepLink = findNavController().createDeepLink()
                .setDestination(R.id.deepLinkFragment)
                .setArguments(args)
                .createPendingIntent()

            val notificationManager =context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationManager.createNotificationChannel(
                    NotificationChannel(
                    "deeplink", "Deep Links", NotificationManager.IMPORTANCE_HIGH)
                )
            }

            val builder = NotificationCompat.Builder(
                 requireContext(), "deeplink")
                .setContentTitle("Navigation")
                .setContentText("Deep link to Android")
                .setSmallIcon(R.drawable.ic_baseline_android_24)
                .setContentIntent(deepLink)
                .setAutoCancel(true)
            notificationManager.notify(0, builder.build())
        }

        }


    }
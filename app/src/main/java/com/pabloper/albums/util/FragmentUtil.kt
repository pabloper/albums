package com.pabloper.albums.util

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class FragmentUtil(private val activity: AppCompatActivity) {

    fun addFragment(
        fragmentContainerId: Int,
        fragment: Fragment,
        tag: String,
        addToBackStack: Boolean
    ) {
        val transaction = activity.supportFragmentManager.beginTransaction()

        if (!fragment.isAdded) {
            if (addToBackStack) {
                transaction.addToBackStack(tag).replace(fragmentContainerId, fragment, tag)
            } else {
                transaction.replace(fragmentContainerId, fragment, tag)
            }
            transaction.commit()
        }
    }
}
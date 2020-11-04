package com.pabloper.albums.util

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.pabloper.albums.R

class FragmentUtil(private val activity: AppCompatActivity) {

    private fun addFragmentIfNotPresent(
        fragmentContainerId: Int,
        fragment: Fragment,
        tag: String,
        addToBackStack: Boolean
    ) {
        val transaction = activity.supportFragmentManager.beginTransaction()
        if (activity.supportFragmentManager.findFragmentByTag(tag) == null) {
            if (addToBackStack) {
                transaction.addToBackStack(tag).replace(fragmentContainerId, fragment, tag)
            } else {
                transaction.replace(fragmentContainerId, fragment, tag)
            }
            transaction.commit()
        }
    }

    fun addFragmentDefault(
        fragment: Fragment,
        tag: String,
        addToBackStack: Boolean
    ) {
        addFragmentIfNotPresent(R.id.fragment, fragment, tag, addToBackStack)
    }
}
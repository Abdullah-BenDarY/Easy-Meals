package com.example.medicalapp.util

import android.content.Context
import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar


fun Fragment.showToast (massage : Any?) {
    Toast.makeText(requireContext(), "$massage", Toast.LENGTH_LONG).show()
}
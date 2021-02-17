package br.com.julianawl.movieteca.extensions

import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.julianawl.movieteca.R

fun Fragment.onError (){
    Toast.makeText(context,
        getString(R.string.error_fetch_movies),
        Toast.LENGTH_SHORT)
        .show()
}
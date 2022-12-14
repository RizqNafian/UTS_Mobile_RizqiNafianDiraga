/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.RizqiNafianDiraga

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class GenreAdapter(context: Context) :
    RecyclerView.Adapter<GenreAdapter.LetterViewHolder>() {

    // Mengambil isi list dari array genre
    private val list = context.resources.getStringArray(R.array.genre).toList()

    // Menampilkan data dari list array ke button
    class LetterViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val button = view.findViewById<Button>(R.id.button_item)
    }

    // Mneghitung total ukuran list array
    override fun getItemCount(): Int {
        return list.size
    }

    // Membuat tampilan layout untuk item view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LetterViewHolder {
        val layout = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_view, parent, false)
        layout.accessibilityDelegate = Accessibility
        return LetterViewHolder(layout)
    }

    // Menganti isi tampilan dengan data baru
    override fun onBindViewHolder(holder: LetterViewHolder, position: Int) {
        val item = list.get(position)
        holder.button.text = item.toString()
        // menambahkan OnClickListener untuk tombol viewholder
        holder.button.setOnClickListener {
            // menghubungkan Genrelist ke Moviellist
            val action = GenreListFragmentDirections.actionGenreListFragmentToMovieListFragment(letter = holder.button.text.toString())
            holder.view.findNavController().navigate(action)
        }
    }

    // mengatur akses delegasi dengan akses service
    companion object Accessibility : View.AccessibilityDelegate() {
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun onInitializeAccessibilityNodeInfo(
            host: View,
            info: AccessibilityNodeInfo
        ) {
            super.onInitializeAccessibilityNodeInfo(host, info)
            val customString = host.context?.getString(R.string.look_up_words)
            val customClick =
                AccessibilityNodeInfo.AccessibilityAction(
                    AccessibilityNodeInfo.ACTION_CLICK,
                    customString
                )
            info.addAction(customClick)
        }
    }
}
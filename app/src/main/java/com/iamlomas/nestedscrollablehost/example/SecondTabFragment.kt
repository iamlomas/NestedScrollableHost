package com.iamlomas.nestedscrollablehost_sample

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iamlomas.nestedscrollablehost.example.ListRecyclerViewAdapter
import com.iamlomas.nestedscrollablehost.example.R

class SecondTabFragment : Fragment(R.layout.fragment_second) {

    private lateinit var myRecyclerView: RecyclerView
    private lateinit var listRecyclerViewAdapter: ListRecyclerViewAdapter
    private lateinit var myRecyclerViewLayoutManager: RecyclerView.LayoutManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myRecyclerView = this.requireView().findViewById(R.id.rvShinobies)
        listRecyclerViewAdapter = ListRecyclerViewAdapter(DataSet.shinobiList)
        myRecyclerViewLayoutManager = LinearLayoutManager(context)
        val btnAdd = this.requireView().findViewById<Button>(R.id.btnAdd)
        val etName = this.requireView().findViewById<EditText>(R.id.etName)

        myRecyclerView.apply {
            setHasFixedSize(true)
            adapter = listRecyclerViewAdapter
            layoutManager = myRecyclerViewLayoutManager
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        btnAdd.setOnClickListener {
            val name = etName.text.toString()
            val newShinobi = Shinobi(name, false)
            DataSet.shinobiList.add(newShinobi)
            listRecyclerViewAdapter.notifyItemInserted(DataSet.shinobiList.lastIndex)
        }
    }
}

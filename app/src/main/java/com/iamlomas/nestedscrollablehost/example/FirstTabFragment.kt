package com.iamlomas.nestedscrollablehost.example

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iamlomas.nestedscrollablehost_sample.DataSet


class FirstTabFragment : Fragment(R.layout.fragment_first) {

    private lateinit var horizontalRecyclerView: RecyclerView
    private lateinit var verticalRecyclerView: RecyclerView
    private lateinit var horizontalRecyclerViewLayoutManager: RecyclerView.LayoutManager
    private lateinit var verticalRecyclerViewLayoutManager: RecyclerView.LayoutManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Adapters
        val horizontalImageAdapter =
            ImageRecyclerViewAdapter(DataSet.imageList, RecyclerView.HORIZONTAL)
        val verticalImageAdapter =
            ImageRecyclerViewAdapter(DataSet.imageList, RecyclerView.VERTICAL)

        // RecyclerViews
        horizontalRecyclerView = this.requireView().findViewById(R.id.rvHorizontal)
        verticalRecyclerView = this.requireView().findViewById(R.id.rvVertical)

        // RecyclerView layout managers
        horizontalRecyclerViewLayoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        verticalRecyclerViewLayoutManager = LinearLayoutManager(context)


        horizontalRecyclerView.apply {
            adapter = horizontalImageAdapter
            layoutManager = horizontalRecyclerViewLayoutManager
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    super.getItemOffsets(outRect, view, parent, state)
                    val itemPosition = parent.getChildAdapterPosition(view)

                    if (itemPosition >= 0) {
                        if (itemPosition == 0) {
                            outRect.left = 20
                        }
                        outRect.right = 20
                    }
                }
            })
        }

        verticalRecyclerView.apply {
            adapter = verticalImageAdapter
            layoutManager = verticalRecyclerViewLayoutManager
            setPadding(10, 0, 10, 0)
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    super.getItemOffsets(outRect, view, parent, state)
                    val itemPosition = parent.getChildAdapterPosition(view)

                    if (itemPosition >= 0) {
                        outRect.bottom = 20
                    }
                }
            })
        }
    }
}

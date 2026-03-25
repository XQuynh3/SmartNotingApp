package com.example.notingapp.adapter

import android.graphics.*
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.max

abstract class SwipeToDeleteCallback :
    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

    private val background = Paint().apply {
        color = Color.parseColor("#FF5252")
    }

    private val iconPaint = Paint().apply {
        color = Color.WHITE
        textSize = 60f
        textAlign = Paint.Align.CENTER
    }

    private val maxSwipe = 220f

    // lưu trạng thái item đang mở
    private var openedPosition = RecyclerView.NO_POSITION

    abstract fun onDeleteClicked(position: Int)

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ) = false

    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {
        return 2f
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        // không dùng
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {

        val itemView = viewHolder.itemView
        val position = viewHolder.adapterPosition

        var dx = max(-maxSwipe, dX)

        // nếu item đã mở → giữ nguyên
        if (!isCurrentlyActive && openedPosition == position) {
            dx = -maxSwipe
        }

        // vẽ nền đỏ
        val rect = RectF(
            itemView.right + dx,
            itemView.top.toFloat(),
            itemView.right.toFloat(),
            itemView.bottom.toFloat()
        )
        c.drawRect(rect, background)

        // vẽ icon
        val iconX = itemView.right - 110f
        val iconY = itemView.top + itemView.height / 2f + 20
        c.drawText("🗑", iconX, iconY, iconPaint)

        itemView.translationX = dx

        // khi thả tay → nếu swipe đủ thì giữ mở
        if (!isCurrentlyActive) {
            if (dx <= -120) {
                openedPosition = position
                itemView.translationX = -maxSwipe
            } else if (openedPosition != position) {
                itemView.translationX = 0f
            }
        }
    }

    fun handleTouch(event: MotionEvent, recyclerView: RecyclerView) {

        if (event.action == MotionEvent.ACTION_UP) {

            val view: View? = recyclerView.findChildViewUnder(event.x, event.y)

            if (view != null) {

                val position = recyclerView.getChildAdapterPosition(view)

                if (position == openedPosition) {

                    // nếu click vùng đỏ → delete
                    if (event.x > view.right - maxSwipe) {
                        onDeleteClicked(position)
                        openedPosition = RecyclerView.NO_POSITION
                    } else {
                        // click item → đóng swipe
                        view.animate().translationX(0f).setDuration(200).start()
                        openedPosition = RecyclerView.NO_POSITION
                    }
                }
            }
        }
    }
}
package com.iamlomas.nestedscrollablehost.nestedscrollablehost_library

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.widget.FrameLayout
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.absoluteValue
import kotlin.math.sign

class NestedScrollableHost : FrameLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    private var touchSlop = 0
    private var initialX = 0f
    private var initialY = 0f
    private val parentViewPager: ViewPager2?
        get() {
            var view: View? = parent as? View
            while (view != null && view !is ViewPager2) {
                view = view.parent as? View
            }
            return view as? ViewPager2
        }

    private val child: View? get() = if (childCount > 0) getChildAt(0) else null

    init {
        touchSlop = ViewConfiguration.get(context).scaledTouchSlop
    }

    private fun canChildScroll(orientation: Int, delta: Float): Boolean {
        val direction = -delta.sign.toInt()
        return when (orientation) {
            0 -> child?.canScrollHorizontally(direction) ?: false
            1 -> child?.canScrollVertically(direction) ?: false
            else -> throw IllegalArgumentException()
        }
    }

    override fun onInterceptTouchEvent(motionEvent: MotionEvent?): Boolean {
        handleInterceptTouchEvent(motionEvent)
        return super.onInterceptTouchEvent(motionEvent)
    }

    private fun handleInterceptTouchEvent(motionEvent: MotionEvent?) {
        val orientation = parentViewPager?.orientation ?: return

        when {
            // Early return if child can't scroll in the same direction as parent
            !canChildScroll(orientation, -1f) && !canChildScroll(orientation, 1f) -> {
                return
            }
            motionEvent?.action == MotionEvent.ACTION_DOWN -> {
                initialX = motionEvent.x
                initialY = motionEvent.y
                parent.requestDisallowInterceptTouchEvent(true)
            }
            motionEvent?.action == MotionEvent.ACTION_MOVE -> {
                val dx = motionEvent.x - initialX
                val dy = motionEvent.y - initialY
                val isViewPager2Horizontal = orientation == ViewPager2.ORIENTATION_HORIZONTAL

                // Assuming ViewPager2 touch-slop is 2x touch-slop of child
                val scaledDx = dx.absoluteValue * if (isViewPager2Horizontal) .5f else 1f
                val scaledDy = dx.absoluteValue * if (isViewPager2Horizontal) 1f else .5f

                if (scaledDx > touchSlop || scaledDy > touchSlop) {
                    if (isViewPager2Horizontal == (scaledDy > scaledDx)) {
                        // Gesture is perpendicular, allow all parent to intercept
                        parent.requestDisallowInterceptTouchEvent(false)
                    } else {
                        // Gesture is parallel, query child if movement in that direction is possible
                        if (canChildScroll(orientation, if (isViewPager2Horizontal) dx else dy)) {
                            // Child can scroll, disallow all parent to intercept
                            parent.requestDisallowInterceptTouchEvent(true)
                        } else {
                            // Child cannot scroll, allow all parent to intercept
                            parent.requestDisallowInterceptTouchEvent(false)
                        }
                    }
                }
            }
        }
    }
}

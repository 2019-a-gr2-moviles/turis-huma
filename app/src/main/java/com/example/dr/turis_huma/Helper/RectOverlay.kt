package com.example.dr.turis_huma.Helper

import android.graphics.*

class RectOverlay internal constructor(overlay: GraphicOverlay,
                                       private val bound: Rect?): GraphicOverlay.Graphic(overlay){

    private val rectPaint:Paint

    init {
        rectPaint = Paint()
        rectPaint.color = RECT_COLOR
        rectPaint.strokeWidth = STROKE_WIDHT
        rectPaint.style = Paint.Style.STROKE

    }

    companion object {

        private var RECT_COLOR = Color.YELLOW
        private var STROKE_WIDHT = 8.0f

    }

    override fun draw(canvas: Canvas) {

        val rect = RectF(bound)
        canvas.drawRect(rect, rectPaint)

    }
}
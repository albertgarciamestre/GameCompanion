package com.game.gamecompanion.masktransformation

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.game.gamecompanion.R
import com.squareup.picasso.Transformation


class MaskTransformation(
    private val context: Context,
    @DrawableRes private val maskID: Int
) : Transformation {

    override fun key(): String {
        return "mask"
    }

    override fun transform(source: Bitmap): Bitmap {
        val width = source.width
        val height = source.height
        val borderWidth = 150

        val output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(output)

        //Draw a full size, red squircle
        val paint = Paint()
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        paint.color = ContextCompat.getColor(context, R.color.itemColor)
        val mask = ContextCompat.getDrawable(context, maskID)!!
        mask.setBounds(0, 0, width, height)
        mask.draw(canvas)
        canvas.drawPaint(paint)

        //Draw a masked, scaled down bitmap of the photo on top
        val maskingPaint = Paint()
        maskingPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        val maskDrawable = ContextCompat.getDrawable(context, maskID)!!
        maskDrawable.setBounds(borderWidth / 2, borderWidth / 2, width - borderWidth / 2, height - borderWidth / 3)

        val overlayBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val overlayCanvas = Canvas(overlayBitmap)
        maskDrawable.draw(overlayCanvas)

        val pictureBitmap = Bitmap.createBitmap(width - borderWidth, height - borderWidth, Bitmap.Config.ARGB_8888)
        val pictureCanvas = Canvas(pictureBitmap)

        val sourceDrawable = BitmapDrawable(context.resources, source)
        sourceDrawable.setBounds(borderWidth / 2, borderWidth / 2, width - borderWidth / 2, height - borderWidth / 2)
        pictureCanvas.drawBitmap(
            sourceDrawable.bitmap,
            null,
            Rect(0, 0, width - borderWidth, height - borderWidth),
            Paint()
        )

        overlayCanvas.drawBitmap(pictureBitmap, (borderWidth / 2).toFloat(), (borderWidth / 2).toFloat(), maskingPaint)

        canvas.drawBitmap(overlayBitmap, 0f, 0f, Paint())

        source.recycle()

        return output
    }

}
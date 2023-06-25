package com.hust.cartracking.core.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

/********************
 * @Author: Tiiee
 * @Date: 6/15/2023
 * @Time: 10:46 PM
 ********************/

fun bitmapDescriptor(
	context: Context,
	vectorResId: Int
): BitmapDescriptor? {
	
	// retrieve the actual drawable
	val drawable = ContextCompat.getDrawable(context, vectorResId) ?: return null
	drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
	val bm = Bitmap.createBitmap(
		drawable.intrinsicWidth,
		drawable.intrinsicHeight,
		Bitmap.Config.ARGB_8888
	)
	
	// draw it onto the bitmap
	val canvas = android.graphics.Canvas(bm)
	drawable.draw(canvas)
	return BitmapDescriptorFactory.fromBitmap(bm)
}

//Get Bitmap from Url
suspend fun getBitmapFromURL(imgUrl: String?): Bitmap? =
	withContext(Dispatchers.IO) {
		try {
			val url = URL(imgUrl)
			val connection: HttpURLConnection =
				url.openConnection() as HttpURLConnection
			connection.doInput = true
			connection.connect()
			val input: InputStream = connection.inputStream
			BitmapFactory.decodeStream(input)
		} catch (e: IOException) {
			Timber.v(e.message.toString())
			null
		}
	}

fun getResizedBitmap(bm: Bitmap, newWidth: Int, newHeight: Int): Bitmap? {
	val width = bm.width
	val height = bm.height
	val scaleWidth = newWidth.toFloat() / width
	val scaleHeight = newHeight.toFloat() / height
	val matrix = Matrix() // CREATE A MATRIX FOR THE MANIPULATION
	matrix.postScale(scaleWidth, scaleHeight) // RESIZE THE BIT MAP
	val resizedBitmap =
		Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false) // "RECREATE" THE NEW BITMAP
	bm.recycle()
	return resizedBitmap
}

fun getRoundedCornerBitmap(bitmap: Bitmap): Bitmap? {
	val w = bitmap.width
	val h = bitmap.height
	val radius = (h / 2).coerceAtMost(w / 2)
	val output = Bitmap.createBitmap(w + 16, h + 16, Bitmap.Config.ARGB_8888)
	val paint = Paint()
	paint.isAntiAlias = true
	val canvas = Canvas(output)
	canvas.drawARGB(0, 0, 0, 0)
	paint.style = Paint.Style.FILL
	canvas.drawCircle((w / 2 + 8).toFloat(), (h / 2 + 8).toFloat(), radius.toFloat(), paint)
	paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
	canvas.drawBitmap(bitmap, 4f, 4f, paint)
	paint.xfermode = null
	paint.style = Paint.Style.STROKE
	paint.color = Color.argb(55f, 139f, 195f, 74f)
	paint.strokeWidth = 10f
	canvas.drawCircle((w / 2 + 8).toFloat(), (h / 2 + 8).toFloat(), radius.toFloat(), paint)
	return output
}
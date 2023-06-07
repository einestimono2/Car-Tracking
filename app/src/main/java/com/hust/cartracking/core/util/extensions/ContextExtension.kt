package com.hust.cartracking.core.util.extensions

/********************
 * @Author: Tiiee
 * @Date: 5/25/2023
 * @Time: 9:59 PM
 ********************/

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.StringRes

//fun Context.sendSharePostIntent(postId: String) {
//	val intent = Intent().apply {
//		action = Intent.ACTION_SEND
//		putExtra(
//			Intent.EXTRA_TEXT,
//			getString(
//				R.string.share_intent_text,
//				"https://pl-coding.com/$postId"
//			)
//		)
//		type = "text/plain"
//	}
//
//	if(intent.resolveActivity(packageManager) != null) {
//		startActivity(Intent.createChooser(intent, "Select an app"))
//	}
//}

fun Context.openUrlInBrowser(url: String) {
	val intent = Intent(Intent.ACTION_VIEW).apply {
		data = Uri.parse(url)
	}
	
	startActivity(Intent.createChooser(intent, "Select an app"))
}

fun Context.showKeyboard() {
	val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
	imm?.showSoftInput(null, InputMethodManager.SHOW_FORCED)
}

fun Context.showToast(
	@StringRes message: Int,
	isLengthLong: Boolean = false,
) {
	Toast.makeText(
		this,
		message,
		if (isLengthLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
	).show()
	
}

fun Context.showToast(
	message: String,
	isLengthLong: Boolean = false,
) {
	Toast.makeText(
		this,
		message,
		if (isLengthLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
	).show()
}
//
//private fun Context.displaySuccessDialog(
//	message: String?,
//) {
//	MaterialDialog(this)
//		.show{
//			title(R.string.text_success)
//			message(text = message)
//			positiveButton(R.string.text_ok){
//				stateMessageCallback.removeMessageFromStack()
//				dismiss()
//			}
//			onDismiss {
//			}
//			cancelable(false)
//		}
//}
//
//private fun Context.displayErrorDialog(
//	message: String?,
//	stateMessageCallback: StateMessageCallback
//) {
//	MaterialDialog(this)
//		.show{
//			title(R.string.text_error)
//			message(text = message)
//			positiveButton(R.string.text_ok){
//				stateMessageCallback.removeMessageFromStack()
//				dismiss()
//			}
//			onDismiss {
//			}
//			cancelable(false)
//		}
//}
//
//private fun Context.displayInfoDialog(
//	message: String?,
//	stateMessageCallback: StateMessageCallback
//) {
//	MaterialDialog(this)
//		.show{
//			title(R.string.text_info)
//			message(text = message)
//			positiveButton(R.string.text_ok){
//				stateMessageCallback.removeMessageFromStack()
//				dismiss()
//			}
//			onDismiss {
//			}
//			cancelable(false)
//		}
//}
//
//private fun Context.areYouSureDialog(
//	message: String,
//	callback: AreYouSureCallback,
//	stateMessageCallback: StateMessageCallback
//) {
//	MaterialDialog(this)
//		.show{
//			title(R.string.are_you_sure)
//			message(text = message)
//			negativeButton(R.string.text_cancel){
//				callback.cancel()
//				stateMessageCallback.removeMessageFromStack()
//				dismiss()
//			}
//			positiveButton(R.string.text_yes){
//				callback.proceed()
//				stateMessageCallback.removeMessageFromStack()
//				dismiss()
//			}
//			onDismiss {
//			}
//			cancelable(false)
//		}
//}
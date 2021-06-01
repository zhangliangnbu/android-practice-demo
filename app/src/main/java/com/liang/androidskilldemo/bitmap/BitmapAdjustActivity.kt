package com.liang.androidskilldemo.bitmap

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.liang.androidskilldemo.R
import kotlinx.android.synthetic.main.activity_bitmap_adjust.*
import utils.BitmapUtils
import utils.FileUtils

class BitmapAdjustActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_CODE_ADD_PICTURE = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bitmap_adjust)
        btn_choose_picture.setOnClickListener {
            addPicture()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ADD_PICTURE && data?.data != null) {
            val filePath = FileUtils.getFilePathByUri(this, data.data!!)
            val bitmap = BitmapFactory.decodeFile(filePath)
            handleBitmap(bitmap)
        }
    }

    private fun handleBitmap(bitmap: Bitmap) {
        // 原始
        printBitmapInfo("origin", bitmap)

        // 截取
        val ratio = 2 // h:w
        val cropRatio = bitmap.height * 1.0f / bitmap.width
        val cropBitmap = if (cropRatio <= ratio) {
            bitmap
        } else {
            val dh = ((cropRatio - ratio) * bitmap.width).toInt()
            Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height - dh)
        }
        printBitmapInfo("crop",cropBitmap)

        // 压缩
        val compressBitmap = BitmapUtils.compressByScale(cropBitmap, 2 * 1024 * 1024L)
        printBitmapInfo("compress", compressBitmap)
        iv_bitmap.setImageBitmap(compressBitmap)
    }

    private fun printBitmapInfo(tag: String, bitmap: Bitmap) {
        Log.d(tag, "大小=${bitmap.byteCount / 1024f / 1024f}MB, 宽=${bitmap.width}, 高=${bitmap.height}")
    }

    private fun addPicture() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false)
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_CODE_ADD_PICTURE)
    }
}

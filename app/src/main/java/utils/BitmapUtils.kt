package utils

import android.graphics.*
import java.io.ByteArrayOutputStream

/**
 * created by zhangliang on 2018/11/27
 * profile: zhangliangnbu@163.com
 */
object BitmapUtils {

    //将bitmap调整到指定大小
    fun sizeBitmap(origin: Bitmap?, newWidth: Int, newHeight: Int): Bitmap? {
        if (origin == null) {
            return null
        }
        val height = origin.height
        val width = origin.width
        val scaleWidth = newWidth.toFloat() / width
        val scaleHeight = newHeight.toFloat() / height
        val matrix = Matrix()
        matrix.postScale(scaleWidth, scaleHeight)// 使用后乘
        val newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false)
        if (!origin.isRecycled) {//这时候origin还有吗？
            origin.recycle()
        }
        return newBM
    }

    //按比例缩放
    fun scaleBitmap(origin: Bitmap?, scale: Float): Bitmap? {
        if (origin == null) {
            return null
        }
        val width = origin.width
        val height = origin.height
        val matrix = Matrix()
        matrix.preScale(scale, scale)
        val newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false)
        if (newBM == origin) {
            return newBM
        }
        origin.recycle()
        return newBM
    }

    fun cropBitmap(bitmap: Bitmap): Bitmap {//从中间截取一个正方形
        val w = bitmap.width // 得到图片的宽，高
        val h = bitmap.height
        val cropWidth = if (w >= h) h else w// 裁切后所取的正方形区域边长

        return Bitmap.createBitmap(bitmap, (bitmap.width - cropWidth) / 2,
                (bitmap.height - cropWidth) / 2, cropWidth, cropWidth)
    }

    fun getCircleBitmap(bitmap: Bitmap?): Bitmap? {//把图片裁剪成圆形
        var bitmap: Bitmap? = bitmap ?: return null
        bitmap = cropBitmap(bitmap!!)//裁剪成正方形
        try {
            val circleBitmap = Bitmap.createBitmap(bitmap.width,
                    bitmap.height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(circleBitmap)
            val paint = Paint()
            val rect = Rect(0, 0, bitmap.width,
                    bitmap.height)
            val rectF = RectF(Rect(0, 0, bitmap.width,
                    bitmap.height))
            var roundPx = 0.0f
            roundPx = bitmap.width.toFloat()
            paint.isAntiAlias = true
            canvas.drawARGB(0, 0, 0, 0)
            paint.color = Color.WHITE
            canvas.drawRoundRect(rectF, roundPx, roundPx, paint)
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
            val src = Rect(0, 0, bitmap.width,
                    bitmap.height)
            canvas.drawBitmap(bitmap, src, rect, paint)
            return circleBitmap
        } catch (e: Exception) {
            return bitmap
        }

    }

    fun compressByQuality(src: Bitmap,
                          maxByteSize: Long,
                          recycle: Boolean): Bitmap? {
        if (isEmptyBitmap(src) || maxByteSize <= 0) return null
        val baos = ByteArrayOutputStream()
        src.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val bytes: ByteArray
        if (baos.size() <= maxByteSize) {// 最好质量的不大于最大字节，则返回最佳质量
            bytes = baos.toByteArray()
        } else {
            baos.reset()
            src.compress(Bitmap.CompressFormat.JPEG, 0, baos)
            if (baos.size() >= maxByteSize) { // 最差质量不小于最大字节，则返回最差质量
                bytes = baos.toByteArray()
            } else {
                // 二分法寻找最佳质量
                var st = 0
                var end = 100
                var mid = 0
                while (st < end) {
                    mid = (st + end) / 2
                    baos.reset()
                    src.compress(Bitmap.CompressFormat.JPEG, mid, baos)
                    val len = baos.size()
                    if (len.toLong() == maxByteSize) {
                        break
                    } else if (len > maxByteSize) {
                        end = mid - 1
                    } else {
                        st = mid + 1
                    }
                }
                if (end == mid - 1) {
                    baos.reset()
                    src.compress(Bitmap.CompressFormat.JPEG, st, baos)
                }
                bytes = baos.toByteArray()
            }
        }
        if (recycle && !src.isRecycled) src.recycle()
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }

    private fun isEmptyBitmap(src: Bitmap?): Boolean {
        return src == null || src.width == 0 || src.height == 0
    }

    fun compressByScale(src: Bitmap, maxByteSize: Long): Bitmap {

        // 每次压缩比例
        val scale = 0.9f

        // 计算最终的缩放比
        var tempHeight = src.height
        var tempWidth = src.width
        while (tempHeight * tempWidth * 4 > maxByteSize) {
            tempHeight = (tempHeight * scale).toInt()
            tempWidth = (tempWidth * scale).toInt()
        }
        val finalHeightScale = tempHeight * 1.0f / src.height
        val finalWidthScale = tempWidth * 1.0f / src.width
        val matrix = Matrix()
        matrix.setScale(finalWidthScale, finalHeightScale)

        // 生成bitmap
        val finalBitmap = Bitmap.createBitmap(src, 0, 0, src.width, src.height, matrix, true)
//        if(!src.isRecycled) {
//            src.recycle()
//        }
        return finalBitmap
    }


}
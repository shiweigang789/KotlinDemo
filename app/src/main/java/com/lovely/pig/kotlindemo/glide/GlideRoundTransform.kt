package com.lovely.pig.kotlindemo.glide

import android.content.res.Resources
import android.graphics.*
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import java.security.MessageDigest

/**
 * 作者 swg
 * 时间 2019/6/3 11:13
 * 文件 KotlinDemo
 * 描述
 */
class GlideRoundTransform @JvmOverloads constructor(dp: Int = 4) : BitmapTransformation() {

    private var radius = 0f

    init {
        this.radius = Resources.getSystem().displayMetrics.density * dp
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {

    }

    override fun transform(pool: BitmapPool, toTransform: Bitmap, outWidth: Int, outHeight: Int): Bitmap {
        return roundCrop(pool, toTransform)
    }

    private fun roundCrop(pool: BitmapPool, source: Bitmap): Bitmap {
        val result = pool.get(source.width, source.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(result)
        val paint = Paint()
        paint.shader = BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        paint.isAntiAlias = true
        val rectF = RectF(0f, 0f, source.width.toFloat(), source.height.toFloat())
        canvas.drawRoundRect(rectF, radius, radius, paint)
        return result
    }

}
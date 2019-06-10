package com.lovely.pig.kotlindemo.glide

import com.bumptech.glide.load.Options
import com.bumptech.glide.load.model.*
import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader
import java.io.InputStream
import java.util.regex.Pattern

/**
 * 作者 swg
 * 时间 2019/6/3 11:34
 * 文件 KotlinDemo
 * 描述
 */
class CustomBaseGlideUrlLoader(concreteLoader: ModelLoader<GlideUrl, InputStream>,
                               modelCache: ModelCache<String, GlideUrl>) :
        BaseGlideUrlLoader<String>(concreteLoader, modelCache) {
    override fun getUrl(model: String, width: Int, height: Int, options: Options?): String {
        val m = PATTERN.matcher(model)
        var bestBucket: Int
        if (m.find()) {
            val found = m.group(1).split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            for (bucketStr in found) {
                bestBucket = Integer.parseInt(bucketStr)
                if (bestBucket >= width) {
                    break
                }
            }
        }
        return model
    }

    override fun handles(model: String): Boolean {
        return true
    }

    class Factory : ModelLoaderFactory<String, InputStream> {
        override fun build(multiFactory: MultiModelLoaderFactory): ModelLoader<String, InputStream> {
            return CustomBaseGlideUrlLoader(multiFactory.build(GlideUrl::class.java, InputStream::class.java), urlCache)
        }

        override fun teardown() {
        }

    }


    companion object {
        private val urlCache = ModelCache<String, GlideUrl>(150)
        /**
         * Url的匹配规则
         */
        private val PATTERN = Pattern.compile("__w-((?:-?\\d+)+)__")
    }

}
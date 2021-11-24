package com.kobe.common.extensions

import android.content.res.ColorStateList
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.widget.ImageViewCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.*
import com.bumptech.glide.request.RequestOptions
import com.kobe.common.R

fun ImageView.loadImage(
    url: String,
    @DrawableRes errorResourceId: Int = R.drawable.ic_image_placeholder,
    @DrawableRes placeholderResourceId: Int = R.drawable.ic_image_placeholder,
    imageViewTransformation: ImageViewTransformation = ImageViewTransformation.FIT_CENTER,
    cornerRadius: Int = 0
) {
    val requestOptions = RequestOptions()
    val transforms = getTransforms(imageViewTransformation, cornerRadius)

    Glide.with(this)
        .load(url)
        .apply(requestOptions.transform(*transforms.toTypedArray()))
        .error(errorResourceId)
        .placeholder(placeholderResourceId)
        .into(this)
}

fun ImageView.loadImage(
    url: String,
    @DrawableRes errorResourceId: Int = R.drawable.ic_image_placeholder,
    @DrawableRes placeholderResourceId: Int = R.drawable.ic_image_placeholder,
    imageViewTransformation: ImageViewTransformation = ImageViewTransformation.FIT_CENTER,
    topLeftCornerRadius: Int = 0,
    topRightCornerRadius: Int = 0,
    bottomLeftCornerRadius: Int = 0,
    bottomRightCornerRadius: Int = 0
) {
    val requestOptions = RequestOptions()
    val transforms = getTransforms(
        imageViewTransformation,
        topLeftCornerRadius,
        topRightCornerRadius,
        bottomLeftCornerRadius,
        bottomRightCornerRadius
    )

    Glide.with(this)
        .load(url)
        .apply(requestOptions.transform(*transforms.toTypedArray()))
        .error(errorResourceId)
        .placeholder(placeholderResourceId)
        .into(this)
}

fun ImageView.loadImage(
    @DrawableRes drawableId: Int,
    imageViewTransformation: ImageViewTransformation = ImageViewTransformation.FIT_CENTER,
    cornerRadius: Int = 0
) {
    val requestOptions = RequestOptions()
    val transforms = getTransforms(imageViewTransformation, cornerRadius)

    Glide.with(this)
        .load(drawableId)
        .apply(requestOptions.transform(*transforms.toTypedArray()))
        .into(this)
}

fun ImageView.loadImage(
    @DrawableRes drawableId: Int,
    imageViewTransformation: ImageViewTransformation = ImageViewTransformation.FIT_CENTER,
    topLeftCornerRadius: Int = 0,
    topRightCornerRadius: Int = 0,
    bottomLeftCornerRadius: Int = 0,
    bottomRightCornerRadius: Int = 0
) {
    val requestOptions = RequestOptions()
    val transforms = getTransforms(
        imageViewTransformation,
        topLeftCornerRadius,
        topRightCornerRadius,
        bottomLeftCornerRadius,
        bottomRightCornerRadius
    )

    Glide.with(this)
        .load(drawableId)
        .apply(requestOptions.transform(*transforms.toTypedArray()))
        .into(this)
}

fun ImageView.clear() {
    Glide.with(this).clear(this).also { setImageDrawable(null) }
}

fun ImageView.setTintColor(@ColorRes color: Int?) {
    if (color == null) {
        ImageViewCompat.setImageTintList(this, null)
    } else {
        ImageViewCompat.setImageTintList(
            this,
            ColorStateList.valueOf(context.getColor(color))
        )
    }
}

enum class ImageViewTransformation {
    CENTER_CROPPED,
    FIT_CENTER,
    CIRCLE_CROP
}

private fun getTransforms(
    imageViewTransformation: ImageViewTransformation,
    roundedCornerRadius: Int
): List<BitmapTransformation> {
    return if (roundedCornerRadius > 0) {
        when (imageViewTransformation) {
            ImageViewTransformation.CENTER_CROPPED -> listOf(
                CenterCrop(),
                RoundedCorners(roundedCornerRadius)
            )
            ImageViewTransformation.FIT_CENTER -> listOf(
                FitCenter(),
                RoundedCorners(roundedCornerRadius)
            )
            ImageViewTransformation.CIRCLE_CROP -> listOf(
                CircleCrop(),
                RoundedCorners(roundedCornerRadius)
            )
        }
    } else {
        when (imageViewTransformation) {
            ImageViewTransformation.CENTER_CROPPED -> listOf(CenterCrop())
            ImageViewTransformation.FIT_CENTER -> listOf(FitCenter())
            ImageViewTransformation.CIRCLE_CROP -> listOf(CircleCrop())
        }
    }
}

private fun getTransforms(
    imageViewTransformation: ImageViewTransformation,
    topLeftCornerRadius: Int,
    topRightCornerRadius: Int,
    bottomLeftCornerRadius: Int,
    bottomRightCornerRadius: Int
): List<BitmapTransformation> {
    return if (
        topLeftCornerRadius < 0
        || topRightCornerRadius < 0
        || bottomLeftCornerRadius < 0
        || bottomRightCornerRadius < 0
    ) {
        when (imageViewTransformation) {
            ImageViewTransformation.CENTER_CROPPED -> listOf(CenterCrop())
            ImageViewTransformation.FIT_CENTER -> listOf(FitCenter())
            ImageViewTransformation.CIRCLE_CROP -> listOf(CircleCrop())
        }
    } else {
        when (imageViewTransformation) {
            ImageViewTransformation.CENTER_CROPPED -> listOf(
                CenterCrop(),
                GranularRoundedCorners(
                    topLeftCornerRadius.toFloat(),
                    topRightCornerRadius.toFloat(),
                    bottomRightCornerRadius.toFloat(),
                    bottomLeftCornerRadius.toFloat()
                )
            )
            ImageViewTransformation.FIT_CENTER -> listOf(
                FitCenter(),
                GranularRoundedCorners(
                    topLeftCornerRadius.toFloat(),
                    topRightCornerRadius.toFloat(),
                    bottomRightCornerRadius.toFloat(),
                    bottomLeftCornerRadius.toFloat()
                )
            )
            ImageViewTransformation.CIRCLE_CROP -> listOf(
                CircleCrop(),
                GranularRoundedCorners(
                    topLeftCornerRadius.toFloat(),
                    topRightCornerRadius.toFloat(),
                    bottomRightCornerRadius.toFloat(),
                    bottomLeftCornerRadius.toFloat()
                )
            )
        }
    }
}

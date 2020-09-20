package jp.wasabeef.transformations.core

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import android.util.Size

/**
 * Copyright (C) 2020 Wasabeef, molexx
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Crops a rectangle, allowing its dimensions and positioning to be specified by a combination of:
 * width/height in pixels
 * width/height as a ratio of the source image
 * aspect ratio
 * offset from start/top in pixels
 * horizontal and vertical gravity
 *
 *
 * If aspect ratio is provided then both width and height should not be provided or the ratio wil
 * be
 * ignored.
 * If neither width or height are provided then the aspect ratio is used to crop the largest
 * possible image.
 *
 *
 * Constructors accepting width and height expect pixels if the values are ints and ratio of source
 * image if the
 * values are floats.
 */
open class Crop : Transformation {

  enum class GravityHorizontal {
    START,
    CENTER,
    END
  }

  enum class GravityVertical {
    TOP,
    CENTER,
    BOTTOM
  }

  private var aspectRatio = 0f
  private var start = 0
  private var top = 0
  private var width = 0
  private var height = 0
  private var widthRatio = 0f
  private var heightRatio = 0f
  private var gravityHorizontal = GravityHorizontal.CENTER
  private var gravityVertical = GravityVertical.CENTER

  /**
   * Crops to the given size and offset in pixels.
   * If either width or height is zero then the original image's dimension is used.
   *
   * @param start   offset in pixels from the start edge of the source image
   * @param top    offset in pixels from the top of the source image
   * @param width  in pixels
   * @param height in pixels
   */
  constructor(start: Int, top: Int, width: Int, height: Int) {
    this.start = start
    this.top = top
    this.width = width
    this.height = height
  }
  /**
   * Crops to the given width and height (in pixels) using the given gravity.
   * If either width or height is zero then the original image's dimension is used.
   *
   * @param width             in pixels
   * @param height            in pixels
   * @param gravityHorizontal position of the cropped area within the larger source image
   * @param gravityVertical   position of the cropped area within the larger source image
   */
  /**
   * Crops to the given width and height (in pixels), defaulting gravity to CENTER/CENTER.
   * If either width or height is zero then the original image's dimension is used.
   *
   * @param width  in pixels
   * @param height in pixels
   */
  @JvmOverloads
  constructor(
    width: Int, height: Int, gravityHorizontal: GravityHorizontal = GravityHorizontal.CENTER,
    gravityVertical: GravityVertical = GravityVertical.CENTER
  ) {
    this.width = width
    this.height = height
    this.gravityHorizontal = gravityHorizontal
    this.gravityVertical = gravityVertical
  }
  /**
   * Crops to a ratio of the source image's width/height.
   *
   *
   * e.g. (0.5, 0.5, START, TOP) will crop a quarter-sized box from the top start of the original.
   *
   *
   * If widthRatio or heightRatio are zero then 100% of the original image's dimension will be
   * used.
   *
   * @param widthRatio        width of the target image relative to the width of the source image; 1 =
   * 100%
   * @param heightRatio       height of the target image relative to the height of the source image; 1 =
   * 100%
   * @param gravityHorizontal position of the cropped area within the larger source image
   * @param gravityVertical   position of the cropped area within the larger source image
   */
  /**
   * Crops to a ratio of the source image's width/height, defaulting to CENTER/CENTER gravity.
   *
   *
   * e.g. (0.5, 0.5) will crop a quarter-sized box from the middle of the original.
   *
   *
   * If widthRatio or heightRatio are zero then 100% of the original image's dimension will be
   * used.
   *
   * @param widthRatio  width of the target image relative to the width of the source image; 1 =
   * 100%
   * @param heightRatio height of the target image relative to the height of the source image; 1 =
   * 100%
   */
  @JvmOverloads
  constructor(
    widthRatio: Float,
    heightRatio: Float,
    gravityHorizontal: GravityHorizontal = GravityHorizontal.CENTER,
    gravityVertical: GravityVertical = GravityVertical.CENTER
  ) {
    this.widthRatio = widthRatio
    this.heightRatio = heightRatio
    this.gravityHorizontal = gravityHorizontal
    this.gravityVertical = gravityVertical
  }

  /**
   * Crops to the desired aspectRatio driven by either width OR height in pixels.
   * If one of width/height is greater than zero the other is calculated
   * If width and height are both zero then the largest area matching the aspectRatio is returned
   * If both width and height are specified then the aspectRatio is ignored
   *
   *
   * If aspectRatio is 0 then the result will be the same as calling the version without
   * aspectRatio.
   *
   * @param width             in pixels, one of width/height should be zero
   * @param height            in pixels, one of width/height should be zero
   * @param aspectRatio       width/height: greater than 1 is landscape, less than 1 is portrait, 1 is
   * square
   * @param gravityHorizontal position of the cropped area within the larger source image
   * @param gravityVertical   position of the cropped area within the larger source image
   */
  constructor(
    width: Int, height: Int, aspectRatio: Float,
    gravityHorizontal: GravityHorizontal, gravityVertical: GravityVertical
  ) {
    this.width = width
    this.height = height
    this.aspectRatio = aspectRatio
    this.gravityHorizontal = gravityHorizontal
    this.gravityVertical = gravityVertical
  }

  /**
   * Crops to the desired aspectRatio driven by either width OR height as a ratio to the original
   * image's dimension.
   * If one of width/height is greater than zero the other is calculated
   * If width and height are both zero then the largest area matching the aspectRatio is returned
   * If both width and height are specified then the aspectRatio is ignored
   *
   *
   * If aspectRatio is 0 then the result will be the same as calling the version without
   * aspectRatio.
   *
   *
   * e.g. to get an image with a width of 50% of the source image's width and cropped to 16:9 from
   * the center/center:
   * CropTransformation(0.5, (float)0, (float)16/9, CENTER, CENTER);
   *
   * @param widthRatio        width of the target image relative to the width of the source image; 1 =
   * 100%
   * @param heightRatio       height of the target image relative to the height of the source image; 1 =
   * 100%
   * @param aspectRatio       width/height: greater than 1 is landscape, less than 1 is portrait, 1 is
   * square
   * @param gravityHorizontal position of the cropped area within the larger source image
   * @param gravityVertical   position of the cropped area within the larger source image
   */
  constructor(
    widthRatio: Float, heightRatio: Float, aspectRatio: Float,
    gravityHorizontal: GravityHorizontal, gravityVertical: GravityVertical
  ) {
    this.widthRatio = widthRatio
    this.heightRatio = heightRatio
    this.aspectRatio = aspectRatio
    this.gravityHorizontal = gravityHorizontal
    this.gravityVertical = gravityVertical
  }

  /**
   * Crops to the largest image that will fit the given aspectRatio.
   * This will effectively chop off either the top/bottom or start/end the source image.
   *
   * @param aspectRatio       width/height: greater than 1 is landscape, less than 1 is portrait, 1 is
   * square
   * @param gravityHorizontal position of the cropped area within the larger source image
   * @param gravityVertical   position of the cropped area within the larger source image
   */
  constructor(
    aspectRatio: Float, gravityHorizontal: GravityHorizontal,
    gravityVertical: GravityVertical
  ) {
    this.aspectRatio = aspectRatio
    this.gravityHorizontal = gravityHorizontal
    this.gravityVertical = gravityVertical
  }

  fun calculateSize(source: Bitmap): Size {
    if (width == 0 && widthRatio != 0f) {
      width = (source.width.toFloat() * widthRatio).toInt()
    }
    if (height == 0 && heightRatio != 0f) {
      height = (source.height.toFloat() * heightRatio).toInt()
    }
    if (aspectRatio != 0f) {
      if (width == 0 && height == 0) {
        val sourceRatio = source.width.toFloat() / source.height.toFloat()
        if (sourceRatio > aspectRatio) {
          // source is wider than we want, restrict by height
          height = source.height
        } else {
          // source is taller than we want, restrict by width
          width = source.width
        }
      }
      if (width != 0) {
        height = (width.toFloat() / aspectRatio).toInt()
      } else {
        if (height != 0) {
          width = (height.toFloat() * aspectRatio).toInt()
        }
      }
    }
    if (width == 0) {
      width = source.width
    }
    if (height == 0) {
      height = source.height
    }
    start = getStart(source)
    top = getTop(source)

    return Size(width, height)
  }

  override fun transform(
    context: Context,
    source: Bitmap,
    destination: Bitmap
  ): Bitmap {
    calculateSize(source)

    destination.density = source.density
    destination.setHasAlpha(true)

    val sourceRect = Rect(start, top, start + width, top + height)
    val targetRect = Rect(0, 0, width, height)
    val canvas = Canvas(destination)
    canvas.drawBitmap(source, sourceRect, targetRect, null)
    canvas.setBitmap(null)

    return destination
  }

  private fun getTop(source: Bitmap): Int = when (gravityVertical) {
    GravityVertical.TOP -> 0
    GravityVertical.CENTER -> (source.height - height) / 2
    GravityVertical.BOTTOM -> source.height - height
  }

  private fun getStart(source: Bitmap): Int = when (gravityHorizontal) {
    GravityHorizontal.START -> 0
    GravityHorizontal.CENTER -> (source.width - width) / 2
    GravityHorizontal.END -> source.width - width
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as Crop

    if (aspectRatio != other.aspectRatio) return false
    if (start != other.start) return false
    if (top != other.top) return false
    if (width != other.width) return false
    if (height != other.height) return false
    if (widthRatio != other.widthRatio) return false
    if (heightRatio != other.heightRatio) return false
    if (gravityHorizontal != other.gravityHorizontal) return false
    if (gravityVertical != other.gravityVertical) return false

    return true
  }

  override fun hashCode(): Int {
    var result = aspectRatio.hashCode()
    result = 31 * result + start
    result = 31 * result + top
    result = 31 * result + width
    result = 31 * result + height
    result = 31 * result + widthRatio.hashCode()
    result = 31 * result + heightRatio.hashCode()
    result = 31 * result + gravityHorizontal.hashCode()
    result = 31 * result + gravityVertical.hashCode()
    return result
  }

  override fun key(): String {
    return "$id(start=$start, top=$top, width=$width, height=$height, widthRatio=$widthRatio," +
      " heightRatio=$heightRatio, aspectRatio=$aspectRatio, gravityHorizontal=$gravityHorizontal," +
      " gravityVertical=$gravityVertical"
  }
}
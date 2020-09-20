package jp.wasabeef.transformations

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import jp.wasabeef.transformations.MainAdapter.Type.ColorFilter
import jp.wasabeef.transformations.MainAdapter.Type.CropBottom
import jp.wasabeef.transformations.MainAdapter.Type.CropBottomRatio4x3
import jp.wasabeef.transformations.MainAdapter.Type.CropCenter
import jp.wasabeef.transformations.MainAdapter.Type.CropCenterRatio16x9
import jp.wasabeef.transformations.MainAdapter.Type.CropCenterRatio4x3
import jp.wasabeef.transformations.MainAdapter.Type.CropCircle
import jp.wasabeef.transformations.MainAdapter.Type.CropCircleWithBorder
import jp.wasabeef.transformations.MainAdapter.Type.CropSquare
import jp.wasabeef.transformations.MainAdapter.Type.CropTop
import jp.wasabeef.transformations.MainAdapter.Type.CropTopRatio16x9
import jp.wasabeef.transformations.MainAdapter.Type.Grayscale
import jp.wasabeef.transformations.MainAdapter.Type.Mask
import jp.wasabeef.transformations.MainAdapter.Type.NinePatchMask
import jp.wasabeef.transformations.MainAdapter.Type.RoundedCorners
import jp.wasabeef.transformations.MainAdapter.Type.RoundedCornersTopLeft
import jp.wasabeef.transformations.core.CornerType
import jp.wasabeef.transformations.glide.BlurTransformation
import jp.wasabeef.transformations.glide.CenterCropTransformation
import jp.wasabeef.transformations.glide.ColorFilterTransformation
import jp.wasabeef.transformations.glide.CropCenterBottomTransformation
import jp.wasabeef.transformations.glide.CropCenterTopTransformation
import jp.wasabeef.transformations.glide.CropCenterTransformation
import jp.wasabeef.transformations.glide.CropCircleTransformation
import jp.wasabeef.transformations.glide.CropCircleWithBorderTransformation
import jp.wasabeef.transformations.glide.CropSquareTransformation
import jp.wasabeef.transformations.glide.CropTransformation
import jp.wasabeef.transformations.glide.GrayscaleTransformation
import jp.wasabeef.transformations.glide.MaskTransformation
import jp.wasabeef.transformations.glide.RoundedCornersTransformation
import jp.wasabeef.transformations.types.GravityHorizontal
import jp.wasabeef.transformations.types.GravityVertical

/**
 * Created by Wasabeef on 2020/09/20.
 */
class MainAdapter(
  private val context: Context,
  private val dataSet: MutableList<Type>
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

  companion object {
    private const val IMAGE_URL = "https://images.unsplash.com/photo-1600353068149-1dbe0f180ef9"
    private const val SKIP_CACHE = true
  }

  enum class Type {
    Mask,
    NinePatchMask,
    CropTop,
    CropCenter,
    CropCenterRatio16x9,
    CropCenterRatio4x3,
    CropTopRatio16x9,
    CropBottomRatio4x3,
    CropBottom,
    CropSquare,
    CropCircle,
    CropCircleWithBorder,
    ColorFilter,
    Grayscale,
    RoundedCorners,
    RoundedCornersTopLeft,
    RSGaussianBlurLight,
    RSGaussianBlurDeep,
    StackBlurLight,
    StackBlurDeep,
    Toon,
    Sepia,
    Contrast,
    Invert,
    Pixel,
    Sketch,
    Swirl,
    Brightness,
    Kuawahara,
    Vignette
  }

  override fun getItemCount(): Int {
    return dataSet.size
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val v = LayoutInflater.from(context).inflate(R.layout.layout_list_item, parent, false)
    return ViewHolder(v)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    when (dataSet[position]) {
      Mask -> {
        Glide.with(context)
          .load(IMAGE_URL)
          .skipMemoryCache(SKIP_CACHE)
          .apply(
            bitmapTransform(
              MultiTransformation(
                CropCenterTransformation(),
                MaskTransformation(R.drawable.mask_starfish)
              )
            )
          ).into(holder.image)
      }
      NinePatchMask -> {
        Glide.with(context)
          .load(IMAGE_URL)
          .skipMemoryCache(SKIP_CACHE)
          .apply(
            bitmapTransform(
              MultiTransformation(
                CropCenterTransformation(),
                MaskTransformation(R.drawable.mask_chat_right)
              )
            )
          ).into(holder.image)
      }

      CropTop -> Glide.with(context)
        .load(IMAGE_URL)
        .skipMemoryCache(SKIP_CACHE)
        .apply(bitmapTransform(CropCenterTopTransformation()))
        .into(holder.image)

      CropCenter -> Glide.with(context)
        .load(IMAGE_URL)
        .skipMemoryCache(SKIP_CACHE)
        .apply(bitmapTransform(CenterCropTransformation()))
        .into(holder.image)

      CropCenterRatio16x9 -> Glide.with(context)
        .load(IMAGE_URL)
        .skipMemoryCache(SKIP_CACHE)
        .apply(
          bitmapTransform(
            CropTransformation(
              16f / 9f
            )
          )
        )
        .into(holder.image)

      CropCenterRatio4x3 -> Glide.with(context)
        .load(IMAGE_URL)
        .skipMemoryCache(SKIP_CACHE)
        .apply(
          bitmapTransform(
            CropTransformation(4f / 3f)
          )
        ).into(holder.image)

      CropTopRatio16x9 -> Glide.with(context)
        .load(IMAGE_URL)
        .skipMemoryCache(SKIP_CACHE)
        .apply(
          bitmapTransform(
            CropTransformation(
              16f / 9f,
              GravityHorizontal.START,
              GravityVertical.TOP
            )
          )
        )
        .into(holder.image)

      CropBottomRatio4x3 -> Glide.with(context)
        .load(IMAGE_URL)
        .skipMemoryCache(SKIP_CACHE)
        .apply(
          bitmapTransform(
            CropTransformation(4f / 3f, GravityHorizontal.START, GravityVertical.BOTTOM)
          )
        ).into(holder.image)

      CropBottom -> Glide.with(context)
        .load(IMAGE_URL)
        .skipMemoryCache(SKIP_CACHE)
        .apply(bitmapTransform(CropCenterBottomTransformation()))
        .into(holder.image)

      CropSquare -> Glide.with(context)
        .load(IMAGE_URL)
        .skipMemoryCache(SKIP_CACHE)
        .apply(bitmapTransform(CropSquareTransformation()))
        .into(holder.image)

      CropCircle -> Glide.with(context)
        .load(IMAGE_URL)
        .skipMemoryCache(SKIP_CACHE)
        .apply(
          bitmapTransform(
            MultiTransformation(
              CropCenterTransformation(),
              CropCircleTransformation()
            )
          )
        )
        .into(holder.image)

      CropCircleWithBorder -> Glide.with(context)
        .load(IMAGE_URL)
        .skipMemoryCache(SKIP_CACHE)
        .apply(
          bitmapTransform(
            MultiTransformation(
              CropCenterTransformation(),
              CropCircleWithBorderTransformation(4.dp, Color.rgb(0, 191, 255))
            )
          )
        )
        .into(holder.image)

      ColorFilter -> Glide.with(context)
        .load(IMAGE_URL)
        .skipMemoryCache(SKIP_CACHE)
        .apply(bitmapTransform(ColorFilterTransformation(Color.argb(90, 51, 204, 255))))
        .into(holder.image)

      Grayscale -> Glide.with(context)
        .load(IMAGE_URL)
        .skipMemoryCache(SKIP_CACHE)
        .apply(bitmapTransform(GrayscaleTransformation()))
        .into(holder.image)

      RoundedCorners -> Glide.with(context)
        .load(IMAGE_URL)
        .skipMemoryCache(SKIP_CACHE)
        .apply(
          bitmapTransform(
            MultiTransformation(
              CropCenterTransformation(),
              RoundedCornersTransformation(radius = 120)
            )
          )
        )
        .into(holder.image)

      RoundedCornersTopLeft -> Glide.with(context)
        .load(IMAGE_URL)
        .skipMemoryCache(SKIP_CACHE)
        .apply(
          bitmapTransform(
            MultiTransformation(
              CropCenterTransformation(),
              RoundedCornersTransformation(
                radius = 120,
                cornerType = CornerType.DIAGONAL_FROM_TOP_LEFT
              )
            )
          )
        )
        .into(holder.image)

      Type.RSGaussianBlurLight -> Glide.with(context)
        .load(IMAGE_URL)
        .skipMemoryCache(SKIP_CACHE)
        .apply(
          bitmapTransform(
            MultiTransformation(
              BlurTransformation(15, sampling = 1)
            )
          )
        ).into(holder.image)
      Type.RSGaussianBlurDeep -> Glide.with(context)
        .load(IMAGE_URL)
        .skipMemoryCache(SKIP_CACHE)
        .apply(
          bitmapTransform(
            MultiTransformation(
              BlurTransformation(25, sampling = 4)
            )
          )
        )
        .into(holder.image)
      Type.StackBlurLight -> Glide.with(context)
        .load(IMAGE_URL)
        .skipMemoryCache(SKIP_CACHE)
        .apply(bitmapTransform(BlurTransformation(25, sampling = 1, rs = false)))
        .into(holder.image)
      Type.StackBlurDeep -> Glide.with(context)
        .load(IMAGE_URL)
        .skipMemoryCache(SKIP_CACHE)
        .apply(bitmapTransform(BlurTransformation(25, sampling = 8, rs = false)))
        .into(holder.image)

      else -> {
        // no op
      }


//      Toon -> Glide.with(context)
//        .load(IMAGE_URL)
//        .apply(bitmapTransform(ToonFilterTransformation()))
//        .into(holder.image)
//
//      Sepia -> Glide.with(context)
//        .load(R.drawable.check)
//        .apply(bitmapTransform(SepiaFilterTransformation()))
//        .into(holder.image)
//
//      Contrast -> Glide.with(context)
//        .load(R.drawable.check)
//        .apply(bitmapTransform(ContrastFilterTransformation(2.0f)))
//        .into(holder.image)
//
//      Invert -> Glide.with(context)
//        .load(R.drawable.check)
//        .apply(bitmapTransform(InvertFilterTransformation()))
//        .into(holder.image)
//
//      Pixel -> Glide.with(context)
//        .load(R.drawable.check)
//        .apply(bitmapTransform(PixelationFilterTransformation(20f)))
//        .into(holder.image)
//
//      Sketch -> Glide.with(context)
//        .load(R.drawable.check)
//        .apply(bitmapTransform(SketchFilterTransformation()))
//        .into(holder.image)
//
//      Swirl -> Glide.with(context)
//        .load(R.drawable.check)
//        .apply(
//          bitmapTransform(
//            SwirlFilterTransformation(0.5f, 1.0f, PointF(0.5f, 0.5f))
//          ).dontAnimate()
//        )
//        .into(holder.image)
//
//      Brightness -> Glide.with(context)
//        .load(R.drawable.check)
//        .apply(bitmapTransform(BrightnessFilterTransformation(0.5f)).dontAnimate())
//        .into(holder.image)
//
//      Kuawahara -> Glide.with(context)
//        .load(R.drawable.check)
//        .apply(bitmapTransform(KuwaharaFilterTransformation(25)).dontAnimate())
//        .into(holder.image)
//
//      Vignette -> Glide.with(context)
//        .load(R.drawable.check)
//        .apply(
//          bitmapTransform(
//            VignetteFilterTransformation(
//              PointF(0.5f, 0.5f),
//              floatArrayOf(0.0f, 0.0f, 0.0f), 0f, 0.75f
//            )
//          ).dontAnimate()
//        )
//        .into(holder.image)
    }
    holder.title.text = dataSet[position].name
  }

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var image: ImageView = itemView.findViewById(R.id.image)
    var title: TextView = itemView.findViewById(R.id.title)
  }
}

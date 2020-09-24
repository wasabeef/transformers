<p align="center">
  <img src="https://github.com/wasabeef/transformers/raw/main/art/logo.jpg" width="640px"/>
</p>
<p align="center">
  <a href="https://www.apache.org/licenses/LICENSE-2.0">
    <img src="https://img.shields.io/badge/license-Apache%202-blue.svg" />
  </a>
  <a href="https://bintray.com/wasabeef/maven/transformers/_latestVersion">
    <img src="https://api.bintray.com/packages/wasabeef/maven/transformers/images/download.svg" />
  </a>
  <a href="https://github.com/wasabeef/transformers/actions">
    <img src="https://github.com/wasabeef/transformers/workflows/Android%20CI/badge.svg" />
  </a>
</p>

## What is Transformers?

An Android transformation library providing a variety of image transformations for [Coil], [Glide], [Picasso], and [Fresco].

<a href="https://github.com/coil-kt/coil">
  <img src="https://github.com/wasabeef/transformers/raw/main/art/coil.png" width="58px"/>
</a>
<a href="https://github.com/bumptech/glide">
  <img src="https://github.com/wasabeef/transformers/raw/main/art/glide.png" width="64px"/>
</a>
<a href="https://github.com/square/picasso">
  <img src="https://github.com/wasabeef/transformers/raw/main/art/picasso.jpg" width="64px"/>
</a>
<a href="https://github.com/facebook/fresco">
  <img src="https://github.com/wasabeef/transformers/raw/main/art/fresco.png" width="64px"/>
</a>
<a href="https://developer.android.com/jetpack/compose">
  <img src="https://github.com/wasabeef/transformers/raw/main/art/jetpack-compose.svg" width="100px"/>
</a>

<br>

### Part of the sample
<img src="https://github.com/wasabeef/image-transformations/raw/main/art/Original.png" width="120px" /><img src="https://github.com/wasabeef/image-transformations/raw/main/art/Mask.png" width="120px" /><img src="https://github.com/wasabeef/image-transformations/raw/main/art/CropCircle.png" width="120px" /><img src="https://github.com/wasabeef/image-transformations/raw/main/art/RSGaussianBlurDeep.png" width="120px" /><img src="https://github.com/wasabeef/image-transformations/raw/main/art/RoundedCornersTopLeft.png" width="120px" />

<img src="https://github.com/wasabeef/image-transformations/raw/main/art/PixelDeep.png" width="120px" /><img src="https://github.com/wasabeef/image-transformations/raw/main/art/ZoomBlur.png" width="120px" /><img src="https://github.com/wasabeef/image-transformations/raw/main/art/Kuawahara.png" width="120px" /><img src="https://github.com/wasabeef/image-transformations/raw/main/art/Vignette.png" width="120px" /><img src="https://github.com/wasabeef/image-transformations/raw/main/art/ToneCurve.png" width="120px" />

<br>

> [Glide Transformations](https://github.com/wasabeef/glide-transformations), [Picasso Transformations](https://github.com/wasabeef/picasso-transformations), [Fresco Processors](https://github.com/wasabeef/fresco-processors) are deprecated.   
> Development will stop soon.. For an up-to-date version, please use this.


## Installation

### Requirements
- Android 5.0+ Lollipop (API level 21)

### Gradle settings
```gradle
repositories {
  jcenter()
}
```

#### For [Coil] <a href="https://github.com/coil-kt/coil"><img src="https://github.com/wasabeef/transformers/raw/main/art/coil.png" width="12px"/></a>
```gradle
dependencies {
  implementation 'jp.wasabeef.transformers:coil:1.x.x'
  // Use the GPU Filters 
  implementation 'jp.wasabeef.transformers:coil-gpu:1.x.x'
}
```
```kotlin
imageView.load(IMAGE_URL) {
  transformations(
    CropCenterTransformation(),
    RoundedCornersTransformation(radius = 120, cornerType = CornerType.DIAGONAL_FROM_TOP_LEFT)
  )
}
```

#### For [Glide] <a href="https://github.com/bumptech/glide"><img src="https://github.com/wasabeef/transformers/raw/main/art/glide.png" width="12px"/></a>
```gradle
dependencies {
  implementation 'jp.wasabeef.transformers:glide:1.x.x'
  implementation 'jp.wasabeef.transformers:glide-gpu:1.x.x' // Use the GPU Filters 
}
```
```kotlin
Glide.with(context)
  .load(IMAGE_URL)
  .apply(
    bitmapTransform(
      MultiTransformation(
        CropCenterTransformation(),
        BlurTransformation(context, 15, sampling = 1)
      )
    )
  ).into(imageView)
```

#### For [Picasso] <a href="https://github.com/square/picasso"><img src="https://github.com/wasabeef/transformers/raw/main/art/picasso.jpg" width="12px"/></a>
```gradle
dependencies {
  implementation 'jp.wasabeef.transformers:picasso:1.x.x'
  // Use the GPU Filters 
  implementation 'jp.wasabeef.transformers:picasso-gpu:1.x.x'
}
```
```kotlin
Picasso.get()
  .load(IMAGE_URL)
  .fit().centerInside()
  .transform(
    mutableListOf(
      CropCenterTransformation(),
      BlurTransformation(context, 25, sampling = 4)
    )
  ).into(imageView)
```

#### For [Fresco] <a href="https://github.com/facebook/fresco"><img src="https://github.com/wasabeef/transformers/raw/main/art/fresco.png" width="12px"/></a>
```gradle
dependencies {
  implementation 'jp.wasabeef.transformers:fresco:1.x.x'
  // Use the GPU Filters 
  implementation 'jp.wasabeef.transformers:fresco-gpu:1.x.x'
}
```
```kotlin
val request: ImageRequest =
  ImageRequestBuilder.newBuilderWithSource(IMAGE_URL.toUri())
  .setPostprocessor(BlurPostprocessor(context, 25, 4))
  .build()

holder.image.controller = Fresco.newDraweeControllerBuilder()
  .setImageRequest(request)
  .setOldController(draweeView.controller)
  .build()
```


### Sample transformations
| Original | Mask | NinePatchMask | CropTop |
|:---:|:---:|:---:|:---:|
| <img src="https://github.com/wasabeef/image-transformations/raw/main/art/Original.png" width="120px" /> | <img src="https://github.com/wasabeef/image-transformations/raw/main/art/Mask.png" width="120px" /> | <img src="https://github.com/wasabeef/image-transformations/raw/main/art/NinePatchMask.png" width="120px" /> | <img src="https://github.com/wasabeef/image-transformations/raw/main/art/CropTop.png" width="120px" /> |
| CropCenter | CropBottom | CropCenterRatio16x9 | CropCenterRatio4x3 |
| <img src="https://github.com/wasabeef/image-transformations/raw/main/art/CropCenter.png" width="120px" /> | <img src="https://github.com/wasabeef/image-transformations/raw/main/art/CropBottom.png" width="120px" /> | <img src="https://github.com/wasabeef/image-transformations/raw/main/art/CropCenterRatio16x9.png" width="120px" /> | <img src="https://github.com/wasabeef/image-transformations/raw/main/art/CropCenterRatio4x3.png" width="120px" /> |
| CropTopRatio16x9 | CropBottomRatio4x3 | CropSquare | CropCircle |
| <img src="https://github.com/wasabeef/image-transformations/raw/main/art/CropTopRatio16x9.png" width="120px" /> | <img src="https://github.com/wasabeef/image-transformations/raw/main/art/CropBottomRatio4x3.png" width="120px" /> | <img src="https://github.com/wasabeef/image-transformations/raw/main/art/CropSquare.png" width="120px" /> | <img src="https://github.com/wasabeef/image-transformations/raw/main/art/CropCircle.png" width="120px" /> |
| CropCircleWithBorder | ColorFilter | Grayscale | RoundedCorners |
| <img src="https://github.com/wasabeef/image-transformations/raw/main/art/CropCircleWithBorder.png" width="120px" /> | <img src="https://github.com/wasabeef/image-transformations/raw/main/art/ColorFilter.png" width="120px" /> | <img src="https://github.com/wasabeef/image-transformations/raw/main/art/Grayscale.png" width="120px" /> | <img src="https://github.com/wasabeef/image-transformations/raw/main/art/RoundedCorners.png" width="120px" /> |
| RoundedCornersTopLeft | RSGaussianBlurLight | RSGaussianBlurDeep | StackBlurLight |
| <img src="https://github.com/wasabeef/image-transformations/raw/main/art/RoundedCornersTopLeft.png" width="120px" /> | <img src="https://github.com/wasabeef/image-transformations/raw/main/art/RSGaussianBlurLight.png" width="120px" /> | <img src="https://github.com/wasabeef/image-transformations/raw/main/art/RSGaussianBlurDeep.png" width="120px" /> | <img src="https://github.com/wasabeef/image-transformations/raw/main/art/StackBlurLight.png" width="120px" /> |
| StackBlurDeep | 
| <img src="https://github.com/wasabeef/image-transformations/raw/main/art/StackBlurDeep.png" width="120px" /> | 

<kbd>coil</kbd>, <kbd>glide</kbd>, <kbd>picasso</kbd>
- BlurTransformation
- ColorFilterTransformation
- CropCenterBottomTransformation
- CropCenterTopTransformation
- CropCenterTransformation
- CropCircleTransformation
- CropCircleWithBorderTransformation
- CropSquareTransformation
- CropTransformation
- GrayscaleTransformation
- MaskTransformation
- RoundedCornersTransformation

<kbd>fresco</kbd>
- BlurPostprocessor
- ColorFilterPostprocessor
- CombinePostProcessors
- GrayscalePostprocessor
- MaskPostprocessor


### Sample transformations with [GPUImage](https://github.com/cats-oss/android-gpuimage)
| Original | Sepia | Contrast | Invert |
|:---:|:---:|:---:|:---:|
| <img src="https://github.com/wasabeef/image-transformations/raw/main/art/Original.png" width="120px" /> | <img src="https://github.com/wasabeef/image-transformations/raw/main/art/Sepia.png" width="120px" /> | <img src="https://github.com/wasabeef/image-transformations/raw/main/art/Contrast.png" width="120px" /> | <img src="https://github.com/wasabeef/image-transformations/raw/main/art/Invert.png" width="120px" /> |
| PixelLight | PixelDeep | Sketch | Swirl |
| <img src="https://github.com/wasabeef/image-transformations/raw/main/art/PixelLight.png" width="120px" /> | <img src="https://github.com/wasabeef/image-transformations/raw/main/art/PixelDeep.png" width="120px" /> | <img src="https://github.com/wasabeef/image-transformations/raw/main/art/Sketch.png" width="120px" /> | <img src="https://github.com/wasabeef/image-transformations/raw/main/art/Swirl.png" width="120px" /> |
| Brightness | Kuawahara | Vignette | ZoomBlur |
| <img src="https://github.com/wasabeef/image-transformations/raw/main/art/Brightness.png" width="120px" /> | <img src="https://github.com/wasabeef/image-transformations/raw/main/art/Kuawahara.png" width="120px" /> | <img src="https://github.com/wasabeef/image-transformations/raw/main/art/Vignette.png" width="120px" /> | <img src="https://github.com/wasabeef/image-transformations/raw/main/art/ZoomBlur.png" width="120px" /> |
| WhiteBalance | Halftone | Sharpness | Toon |
| <img src="https://github.com/wasabeef/image-transformations/raw/main/art/WhiteBalance.png" width="120px" /> | <img src="https://github.com/wasabeef/image-transformations/raw/main/art/Halftone.png" width="120px" /> | <img src="https://github.com/wasabeef/image-transformations/raw/main/art/Sharpness.png" width="120px" /> | <img src="https://github.com/wasabeef/image-transformations/raw/main/art/Toon.png" width="120px" /> |
| ToneCurve | 
| <img src="https://github.com/wasabeef/image-transformations/raw/main/art/ToneCurve.png" width="120px" /> | 

<kbd>coil-gpu</kbd>, <kbd>glide-gpu</kbd>, <kbd>picasso-gpu</kbd>
- BrightnessFilterTransformation
- ContrastFilterTransformation
- HalftoneFilterTransformation
- InvertFilterTransformation
- KuwaharaFilterTransformation
- PixelationFilterTransformation
- SepiaFilterTransformation
- SharpenFilterTransformation
- SketchFilterTransformation
- SwirlFilterTransformation
- ToneCurveFilterTransformation
- ToonFilterTransformation
- VignetteFilterTransformation
- WhiteBalanceFilterTransformation
- ZoomBlurFilterTransformation

<kbd>fresco-gpu</kbd>
- BrightnessFilterPostprocessor
- ContrastFilterPostprocessor
- HalftoneFilterPostprocessor
- InvertFilterPostprocessor
- KuwaharaFilterPostprocessor
- PixelationFilterPostprocessor
- SepiaFilterPostprocessor
- SharpenFilterPostprocessor
- SketchFilterPostprocessor
- SwirlFilterPostprocessor
- ToneCurveFilterPostprocessor
- ToonFilterPostprocessor
- VignetteFilterPostprocessor
- WhiteBalanceFilterPostprocessor
- ZoomBlurFilterPostprocessor

## Development

### Setup 

Things you will need

- Linux, Mac OS X, or Windows.
- [Android Studio (Preview)](https://developer.android.com/studio/preview)
- npm

```
$ npm install
```

### Build

```
$ ./gradlew assemble
```

### Formatting

```
$ ./gradlew ktlint
```

### Publishing to [Bintray](https://bintray.com/bintray/jcenter)

```
$ ./gradlew clean install build
$ ./gradlew bintrayUpload -PbintrayUser=******** -PbintrayKey=****************************************
```



[Coil]: https://github.com/coil-kt/coil
[Glide]: https://github.com/bumptech/glide
[Picasso]: https://github.com/square/picasso
[Fresco]: https://github.com/facebook/fresco

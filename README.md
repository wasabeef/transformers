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
</p>

## 

```gradle
repositories {
  jcenter()
}
```

For [Coil]
```gradle
dependencies {
  implementation 'jp.wasabeef.transformers:coil:1.x.x'
  implementation 'jp.wasabeef.transformers:coil-gpu:1.x.x' // If you want to use the GPU Filters 
}
```

For [Glide]
```gradle
dependencies {
  implementation 'jp.wasabeef.transformers:glide:1.x.x'
  implementation 'jp.wasabeef.transformers:glide-gpu:1.x.x' // If you want to use the GPU Filters
}
```

For [Picasso]
```gradle
dependencies {
  implementation 'jp.wasabeef.transformers:picasso:1.x.x'
  implementation 'jp.wasabeef.transformers:picasso-gpu:1.x.x' // If you want to use the GPU Filters
}
```

For [Fresco]
```gradle
dependencies {
  implementation 'jp.wasabeef.transformers:fresco:1.x.x'
  implementation 'jp.wasabeef.transformers:fresco-gpu:1.x.x' // If you want to use the GPU Filters
}
```


## Sample transformations
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



## Sample transformations with [GPUImage](https://github.com/cats-oss/android-gpuimage)
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

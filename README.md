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

## Formatting

```
$ ./gradlew ktlint
```


## Sample toransformers
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
|:---:|
| <img src="https://github.com/wasabeef/image-transformations/raw/main/art/StackBlurDeep.png" width="120px" /> | 



## Sample toransformers with [GPUImage](https://github.com/cats-oss/android-gpuimage)
| Original | Original | Original | Original |
|:---:|:---:|:---:|:---:|
| <img src="https://github.com/wasabeef/image-transformations/raw/main/art/Original.png" width="120px" /> | <img src="https://github.com/wasabeef/image-transformations/raw/main/art/ToneCurve.png" width="120px" /> | <img src="https://github.com/wasabeef/image-transformations/raw/main/art/Mask.png" width="120px" /> | <img src="https://github.com/wasabeef/image-transformations/raw/main/art/NinePatchMask.png" width="120px" /> |
| Original | ToneCurve | Original | Original |
|:---:|:---:|:---:|:---:|
| <img src="https://github.com/wasabeef/image-transformations/raw/main/art/Original.png" width="120px" /> | <img src="https://github.com/wasabeef/image-transformations/raw/main/art/ToneCurve.png" width="120px" /> | <img src="https://github.com/wasabeef/image-transformations/raw/main/art/Mask.png" width="120px" /> | <img src="https://github.com/wasabeef/image-transformations/raw/main/art/NinePatchMask.png" width="120px" /> |
| Original | Original | Original | Original |
|:---:|:---:|:---:|:---:|
| <img src="https://github.com/wasabeef/image-transformations/raw/main/art/Original.png" width="120px" /> | <img src="https://github.com/wasabeef/image-transformations/raw/main/art/ToneCurve.png" width="120px" /> | <img src="https://github.com/wasabeef/image-transformations/raw/main/art/Mask.png" width="120px" /> | <img src="https://github.com/wasabeef/image-transformations/raw/main/art/NinePatchMask.png" width="120px" /> |
| Original | ToneCurve | Original | Original |
|:---:|:---:|:---:|:---:|
| <img src="https://github.com/wasabeef/image-transformations/raw/main/art/Original.png" width="120px" /> | <img src="https://github.com/wasabeef/image-transformations/raw/main/art/ToneCurve.png" width="120px" /> | <img src="https://github.com/wasabeef/image-transformations/raw/main/art/Mask.png" width="120px" /> | <img src="https://github.com/wasabeef/image-transformations/raw/main/art/NinePatchMask.png" width="120px" /> |
| Original | Original | Original | Original |
|:---:|:---:|:---:|:---:|
| <img src="https://github.com/wasabeef/image-transformations/raw/main/art/Original.png" width="120px" /> | <img src="https://github.com/wasabeef/image-transformations/raw/main/art/ToneCurve.png" width="120px" /> | <img src="https://github.com/wasabeef/image-transformations/raw/main/art/Mask.png" width="120px" /> | <img src="https://github.com/wasabeef/image-transformations/raw/main/art/NinePatchMask.png" width="120px" /> |

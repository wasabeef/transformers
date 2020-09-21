package jp.wasabeef.transformers

/**
 * Copyright (C) 2020 Wasabeef
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

enum class Type {
  Original,
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
  PixelLight,
  PixelDeep,
  Sketch,
  Swirl,
  Brightness,
  Kuawahara,
  Vignette,
  ZoomBlur,
  WhiteBalance,
  Halftone,
  Sharpness,
  ToneCurve
}
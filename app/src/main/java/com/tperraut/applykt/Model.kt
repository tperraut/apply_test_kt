package com.tperraut.applykt

import android.support.annotation.DrawableRes
import android.support.annotation.StringRes

data class Model(@StringRes val mTitleRes: Int,
                 @StringRes val mDescriptionRes: Int,
                 @DrawableRes val mImageRes: Int)
package com.ssiriwardana.a30daysofmotivation.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class MotivationalCardData (
    @StringRes val quote: Int,
    @StringRes val author: Int,
    @DrawableRes val image: Int

)
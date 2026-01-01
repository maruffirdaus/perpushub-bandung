package com.perpushub.bandung.ui.main.common.util

import kotlin.math.PI
import kotlin.math.ln
import kotlin.math.tan

object GeoUtil {
    fun lonToRelativeX(lon: Double): Double {
        return (lon + 180.0) / 360.0
    }

    fun latToRelativeY(lat: Double): Double {
        val latRad = lat * PI / 180.0
        val mercatorN = ln(tan((PI / 4.0) + (latRad / 2.0)))
        return 0.5 - (mercatorN / (2.0 * PI))
    }
}
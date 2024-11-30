package com.kigi.commonutil

import android.annotation.SuppressLint
import android.content.Context.LOCATION_SERVICE
import android.location.Location
import android.location.LocationManager
import android.text.TextUtils
import android.util.Log

/**
 * 地址服务
 */
@SuppressLint("MissingPermission")
object LocationService {
    private var longitude = ""
    private var latitude = ""
    private val context by lazy {
        CommonBaseApp.context
    }

    private val mLocationManager: LocationManager by lazy {
        context.getSystemService(
            LOCATION_SERVICE
        ) as LocationManager // 位置
    }

    init {
        Log.d("LocationService", "LocationService init")
        mLocationManager.requestLocationUpdates(
            LocationManager.NETWORK_PROVIDER,
            5000,
            2000f
        ) { l -> updateLocation(l) }
    }

    fun getLocation(): Array<String> {
        if (TextUtils.isEmpty(longitude) || TextUtils.isEmpty(latitude)) {
            val lo = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            return arrayOf(lo?.longitude.toString(), lo?.latitude.toString())
        } else {
            return arrayOf(longitude, latitude)
        }
    }

    private fun updateLocation(mlocation: Location) {
        val stringBuffer = StringBuffer()
        stringBuffer.append("经度:" + mlocation.longitude)
        stringBuffer.append("纬度:" + mlocation.latitude)
        stringBuffer.append("海拔:" + mlocation.altitude)
        stringBuffer.append("速度:" + mlocation.speed)
        stringBuffer.append("方向:" + mlocation.bearing)
        Log.d("LocationService", "location info -> $stringBuffer")
    }
}
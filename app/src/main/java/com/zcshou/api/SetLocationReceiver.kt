package com.zcshou.api

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.baidu.mapapi.model.LatLng
import com.baidu.mapapi.map.BitmapDescriptorFactory
import com.baidu.mapapi.map.MapStatusUpdateFactory
import com.baidu.mapapi.map.MarkerOptions
import com.zcshou.gogogo.MainActivity
import com.zcshou.utils.MapUtils

class SetLocationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != "com.zcshou.api.SET_LOCATION") return

        // 1. 以 String 读取
        val lonStr = intent.getStringExtra("longitude")
        val latStr = intent.getStringExtra("latitude")

        // 2. 转为 Double
        val lon = lonStr?.toDoubleOrNull()
        val lat = latStr?.toDoubleOrNull()

        if (lat == null || lon == null) {
            Toast.makeText(context, "坐标参数错误：lat=$latStr, lon=$lonStr", Toast.LENGTH_SHORT).show()
            return
        }
        Log.i("SetLocationReceiver", "$lon $lat")

        val target = LatLng(lat, lon)
        Handler(Looper.getMainLooper()).post {
            MainActivity.updateMapLocation(target)
        }
    }
}

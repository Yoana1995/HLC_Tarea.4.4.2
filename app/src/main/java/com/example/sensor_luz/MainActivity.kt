package com.example.sensor_luz

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity(), SensorEventListener {

    var sensorManager:SensorManager?=null
    var sensor:Sensor?=null

    var image:ImageView?=null
    var blub:Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        image = findViewById(R.id.imageView)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager?.getDefaultSensor(Sensor.TYPE_LIGHT)
        onResumen()
    }

    fun onResumen(){
        super.onResume()
        sensorManager?.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }
    override fun onPause(){
        super.onPause()
        sensorManager?.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event!!.values[0] > 40){
            if(blub){
                image?.setImageResource(R.drawable.apagada)
                blub=false
            }else{
                return
            }
        }else{
            blub=true
            image?.setImageResource(R.drawable.encendida)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
}
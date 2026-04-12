
package com.example.notingapp.sensors

import android.hardware.*

class ShakeDetector(val onShake:()->Unit):SensorEventListener{

 override fun onAccuracyChanged(sensor:Sensor?,accuracy:Int){}

 override fun onSensorChanged(event:SensorEvent){

  val x=event.values[0]
  val y=event.values[1]
  val z=event.values[2]

  val force=x*x+y*y+z*z

  if(force>500){
   onShake()
  }

 }

}

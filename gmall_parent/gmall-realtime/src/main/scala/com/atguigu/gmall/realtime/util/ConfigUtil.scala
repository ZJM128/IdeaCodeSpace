package com.atguigu.gmall.realtime.util

import java.io.InputStream
import java.util.Properties

object ConfigUtil {
  private val is: InputStream = ClassLoader.getSystemResourceAsStream("config.properties")
  private val properties = new Properties()
  properties.load(is)
  def getProperty(propertyName:String):String=properties.getProperty(propertyName)
  
}

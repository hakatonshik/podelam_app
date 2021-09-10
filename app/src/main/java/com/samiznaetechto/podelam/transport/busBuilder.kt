package com.samiznaetechto.podelam.transport

class busBuilder {

    var Azimuth = "0"
    var GaragNumb = "0"
    var Graph = "0"
    var Latitude = "0"
    var Longitude = "0"
    var Marsh = "0"
    var Smena = "0"
    var Speed = "0"
    var TimeNav = "0"
    var data = busData(
        Azimuth,
        GaragNumb,
        Graph,
        Latitude,
        Longitude,
        Marsh,
        Smena,
        Speed,
        TimeNav
    )
    fun getBusData () : busData = data

    fun create(json : String) : Unit {
        val bus: busData? =
            /*Gson().fromJson<busData>(json, busData::class.java)*/
            null
        data = bus!!
    }
}
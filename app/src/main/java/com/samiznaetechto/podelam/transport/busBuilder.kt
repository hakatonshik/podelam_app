package com.samiznaetechto.podelam.transport

class busBuilder {

    private var Azimuth = "0"
    private var GaragNumb = "0"
    private var Graph = "0"
    private var Latitude = "0"
    private var Longitude = "0"
    private var Marsh = "0"
    private var Smena = "0"
    private var Speed = "0"
    private var TimeNav = "0"
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
package com.samiznaetechto.podelam.transport

object openDataWrapper {
    fun getBus(route : Int): Bus
    {
        return Bus(0, route, 0f, 0f)
    }
}
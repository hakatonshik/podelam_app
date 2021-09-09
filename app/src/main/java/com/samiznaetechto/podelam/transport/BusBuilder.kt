package com.samiznaetechto.podelam.transport

class BusBuilder {
    var id = 0
    var route = 0
    var xpos = 0f
    var ypos = 0f

    fun Update () : Bus {
        val updatedBus = openDataWrapper.getBus(route)
        _sync(updatedBus)
        return updatedBus
    }

    private  fun _sync(bus : Bus)
    {
        id = bus.id
        route = bus.route
        xpos = bus.xpos
        ypos = bus.ypos
    }
}
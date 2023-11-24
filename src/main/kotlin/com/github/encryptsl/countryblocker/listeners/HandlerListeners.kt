package com.github.encryptsl.countryblocker.listeners

import com.github.encryptsl.countryblocker.CountryBlocker
import org.bukkit.event.Listener

class HandlerListeners(private val countryBlocker: CountryBlocker) {

    fun registerListeners() {
        val start = System.currentTimeMillis()
        val list = arrayListOf<Listener>(PlayerLoginEventClass(countryBlocker))
        list.forEach { s ->
            countryBlocker.server.pluginManager.registerEvents(s, countryBlocker)
            countryBlocker.slF4JLogger.info("Bukkit listeners ${s.javaClass.simpleName} registered () -> ok")
        }
        countryBlocker.slF4JLogger.info("Listeners registered(${list.size}) in time ${System.currentTimeMillis() - start} ms -> ok")
    }

}
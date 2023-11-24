package com.github.encryptsl.countryblocker

import com.github.encryptsl.countryblocker.commands.CountryCMD
import com.github.encryptsl.countryblocker.database.Database
import com.github.encryptsl.countryblocker.listeners.HandlerListeners
import org.bukkit.plugin.java.JavaPlugin

class CountryBlocker : JavaPlugin() {

    val database: Database by lazy { Database(this) }
    private val handlerListeners: HandlerListeners by lazy { HandlerListeners(this) }

    override fun onEnable() {
        val start = System.currentTimeMillis()
        saveDefaultConfig()
        config.options().copyDefaults(true)
        saveConfig()
        slF4JLogger.info("Plugin is enabled !")
        database.init()
        handlerListeners.registerListeners()
        getCommand("country")?.setExecutor(CountryCMD(this))
        slF4JLogger.info("Plugin enabled in time ${System.currentTimeMillis() - start} ms")
    }

    override fun onDisable() {
        slF4JLogger.info("Plugin is disabled !")
    }
}
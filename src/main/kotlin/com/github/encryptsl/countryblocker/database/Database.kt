package com.github.encryptsl.countryblocker.database

import com.maxmind.geoip2.DatabaseReader
import com.github.encryptsl.countryblocker.CountryBlocker
import java.io.File

class Database(private val countryBlocker: CountryBlocker) {

    fun init() {
        if (!File(countryBlocker.dataFolder.path, "GeoLite2-Country.mmdb").exists()) {
            countryBlocker.saveResource("GeoLite2-Country.mmdb", false)
            countryBlocker.slF4JLogger.info("GeoLite2-Country database was initialized !")
        }
    }

    fun getDatabase(): DatabaseReader  {
        val file = File(countryBlocker.dataFolder.path, "GeoLite2-Country.mmdb")
        return DatabaseReader.Builder(file).build()
    }
}
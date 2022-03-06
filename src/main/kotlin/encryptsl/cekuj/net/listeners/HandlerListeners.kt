package encryptsl.cekuj.net.listeners

import encryptsl.cekuj.net.CountryBlocker
import org.bukkit.event.Listener

class HandlerListeners(private val countryBlocker: CountryBlocker) {

    fun registerListeners() {
        val start = System.currentTimeMillis()
        val list: List<Listener> = arrayListOf(PlayerLoginEventClass(countryBlocker))
        list.forEach { s ->  countryBlocker.slF4JLogger.info("Bukkit listeners ${s.javaClass.simpleName} registered () -> ok") }
        countryBlocker.slF4JLogger.info("Listeners registered(${list.size}) in time ${System.currentTimeMillis() - start} ms -> ok")
    }

}
package encryptsl.cekuj.net.listeners

import encryptsl.cekuj.net.CountryBlocker
import net.kyori.adventure.text.Component
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerLoginEvent

class PlayerLoginEventClass(private val countryBlocker: CountryBlocker) : Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    fun detectedCountry(event: PlayerLoginEvent) {
        val country = countryBlocker.database.getDatabase().country(event.player.address.address).country.isoCode
        if (event.player.address.address.hostAddress == "127.0.0.1") return
        countryBlocker.config.getStringList("CountryBlocker.blockList").forEach { country_code ->
            if (country == country_code) {
                event.disallow(
                    PlayerLoginEvent.Result.KICK_OTHER,
                    Component.text(
                        countryBlocker.config.getString("CountryBlocker.deny_message")!!
                            .replace("[connected_country]", country)
                    )
                )
            }
        }
    }
}
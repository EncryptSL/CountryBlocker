package encryptsl.cekuj.net.listeners

import encryptsl.cekuj.net.CountryBlocker
import encryptsl.cekuj.net.util.IPTool
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerLoginEvent
import java.net.InetAddress

class PlayerLoginEventClass(private val countryBlocker: CountryBlocker) : Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    fun detectedCountry(event: PlayerLoginEvent) {
        if (event.player.address == null || IPTool.getIP(event.player) == "127.0.0.1") return
        val country = countryBlocker.database.getDatabase().country(InetAddress.getByName(IPTool.getIP(event.player))).country.isoCode
        countryBlocker.config.getStringList("CountryBlocker.blockList").forEach { country_code ->
            if (country == country_code) {
                Bukkit.broadcast(MiniMessage.miniMessage().deserialize(
                    countryBlocker.config.getString("CountryBlocker.announcement").toString(),
                    TagResolver.resolver(
                        Placeholder.parsed("user", event.player.name),
                        Placeholder.parsed("ip", IPTool.getIP(event.player)),
                        Placeholder.parsed("country", country),
                        Placeholder.parsed("time", IPTool.getTime())
                    )
                ), "country.blocker.announcement")
                event.disallow(
                    PlayerLoginEvent.Result.KICK_OTHER,
                    MiniMessage.miniMessage().deserialize(countryBlocker.config.getString("CountryBlocker.deny_message").toString(), TagResolver.resolver(Placeholder.parsed("connected_country", country)))
                )
            }
        }
    }
}
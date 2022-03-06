package encryptsl.cekuj.net.util

import org.bukkit.entity.Player
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Optional

object IPTool {

    fun getIP(player: Player): String {
        return Optional.ofNullable(player.address.address.hostAddress).orElse("127.0.0.1")
    }

    fun getTime(): String {
        val dtf: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")
        val now: LocalDateTime = LocalDateTime.now()
        return dtf.format(now)
    }

}
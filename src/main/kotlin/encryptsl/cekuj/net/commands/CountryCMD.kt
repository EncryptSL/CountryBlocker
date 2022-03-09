package encryptsl.cekuj.net.commands

import encryptsl.cekuj.net.CountryBlocker
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.util.StringUtil

class CountryCMD(private val countryBlocker: CountryBlocker) : CommandExecutor, TabExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (command.name == "country") {
            if (args.size == 1) {
                if (args[0] == "help") {
                    if (!sender.hasPermission("country.blocker.command.help")) {
                        sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>You don't have permissions for this command !"))
                        return true
                    }
                    sender.sendMessage(
                        MiniMessage.miniMessage()
                            .deserialize("<gray>========= <gold>[ <yellow>BlockCountry <gold>] <gray>=========")
                    )
                    sender.sendMessage(MiniMessage.miniMessage().deserialize(" "))
                    sender.sendMessage(
                        MiniMessage.miniMessage().deserialize("<gray>+ <gold>/country help <yellow>- <gray>Print commands")
                    )
                    sender.sendMessage(
                        MiniMessage.miniMessage()
                            .deserialize("<gray>+ <gold>/country add [country_iso_code] <yellow>- <gray>Add country to blockList")
                    )
                    sender.sendMessage(
                        MiniMessage.miniMessage()
                            .deserialize("<gray>+ <gold>/country remove [country_iso_code] <yellow>- <gray>Remove country from blockList")
                    )
                    sender.sendMessage(
                        MiniMessage.miniMessage()
                            .deserialize("<gray>+ <gold>/country list <yellow>- <gray>Print blocked countries")
                    )
                    sender.sendMessage(MiniMessage.miniMessage().deserialize(" "))
                    sender.sendMessage(
                        MiniMessage.miniMessage()
                            .deserialize("<gray>========= <gold>[ <yellow>BlockCountry <gold>] <gray>=========")
                    )
                }
                if (args[0] == "add") {
                    if (!sender.hasPermission("country.blocker.command.add")) {
                        sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>You don't have permissions for this command !"))
                        return true
                    }
                    sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>Syntax error /country add [country_iso_code]"))
                }
                if (args[0] == "remove") {
                    if (!sender.hasPermission("country.blocker.command.remove")) {
                        sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>You don't have permissions for this command !"))
                        return true
                    }
                    sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>Syntax error /country add [country_iso_code]"))
                }
                if (args[0] == "list") {
                    if (!sender.hasPermission("country.blocker.command.list")) {
                        sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>You don't have permissions for this command !"))
                        return true
                    }
                    val list = countryBlocker.config.getStringList("CountryBlocker.blockList").joinToString()
                    sender.sendMessage(MiniMessage.miniMessage().deserialize("<gray>[<gold>BLOCKED<gray>] <yellow> $list"))
                }
            }

            if (args.size == 2) {
                if (args[0] == "add") {
                    if (!sender.hasPermission("country.blocker.command.add")) {
                        sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>You don't have permissions for this command !"))
                        return true
                    }
                    val blockList: MutableList<String> = countryBlocker.config.getStringList("CountryBlocker.blockList")
                    if (blockList.contains(args[1])) {
                        sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>This country exist in list !"))
                        return true
                    }

                    blockList.add(args[1])
                    countryBlocker.config.set("CountryBlocker.blockList", blockList)
                    countryBlocker.saveConfig()
                    sender.sendMessage(MiniMessage.miniMessage().deserialize("<green>Successfully country <country> was added !", TagResolver.resolver(Placeholder.parsed("country", args[1]))))
                }
                if (args[0] == "remove") {
                    if (!sender.hasPermission("country.blocker.command.remove")) {
                        sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>You don't have permissions for this command !"))
                        return true
                    }
                    val blockList: MutableList<String> = countryBlocker.config.getStringList("CountryBlocker.blockList")
                    if (!blockList.contains(args[1])) {
                        sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>This country not exist in list !"))
                        return true
                    }
                    blockList.remove(args[1])
                    countryBlocker.config.set("CountryBlocker.blockList", blockList)
                    countryBlocker.saveConfig()
                    sender.sendMessage(MiniMessage.miniMessage().deserialize("<green>Successfully country <country> was deleted !", TagResolver.resolver(Placeholder.parsed("country", args[1]))))
                }
            }
        }
        return false
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): MutableList<String>? {
        if (command.name == "country") {
            val suggestCommands: MutableList<String> = arrayListOf()
            val completions: MutableList<String> = arrayListOf()
            val blockedList: MutableList<String> = countryBlocker.config.getStringList("CountryBlocker.blockList")
            if (args.size == 1) {
                if (sender.hasPermission("country.blocker.command.help"))
                    suggestCommands.add("help")
                if (sender.hasPermission("country.blocker.command.add"))
                    suggestCommands.add("add")
                if (sender.hasPermission("country.blocker.command.remove"))
                    suggestCommands.add("remove")
                if (sender.hasPermission("country.blocker.command.list"))
                    suggestCommands.add("list")
                StringUtil.copyPartialMatches(args[0], suggestCommands, completions)
            } else if (args.size == 2) {
                if (args[0] == "remove") {
                    StringUtil.copyPartialMatches(args[1], blockedList, completions)
                }
            }
            completions.sort()
            return completions
        }
        return null
    }
}
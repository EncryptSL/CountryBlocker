# CountryBlocker
![release](https://img.shields.io/github/v/release/encryptsl/countryblocker?style=flat-square)
![downloads](https://img.shields.io/github/downloads/encryptsl/countryblocker/total?style=flat-square)

Minecraft connection country blocker - simple plugin based on PaperMC, plugin can disable connection from blocked country.

1. StoryLine is simple, I am a developer from Czech Republic and in memory of Czechs is Russian Federation like an aggressive state.

2. For this simple reason a i created this plugin for blocking connection from any country. 

3. Plugin is created in Kotlin and for detection uses Maxmind GeoLite included database in free.

#### Слава Україні


Configuration: 
announcement - Is for admins notification about connection from blocked country.
deny_message - Is for denied connection notify for user on his screen.
blockList - There you can block any country with ISO format code. 

Permission:
broadcast announcement for admins: `country.blocker.announcement`

List with iso codes: https://en.wikipedia.org/wiki/List_of_ISO_3166_country_codes

```yml
CountryBlocker:
  announcement: "<hover:show_text:\"<blue>CountryBlocker: <white><user>\n<gray>IPAddress: <gold><ip>\n<dark_green>Country: <white><country>\n<gold>Time: <white><time>\"><gray>User <user> trying connect on this server from <country>"
  deny_message: "<gray>Your country <yellow><connected_country> <gray>was blocked on this server !"
  blockList:
    - RU
```


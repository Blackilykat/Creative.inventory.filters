# Creative inventory filters
A spigot plugin that applies filters to the items that player take from the creataive inventory.

### Features

- Removing specific NBT tags specified in the config from items taken from the creative inventory
- Blacklisting specific items, making them vanish when spawned in
- Blacklisting sepcific items from having NBT data, making them become the base item when spawned in
- Whitelisting specific items from having NBT data, making them ignore 
- A general bypass permission to bypass all filters with any item
- A specific bypass permission to bypass all filters for one specific item

### Dependecies

- [NBT-API](https://www.curseforge.com/minecraft/bukkit-plugins/nbt-api)
- [ProtocolLib](https://ci.dmulloy2.net/job/ProtocolLib/lastSuccessfulBuild/)

### Planned features

- A command to reload the config
- A command to change anything in the config in-game  
- A specific bypass permission to bypass one specific filter for any item
- A working replacement item for blacklisted ones *(half-works: can't set amount, name and lore)*
- Filtering items inside of shulkers, chests etc.

### Contact me

If you need help or I'm not replying to an issue feel free to [message me on discord](https://discordapp.com/users/442033332952498177)

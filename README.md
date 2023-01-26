# Creative inventory filters
A spigot plugin that applies filters to the items that player take from the creataive inventory.

## Features
***
- Removing specific NBT tags specified in the config from items taken from the creative inventory
- Blacklisting specific items, making them vanish when spawned in
- Blacklisting sepcific items from having NBT data, making them become the base item when spawned in
- Whitelisting specific items from having NBT data, making them ignore 
- A general bypass permission to bypass all filters with any item
- A specific bypass permission to bypass all filters for one specific item
- A command to edit the config in-game.

## Dependecies
***
- [NBT-API](https://www.curseforge.com/minecraft/bukkit-plugins/nbt-api)
- [ProtocolLib](https://ci.dmulloy2.net/job/ProtocolLib/lastSuccessfulBuild/)
- [CommandAPI](https://www.spigotmc.org/resources/api-commandapi-1-13-1-19-3.62353/)

## Commands
***
### /creativeinventoryfilters reload
reloads the config.yml

### /creativeinventoryfilters set <setting\> <item\> <value\>
Changes the config in-game, please note that reloading it cancels these changes.

## Permissions
***
###### To edit permissions individually you can use a permission plugin like [LuckPerms](https://luckperms.net/).
### creativeinventoryfilters.bypass
Lets you bypass all filters.
Default: OP
### creativeinventoryfilters.reload
Gives access to the reload command.
Default: OP
### creativeinventoryfilters.set
Gives acces to the set command.
Default: OP
### creativeinventoryfilters.debug
Gives access to debugging commands
Default: false


## Planned features
***
- A gui to change anything in the config in-game  
- A specific bypass permission to bypass one specific filter for any item
- A working replacement item for blacklisted ones *(half-works: can't set amount, name and lore)*
- Filtering items inside shulkers, chests etc.
- Blocking the use of specific items while letting the player have them in their inventory
- A configurable admin warning system when the filter removes an nbt component, letting them accept or deny it

## Contact me
***
If you need help or I'm not replying to an issue feel free to [message me on discord](https://discordapp.com/users/442033332952498177)

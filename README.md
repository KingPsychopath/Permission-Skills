# Permission-Skills
Permission Skills Source-code
Permission Skills adds 61 skills to your server which greatly enhances the role playing experience. All skills can be allocated to players through a regular permissions plugin and can be customised to your hearts content. You can add the skills to a group of players or individual players to create unique guilds, races and factions or even add perks to players who donate.

Skills
All skills require the ".active" extension to enable them, for example "pss.invisible.active". On top of this most skills are customisable with various extensions, for example "pss.maxhealth.active" & "pss.maxhealth.health.10" which would activate the maximum health a player can have to only 5 hearts (10 x half heart).

Click here for the full list of default permission nodes.

Skill	Skill Permission	Extensions
Peacekeeper	pss.peacekeeper	.active
.cooldown
Max Player Health	pss.maxhealth	.active
.cooldown
.health
Mobs Don't Target Unless Provoked	pss.mobtarget	.active
.cooldown
Recover Health	pss.recoverhealth	.active
.cooldown
XP Multiplier	pss.xpmultiplier	.active
.cooldown
.multiplier
Recover Items On Death	pss.recoveritems	.active
.cooldown
Explode On Death	pss.explodedeath	.active
.cooldown
.power
Recover Exp On Death	pss.recoverexp	.active
.cooldown
Invisibility	pss.invisible	.active
.cooldown
See Invisible Players	pss.seeinvisible	.active
.cooldown
Flight	pss.flight	.active
.cooldown
.power
High Jump	pss.highjump	.active
.cooldown
.power
Breath Underwater	pss.oxygen	.active
.cooldown
.power
Speed Walk	pss.speed	.active
.cooldown
.power
Skip Across Water	pss.swimmer	.active
.cooldown
Damage From Sunlight	pss.sunlight	.active
.cooldown
.damage
Damage From Moonlight	pss.moonlight	.active
.cooldown
.damage
Damage From Altitude	pss.altitude	.active
.cooldown
.damage
.min
.max
Damage From Storm	pss.storm	.active
.cooldown
.damage
Damage From Land	pss.landdamage	.active
.cooldown
.damage
Damage From Water	pss.waterdamage	.active
.cooldown
.damage
Explosive Arrow	pss.explosivearrow	.active
.cooldown
.power
Zombie Spawn On Arrow Hit	pss.zombiearrow	.active
.cooldown
Lightning Strike On Arrow Hit	pss.lightningarrow	.active
.cooldown
Teleport To Arrow Location On Hit	pss.tparrow	.active
.cooldown
Fire Arrow	pss.firearrow	.active
.cooldown
.ticks
Straight Arrow	pss.straightarrow	.active
.cooldown
.power
Knock Entity Back On Interact	pss.knockback	.active
.cooldown
.power
Poison Arrow	pss.poisonarrow	.active
.cooldown
.power
.ticks
Blindness Arrow	pss.blindnessarrow	.active
.cooldown
.power
.ticks
Confusion Arrow	pss.confusionarrow	.active
.cooldown
.power
.ticks
Mob Arrow	pss.mobarrow	.active
.cooldown
Fire Blade	pss.fireblade	.active
.cooldown
.ticks
Fire Punch	pss.firepunch	.active
.cooldown
.ticks
Poison Blade	pss.poisonblade	.active
.cooldown
.power
.ticks
Reflect Damage	pss.reflect	.active
.cooldown
.damage
Attack Damage From Projectiles	pss.attackprojectile	.active
.cooldown
.multiplier
Attack Damage From Melee	pss.attackmelee	.active
.cooldown
.multiplier
Defence Damage From Cactus Contact	pss.defensecontact	.active
.cooldown
.multiplier
Defence Damage From Entity / Player Melee	pss.defenseentity_attack	.active
.cooldown
.multiplier
Defence Damage From Projectile	pss.defenseprojectile	.active
.cooldown
.multiplier
Defence Damage From Suffocation	pss.defensesuffocation	.active
.cooldown
.multiplier
Defence Damage From Falling	pss.defensefall	.active
.cooldown
.multiplier
Defence Damage From Direct Fire	pss.defensefire	.active
.cooldown
.multiplier
Defence Damage From Fire Ticks	pss.defensefire_tick	.active
.cooldown
.multiplier
Defence Damage From Lava Melting	pss.defensemelting	.active
.cooldown
.multiplier
Defence Damage From Direct Lava	pss.defenselava	.active
.cooldown
.multiplier
Defence Damage From Drowning	pss.defensedrowning	.active
.cooldown
.multiplier
Defence Damage From Block Explosion	pss.defenseblock_explosion	.active
.cooldown
.multiplier
Defence Damage From Creeper Explosion	pss.defenseentity_explosion	.active
.cooldown
.multiplier
Defence Damage From Falling Below Bedrock	pss.defensevoid	.active
.cooldown
.multiplier
Defence Damage From Lightning	pss.defenselightning	.active
.cooldown
.multiplier
Defence Damage From Suicide Command	pss.defensesuicide	.active
.cooldown
.multiplier
Defence Damage From Starving	pss.defensestarvation	.active
.cooldown
.multiplier
Defence Damage From Poison Ticks	pss.defensepoison	.active
.cooldown
.multiplier
Defence Damage From Magic	pss.defensemagic	.active
.cooldown
.multiplier
Defence Damage From Wither	pss.defensewither	.active
.cooldown
.multiplier
Defence Damage From Falling Blocks	pss.defensefalling_block	.active
.cooldown
.multiplier
Defence Damage From Custom Damage	pss.defensecustom	.active
.cooldown
.multiplier
Default Extensions
Extensions are the customable nodes attached to skill permissions, for example pss.firearrow.ticks.200. The config.yml file contains all the values for the plugin to check but you can change these manually and use the /pss reload command. If there are no values in the list in config.yml a default value is used:

Extension	Default Value	Limits
.active	false	true or false
.power	1	Between 1 & 20
.health	20	Between 1 & 20
.damage	1	Between 1 & 20
.multiplier	100	Between 0 & 1000 (where 0 is no damage and 1000 is x10 damage)
.ticks	100	Between 1 & 10000
.cooldown	0L	None (0 = no cooldown, 60 = 60 seconds)
.min	0	Between 0 & 255
.max	255	Between 0 & 255
Worlds & Biomes
All skills require the player to be in an active world and biome (similar to the Guilds plugin) to work. Using active worlds and biomes you can restrict the plugin to just one world, have races where there abilities only work in one world or even create a guild that can only live in the desert.

pss.world.all = all worlds pss.biome.all = all biomes

Examples:

Permissions	Result
pss.world.world
pss.sunlight.damage.1
pss.moonlight.damage.1	Player will receive half a heart damage ever coulpe seconds if not in the world "world"
pss.world.all
pss.biome.desert
pss.attackmelee.active
pss.attackmelee.multiplier.200	Player will deal double damage while in any desert biome.
pss.world.all
pss.biome.all
pss.moonlight.active
pss.sunlight.active
pss.moonlight.damage.1
pss.sunlight.damage.1	Player will receive damage unless they stay underground.
Permission Extension Values
For Permission Skills to know what permission extension values to search a player for the plugin uses values saved in the config,yml. These can be changed allowing the server owner to customise the plugin. Click here for the default config.yml.

If you only require pss.maxhealth.health to only have two values 18 and 16 then you can remove the rest allowing the plugin to be optimised and customised to your specific needs. pss.maxhealth.health.18 & pss.maxhealth.health.16. The plugin will only need to check for these permissions speeding up the process.

HealthValues:
- 16
- 18
CoolDownValues:
- 0 // No cooldown
- 60 // 60 second cooldown
Commands
Command	Description	Permission
/pss reload	Reload plugin	pss.reload
/pss list	List all available permissions including all extensions	pss.list
/pss exempt <player>	Stop this player from being able to use the plugin skills regardless of permissions. Useful for players with "*" permission.	pss.exempt
/pss search <string>	Search permissions list	pss.search

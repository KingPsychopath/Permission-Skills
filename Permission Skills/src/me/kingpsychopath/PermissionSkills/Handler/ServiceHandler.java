package me.kingpsychopath.PermissionSkills.Handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import me.kingpsychopath.PermissionSkills.MechanicBase;
import me.kingpsychopath.PermissionSkills.PermissionSkills;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;

/**
 * Created by Owen on 03/07/2016.
 */
public class ServiceHandler extends MechanicBase implements Listener {
    public static Logger log = Logger.getLogger("Minecraft");

    private PermissionSkills ps;

    public ServiceHandler(PermissionSkills instance) {
        super(instance); ps = instance;
    }

    public PluginManager pm;

    public final String INVALID_CMD = "Unknown command. Type \"/help\" for help.";

    public void disable() {
        onDisable();
        setEnabled(false);
    }

    public void enable() {
        boolean enabled = onEnable();
        setEnabled(enabled);
    }

    public boolean onEnable() {
        Initialize();
        PermissionSkills.getPlugin().getServer().getPluginManager().registerEvents(this, PermissionSkills.getPlugin());
        pm = Bukkit.getPluginManager();

        return true;
    }

    public void onDisable() {
        clearScheduler();
    }

    public Boolean DebugMessages;
    public Boolean ExemptOp;
    public Boolean ExemptCreative;
    public Map<Player, Integer> TasksSun;
    public Map<Player, Integer> TasksWater;
    public Map<Player, Integer> TasksStorm;
    public Map<Player, Integer> TasksLand;
    public Map<Player, Integer> TasksMoon;
    public Map<Player, Integer> TasksAltitude;
    public Map<Player, Integer> TasksHeal;
    public List<Integer> PowerValues;
    public List<Integer> MultiplierValues;
    public List<Integer> TickValues;
    public List<Long> CoolDownValues;
    public List<Integer> MinMaxValues;
    public List<Integer> HealthValues;
    public List<Integer> DamageValues;
    public List<String> PermissionNodes;
    public List<String> ExemptPlayers;
    public Map<Player, List<ItemStack>> Inventory;

    public void Initialize() {
        DebugMessages = true;
        ExemptOp = true;
        ExemptCreative = true;
        TasksSun = new HashMap<Player, Integer>();
        TasksWater = new HashMap<Player, Integer>();
        TasksStorm = new HashMap<Player, Integer>();
        TasksLand = new HashMap<Player, Integer>();
        TasksMoon = new HashMap<Player, Integer>();
        TasksAltitude = new HashMap<Player, Integer>();
        TasksHeal = new HashMap<Player, Integer>();
        PowerValues = new ArrayList<Integer>();
        MultiplierValues = new ArrayList<Integer>();
        TickValues = new ArrayList<Integer>();
        CoolDownValues = new ArrayList<Long>();
        MinMaxValues = new ArrayList<Integer>();
        HealthValues = new ArrayList<Integer>();
        DamageValues = new ArrayList<Integer>();
        PermissionNodes = new ArrayList<String>();
        ExemptPlayers = new ArrayList<String>();
        Inventory = new HashMap<Player, List<ItemStack>>();
    }

    public boolean isHelmet(ItemStack is) {
        Material m = is.getType();
        if (m == Material.LEATHER_HELMET || m == Material.GOLD_HELMET || m == Material.DIAMOND_HELMET || m == Material.CHAINMAIL_HELMET
                || m == Material.IRON_HELMET) {
            return true;
        }
        return false;
    }

    public boolean isChestplate(ItemStack is) {
        Material m = is.getType();
        if (m == Material.LEATHER_CHESTPLATE || m == Material.GOLD_CHESTPLATE || m == Material.DIAMOND_CHESTPLATE || m == Material.CHAINMAIL_CHESTPLATE
                || m == Material.IRON_CHESTPLATE) {
            return true;
        }
        return false;
    }

    public boolean isLeggings(ItemStack is) {
        Material m = is.getType();
        if (m == Material.LEATHER_LEGGINGS || m == Material.GOLD_LEGGINGS || m == Material.DIAMOND_LEGGINGS || m == Material.CHAINMAIL_LEGGINGS
                || m == Material.IRON_LEGGINGS) {
            return true;
        }
        return false;
    }

    public boolean isBoots(ItemStack is) {
        Material m = is.getType();
        if (m == Material.LEATHER_BOOTS || m == Material.GOLD_BOOTS || m == Material.DIAMOND_BOOTS || m == Material.CHAINMAIL_BOOTS || m == Material.IRON_BOOTS) {
            return true;
        }
        return false;
    }

    public boolean isAxe(ItemStack is) {
        Material m = is.getType();
        if (m == Material.WOOD_AXE || m == Material.GOLD_AXE || m == Material.STONE_AXE || m == Material.IRON_AXE || m == Material.DIAMOND_AXE) {
            return true;
        }
        return false;
    }

    public boolean isBlade(ItemStack is) {
        Material m = is.getType();
        if (m == Material.WOOD_SWORD || m == Material.GOLD_SWORD || m == Material.STONE_SWORD || m == Material.IRON_SWORD || m == Material.DIAMOND_SWORD) {
            return true;
        }
        return false;
    }

    public boolean isPick(ItemStack is) {
        Material m = is.getType();
        if (m == Material.WOOD_PICKAXE || m == Material.GOLD_PICKAXE || m == Material.STONE_PICKAXE || m == Material.IRON_PICKAXE || m == Material.DIAMOND_PICKAXE) {
            return true;
        }
        return false;
    }

    public boolean isShovel(ItemStack is) {
        Material m = is.getType();
        if (m == Material.WOOD_SPADE || m == Material.GOLD_SPADE || m == Material.STONE_SPADE || m == Material.IRON_SPADE || m == Material.DIAMOND_SPADE) {
            return true;
        }
        return false;
    }

    public boolean isHoe(ItemStack is) {
        Material m = is.getType();
        if (m == Material.WOOD_HOE || m == Material.GOLD_HOE || m == Material.STONE_HOE || m == Material.IRON_HOE || m == Material.DIAMOND_HOE) {
            return true;
        }
        return false;
    }

    public boolean isBow(ItemStack is) {
        Material m = is.getType();
        if (m == Material.BOW) {
            return true;
        }
        return false;
    }

    public boolean isShear(ItemStack is) {
        Material m = is.getType();
        if (m == Material.SHEARS) {
            return true;
        }
        return false;
    }

    public void clearScheduler() {
        Bukkit.getScheduler().cancelTasks(PermissionSkills.getPlugin());
    }

    public void clearPlayerScheduler(final Player p) {
        if (TasksWater.containsKey(p)) {
            Bukkit.getScheduler().cancelTask((int)TasksWater.get(p));
            TasksWater.remove(p);
        }
        if (TasksLand.containsKey(p)) {
            Bukkit.getScheduler().cancelTask((int)TasksLand.get(p));
            TasksLand.remove(p);
        }
        if (TasksSun.containsKey(p)) {
            Bukkit.getScheduler().cancelTask((int)TasksSun.get(p));
            TasksSun.remove(p);
        }
        if (TasksMoon.containsKey(p)) {
            Bukkit.getScheduler().cancelTask((int)TasksMoon.get(p));
            TasksMoon.remove(p);
        }
        if (TasksStorm.containsKey(p)) {
            Bukkit.getScheduler().cancelTask((int)TasksStorm.get(p));
            TasksStorm.remove(p);
        }
        if (TasksAltitude.containsKey(p)) {
            Bukkit.getScheduler().cancelTask((int)TasksAltitude.get(p));
            TasksAltitude.remove(p);
        }
        if (TasksHeal.containsKey(p)) {
            Bukkit.getScheduler().cancelTask((int)TasksHeal.get(p));
            TasksHeal.remove(p);
        }
    }

    private void setCoolDown(final Player player, final String name) {
        player.setMetadata(name.toLowerCase(), (MetadataValue)new FixedMetadataValue(PermissionSkills.getPlugin(), (Object)System.currentTimeMillis()));
    }

    private boolean getCoolDown(final Player player, final String name) {
        final List<MetadataValue> values = (List<MetadataValue>)player.getMetadata(name.toLowerCase());
        for (final MetadataValue value : values) {
            if (value.getOwningPlugin().getDescription().getName().equals(ps.getDescription().getName())) {
                return useCoolDown(player, name.toLowerCase(), value.asLong());
            }
        }
        setCoolDown(player, name);
        return true;
    }

    private boolean useCoolDown(final Player player, final String name, final Long time) {
        if (skillCoolDown(player, name) <= 0L) {
            return true;
        }
        if (System.currentTimeMillis() - time < skillCoolDown(player, name)) {
            return false;
        }
        setCoolDown(player, name);
        return true;
    }

    public boolean skillWorld(final Player p, final World w) {
        return p.hasPermission("pss.world.all") || p.hasPermission("pss.world." + w.toString().toLowerCase());
    }

    public boolean skillBiome(final Player p, final Biome b) {
        return p.hasPermission("pss.biome.all") || p.hasPermission("pss.biome." + b.toString().toLowerCase());
    }

    public boolean skillActive(final Player p, final String s) {
        return (!ExemptOp || !p.isOp()) && !ExemptPlayers.contains(p.getName()) && (!ExemptCreative || !p.getGameMode().equals((Object) GameMode.CREATIVE)) && p.hasPermission("pss." + s + ".active") && getCoolDown(p, s.toLowerCase());
    }

    public Integer skillPower(final Player p, final String s) {
        for (final Integer i : PowerValues) {
            if (p.hasPermission("pss." + s + ".power." + i.toString())) {
                if (i > 20) {
                    return 20;
                }
                return i;
            }
        }
        return 1;
    }

    public Integer skillHealth(final Player p, final String s) {
        for (final Integer i : HealthValues) {
            if (p.hasPermission("pss." + s + ".health." + i.toString())) {
                if (i > 20) {
                    return 20;
                }
                return i;
            }
        }
        return 20;
    }

    public Integer skillDamage(final Player p, final String s) {
        for (final Integer i : DamageValues) {
            if (p.hasPermission("pss." + s + ".damage." + i.toString())) {
                if (i > 20) {
                    return 20;
                }
                return i;
            }
        }
        return 1;
    }

    public Double skillMultiplier(final Player p, final String s) {
        for (final Integer i : MultiplierValues) {
            if (p.hasPermission("pss." + s + ".multiplier." + i.toString())) {
                final Double Value = i / 100.0;
                if (Value > 10.0) {
                    return 10.0;
                }
                return Value;
            }
        }
        return 1.0;
    }

    public Integer skillTicks(final Player p, final String s) {
        for (final Integer i : TickValues) {
            if (p.hasPermission("pss." + s + ".ticks." + i.toString())) {
                if (i > 10000) {
                    return 10000;
                }
                return i;
            }
        }
        return 100;
    }

    public Long skillCoolDown(final Player p, final String s) {
        for (final Long i : CoolDownValues) {
            if (p.hasPermission("pss." + s + ".cooldown." + i.toString())) {
                return i * 1000L;
            }
        }
        return 0L;
    }

    public Integer skillMin(final Player p, final String s) {
        Integer i = 0;
        while (i < 255) {
            if (p.hasPermission("pss." + s + ".min." + i.toString())) {
                if (i > 255) {
                    return 255;
                }
                return i;
            }
            else {
                ++i;
            }
        }
        return 0;
    }

    public Integer skillMax(final Player p, final String s) {
        Integer i = 0;
        while (i < 255) {
            if (p.hasPermission("pss." + s + ".max." + i.toString())) {
                if (i > 255) {
                    return 255;
                }
                return i;
            }
            else {
                ++i;
            }
        }
        return 255;
    }

    public void showPlayer(final Player p) {
        for (Player pl : Bukkit.getOnlinePlayers()) {
            pl.showPlayer(p);
        }
    }

    public void hidePlayer(final Player p) {
        Player[] onlinePlayers;
        for (Player pl : Bukkit.getOnlinePlayers()) {
            if (!pl.equals(p) && !skillActive(pl, "seeinvisible")) {
                pl.hidePlayer(p);
            }
        }
    }

}

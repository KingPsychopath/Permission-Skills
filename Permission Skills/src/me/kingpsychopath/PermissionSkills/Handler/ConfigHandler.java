package me.kingpsychopath.PermissionSkills.Handler;

import java.util.logging.Logger;
import me.kingpsychopath.PermissionSkills.MechanicBase;
import me.kingpsychopath.PermissionSkills.PermissionSkills;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

/**
 * Created by Owen on 03/07/2016.
 */
public class ConfigHandler extends MechanicBase implements Listener {
    public static Logger log = Logger.getLogger("Minecraft");

    private PermissionSkills ps;

    public ConfigHandler(PermissionSkills instance) {
        super(instance); ps = instance;
    }

    private PluginManager pm;

    private final String INVALID_CMD = "Unknown command. Type \"/help\" for help.";

    public void disable() {
        onDisable(); setEnabled(false);
    }

    public void enable() {
        boolean enabled = onEnable();
        setEnabled(enabled);
    }

    public boolean onEnable() {
        ps.saveDefaultConfig();
        PermissionSkills.getPlugin().getServer().getPluginManager().registerEvents(this, PermissionSkills.getPlugin());
        pm = Bukkit.getPluginManager();
        load();

        return true;
    }
    
    public void onDisable() {
        save();
    }

    public void save() {
        ps.reloadConfig();
        final FileConfiguration Config = ps.getConfig();
        Config.set("ExemptPlayers", (Object)null);
        Config.set("ExemptPlayers", (Object)ps.getServiceHandler().ExemptPlayers);
        ps.saveConfig();
    }

    public void load() {
        ps.reloadConfig();
        final FileConfiguration Config = ps.getConfig();
        ps.getServiceHandler().DebugMessages = Config.getBoolean("DebugMessages", true);
        ps.getServiceHandler().ExemptOp = Config.getBoolean("ExemptOp", true);
        ps.getServiceHandler().ExemptCreative = Config.getBoolean("ExemptCreative", true);
        ps.getServiceHandler().ExemptPlayers.clear();
        if (Config.isSet("ExemptPlayers")) {
            for (final String s : Config.getStringList("ExemptPlayers")) {
                if (!ps.getServiceHandler().ExemptPlayers.contains(s)) {
                    ps.getServiceHandler().ExemptPlayers.add(s);
                }
            }
        }
        Boolean Found = false;
        if (Config.isSet("PowerValues")) {
            for (final Integer i : Config.getIntegerList("PowerValues")) {
                Found = true;
                if (!ps.getServiceHandler().PowerValues.contains(i)) {
                    ps.getServiceHandler().PowerValues.add(i);
                }
            }
        }
        if (!Found) {
            ps.getServiceHandler().PowerValues.add(1);
            ps.getServiceHandler().PowerValues.add(2);
            ps.getServiceHandler().PowerValues.add(5);
            ps.getServiceHandler().PowerValues.add(10);
        }
        Found = false;
        if (Config.isSet("MultiplierValues")) {
            for (final Integer i : Config.getIntegerList("MultiplierValues")) {
                Found = true;
                if (!ps.getServiceHandler().MultiplierValues.contains(i)) {
                    ps.getServiceHandler().MultiplierValues.add(i);
                }
            }
        }
        if (!Found) {
            ps.getServiceHandler().MultiplierValues.add(0);
            ps.getServiceHandler().MultiplierValues.add(25);
            ps.getServiceHandler().MultiplierValues.add(50);
            ps.getServiceHandler().MultiplierValues.add(75);
            ps.getServiceHandler().MultiplierValues.add(100);
            ps.getServiceHandler().MultiplierValues.add(200);
            ps.getServiceHandler().MultiplierValues.add(250);
            ps.getServiceHandler().MultiplierValues.add(500);
            ps.getServiceHandler().MultiplierValues.add(1000);
        }
        Found = false;
        if (Config.isSet("TickValues")) {
            for (final Integer i : Config.getIntegerList("TickValues")) {
                Found = true;
                if (!ps.getServiceHandler().TickValues.contains(i)) {
                    ps.getServiceHandler().TickValues.add(i);
                }
            }
        }
        if (!Found) {
            ps.getServiceHandler().TickValues.add(0);
            ps.getServiceHandler().TickValues.add(100);
            ps.getServiceHandler().TickValues.add(250);
            ps.getServiceHandler().TickValues.add(500);
            ps.getServiceHandler().TickValues.add(750);
            ps.getServiceHandler().TickValues.add(1000);
        }
        Found = false;
        if (Config.isSet("CoolDownValues")) {
            for (final Long l : Config.getLongList("CoolDownValues")) {
                Found = true;
                if (!ps.getServiceHandler().CoolDownValues.contains(l)) {
                    ps.getServiceHandler().CoolDownValues.add(l);
                }
            }
        }
        if (!Found) {
            ps.getServiceHandler().CoolDownValues.add(0L);
            ps.getServiceHandler().CoolDownValues.add(1000L);
            ps.getServiceHandler().CoolDownValues.add(10000L);
        }
        Found = false;
        if (Config.isSet("HealthValues")) {
            for (final Integer i : Config.getIntegerList("HealthValues")) {
                Found = true;
                if (!ps.getServiceHandler().HealthValues.contains(i)) {
                    ps.getServiceHandler().HealthValues.add(i);
                }
            }
        }
        if (!Found) {
            ps.getServiceHandler().HealthValues.add(5);
            ps.getServiceHandler().HealthValues.add(10);
            ps.getServiceHandler().HealthValues.add(15);
            ps.getServiceHandler().HealthValues.add(20);
        }
        Found = false;
        if (Config.isSet("DamageValues")) {
            for (final Integer i : Config.getIntegerList("DamageValues")) {
                Found = true;
                if (!ps.getServiceHandler().DamageValues.contains(i)) {
                    ps.getServiceHandler().DamageValues.add(i);
                }
            }
        }
        if (!Found) {
            ps.getServiceHandler().DamageValues.add(1);
            ps.getServiceHandler().DamageValues.add(2);
            ps.getServiceHandler().DamageValues.add(5);
        }
        Found = false;
        if (Config.isSet("MinMaxValues")) {
            for (final Integer i : Config.getIntegerList("MinMaxValues")) {
                Found = true;
                if (!ps.getServiceHandler().MinMaxValues.contains(i)) {
                    ps.getServiceHandler().MinMaxValues.add(i);
                }
            }
        }
        if (!Found) {
            ps.getServiceHandler().MinMaxValues.add(20);
            ps.getServiceHandler().MinMaxValues.add(200);
        }
    }
}

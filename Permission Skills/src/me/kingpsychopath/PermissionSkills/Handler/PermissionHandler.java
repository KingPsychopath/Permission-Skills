package me.kingpsychopath.PermissionSkills.Handler;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.kingpsychopath.PermissionSkills.MechanicBase;
import me.kingpsychopath.PermissionSkills.PermissionSkills;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.PluginManager;

/**
 * Created by Owen on 03/07/2016.
 */
public class PermissionHandler extends MechanicBase implements Listener {
    public static Logger log = Logger.getLogger("Minecraft");
    
    private PermissionSkills ps;
    private PluginManager pm;

    public PermissionHandler(PermissionSkills instance) {
        super(instance); ps = instance;
    }
    
    private final String INVALID_CMD = "Unknown command. Type \"/help\" for help.";

    public void disable() {
        setEnabled(false);
    }

    public void enable() {
        boolean enabled = onEnable();
        setEnabled(enabled);
    }

    public boolean onEnable() {
        PermissionSkills.getPlugin().getServer().getPluginManager().registerEvents(this, PermissionSkills.getPlugin());
        pm = Bukkit.getPluginManager();
        setPermissionsList();
        savePermissionsList();

        return true;
    }


    private void savePermissionsList() {
        if (!new File(ps.getDataFolder(), "permissions.yml").exists()) {
            ps.saveResource("permissions.yml", false);
        }
        final File PermissionsFile = new File(ps.getDataFolder(), "permissions.yml");
        final FileConfiguration Permissions = (FileConfiguration) YamlConfiguration.loadConfiguration(PermissionsFile);
        final Set<String> keys = (Set<String>)Permissions.getConfigurationSection("").getKeys(false);
        for (final String str : keys) {
            Permissions.set(str, (Object)null);
        }
        int i = 0;
        for (final String s : ps.getServiceHandler().PermissionNodes) {
            ++i;
            Permissions.set(new StringBuilder(String.valueOf(i)).toString(), (Object)s);
        }
        try {
            Permissions.options().copyDefaults(true);
            Permissions.save(PermissionsFile);
        }
        catch (IOException ex) {
            log.log(Level.SEVERE, "Could not save config to " + PermissionsFile, ex);
        }
    }

    private void setPermissionsList() {
        ps.getServiceHandler().PermissionNodes.clear();
        ps.getServiceHandler().PermissionNodes.add("pss.reload");
        ps.getServiceHandler().PermissionNodes.add("pss.search");
        ps.getServiceHandler().PermissionNodes.add("pss.list");
        ps.getServiceHandler().PermissionNodes.add("pss.exempt");
        ps.getServiceHandler().PermissionNodes.add("pss.world.all");
        for (final World w : Bukkit.getWorlds()) {
            ps.getServiceHandler().PermissionNodes.add("pss.world." + w.getName().toLowerCase());
        }
        ps.getServiceHandler().PermissionNodes.add("pss.biome.all");
        Biome[] values;
        for (int length = (values = Biome.values()).length, n = 0; n < length; ++n) {
            final Biome b = values[n];
            ps.getServiceHandler().PermissionNodes.add("pss.biome." + b.name().toLowerCase());
        }
        ps.getServiceHandler().PermissionNodes.add("pss.maxhealth.active");
        for (final Integer i : ps.getServiceHandler().HealthValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.maxhealth.health." + i);
        }
        for (final Long j : ps.getServiceHandler().CoolDownValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.maxhealth.cooldown." + j);
        }
        ps.getServiceHandler().PermissionNodes.add("pss.waterregen.active");
        ps.getServiceHandler().PermissionNodes.add("pss.mobtarget.active");
        for (final Long j : ps.getServiceHandler().CoolDownValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.mobtarget.cooldown." + j);
        }
        ps.getServiceHandler().PermissionNodes.add("pss.recoverhealth.active");
        for (final Long j : ps.getServiceHandler().CoolDownValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.recoverhealth.cooldown." + j);
        }
        ps.getServiceHandler().PermissionNodes.add("pss.xpmultiplier.active");
        for (final Integer i : ps.getServiceHandler().MultiplierValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.xpmultiplier.multiplier." + i);
        }
        for (final Long j : ps.getServiceHandler().CoolDownValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.xpmultiplier.cooldown." + j);
        }
        ps.getServiceHandler().PermissionNodes.add("pss.recoveritems.active");
        for (final Long j : ps.getServiceHandler().CoolDownValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.recoveritems.cooldown." + j);
        }
        ps.getServiceHandler().PermissionNodes.add("pss.explodedeath.active");
        for (final Integer i : ps.getServiceHandler().PowerValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.explodedeath.power." + i);
        }
        for (final Long j : ps.getServiceHandler().CoolDownValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.explodedeath.cooldown." + j);
        }
        ps.getServiceHandler().PermissionNodes.add("pss.recoverexp.active");
        for (final Long j : ps.getServiceHandler().CoolDownValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.recoverexp.cooldown." + j);
        }
        ps.getServiceHandler().PermissionNodes.add("pss.peacekeeper.active");
        for (final Long j : ps.getServiceHandler().CoolDownValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.peacekeeper.cooldown." + j);
        }
        ps.getServiceHandler().PermissionNodes.add("pss.invisible.active");
        for (final Long j : ps.getServiceHandler().CoolDownValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.invisible.cooldown." + j);
        }
        ps.getServiceHandler().PermissionNodes.add("pss.seeinvisible.active");
        for (final Long j : ps.getServiceHandler().CoolDownValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.seeinvisible.cooldown." + j);
        }
        ps.getServiceHandler().PermissionNodes.add("pss.flight.active");
        for (final Integer i : ps.getServiceHandler().PowerValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.flight.power." + i);
        }
        for (final Long j : ps.getServiceHandler().CoolDownValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.flight.cooldown." + j);
        }
        ps.getServiceHandler().PermissionNodes.add("pss.highjump.active");
        for (final Integer i : ps.getServiceHandler().PowerValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.highjump.power." + i);
        }
        for (final Long j : ps.getServiceHandler().CoolDownValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.highjump.cooldown." + j);
        }
        ps.getServiceHandler().PermissionNodes.add("pss.oxygen.active");
        for (final Integer i : ps.getServiceHandler().PowerValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.oxygen.power." + i);
        }
        for (final Long j : ps.getServiceHandler().CoolDownValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.oxygen.cooldown." + j);
        }
        ps.getServiceHandler().PermissionNodes.add("pss.speed.active");
        for (final Integer i : ps.getServiceHandler().PowerValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.speed.power." + i);
        }
        for (final Long j : ps.getServiceHandler().CoolDownValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.speed.cooldown." + j);
        }
        ps.getServiceHandler().PermissionNodes.add("pss.swimmer.active");
        for (final Long j : ps.getServiceHandler().CoolDownValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.swimmer.cooldown." + j);
        }
        ps.getServiceHandler().PermissionNodes.add("pss.sunlight.active");
        for (final Integer i : ps.getServiceHandler().DamageValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.sunlight.damage." + i);
        }
        ps.getServiceHandler().PermissionNodes.add("pss.altitude.active");
        for (final Integer i : ps.getServiceHandler().DamageValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.altitude.damage." + i);
        }
        for (final Integer i : ps.getServiceHandler().MinMaxValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.altitude.min." + i);
        }
        for (final Integer i : ps.getServiceHandler().MinMaxValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.altitude.max." + i);
        }
        ps.getServiceHandler().PermissionNodes.add("pss.moonlight.active");
        for (final Integer i : ps.getServiceHandler().DamageValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.moonlight.damage." + i);
        }
        ps.getServiceHandler().PermissionNodes.add("pss.storm.active");
        for (final Integer i : ps.getServiceHandler().DamageValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.storm.damage." + i);
        }
        ps.getServiceHandler().PermissionNodes.add("pss.landdamage.active");
        for (final Integer i : ps.getServiceHandler().DamageValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.landdamage.damage." + i);
        }
        ps.getServiceHandler().PermissionNodes.add("pss.waterdamage.active");
        for (final Integer i : ps.getServiceHandler().DamageValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.waterdamage.damage." + i);
        }
        ps.getServiceHandler().PermissionNodes.add("pss.explosivearrow.active");
        for (final Integer i : ps.getServiceHandler().PowerValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.explosivearrow.power." + i);
        }
        for (final Long j : ps.getServiceHandler().CoolDownValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.explosivearrow.cooldown." + j);
        }
        ps.getServiceHandler().PermissionNodes.add("pss.zombiearrow.active");
        for (final Long j : ps.getServiceHandler().CoolDownValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.zombiearrow.cooldown." + j);
        }
        ps.getServiceHandler().PermissionNodes.add("pss.lightningarrow.active");
        for (final Long j : ps.getServiceHandler().CoolDownValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.lightningarrow.cooldown." + j);
        }
        ps.getServiceHandler().PermissionNodes.add("pss.tparrow.active");
        for (final Long j : ps.getServiceHandler().CoolDownValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.tparrow.cooldown." + j);
        }
        ps.getServiceHandler().PermissionNodes.add("pss.firearrow.active");
        for (final Integer i : ps.getServiceHandler().TickValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.firearrow.ticks." + i);
        }
        for (final Long j : ps.getServiceHandler().CoolDownValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.firearrow.cooldown." + j);
        }
        ps.getServiceHandler().PermissionNodes.add("pss.straightarrow.active");
        for (final Integer i : ps.getServiceHandler().PowerValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.straightarrow.power." + i);
        }
        for (final Long j : ps.getServiceHandler().CoolDownValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.straightarrow.cooldown." + j);
        }
        ps.getServiceHandler().PermissionNodes.add("pss.knockback.active");
        for (final Integer i : ps.getServiceHandler().PowerValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.knockback.power." + i);
        }
        for (final Long j : ps.getServiceHandler().CoolDownValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.knockback.cooldown." + j);
        }
        EntityDamageEvent.DamageCause[] values2;
        for (int length2 = (values2 = EntityDamageEvent.DamageCause.values()).length, n2 = 0; n2 < length2; ++n2) {
            final EntityDamageEvent.DamageCause dc = values2[n2];
            ps.getServiceHandler().PermissionNodes.add("pss.defense" + dc.name().toLowerCase() + ".active");
            for (final Integer k : ps.getServiceHandler().MultiplierValues) {
                ps.getServiceHandler().PermissionNodes.add("pss.defense" + dc.name().toLowerCase() + ".multiplier" + k);
            }
            for (final Long l : ps.getServiceHandler().CoolDownValues) {
                ps.getServiceHandler().PermissionNodes.add("pss.defense" + dc.name().toLowerCase() + ".cooldown." + l);
            }
        }
        ps.getServiceHandler().PermissionNodes.add("pss.attackprojectile.active");
        for (final Integer i : ps.getServiceHandler().MultiplierValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.attackprojectile.multiplier." + i);
        }
        for (final Long j : ps.getServiceHandler().CoolDownValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.attackprojectile.cooldown." + j);
        }
        ps.getServiceHandler().PermissionNodes.add("pss.attackmelee.active");
        for (final Integer i : ps.getServiceHandler().MultiplierValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.attackmelee.multiplier." + i);
        }
        for (final Long j : ps.getServiceHandler().CoolDownValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.attackmelee.cooldown." + j);
        }
        ps.getServiceHandler().PermissionNodes.add("pss.poisonarrow.active");
        for (final Integer i : ps.getServiceHandler().PowerValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.poisonarrow.power." + i);
        }
        for (final Long j : ps.getServiceHandler().CoolDownValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.poisonarrow.cooldown." + j);
        }
        for (final Integer i : ps.getServiceHandler().TickValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.poisonarrow.ticks." + i);
        }
        ps.getServiceHandler().PermissionNodes.add("pss.blindnessarrow.active");
        for (final Integer i : ps.getServiceHandler().PowerValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.blindnessarrow.power." + i);
        }
        for (final Integer i : ps.getServiceHandler().TickValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.blindnessarrow.ticks." + i);
        }
        for (final Long j : ps.getServiceHandler().CoolDownValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.blindnessarrow.cooldown." + j);
        }
        ps.getServiceHandler().PermissionNodes.add("pss.confusionarrow.active");
        for (final Integer i : ps.getServiceHandler().PowerValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.confusionarrow.power." + i);
        }
        for (final Integer i : ps.getServiceHandler().TickValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.confusionarrow.ticks." + i);
        }
        for (final Long j : ps.getServiceHandler().CoolDownValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.confusionarrow.cooldown." + j);
        }
        ps.getServiceHandler().PermissionNodes.add("pss.mobarrow.active");
        for (final Long j : ps.getServiceHandler().CoolDownValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.mobarrow.cooldown." + j);
        }
        ps.getServiceHandler().PermissionNodes.add("pss.fireblade.active");
        for (final Integer i : ps.getServiceHandler().TickValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.fireblade.ticks." + i);
        }
        for (final Long j : ps.getServiceHandler().CoolDownValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.fireblade.cooldown." + j);
        }
        ps.getServiceHandler().PermissionNodes.add("pss.firepunch.active");
        for (final Integer i : ps.getServiceHandler().TickValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.firepunch.ticks." + i);
        }
        for (final Long j : ps.getServiceHandler().CoolDownValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.firepunch.cooldown." + j);
        }
        ps.getServiceHandler().PermissionNodes.add("pss.poisonblade.active");
        for (final Integer i : ps.getServiceHandler().PowerValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.poisonblade.power." + i);
        }
        for (final Integer i : ps.getServiceHandler().TickValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.poisonblade.ticks." + i);
        }
        for (final Long j : ps.getServiceHandler().CoolDownValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.poisonblade.cooldown." + j);
        }
        ps.getServiceHandler().PermissionNodes.add("pss.reflect.active");
        for (final Integer i : ps.getServiceHandler().DamageValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.reflect.damage." + i);
        }
        for (final Long j : ps.getServiceHandler().CoolDownValues) {
            ps.getServiceHandler().PermissionNodes.add("pss.reflect.cooldown." + j);
        }
    }

}

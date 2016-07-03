package me.kingpsychopath.PermissionSkills.Schedule;

import me.kingpsychopath.PermissionSkills.*;
import org.bukkit.entity.*;
import org.bukkit.plugin.*;

public class Scheduler
{
    private PermissionSkills ps;
    
    public Scheduler(final PermissionSkills instance) {
        ps = instance;
    }
    
    public int sun(final Player p) {
        final int i = ps.getServer().getScheduler().scheduleSyncRepeatingTask((Plugin)ps, (Runnable)new Runnable() {
            @Override
            public void run() {
                p.damage((int) ps.getServiceHandler().skillDamage(p, "sunlight"));
            }
        }, 0L, 50L);
        return i;
    }
    
    public int heal(final Player p) {
        final int i = ps.getServer().getScheduler().scheduleSyncRepeatingTask(PermissionSkills.getPlugin(), (Runnable) new Runnable() {
            @Override
            public void run() {
                p.setHealth(p.getHealth() + 1);
            }
        }, 0L, 50L);
        return i;
    }
    
    public int altitude(final Player p) {
        final int i = ps.getServer().getScheduler().scheduleSyncRepeatingTask(PermissionSkills.getPlugin(), (Runnable) new Runnable() {
            @Override
            public void run() {
                p.damage((int) ps.getServiceHandler().skillDamage(p, "altitude"));
            }
        }, 0L, 50L);
        return i;
    }
    
    public int moon(final Player p) {
        final int i = ps.getServer().getScheduler().scheduleSyncRepeatingTask(PermissionSkills.getPlugin(), (Runnable) new Runnable() {
            @Override
            public void run() {
                p.damage((int) ps.getServiceHandler().skillDamage(p, "moonlight"));
            }
        }, 0L, 50L);
        return i;
    }
    
    public int storm(final Player p) {
        final int i = ps.getServer().getScheduler().scheduleSyncRepeatingTask(PermissionSkills.getPlugin(), (Runnable) new Runnable() {
            @Override
            public void run() {
                p.damage((int) ps.getServiceHandler().skillDamage(p, "storm"));
            }
        }, 0L, 50L);
        return i;
    }
    
    public int land(final Player p) {
        final int i = ps.getServer().getScheduler().scheduleSyncRepeatingTask(PermissionSkills.getPlugin(), (Runnable) new Runnable() {
            @Override
            public void run() {
                p.damage((int) ps.getServiceHandler().skillDamage(p, "landdamage"));
            }
        }, 0L, 50L);
        return i;
    }
    
    public int water(final Player p) {
        final int i = ps.getServer().getScheduler().scheduleSyncRepeatingTask(PermissionSkills.getPlugin(), (Runnable) new Runnable() {
            @Override
            public void run() {
                p.damage((int) ps.getServiceHandler().skillDamage(p, "waterdamage"));
            }
        }, 0L, 50L);
        return i;
    }
}

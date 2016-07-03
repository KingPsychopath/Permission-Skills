package me.kingpsychopath.PermissionSkills;

import org.bukkit.permissions.Permission;

public abstract class MechanicBase {

    public PermissionSkills plugin;
    private boolean isEnabled;

    public MechanicBase(PermissionSkills instance) {
        plugin = instance;
        plugin.addMechanic(this);
        isEnabled = false;
    }

    public abstract void enable();

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean bool) {
        isEnabled = bool;
    }

    public void disable() {
        setEnabled(false);
        plugin = null;
    }
}
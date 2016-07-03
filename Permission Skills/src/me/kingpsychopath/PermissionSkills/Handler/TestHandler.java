package me.kingpsychopath.PermissionSkills.Handler;

import java.util.logging.Logger;
import me.kingpsychopath.PermissionSkills.MechanicBase;
import me.kingpsychopath.PermissionSkills.PermissionSkills;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

/**
 * Created by Owen on 03/07/2016.
 */
public class TestHandler extends MechanicBase implements Listener {
    public static Logger log = Logger.getLogger("Minecraft");

    public TestHandler(PermissionSkills instance) {
        super(instance);
    }

    private PluginManager pm;

    private final String INVALID_CMD = "Unknown command. Type \"/help\" for help.";

    public void disable() {
        setEnabled(false);
    }

    public void enable() {
        onEnable();
        setEnabled(true);
    }

    public void onEnable() {
        PermissionSkills.getPlugin().getServer().getPluginManager().registerEvents(this, PermissionSkills.getPlugin());
        pm = Bukkit.getPluginManager();
    }


}

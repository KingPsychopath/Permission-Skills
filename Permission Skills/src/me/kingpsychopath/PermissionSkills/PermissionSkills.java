package me.kingpsychopath.PermissionSkills;

import java.util.ArrayList;
import java.util.logging.Level;
import me.kingpsychopath.PermissionSkills.Commands.CommandPSS;
import me.kingpsychopath.PermissionSkills.Handler.ConfigHandler;
import me.kingpsychopath.PermissionSkills.Handler.PermissionHandler;
import me.kingpsychopath.PermissionSkills.Handler.ServiceHandler;
import me.kingpsychopath.PermissionSkills.Listeners.PlayerListener;
import me.kingpsychopath.PermissionSkills.enums.CC;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class PermissionSkills extends JavaPlugin implements Listener
{
    private final ArrayList<MechanicBase> mechanics = new ArrayList<MechanicBase>();
    private static PermissionSkills plugin;

    private ServiceHandler sHandler;
    private PermissionHandler pHandler;
    private ConfigHandler cHandler;

    public ServiceHandler getServiceHandler() {
        return sHandler;
    }

    public PermissionHandler getPermissionHandler() {
        return pHandler;
    }

    public ConfigHandler getConfigHandler() {
        return cHandler;
    }

    public static PermissionSkills getInstance() {
        return getPlugin();
    }

    public void onDisable() {
        for (MechanicBase m : mechanics) {
            m.disable();
        }
    }

    private boolean run = false;

    public void onEnable() {
        setPlugin(this);

        sHandler = new ServiceHandler(this);
        pHandler = new PermissionHandler(this);
        cHandler = new ConfigHandler(this);

        new PlayerListener(this);

        CommandPSS commands = new CommandPSS(this);
        plugin.getCommand("pss").setExecutor(commands);


        for (MechanicBase m : mechanics) {
            m.enable();
        }
    }

    public void addMechanic(MechanicBase m) {
        mechanics.add(m);
    }

    public static PermissionSkills getPlugin() {
        return plugin;
    }

    public static void setPlugin(PermissionSkills plugin) {
        PermissionSkills.plugin = plugin;
    }

    public static void d(Object o) {
        d(o, CC.CYAN);
    }

    public static void d(Object o, CC color) {
        PermissionSkills.plugin.getLogger().log(Level.INFO, "{0}{1}{2}", new Object[]{color, o, CC.DEFAULT});
    }

    public void sendConsole(String msg) {
        msg = ChatColor.AQUA + "[PermissionSkills] " + ChatColor.GRAY + msg;
        msg = msg.replaceAll("&([0-9a-fk-or])", "");
        System.out.println(msg);
    }

    public void sendPlayer(final Player p, String msg) {
        msg = ChatColor.AQUA + "[PermissionSkills] " + ChatColor.GRAY + msg;
        msg = msg.replaceAll("&([0-9a-fk-or])", "");
        p.sendMessage(msg);
    }
}

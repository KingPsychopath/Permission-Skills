package me.kingpsychopath.PermissionSkills.Commands;

import me.kingpsychopath.PermissionSkills.PermissionSkills;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Owen on 03/07/2016.
 */
public class CommandPSS implements CommandExecutor {
    private PermissionSkills ps;

    public CommandPSS(PermissionSkills instance) {
        ps = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length <= 0) {
            if (sender instanceof Player) {
                ps.sendPlayer((Player) sender, ps.getDescription().getVersion());
            } else {
                ps.sendConsole("PermissionSkills " + ps.getDescription().getVersion());
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            if (sender instanceof Player) {
                if (((Player) sender).hasPermission("pss.reload")) {
                    ps.getServiceHandler().clearScheduler();
                    ps.onEnable();
                    ps.sendConsole("You have successfully initiated a reload");
                    return true;
                }
                ps.sendPlayer((Player) sender, "no permission.");
                return true;
            } else {
                ps.getServiceHandler().clearScheduler();
                ps.onEnable();
                ps.sendConsole("You have successfully initiated a reload");
                return true;
            }
        } else if (args[0].equalsIgnoreCase("exempt")) {
            if (args.length <= 1) {
                if (sender instanceof Player) {
                    ps.sendPlayer((Player) sender, "Yeah, mission control we're missing some parameters here..");
                    ps.sendPlayer((Player) sender, "/pss exempt <PLAYER>");
                } else {
                    ps.sendConsole("Yeah, mission control we're missing some parameters here...");
                    ps.sendConsole("/pss exempt <PLAYER>");
                }
                return true;
            }

            final Player player = Bukkit.getPlayer(args[1]);
            if (player == null) {
                if (sender instanceof Player) {
                    ps.sendPlayer((Player) sender, String.valueOf(args[1]) + " is/are not a recognised player.");
                } else {
                    ps.sendConsole(String.valueOf(args[1]) + " is/are not a recognised player.");
                }
                return true;
            }

            if (!(sender instanceof Player)) {
                ps.getServiceHandler().ExemptPlayers.add(player.getName());
                ps.sendConsole(String.valueOf(player.getName()) + " is now exempt from Permission Skills.");
                return true;
            }
            if (((Player) sender).hasPermission("pss.exempt")) {
                ps.getServiceHandler().ExemptPlayers.add(player.getName());
                ps.sendPlayer((Player) sender, String.valueOf(player.getName()) + " is now exempt from Permission Skills.");
                return true;
            }

            ps.sendPlayer((Player) sender, "Yeah, you don't really have permission to do this.");
            return true;
        } else if (args[0].equalsIgnoreCase("search")) {
            if (args.length <= 1) {
                if (sender instanceof Player) {
                    ps.sendPlayer((Player) sender, "Yeah, mission control we're missing some parameters here..");
                    ps.sendPlayer((Player) sender, "/pss search <LIST>");
                } else {
                    ps.sendConsole("Yeah, mission control we're missing some parameters here...");
                    ps.sendConsole("/pss search <LIST>");
                }
                return true;
            }
            Integer Count = 0;
            final String Search = args[1];
            if (sender instanceof Player) {
                if (!((Player) sender).hasPermission("pss.search")) {
                    ps.sendPlayer((Player) sender, "Nuh, uh. You can't do this; Not today....");
                    return true;
                }

                for (final String str : ps.getServiceHandler().PermissionNodes) {
                    if (str.contains(Search)) {
                        ps.sendPlayer((Player) sender, str);
                        ++Count;
                    }
                }
                if (Count > 0) {
                    ps.sendPlayer((Player) sender, Count + "results found");
                } else {
                    ps.sendPlayer((Player) sender, "0 results found");
                }
            } else {
                for (final String str : ps.getServiceHandler().PermissionNodes) {
                    if (str.contains(Search)) {
                        ps.sendConsole(str);
                        ++Count;
                    }
                }
                if (Count > 0) {
                    ps.sendConsole(Count + " results found");
                } else {
                    ps.sendConsole("0 results found");
                }
            }
        } else if (args[0].equalsIgnoreCase("list")) {
            if (sender instanceof Player) {
                if (!((Player) sender).hasPermission("pss.list")) {
                    ps.sendPlayer((Player) sender, "Mhmmm. Sweet dawgy. The taste of having no permissions...");
                    return true;
                }
                for (final String str2 : ps.getServiceHandler().PermissionNodes) {
                    ps.sendPlayer((Player) sender, str2);
                }
                ps.sendPlayer((Player) sender, String.valueOf(ps.getServiceHandler().PermissionNodes.size()) + " results found");
            } else {
                for (final String str2 : ps.getServiceHandler().PermissionNodes) {
                    ps.sendConsole(str2);
                }
                ps.sendConsole(String.valueOf(ps.getServiceHandler().PermissionNodes.size()) + " results found");
            }
        }
        return true;
    }
}

package me.kingpsychopath.PermissionSkills.Listeners;

import me.kingpsychopath.PermissionSkills.*;
import org.bukkit.event.*;
import org.bukkit.inventory.*;
import org.bukkit.potion.*;
import org.bukkit.block.*;
import org.bukkit.util.*;
import me.kingpsychopath.PermissionSkills.Schedule.*;
import java.util.*;
import org.bukkit.event.player.*;
import org.bukkit.*;
import org.bukkit.event.entity.*;
import org.bukkit.entity.*;
import org.bukkit.util.Vector;

public class PlayerListener extends MechanicBase implements Listener
{
    private PermissionSkills ps;
    
    public PlayerListener(PermissionSkills instance) {
        super (instance); ps = instance;
    }

    public void disable() {
        setEnabled(false);
    }

    public void enable() {
        boolean enabled = onEnable();
        setEnabled(enabled);//t
    }

    public boolean onEnable() {
        PermissionSkills.getPlugin().getServer().getPluginManager().registerEvents(this, PermissionSkills.getPlugin());
        return true;
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerJoin(final PlayerJoinEvent event) {
        final Player p = event.getPlayer();
        final World w = p.getWorld();
        final Biome b = p.getLocation().getBlock().getBiome();

        if (ps.getServiceHandler().skillWorld(p, w) && ps.getServiceHandler().skillBiome(p, b) && ps.getServiceHandler().skillActive(p, "maxhealth")) {
            final Integer Health = ps.getServiceHandler().skillHealth(p, "maxhealth");
            if (Health < 20 && Health > 0 && p.getHealth() > Health) {
                p.setHealth((int) Health);
            }
        }

        boolean Active = false;

        for (final World wld : Bukkit.getWorlds()) {
            if (ps.getServiceHandler().skillWorld(p, wld)) {
                Active = true;
            }
        }

        if (!Active && ps.getServiceHandler().DebugMessages) {
            ps.sendConsole("WARNING! " + p.getName() + " does not have an active world!");
        }

        Active = false;
        for (final World wld : Bukkit.getWorlds()) {
            if (ps.getServiceHandler().skillWorld(p, wld)) {
                Active = true;
            }
        }

        if (!Active && ps.getServiceHandler().DebugMessages) {
            ps.sendConsole("WARNING! " + p.getName() + " does not have an active biome!");
        }
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerQuit(final PlayerQuitEvent event) {
        ps.getServiceHandler().clearPlayerScheduler(event.getPlayer());
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerChangeWorld(final PlayerChangedWorldEvent event) {
        final Player p = event.getPlayer();
        final World w = p.getWorld();
        final Biome b = p.getLocation().getBlock().getBiome();
        if (ps.getServiceHandler().skillWorld(p, w) && ps.getServiceHandler().skillBiome(p, b) && ps.getServiceHandler().skillActive(p, "maxhealth")) {
            final Integer Health = ps.getServiceHandler().skillHealth(p, "maxhealth");
            if (Health < 20 && Health > 0 && p.getHealth() > Health) {
                p.setHealth((int) Health);
            }
        }
        ps.getServiceHandler().clearPlayerScheduler(p);
    }
    
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onMobTarget(final EntityTargetEvent event) {
        if (event.getTarget() instanceof Player && event.getEntity() instanceof LivingEntity) {
            final Player p = (Player)event.getTarget();
            if (ps.getServiceHandler().skillWorld(p, p.getWorld()) && ps.getServiceHandler().skillBiome(p, p.getLocation().getBlock().getBiome()) && ps.getServiceHandler().skillActive(p, "mobtarget") && event.getReason().equals((Object) EntityTargetEvent.TargetReason.CLOSEST_PLAYER)) {
                event.setCancelled(true);
            }
        }
    }
    
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPlayerHealth(final EntityRegainHealthEvent event) {
        if (event.getEntity() instanceof Player) {
            final Player p = (Player)event.getEntity();
            final World w = p.getWorld();
            final Biome b = p.getLocation().getBlock().getBiome();
            if (ps.getServiceHandler().skillWorld(p, w) && ps.getServiceHandler().skillBiome(p, b)) {
                if (ps.getServiceHandler().skillActive(p, "maxhealth")) {
                    final Integer Health = ps.getServiceHandler().skillHealth(p, "maxhealth");
                    if (Health < 20 && Health > 0 && p.getHealth() > Health) {
                        p.setHealth((int)Health);
                    }
                }
                if (ps.getServiceHandler().skillActive(p, "recoverhealth")) {
                    event.setCancelled(true);
                }
            }
        }
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onExpChange(final PlayerExpChangeEvent event) {
        final Player p = event.getPlayer();
        double multiplier = 1.0;
        if (ps.getServiceHandler().skillWorld(p, p.getWorld()) && ps.getServiceHandler().skillBiome(p, p.getLocation().getBlock().getBiome()) && ps.getServiceHandler().skillActive(p, "xpmultiplier")) {
            if (ps.getServiceHandler().skillMultiplier(p, "xpmultiplier") == 0.0) {
                event.setAmount(0);
                return;
            }
            multiplier = ps.getServiceHandler().skillMultiplier(p, "xpmultiplier");
            event.setAmount((int) Math.round(event.getAmount() * multiplier));
        }
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerRespawn(final PlayerRespawnEvent event) {
        final Player p = event.getPlayer();
        if (ps.getServiceHandler().skillWorld(p, p.getWorld()) && ps.getServiceHandler().skillBiome(p, p.getLocation().getBlock().getBiome()) && ps.getServiceHandler().skillActive(p, "maxhealth")) {
            final Integer Health = ps.getServiceHandler().skillHealth(p, "maxhealth");
            if (Health < 20 && Health > 0 && p.getHealth() > Health) {
                p.setHealth((int) Health);
            }
        }
        if (ps.getServiceHandler().skillActive(p, "recoveritems") && this.ps.getServiceHandler().Inventory.containsKey(p)) {
            for (final ItemStack i : ps.getServiceHandler().Inventory.get(p)) {
                p.getInventory().addItem(new ItemStack[] { i });
            }
            ps.getServiceHandler().Inventory.remove(p);
        }
        ps.getServiceHandler().clearPlayerScheduler(p);
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerDeath(final PlayerDeathEvent event) {
            final Player p = event.getEntity();
            final World w = p.getWorld();
            final Biome b = p.getLocation().getBlock().getBiome();

            if (ps.getServiceHandler().skillWorld(p, w) && ps.getServiceHandler().skillBiome(p, b)) {
                if (ps.getServiceHandler().skillActive(p, "explodedeath")) {
                    p.getWorld().createExplosion(p.getLocation(), (float) ps.getServiceHandler().skillPower(p, "explodedeath"));
                }
                if (ps.getServiceHandler().skillActive(p, "recoverexp")) {
                    event.setNewExp(p.getTotalExperience());
                    event.setDroppedExp(0);
                }
                if (ps.getServiceHandler().skillActive(p, "recoveritems")) {
                    ps.getServiceHandler().Inventory.put(p, event.getDrops());
                    event.getDrops().clear();
                }
            }
        ps.getServiceHandler().clearPlayerScheduler(p);
    }
    
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPlayerToggleSneak(final PlayerToggleSneakEvent event) {
        final Player p = event.getPlayer();
        final World w = p.getWorld();
        final Biome b = p.getLocation().getBlock().getBiome();
        if (ps.getServiceHandler().skillWorld(p, w) && ps.getServiceHandler().skillBiome(p, b) && ps.getServiceHandler().skillActive(p, "invisible")) {
            if (event.isSneaking()) {
                ps.getServiceHandler().hidePlayer(p);
            }
            else {
                ps.getServiceHandler().showPlayer(p);
            }
        }
    }
    
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPlayerMove(final PlayerMoveEvent event) {
        final Player p = event.getPlayer();
        final World w = p.getWorld();
        final Biome b = p.getLocation().getBlock().getBiome();
        if (ps.getServiceHandler().skillWorld(p, w) && ps.getServiceHandler().skillBiome(p, b)) {
            if (p.isFlying()) {
                ps.getServiceHandler().clearPlayerScheduler(p);
            }
            if (ps.getServiceHandler().skillActive(p, "flight") && p.isSneaking()) {
                p.setVelocity(p.getLocation().getDirection().multiply((int)ps.getServiceHandler().skillPower(p, "flight")));
            }
            if (ps.getServiceHandler().skillActive(p, "highjump")) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 10, (int)ps.getServiceHandler().skillPower(p, "highjump")));
            }
            if (ps.getServiceHandler().skillActive(p, "oxygen") && (p.getLocation().getBlock().getType().equals((Object)Material.STATIONARY_WATER) || p.getLocation().add(0.0, 1.0, 0.0).getBlock().getType().equals((Object)Material.STATIONARY_WATER))) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 10, (int)ps.getServiceHandler().skillPower(p, "oxygen")));
            }
            if (ps.getServiceHandler().skillActive(p, "speed")) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10, (int)ps.getServiceHandler().skillPower(p, "speed")));
            }
            if (ps.getServiceHandler().skillActive(p, "swimmer") && (p.getLocation().getBlock().getType() == Material.STATIONARY_WATER || p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.STATIONARY_WATER)) {
                final Vector dir = p.getLocation().getDirection();
                final Vector vec = new Vector(dir.getX() * 0.4, 0.0, dir.getZ() * 0.4);
                p.setVelocity(vec);
            }
            final Long time = p.getWorld().getTime();
            if (ps.getServiceHandler().skillActive(p, "sunlight")) {
                if (time > 0L && time < 13000L) {
                    if (p.getWorld().getBlockAt(p.getLocation()).getLightFromSky() > 8) {
                        if (!ps.getServiceHandler().TasksSun.containsKey(p)) {
                            ps.getServiceHandler().TasksSun.put(p, new Scheduler(PermissionSkills.getPlugin()).sun(p));
                        }
                    }
                    else if (ps.getServiceHandler().TasksSun.containsKey(p)) {
                        Bukkit.getScheduler().cancelTask((int)ps.getServiceHandler().TasksSun.get(p));
                        ps.getServiceHandler().TasksSun.remove(p);
                    }
                }
                else if (ps.getServiceHandler().TasksSun.containsKey(p)) {
                    Bukkit.getScheduler().cancelTask((int)ps.getServiceHandler().TasksSun.get(p));
                    ps.getServiceHandler().TasksSun.remove(p);
                }
            }
            if (ps.getServiceHandler().skillActive(p, "moonlight")) {
                if (time > 13000L && time < 23000L) {
                    if (p.getWorld().getBlockAt(p.getLocation()).getLightFromSky() > 8) {
                        if (!ps.getServiceHandler().TasksMoon.containsKey(p)) {
                            ps.getServiceHandler().TasksMoon.put(p, new Scheduler(PermissionSkills.getPlugin()).sun(p));
                        }
                    }
                    else if (ps.getServiceHandler().TasksMoon.containsKey(p)) {
                        Bukkit.getScheduler().cancelTask((int)ps.getServiceHandler().TasksMoon.get(p));
                        ps.getServiceHandler().TasksMoon.remove(p);
                    }
                }
                else if (ps.getServiceHandler().TasksMoon.containsKey(p)) {
                    Bukkit.getScheduler().cancelTask((int)ps.getServiceHandler().TasksMoon.get(p));
                    ps.getServiceHandler().TasksMoon.remove(p);
                }
            }
            if (ps.getServiceHandler().skillActive(p, "storm")) {
                if (p.getWorld().hasStorm()) {
                    if (p.getWorld().getHighestBlockAt(p.getLocation().getBlock().getLocation()).getY() <= p.getLocation().getBlock().getLocation().getY()) {
                        if (!ps.getServiceHandler().TasksStorm.containsKey(p)) {
                            ps.getServiceHandler().TasksStorm.put(p, new Scheduler(PermissionSkills.getPlugin()).storm(p));
                        }
                    }
                    else if (ps.getServiceHandler().TasksStorm.containsKey(p)) {
                        Bukkit.getScheduler().cancelTask((int)ps.getServiceHandler().TasksStorm.get(p));
                        ps.getServiceHandler().TasksStorm.remove(p);
                    }
                }
                else if (ps.getServiceHandler().TasksStorm.containsKey(p)) {
                    Bukkit.getScheduler().cancelTask((int)ps.getServiceHandler().TasksStorm.get(p));
                    ps.getServiceHandler().TasksStorm.remove(p);
                }
            }
            if (ps.getServiceHandler().skillActive(p, "waterdamage")) {
                if (p.getLocation().getBlock().getTypeId() == 8 || p.getLocation().getBlock().getTypeId() == 9) {
                    if (!ps.getServiceHandler().TasksWater.containsKey(p)) {
                        ps.getServiceHandler().TasksWater.put(p, new Scheduler(PermissionSkills.getPlugin()).water(p));
                    }
                }
                else if (ps.getServiceHandler().TasksWater.containsKey(p)) {
                    Bukkit.getScheduler().cancelTask((int)ps.getServiceHandler().TasksWater.get(p));
                    ps.getServiceHandler().TasksWater.remove(p);
                }
            }
            if (ps.getServiceHandler().skillActive(p, "waterregen")) {
                if (p.getLocation().getBlock().getTypeId() == 8 || p.getLocation().getBlock().getTypeId() == 9) {
                    if (!ps.getServiceHandler().TasksHeal.containsKey(p)) {
                        ps.getServiceHandler().TasksHeal.put(p, new Scheduler(PermissionSkills.getPlugin()).heal(p));
                    }
                }
                else if (ps.getServiceHandler().TasksHeal.containsKey(p)) {
                    Bukkit.getScheduler().cancelTask((int)ps.getServiceHandler().TasksHeal.get(p));
                    ps.getServiceHandler().TasksHeal.remove(p);
                }
            }
            if (ps.getServiceHandler().skillActive(p, "altitude")) {
                if (p.getLocation().getBlockY() > ps.getServiceHandler().skillMin(p, "altitude") || p.getLocation().getBlockY() < ps.getServiceHandler().skillMax(p, "altitude")) {
                    if (!ps.getServiceHandler().TasksAltitude.containsKey(p)) {
                        ps.getServiceHandler().TasksAltitude.put(p, new Scheduler(PermissionSkills.getPlugin()).altitude(p));
                    }
                }
                else if (ps.getServiceHandler().TasksAltitude.containsKey(p)) {
                    Bukkit.getScheduler().cancelTask((int)ps.getServiceHandler().TasksAltitude.get(p));
                    ps.getServiceHandler().TasksAltitude.remove(p);
                }
            }
            if (ps.getServiceHandler().skillActive(p, "landdamage")) {
                final List<Integer> blocks = new ArrayList<Integer>();
                blocks.add(p.getLocation().getBlock().getTypeId());
                blocks.add(p.getLocation().add(0.0, 1.0, 0.0).getBlock().getTypeId());
                blocks.add(p.getLocation().subtract(0.0, 1.0, 0.0).getBlock().getTypeId());
                if (!blocks.contains(8) && !blocks.contains(9) && !blocks.contains(0)) {
                    if (!ps.getServiceHandler().TasksLand.containsKey(p)) {
                        ps.getServiceHandler().TasksLand.put(p, new Scheduler(PermissionSkills.getPlugin()).land(p));
                    }
                }
                else if (ps.getServiceHandler().TasksLand.containsKey(p)) {
                    Bukkit.getScheduler().cancelTask((int) ps.getServiceHandler().TasksLand.get(p));
                    ps.getServiceHandler().TasksLand.remove(p);
                }
            }
        }
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onProjectileHit(final ProjectileHitEvent event) {
        final Entity entity = (Entity)event.getEntity();
        if (entity instanceof Arrow) {
            final Arrow arrow = (Arrow)entity;
            final Entity shooter = (Entity)arrow.getShooter();
            if (shooter instanceof Player) {
                final Player p = (Player)shooter;
                final World w = p.getWorld();
                final Biome b = p.getLocation().getBlock().getBiome();
                if (ps.getServiceHandler().skillWorld(p, w) && ps.getServiceHandler().skillBiome(p, b)) {
                    final Location loc = arrow.getLocation();
                    if (ps.getServiceHandler().skillActive(p, "explosivearrow")) {
                        w.createExplosion(loc, (float)ps.getServiceHandler().skillPower(p, "explosivearrow"));
                    }
                    if (ps.getServiceHandler().skillActive(p, "zombiearrow")) {
                        w.spawnEntity(loc, EntityType.ZOMBIE);
                    }
                    if (ps.getServiceHandler().skillActive(p, "lightningarrow")) {
                        w.strikeLightning(loc);
                    }
                    if (ps.getServiceHandler().skillActive(p, "tparrow")) {
                        p.teleport(loc);
                    }
                }
            }
        }
    }
    
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onEntityShootBowEvent(final EntityShootBowEvent event) {
        if (event.getEntity() instanceof Player) {
            final Player p = (Player)event.getEntity();
            final World w = p.getWorld();
            final Biome b = p.getLocation().getBlock().getBiome();
            if (ps.getServiceHandler().skillWorld(p, w) && ps.getServiceHandler().skillBiome(p, b)) {
                final Arrow arrow = (Arrow)event.getProjectile();
                if (ps.getServiceHandler().skillActive(p, "firearrow")) {
                    arrow.setFireTicks((int) ps.getServiceHandler().skillTicks(p, "firearrow"));
                }
                if (ps.getServiceHandler().skillActive(p, "straightarrow")) {
                    arrow.setVelocity(arrow.getVelocity().multiply((int) ps.getServiceHandler().skillPower(p, "straightarrow")));
                }
            }
        }
    }
    
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPlayerInteractEntity(final PlayerInteractEntityEvent event) {
        if (event.getRightClicked() instanceof LivingEntity) {
            final Player p = event.getPlayer();
            final World w = p.getWorld();
            final Biome b = p.getLocation().getBlock().getBiome();
            if (ps.getServiceHandler().skillWorld(p, w) && ps.getServiceHandler().skillBiome(p, b) && ps.getServiceHandler().skillActive(p, "knockback")) {
                event.getRightClicked().setVelocity(event.getRightClicked().getVelocity().add(event.getRightClicked().getLocation().toVector().subtract(event.getPlayer().getLocation().toVector()).normalize().multiply((int)ps.getServiceHandler().skillPower(p, "knockback"))));
                event.getRightClicked().getWorld().playEffect(event.getRightClicked().getLocation(), Effect.SMOKE, 25);
            }
        }
    }
    
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onEntityDamage(final EntityDamageEvent event) {
        if (event instanceof EntityDamageByEntityEvent) {
            return;
        }
        if (event.getEntity() instanceof Player) {
            final Player p = (Player)event.getEntity();
            double damage = event.getDamage();
            final World w = p.getWorld();
            final Biome b = p.getLocation().getBlock().getBiome();
            if (ps.getServiceHandler().skillWorld(p, w) && ps.getServiceHandler().skillBiome(p, b)) {
                if (event.getCause().equals((Object)EntityDamageEvent.DamageCause.FALL)) {
                    if (ps.getServiceHandler().skillActive(p, "flight") && p.isSneaking() && p.isFlying()) {
                        event.setCancelled(true);
                        return;
                    }
                    if (ps.getServiceHandler().skillActive(p, "highjump") && !p.isSneaking() && p.hasPotionEffect(PotionEffectType.JUMP)) {
                        event.setCancelled(true);
                        return;
                    }
                }
                damage = this.traitDamage(damage, event.getCause(), p);
            }
            damage = Math.ceil(damage);
            event.setDamage((int)damage);
        }
    }
    
    private double traitDamage(double d, final EntityDamageEvent.DamageCause dc, final Player p) {
        double value = 0.0;
        if (ps.getServiceHandler().skillActive(p, "defense" + dc.name().toLowerCase())) {
            value = ps.getServiceHandler().skillMultiplier(p, "defense" + dc.name().toLowerCase());
        }
        if (value == 0.0) {
            d = 0.0;
        }
        else if (value == 100.0) {
            d *= 1.0;
            d = Math.ceil(d);
        }
        else {
            d *= value / 100.0;
            d = Math.ceil(d);
        }
        return d;
    }
    
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onEntityDamageByEntity(final EntityDamageByEntityEvent event) {
        double damage = event.getDamage();
        if (event.getDamager() instanceof Arrow) {
            final Arrow arrow = (Arrow)event.getDamager();
            if (arrow.getShooter() instanceof Player) {
                final Player damager = (Player)arrow.getShooter();
                if (ps.getServiceHandler().skillWorld(damager, damager.getWorld()) && ps.getServiceHandler().skillBiome(damager, damager.getLocation().getBlock().getBiome())) {
                    if (ps.getServiceHandler().skillActive(damager, "invisible") && damager.isSneaking()) {
                        event.setCancelled(true);
                        return;
                    }
                    if (ps.getServiceHandler().skillActive(damager, "peacekeeper") && event.getEntity() instanceof Player) {
                        event.setCancelled(true);
                        return;
                    }
                    if (ps.getServiceHandler().skillActive(damager, "attackprojectile")) {
                        damage *= ps.getServiceHandler().skillMultiplier(damager, "attackprojectile");
                    }
                    if (event.getEntity() instanceof LivingEntity) {
                        if (ps.getServiceHandler().skillActive(damager, "poisonarrow")) {
                            ((LivingEntity)event.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.POISON, (int)ps.getServiceHandler().skillTicks(damager, "poisonarrow"), (int)ps.getServiceHandler().skillPower(damager, "poisonarrow")));
                        }
                        if (ps.getServiceHandler().skillActive(damager, "blindnessarrow")) {
                            ((LivingEntity)event.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, (int)ps.getServiceHandler().skillTicks(damager, "blindnessarrow"), (int)ps.getServiceHandler().skillPower(damager, "blindnessarrow")));
                        }
                        if (ps.getServiceHandler().skillActive(damager, "confusionarrow")) {
                            ((LivingEntity)event.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, (int)ps.getServiceHandler().skillTicks(damager, "confusionarrow"), (int)ps.getServiceHandler().skillPower(damager, "confusionarrow")));
                        }
                        if (ps.getServiceHandler().skillActive(damager, "firearrow")) {
                            ((LivingEntity)event.getEntity()).setFireTicks((int)ps.getServiceHandler().skillTicks(damager, "firearrow"));
                        }
                        if (ps.getServiceHandler().skillActive(damager, "mobarrow")) {
                            for (final Entity e : event.getEntity().getNearbyEntities(20.0, 20.0, 20.0)) {
                                if (e instanceof Creature) {
                                    ((Creature)e).setTarget((LivingEntity)event.getEntity());
                                }
                            }
                        }
                    }
                }
            }
            if (event.getEntity() instanceof Player) {
                final Player defender = (Player)event.getEntity();
                if (ps.getServiceHandler().skillWorld(defender, defender.getWorld()) && ps.getServiceHandler().skillBiome(defender, defender.getLocation().getBlock().getBiome())) {
                    damage = this.traitDamage(damage, event.getCause(), defender);
                    if (ps.getServiceHandler().skillActive(defender, "peacekeeper") && event.getDamager() instanceof Player) {
                        event.setCancelled(true);
                        return;
                    }
                }
            }
        }
        if (event.getDamager() instanceof Player) {
            final Player damager2 = (Player)event.getDamager();
            if (ps.getServiceHandler().skillWorld(damager2, damager2.getWorld()) && ps.getServiceHandler().skillBiome(damager2, damager2.getLocation().getBlock().getBiome())) {
                if (ps.getServiceHandler().skillActive(damager2, "invisible") && damager2.isSneaking()) {
                    event.setCancelled(true);
                    return;
                }
                if (ps.getServiceHandler().skillActive(damager2, "attackmelee")) {
                    damage *= ps.getServiceHandler().skillMultiplier(damager2, "attackmelee");
                }
                if (ps.getServiceHandler().skillActive(damager2, "peacekeeper") && event.getEntity() instanceof Player) {
                    event.setCancelled(true);
                    return;
                }
                if (ps.getServiceHandler().skillActive(damager2, "fireblade") && ps.getServiceHandler().isBlade(damager2.getItemInHand())) {
                    ((LivingEntity)event.getEntity()).setFireTicks((int) ps.getServiceHandler().skillTicks(damager2, "fireblade"));
                }
                if (ps.getServiceHandler().skillActive(damager2, "firepunch") && damager2.getItemInHand().getType().equals((Object)Material.AIR)) {
                    ((LivingEntity)event.getEntity()).setFireTicks((int) ps.getServiceHandler().skillTicks(damager2, "firepunch"));
                }
                if (ps.getServiceHandler().skillActive(damager2, "poisonblade") && ps.getServiceHandler().isBlade(damager2.getItemInHand())) {
                    ((LivingEntity)event.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.POISON, (int)ps.getServiceHandler().skillTicks(damager2, "poisonblade"), (int)ps.getServiceHandler().skillPower(damager2, "poisonblade")));
                }
            }
        }
        if (event.getEntity() instanceof Player) {
            final Player defender2 = (Player)event.getEntity();
            if (ps.getServiceHandler().skillWorld(defender2, defender2.getWorld()) && ps.getServiceHandler().skillBiome(defender2, defender2.getLocation().getBlock().getBiome())) {
                if (ps.getServiceHandler().skillActive(defender2, "attackmelee")) {
                    damage *= ps.getServiceHandler().skillMultiplier(defender2, "attackmelee");
                }
                if (ps.getServiceHandler().skillActive(defender2, "reflect") && event.getDamager() instanceof LivingEntity) {
                    ((LivingEntity)event.getDamager()).damage((int)ps.getServiceHandler().skillDamage(defender2, "reflect"));
                }
                if (ps.getServiceHandler().skillActive(defender2, "peacekeeper") && event.getDamager() instanceof Player) {
                    event.setCancelled(true);
                    return;
                }
                damage = this.traitDamage(damage, event.getCause(), defender2);
            }
        }
        damage = Math.ceil(damage);
        event.setDamage((int)damage);
    }
}

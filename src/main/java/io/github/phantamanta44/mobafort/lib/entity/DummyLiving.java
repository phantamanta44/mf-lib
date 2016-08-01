package io.github.phantamanta44.mobafort.lib.entity;

import io.github.phantamanta44.mobafort.lib.math.MathUtils;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.*;

public class DummyLiving implements LivingEntity {

	private final String name;
	private final UUID id;
	private final int hpMax;
	private int hp;

	public DummyLiving(String name, int hpMax) {
		this(name, hpMax, 0);
	}

	public DummyLiving(String name, int hpMax, int hp) {
		this.name = name;
		this.hpMax = hpMax;
		this.hp = hp;
		this.id = UUID.randomUUID();
	}

	@Override
	public UUID getUniqueId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void damage(double dmg) {
		hp = MathUtils.clamp(hp - (int)dmg, 0, hpMax);
	}

	@Override
	public void damage(double dmg, Entity src) {
		damage(dmg);
	}

	@Override
	public double getHealth() {
		return hp;
	}

	@Override
	public void setHealth(double hp) {
		this.hp = MathUtils.clamp((int)hp, 0, hpMax);
	}

	@Override
	public double getMaxHealth() {
		return hpMax;
	}

	@Override
	public double getEyeHeight() {
		throw new UnsupportedOperationException();
	}

	@Override
	public double getEyeHeight(boolean b) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Location getEyeLocation() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Block> getLineOfSight(HashSet<Byte> hashSet, int i) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Block> getLineOfSight(Set<Material> set, int i) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Block getTargetBlock(HashSet<Byte> hashSet, int i) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Block getTargetBlock(Set<Material> set, int i) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Block> getLastTwoTargetBlocks(HashSet<Byte> hashSet, int i) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Block> getLastTwoTargetBlocks(Set<Material> set, int i) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getRemainingAir() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setRemainingAir(int i) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getMaximumAir() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setMaximumAir(int i) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getMaximumNoDamageTicks() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setMaximumNoDamageTicks(int i) {
		throw new UnsupportedOperationException();
	}

	@Override
	public double getLastDamage() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int _INVALID_getLastDamage() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setLastDamage(double v) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void _INVALID_setLastDamage(int i) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getNoDamageTicks() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setNoDamageTicks(int i) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Player getKiller() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addPotionEffect(PotionEffect potionEffect) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addPotionEffect(PotionEffect potionEffect, boolean b) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addPotionEffects(Collection<PotionEffect> collection) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean hasPotionEffect(PotionEffectType potionEffectType) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void removePotionEffect(PotionEffectType potionEffectType) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<PotionEffect> getActivePotionEffects() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean hasLineOfSight(Entity entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean getRemoveWhenFarAway() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setRemoveWhenFarAway(boolean b) {
		throw new UnsupportedOperationException();
	}

	@Override
	public EntityEquipment getEquipment() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setCanPickupItems(boolean b) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean getCanPickupItems() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isLeashed() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Entity getLeashHolder() throws IllegalStateException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean setLeashHolder(Entity entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isGliding() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setGliding(boolean b) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setAI(boolean b) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean hasAI() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setCollidable(boolean b) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isCollidable() {
		throw new UnsupportedOperationException();
	}

	@Override
	public AttributeInstance getAttribute(Attribute attribute) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void _INVALID_damage(int i) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void _INVALID_damage(int i, Entity entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int _INVALID_getHealth() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void _INVALID_setHealth(int i) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int _INVALID_getMaxHealth() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setMaxHealth(double v) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void _INVALID_setMaxHealth(int i) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void resetMaxHealth() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Location getLocation() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Location getLocation(Location location) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setVelocity(Vector vector) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Vector getVelocity() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isOnGround() {
		throw new UnsupportedOperationException();
	}

	@Override
	public World getWorld() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean teleport(Location location) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean teleport(Location location, PlayerTeleportEvent.TeleportCause teleportCause) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean teleport(Entity entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean teleport(Entity entity, PlayerTeleportEvent.TeleportCause teleportCause) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Entity> getNearbyEntities(double v, double v1, double v2) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getEntityId() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getFireTicks() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getMaxFireTicks() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setFireTicks(int i) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isDead() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isValid() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void sendMessage(String s) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void sendMessage(String[] strings) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Server getServer() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Entity getPassenger() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean setPassenger(Entity entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isEmpty() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean eject() {
		throw new UnsupportedOperationException();
	}

	@Override
	public float getFallDistance() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setFallDistance(float v) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setLastDamageCause(EntityDamageEvent entityDamageEvent) {
		throw new UnsupportedOperationException();
	}

	@Override
	public EntityDamageEvent getLastDamageCause() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getTicksLived() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setTicksLived(int i) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void playEffect(EntityEffect entityEffect) {
		throw new UnsupportedOperationException();
	}

	@Override
	public EntityType getType() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isInsideVehicle() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean leaveVehicle() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Entity getVehicle() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setCustomName(String s) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getCustomName() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setCustomNameVisible(boolean b) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isCustomNameVisible() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setGlowing(boolean b) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isGlowing() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setInvulnerable(boolean b) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isInvulnerable() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isSilent() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setSilent(boolean b) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean hasGravity() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setGravity(boolean b) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Spigot spigot() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setMetadata(String s, MetadataValue metadataValue) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<MetadataValue> getMetadata(String s) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean hasMetadata(String s) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void removeMetadata(String s, Plugin plugin) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isPermissionSet(String s) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isPermissionSet(Permission permission) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean hasPermission(String s) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean hasPermission(Permission permission) {
		throw new UnsupportedOperationException();
	}

	@Override
	public PermissionAttachment addAttachment(Plugin plugin, String s, boolean b) {
		throw new UnsupportedOperationException();
	}

	@Override
	public PermissionAttachment addAttachment(Plugin plugin) {
		throw new UnsupportedOperationException();
	}

	@Override
	public PermissionAttachment addAttachment(Plugin plugin, String s, boolean b, int i) {
		throw new UnsupportedOperationException();
	}

	@Override
	public PermissionAttachment addAttachment(Plugin plugin, int i) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void removeAttachment(PermissionAttachment permissionAttachment) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void recalculatePermissions() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<PermissionAttachmentInfo> getEffectivePermissions() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isOp() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setOp(boolean b) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T extends Projectile> T launchProjectile(Class<? extends T> aClass) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T extends Projectile> T launchProjectile(Class<? extends T> aClass, Vector vector) {
		throw new UnsupportedOperationException();
	}

}

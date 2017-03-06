package io.github.phantamanta44.mobafort.lib.effect;

import com.comphenix.packetwrapper.WrapperPlayServerWorldParticles;
import com.comphenix.protocol.wrappers.EnumWrappers;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

public class ParticleUtils {

    public static void dispatchEffect(World world, float x, float y, float z, EnumWrappers.Particle type, int count, float spread, float data) {
        WrapperPlayServerWorldParticles pkt = new WrapperPlayServerWorldParticles();
        pkt.setX(x);
        pkt.setY(y);
        pkt.setZ(z);
        pkt.setParticleType(type);
        pkt.setNumberOfParticles(count);
        pkt.setOffsetX(spread);
        pkt.setOffsetY(spread);
        pkt.setOffsetZ(spread);
        pkt.setParticleData(data);
        world.getPlayers().forEach(pkt::sendPacket);
    }

     public static void dispatchEffect(World world, float x, float y, float z, EnumWrappers.Particle type, int count, float spread) {
        dispatchEffect(world, x, y, z, type, count, spread, 0F);
    }

    public static void dispatchEffect(World world, Vector vec, EnumWrappers.Particle type, int count, float spread, float data) {
        dispatchEffect(world, (float)vec.getX(), (float)vec.getY(), (float)vec.getZ(), type, count, spread, data);
    }

    public static void dispatchEffect(World world, Vector vec, EnumWrappers.Particle type, int count, float spread) {
        dispatchEffect(world, (float)vec.getX(), (float)vec.getY(), (float)vec.getZ(), type, count, spread);
    }

    public static void dispatchEffect(Location loc, EnumWrappers.Particle type, int count, float spread, float data) {
        dispatchEffect(loc.getWorld(), (float)loc.getX(), (float)loc.getY(), (float)loc.getZ(), type, count, spread, data);
    }

    public static void dispatchEffect(Location loc, EnumWrappers.Particle type, int count, float spread) {
        dispatchEffect(loc.getWorld(), (float)loc.getX(), (float)loc.getY(), (float)loc.getZ(), type, count, spread);
    }

    public static void dispatchEffect(Location loc, EnumWrappers.Particle type, int count) {
        dispatchEffect(loc, type, count, 0.5F);
    }

    public static void colourEffect(Location loc, EnumWrappers.Particle type, float r, float g, float b, float intensity) {
        WrapperPlayServerWorldParticles pkt = new WrapperPlayServerWorldParticles();
        pkt.setX((float)loc.getX());
        pkt.setY((float)loc.getY());
        pkt.setZ((float)loc.getZ());
        pkt.setParticleType(type);
        pkt.setNumberOfParticles(0);
        pkt.setOffsetX(r);
        pkt.setOffsetY(g);
        pkt.setOffsetZ(b);
        pkt.setParticleData(intensity);
        loc.getWorld().getPlayers().forEach(pkt::sendPacket);
    }

}

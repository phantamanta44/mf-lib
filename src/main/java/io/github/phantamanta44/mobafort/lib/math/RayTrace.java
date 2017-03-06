package io.github.phantamanta44.mobafort.lib.math;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

import java.util.Iterator;

public class RayTrace implements Iterable<Location> {

    private World world;
    private Vector origin, diff;
    private int parts;

    public RayTrace(World world, Vector origin, Vector dir, double len, int segs) {
        this.world = world;
        this.origin = origin;
        this.diff = dir.clone().multiply(len / (double)segs);
        this.parts = segs;
    }

    public RayTrace(Location origin, Vector dir, double len, int segs) {
        this(origin.getWorld(), origin.toVector(), dir, len, segs);
    }

    public RayTrace(Location loc, double len, int segs) {
        this(loc, loc.getDirection(), len, segs);
    }

    public RayTrace(LivingEntity ent, double len, int segs) {
        this(ent.getLocation(), len, segs);
    }

    @Override
    public Iterator<Location> iterator() {
        return new RayTraceIterator();
    }

    private class RayTraceIterator implements Iterator<Location> {

        private int i = 0;

        @Override
        public boolean hasNext() {
            return i < parts;
        }

        @Override
        public Location next() {
            Vector pos = origin.clone().add(diff.clone().multiply(i++));
            return new Location(world, pos.getX(), pos.getY(), pos.getZ());
        }

    }

}

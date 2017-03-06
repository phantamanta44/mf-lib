package io.github.phantamanta44.mobafort.lib.math;

import org.bukkit.util.Vector;

public class CylBounds {

    private Vector basePos;
    private float radius, height;

    public CylBounds(Vector base, float radius, float height) {
        this(base.getX(), base.getY(), base.getZ(), radius, height);
    }

    public CylBounds(double x, double y, double z, float radius, float height) {
        this.basePos = new Vector(x, y, z);
        this.radius = radius;
        this.height = height;
    }

    public Vector getBasePos() {
        return basePos.clone();
    }

    public float getRadius() {
        return radius;
    }

    public float getHeight() {
        return height;
    }

    public boolean intersects(CylBounds bounds) {
        return MathUtils.withinBounds(
                basePos.getY(), basePos.getY() + height, bounds.basePos.getY(), bounds.basePos.getY() + bounds.height)
                && MathUtils.horDist(basePos, bounds.basePos) <= radius + bounds.radius;
    }

    public boolean intersects(Vector point) {
        return (point.getY() - basePos.getY() <= height) && (MathUtils.horDist(point, basePos) <= radius);
    }

}

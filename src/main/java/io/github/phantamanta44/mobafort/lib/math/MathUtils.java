package io.github.phantamanta44.mobafort.lib.math;

import org.bukkit.util.Vector;

@SuppressWarnings("unchecked")
public class MathUtils {
	
	public static int clamp(int value, int min, int max) {
		return Math.max(Math.min(value, max), min);
	}

	public static float clamp(float value, float min, float max) {
		return Math.max(Math.min(value, max), min);
	}

	public static double clamp(double value, double min, double max) {
		return Math.max(Math.min(value, max), min);
	}

	public static <T extends Number> T box(double val, Class<T> wrapping) {
		if (Integer.class.isAssignableFrom(wrapping))
			return (T)Integer.valueOf((int)val);
		else if (Double.class.isAssignableFrom(wrapping))
			return (T)Double.valueOf(val);
		else if (Float.class.isAssignableFrom(wrapping))
			return (T)Float.valueOf((float)val);
		return null;
	}

	public static <T extends Number> T box(float val, Class<T> wrapping) {
		if (Integer.class.isAssignableFrom(wrapping))
			return (T)Integer.valueOf((int)val);
		else if (Double.class.isAssignableFrom(wrapping))
			return (T)Double.valueOf((double)val);
		else if (Float.class.isAssignableFrom(wrapping))
			return (T)Float.valueOf(val);
		return null;
	}

	public static <T extends Number> T box(int val, Class<T> wrapping) {
		if (Integer.class.isAssignableFrom(wrapping))
			return (T)Integer.valueOf(val);
		else if (Double.class.isAssignableFrom(wrapping))
			return (T)Double.valueOf((double)val);
		else if (Float.class.isAssignableFrom(wrapping))
			return (T)Float.valueOf((float)val);
		return null;
	}

	public static double horDist(Vector a, Vector b) {
		return Math.hypot(a.getX() - b.getX(), a.getZ() - b.getZ());
	}

	public static boolean withinBounds(double lowA, double highA, double lowB, double highB) {
		return withinBounds(lowB, lowA, highA) || withinBounds(highB, lowA, highA)
				|| withinBounds(lowA, lowB, highB) || withinBounds(highA, lowB, highB);
	}

	public static boolean withinBounds(double n, double lower, double upper) {
		return n >= lower && n <= upper;
	}

	public static boolean withinBounds(float n, float lower, float upper) {
		return n >= lower && n <= upper;
	}

	public static boolean withinBounds(int n, int lower, int upper) {
		return n >= lower && n <= upper;
	}

}

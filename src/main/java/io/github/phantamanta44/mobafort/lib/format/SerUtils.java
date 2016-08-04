package io.github.phantamanta44.mobafort.lib.format;

import com.google.gson.JsonObject;
import org.bukkit.util.Vector;

public class SerUtils {

	public static JsonObject serVector(Vector vec) {
		JsonObject ser = new JsonObject();
		ser.addProperty("x", vec.getX());
		ser.addProperty("y", vec.getY());
		ser.addProperty("z", vec.getZ());
		return ser;
	}

	public static Vector deserVector(JsonObject ser) {
		return new Vector(
			ser.get("x").getAsDouble(),
			ser.get("y").getAsDouble(),
			ser.get("z").getAsDouble()
		);
	}

}

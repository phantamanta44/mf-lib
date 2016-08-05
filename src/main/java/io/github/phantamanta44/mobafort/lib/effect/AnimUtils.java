package io.github.phantamanta44.mobafort.lib.effect;

import com.comphenix.packetwrapper.WrapperPlayServerAnimation;
import org.bukkit.entity.Player;

public class AnimUtils {

	private static final int SWING_ARM = 0;
	private static final int DAMAGE = 1;
	private static final int LEAVE_BED = 2;
	private static final int EAT_FOOD = 3;
	private static final int CRIT = 4;
	private static final int MAGIC_CRIT = 5;
	private static final int SOMETHING = 102;
	private static final int SNEAK_START = 104;
	private static final int SNEAK_STOP = 105;

	public static void swingArm(Player player) {
		WrapperPlayServerAnimation pkt = new WrapperPlayServerAnimation();
		pkt.setEntityID(player.getEntityId());
		pkt.setAnimation(SWING_ARM);
		player.getWorld().getPlayers().forEach(pkt::sendPacket);
	}

}

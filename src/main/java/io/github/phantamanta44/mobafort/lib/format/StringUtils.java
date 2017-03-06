package io.github.phantamanta44.mobafort.lib.format;

import org.bukkit.ChatColor;

public class StringUtils {

	public static String concat(String[] parts) {
		return concat(parts, " ");
	}

	public static String concat(String[] parts, String sep) {
		return concat(parts, sep, 0);
	}

	public static String concat(String[] parts, String sep, int offset) {
		StringBuilder sb = new StringBuilder();
		for (int i = offset; i < parts.length - 1; i++)
			sb.append(parts[i]).append(sep);
		return sb.append(parts[parts.length - 1]).toString();
	}

	public static String displayFmt(String str) {
		return capitalize(str.toLowerCase());
	}

	public static String capitalize(String str) {
		return str.substring(0, 1).toUpperCase().concat(str.substring(1, str.length()));
	}

	public static String genResourceBar(int amt, int max, int length) {
		String bar = "|";
		for (int i = 0; i < length; i++)
			bar += "|";
		int ind = (int)Math.floor(((float)amt / (float)max) * (float)bar.length());
		bar = bar.substring(0, ind) + ChatColor.DARK_GRAY + bar.substring(ind, bar.length());
		return String.format("%s[%s%s%s]", ChatColor.GRAY, ChatColor.BLUE, bar, ChatColor.GRAY);
	}

	public static String genTimeBar(int amt, int max) {
		return String.format("%.1fs %s", (float)amt / 20F, StringUtils.genResourceBar(max - amt, max, 60));
	}

	public static int nthOccurence(String str, char c, int n) {
		if (n < 1)
			return -1;
		int seen = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == c) {
				if (++seen == n)
					return i;
			}
		}
		return -1;
	}

}

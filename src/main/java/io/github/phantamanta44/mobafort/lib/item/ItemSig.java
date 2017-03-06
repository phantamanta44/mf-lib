package io.github.phantamanta44.mobafort.lib.item;

import com.google.gson.JsonObject;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemSig {

    public final Material material;
    public final int meta;

    public ItemSig(Material material) {
        this(material, -1);
    }

    public ItemSig(Material material, int meta) {
        this.material = material;
        this.meta = meta;
    }

    @SuppressWarnings("deprecation")
    public ItemSig(ItemStack stack) {
        this(stack.getType(), stack.getData().getData());
    }

    public ItemStack construct(int count) {
        return new ItemStack(material, count, (short)(meta != -1 ? meta : 0));
    }

    @SuppressWarnings("deprecation")
    public boolean matches(ItemStack stack) {
        return stack.getType() == material && (meta == -1 || stack.getData().getData() == meta);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ItemSig && equals((ItemSig)o);
    }

    public boolean equals(ItemSig o) {
        return this.material == o.material && this.meta == o.meta;
    }

    public JsonObject serialize() {
        JsonObject ser = new JsonObject();
        ser.addProperty("material", material.toString());
        ser.addProperty("meta", meta);
        return ser;
    }

    public static ItemSig deserialize(JsonObject ser) {
        return new ItemSig(Material.valueOf(ser.get("material").getAsString()), ser.get("meta").getAsInt());
    }

}

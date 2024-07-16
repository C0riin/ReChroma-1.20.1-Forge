package net.coriin.rechroma.item.custom;

import net.minecraft.world.item.Item;

public class InfoFragmentItem extends Item {

    public String articleName;

    public InfoFragmentItem(Properties pProperties, String articleName) {
        super(pProperties);
        this.articleName = articleName;
    }
}

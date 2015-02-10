package com.raizunne.redstonic.Item.Drill;

import com.raizunne.redstonic.Redstonic;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * Created by Raizunne as a part of Redstonic
 * on 03/02/2015, 09:52 PM.
 */
public class DrillHead extends Item {

    int material;
    IIcon[] heads;

    public DrillHead(int material){
        this.material = material;
        setCreativeTab(Redstonic.redTab);
        setMaxStackSize(1);
        setUnlocalizedName(getUnlocalizedName());
    }

    @Override
    public void onUpdate(ItemStack stack, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
        if(stack.stackTagCompound==null){
            stack.stackTagCompound = new NBTTagCompound();
            stack.stackTagCompound.setInteger("head", material-1);
        }
    }

    @Override
    public String getUnlocalizedName() {
        switch(material){
            case 0: return "UnknownDrillHead";
            case 1: return "IronDrillHead";
            case 2: return "GoldDrillHead";
            case 3: return "DiamondDrillHead";
            case 4: return "HeavyDrillHead";
            case 5: return "FortuitousDrillHead";
            case 6: return "SilkyDrillHead";
            case 7: return "BlazerDrillHead";
            default: return "UnknownDrillHead";
        }
    }

    @Override
    public void registerIcons(IIconRegister i) {
        heads = new IIcon[8];
        heads[0] = i.registerIcon("redstonic:Drill/Heads/Icon/Unknown");
        heads[1] = i.registerIcon("redstonic:Drill/Heads/Icon/Iron");
        heads[2] = i.registerIcon("redstonic:Drill/Heads/Icon/Gold");
        heads[3] = i.registerIcon("redstonic:Drill/Heads/Icon/Diamond");
        heads[4] = i.registerIcon("redstonic:Drill/Heads/Icon/Heavy");
        heads[5] = i.registerIcon("redstonic:Drill/Heads/Icon/Fortuitous");
        heads[6] = i.registerIcon("redstonic:Drill/Heads/Icon/Silky");
        heads[7] = i.registerIcon("redstonic:Drill/Heads/Icon/Blazer");
    }

    @Override
    public IIcon getIcon(ItemStack stack, int pass) {
        return heads[material];
    }

    @Override
    public IIcon getIconIndex(ItemStack p_77650_1_) {
        return heads[material];
    }
}

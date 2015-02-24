package com.raizunne.redstonic.Item.Drill;

import com.raizunne.redstonic.Redstonic;
import com.raizunne.redstonic.Util.Util;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import org.lwjgl.input.Keyboard;

import java.util.List;

/**
 * Created by Raizunne as a part of Redstonic
 * on 03/02/2015, 09:52 PM.
 */
public class DrillBody extends Item{

    int material;
    IIcon[] body;

    public DrillBody(int material){
        this.material = material;
        setCreativeTab(Redstonic.redTab);
        setMaxStackSize(1);
        setUnlocalizedName(getUnlocalizedName());
    }

    @Override
    public String getUnlocalizedName() {
        switch(material){
            case 0: return "UnknownDrillBody";
            case 1: return "IronDrillBody";
            case 2: return "ElectrumDrillBody";
            case 3: return "EnderiumDrillBody";
            case 4: return "UltimateDrillBody";
            default: return "UnknownDrillBody";
        }
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer p_77624_2_, List list, boolean p_77624_4_) {
        if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
            String[] info = Util.getBodyInfo(material);
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    list.add(info[i]);
                }
            }
        }else{
            list.add(Util.ItemShiftInfo);
        }

    }

    @Override
    public void registerIcons(IIconRegister i) {
        body = new IIcon[7];
        body[0] = i.registerIcon("redstonic:Drill/Bodies/Icon/Unknown");
        body[1] = i.registerIcon("redstonic:Drill/Bodies/Icon/Iron");
        body[2] = i.registerIcon("redstonic:Drill/Bodies/Icon/Electrum");
        body[3] = i.registerIcon("redstonic:Drill/Bodies/Icon/Enderium");
        body[4] = i.registerIcon("redstonic:Drill/Bodies/Icon/End");

    }

    @Override
    public IIcon getIcon(ItemStack stack, int pass) {
        return body[material];
    }

    @Override
    public IIcon getIconIndex(ItemStack p_77650_1_) {
        return body[material];
    }
}

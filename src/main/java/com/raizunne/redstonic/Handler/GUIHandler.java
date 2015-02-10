package com.raizunne.redstonic.Handler;

import com.raizunne.redstonic.Gui.Container.ContainerDrillModifier;
import com.raizunne.redstonic.Gui.GuiDrillModifier;
import com.raizunne.redstonic.Gui.GuiManual;
import com.raizunne.redstonic.Redstonic;
import com.raizunne.redstonic.TileEntity.TEDrillModifier;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Raizunne as a part of Redstonic
 * on 05/02/2015, 07:15 PM.
 */
public class GUIHandler implements IGuiHandler{

    public GUIHandler(){
        NetworkRegistry.INSTANCE.registerGuiHandler(Redstonic.instance, this);
    }

    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);
        switch (ID) {
            case 0: if(te!=null && te instanceof TEDrillModifier){
                return new ContainerDrillModifier(player.inventory, (TEDrillModifier)te);
            }
            break;
            case 1: return null;
            default: return null;

        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);
        switch (ID) {
            case 0: if(te!=null && te instanceof TEDrillModifier){
                return new GuiDrillModifier(player.inventory, (TEDrillModifier)te);
            }
            break;
            case 1: return new GuiManual(player, "index");
            case 2: return new GuiManual(player, "Getting Started");
        }
        return null;
    }
}

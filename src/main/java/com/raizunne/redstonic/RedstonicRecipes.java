package com.raizunne.redstonic;

import com.raizunne.redstonic.Item.IRecipes.ContainerSet;
import com.raizunne.redstonic.Item.IRecipes.EnergeticBattery;
import com.raizunne.redstonic.Item.IRecipes.HotswapSet;
import com.raizunne.redstonic.Util.EIOHelper;
import com.raizunne.redstonic.Util.TEHelper;
import com.raizunne.redstonic.Util.Util;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * Created by Raizunne as a part of Redstonic
 * on 06/02/2015, 09:56 PM.
 */
public class RedstonicRecipes {

    public static void init(){
        GameRegistry.addShapelessRecipe(new ItemStack(RedstonicItems.ManualBook), new Object[]{Items.book, Items.stone_pickaxe});

        //MOD RECIPES
        GameRegistry.addRecipe(new ItemStack(RedstonicItems.IronHead), new Object[]{
                " I ",
                "IBI",
                "IBI", 'I', Items.iron_ingot, 'B', Blocks.iron_block});

        GameRegistry.addRecipe(new ItemStack(RedstonicItems.GoldHead), new Object[]{
                " I ",
                "IBI",
                "IBI", 'I', Items.gold_ingot, 'B', Blocks.gold_block});

        GameRegistry.addRecipe(new ItemStack(RedstonicItems.DiamondHead), new Object[]{
                " I ",
                "III",
                "IBI", 'I', Items.diamond, 'B', Blocks.diamond_block});

        GameRegistry.addRecipe(new ItemStack(RedstonicItems.HeavyHead), new Object[]{
                " I ",
                "IDI",
                "GBH", 'D', RedstonicItems.DiamondHead, 'G', RedstonicItems.GoldHead, 'H', RedstonicItems.IronHead, 'I', Items.iron_ingot, 'B', Blocks.iron_block});

        GameRegistry.addRecipe(new ItemStack(RedstonicItems.FortuitousHead), new Object[]{
                " I ",
                "IDI",
                "GBH", 'D', RedstonicItems.DiamondHead, 'G', RedstonicItems.GoldHead, 'H', RedstonicItems.IronHead, 'I', Items.iron_ingot, 'B', Blocks.lapis_block});

        GameRegistry.addRecipe(new ItemStack(RedstonicItems.SpeedAugment), new Object[]{
                "III",
                "IBI",
                "III", 'I', Items.iron_ingot, 'B', RedstonicItems.GoldHead});

        GameRegistry.addRecipe(new ItemStack(RedstonicItems.SpeedIIAugment), new Object[]{
                "I I",
                "BAB",
                "I I", 'I', Items.iron_ingot, 'A', RedstonicItems.SpeedAugment, 'B', Blocks.iron_block, 'D', RedstonicItems.GoldHead});

        GameRegistry.addRecipe(new ItemStack(RedstonicItems.HotswapAugment), new Object[]{
                "III",
                "DCD",
                "III", 'C', Blocks.chest, 'D', RedstonicItems.IronHead, 'I', Items.iron_ingot});

        GameRegistry.addRecipe(new ItemStack(RedstonicItems.SilkyHead), new Object[]{
                " E ",
                "EGE",
                "GDG", 'E', Items.emerald, 'G', Items.gold_ingot, 'D', RedstonicItems.DiamondHead});

        GameRegistry.addRecipe(new ItemStack(RedstonicItems.BlazerHead), new Object[]{
                " C ",
                "CGC",
                "EDE", 'C', Blocks.coal_block, 'E', Items.emerald, 'G', RedstonicItems.DiamondHead, 'D', RedstonicItems.GoldHead});

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonicBlocks.Modifier),
                "WIW",
                "IGI",
                "WIW", 'W', Blocks.log, 'I', Items.iron_ingot, 'G', "gearIron"));

        GameRegistry.addRecipe(new ItemStack(RedstonicItems.BlockAugment), new Object[]{
                "I I",
                " B ",
                "I I", 'I', Items.iron_ingot, 'B', Blocks.stonebrick});

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonicItems.capacitor),
                " R ",
                "RCR",
                "GRG", 'R', Items.redstone, 'C', "ingotCopper", 'G', Items.gold_nugget));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonicItems.basicBattery),
                " R ",
                "SCS",
                "RAR", 'R', Blocks.redstone_block, 'S', "ingotInvar", 'C', "ingotCopper", 'A', RedstonicItems.capacitor));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonicItems.infiniteBattery),
                "III", "III", "III", 'I', RedstonicItems.infiniteBattery));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonicBlocks.Driller),
                "CEC",
                "IBI",
                "CCC", 'C', Blocks.cobblestone, 'E', RedstonicItems.EnergizerFull, 'I', Blocks.iron_block, 'B', "redstonicMidTierBody"));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonicItems.MagnetAugment),
                "III",
                " LB",
                "III", 'I', Items.iron_ingot, 'L', "ingotLumium", 'B', RedstonicItems.basicBattery));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonicItems.IronHandle),
                " I ",
                "ISI",
                "BI ", 'I', Items.iron_ingot, 'S', RedstonicItems.redstoneStick, 'B', Blocks.iron_block));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonicItems.WoodHandle),
                " I ",
                "ISI",
                "II ", 'I', Blocks.log, 'S', Items.stick));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonicItems.IronBlade),
                "  I",
                " I ",
                "B  ", 'I', Items.iron_ingot, 'B', Blocks.iron_block));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonicItems.DiamondBlade),
                "  I",
                " I ",
                "B  ", 'I', Items.diamond, 'B', Blocks.diamond_block));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonicItems.BerserkSwordAugment),
                "III",
                "KBK",
                "III", 'I', Items.iron_ingot, 'B', RedstonicItems.IronBlade, 'K', Blocks.iron_block));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonicItems.BerserkIISwordAugment),
                "IKI",
                "IBI",
                "III", 'I', Items.iron_ingot, 'B', "redstonicMidTierBlade", 'K', RedstonicItems.BerserkSwordAugment));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonicItems.BlazerSwordAugment),
                "CIC",
                "IBI",
                "CIC", 'I', Items.iron_ingot, 'B', "redstonicMidTierBlade", 'C', Items.fire_charge));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonicItems.FortuitousSwordAugment),
                "CIC",
                "IBI",
                "CIC", 'I', Items.iron_ingot, 'B', Util.toStack(Items.dye, 1, 4), 'C', Blocks.lapis_block));

        //TE RECIPES
        if(Loader.isModLoaded("ThermalExpansion")) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonicItems.Energizer),
                    "LUL",
                    "UHU",
                    "LUL", 'L', "ingotLead", 'U', "ingotLumium", 'H', "blockGlassHardened", 'D', RedstonicItems.GoldHead));
            GameRegistry.addRecipe(new ItemStack(RedstonicItems.EnergyAugment), new Object[]{
                    "III",
                    "IBI",
                    "III", 'I', Items.iron_ingot, 'B', TEHelper.capacitorRedstone});

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonicItems.IronBody),
                    " G ",
                    "IBG",
                    "II ", 'I', Items.iron_ingot, 'B', TEHelper.coilElectrum, 'G', "gearIron"));

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonicItems.ElectrumBody),
                    " G ",
                    "IBG",
                    "II ", 'I', "ingotElectrum", 'B', TEHelper.coilElectrum, 'G', TEHelper.gearElectrum));

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonicItems.EnderiumBody),
                    " G ",
                    "IBG",
                    "II ", 'I', "ingotEnderium", 'B', TEHelper.coilElectrum, 'G', TEHelper.gearEnderium));

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonicItems.ElectrumHandle),
                    " I ",
                    "ISI",
                    "II ", 'I', TEHelper.ingotElectrum, 'S', RedstonicItems.redstoneStick));

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonicItems.EnderiumHandle),
                    " I ",
                    "ISI",
                    "II ", 'I', TEHelper.ingotEnderium, 'S', RedstonicItems.redstoneStick));

            GameRegistry.addRecipe(new ItemStack(RedstonicItems.EndHead), new Object[]{
                    " D ",
                    "IWG",
                    "ECE", 'D', RedstonicItems.DiamondHead, 'I', RedstonicItems.IronHead, 'W', Items.nether_star, 'G', RedstonicItems.GoldHead, 'E', RedstonicItems.EnergizerFull, 'C', TEHelper.coilElectrum});

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonicItems.UltimateBody),
                    " G ",
                    "IWG",
                    "II ", 'W', Items.nether_star, 'I', "ingotPlatinum", 'G', RedstonicItems.EnergizerFull));

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonicItems.ElectrumBlade),
                    "  I",
                    " I ",
                    "B  ", 'I', TEHelper.ingotElectrum, 'B', TEHelper.blockElectrum));

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonicItems.EnderiumBlade),
                    "  I",
                    " I ",
                    "B  ", 'I', TEHelper.ingotEnderium, 'B', TEHelper.blockEnderium));

            TEHelper.addTransposerFill(32000, new ItemStack(RedstonicItems.Energizer), new ItemStack(RedstonicItems.EnergizerFull), new FluidStack(FluidRegistry.getFluid("redstone"), 32000), false);
            TEHelper.addTransposerFill(1600, new ItemStack(Items.stick), new ItemStack(RedstonicItems.redstoneStick), new FluidStack(FluidRegistry.getFluid("redstone"), 2000), false);

        }

        //ENDER IO RECIPES
        if(Loader.isModLoaded("EnderIO")){
            EIOHelper.addAlloySmelterRecipe("Energizer Filling", 32000, Util.toStack(Blocks.redstone_block, 20, 0), Util.toStack(RedstonicItems.Energizer), Util.toStack(Blocks.redstone_block, 20, 0), Util.toStack(RedstonicItems.EnergizerFull));
            EIOHelper.addAlloySmelterRecipe("Vibrantium", 16000, Util.sizedStack(EIOHelper.ingotVibrant, 8), Util.sizedStack(EIOHelper.etchingCrystal, 2), Util.sizedStack(EIOHelper.ingotSoularium, 32), Util.toStack(RedstonicItems.ingotVibrantium));
            EIOHelper.addAlloySmelterRecipe("Glowstone Infused Steel", 8000, Util.sizedStack(EIOHelper.ingotElectrical, 2), Util.sizedStack(Util.toStack(Items.glowstone_dust), 8), null, Util.toStack(RedstonicItems.ingotGlowSteel));
            EIOHelper.addAlloySmelterRecipe("Redstone Stick", 2000, Util.toStack(Items.redstone, 20, 0), Util.toStack(Items.stick, 1, 0), null, Util.toStack(RedstonicItems.redstoneStick));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonicItems.Energizer),
                    "LUL",
                    "UHU",
                    "LUL", 'L', "ingotConductiveIron", 'U', "ingotLumium", 'H', "blockGlassHardened", 'D', RedstonicItems.GoldHead));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonicItems.UltimateBody),
                    " G ",
                    "IWG",
                    "II ", 'W', Items.nether_star, 'I', RedstonicItems.ingotVibrantium, 'G', RedstonicItems.EnergizerFull));
            GameRegistry.addRecipe(new ItemStack(RedstonicItems.EndHead), new Object[]{
                    " D ",
                    "IWG",
                    "ECE", 'D', RedstonicItems.DiamondHead, 'I', RedstonicItems.IronHead, 'W', Items.nether_star, 'G', RedstonicItems.GoldHead, 'E', RedstonicItems.EnergizerFull, 'C', EIOHelper.basicCapacitor});
            GameRegistry.addRecipe(new ItemStack(RedstonicItems.gearIron), new Object[]{
                    " I ",
                    "IGI",
                    " I ", 'I', Items.iron_ingot, 'G', EIOHelper.basicGear});
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonicItems.gearEnergized),
                    " I ",
                    "IGI",
                    " I ", 'I', EIOHelper.ingotEnergized, 'G', "gearIron"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonicItems.gearVibrant),
                    " I ",
                    "IGI",
                    " I ", 'I', EIOHelper.ingotVibrant, 'G', "gearEnergized"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonicItems.IronBody),
                    " G ",
                    "IBG",
                    "II ", 'I', Items.iron_ingot, 'B', EIOHelper.basicCapacitor, 'G', "gearIron"));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonicItems.EnergeticBody),
                    " G ",
                    "IBG",
                    "II ", 'I', EIOHelper.ingotEnergized, 'B', EIOHelper.doubleCapacitor, 'G', RedstonicItems.gearEnergized));
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonicItems.VibrantBody),
                    " G ",
                    "IBG",
                    "II ", 'I', EIOHelper.ingotVibrant, 'B', EIOHelper.octadicCapacitor, 'G', RedstonicItems.gearVibrant));

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonicItems.EnergizedHandle),
                    " I ",
                    "ISI",
                    "II ", 'I', EIOHelper.ingotEnergized, 'S', RedstonicItems.redstoneStick));

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonicItems.VibrantHandle),
                    " I ",
                    "ISI",
                    "II ", 'I', EIOHelper.ingotVibrant, 'S', RedstonicItems.redstoneStick));

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonicItems.basicBattery),
                    " R ",
                    "SCS",
                    "RAR", 'R', Blocks.redstone_block, 'S', "ingotElectricalSteel", 'C', "ingotConductiveIron", 'A', EIOHelper.basicCapacitor));

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonicItems.EnergizedBlade),
                    "  I",
                    " I ",
                    "B  ", 'I', EIOHelper.ingotEnergized, 'B', EIOHelper.blockEnergetic));

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonicItems.VibrantBlade),
                    "  I",
                    " I ",
                    "B  ", 'I', EIOHelper.ingotVibrant, 'B', EIOHelper.blockVibrantium));

            ItemStack basicBattery = new ItemStack(RedstonicItems.basicBattery);
            basicBattery.setItemDamage(OreDictionary.WILDCARD_VALUE);

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonicItems.energizedBattery),
                    " R ",
                    "SCS",
                    "RAR", 'R', Blocks.redstone_block, 'S', "ingotEnergeticAlloy", 'C', "blockPhasedIron", 'A', basicBattery));

            ItemStack energizedBattery = new ItemStack(RedstonicItems.basicBattery);
            energizedBattery.setItemDamage(OreDictionary.WILDCARD_VALUE);

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonicItems.greatBattery),
                    " R ",
                    "SCS",
                    "RAR", 'R', Blocks.redstone_block, 'S', RedstonicItems.ingotGlowSteel, 'C', "blockPhasedGold" , 'A', energizedBattery));

            GameRegistry.addRecipe(new ItemStack(RedstonicItems.EnergyAugment), new Object[]{
                    "III",
                    "IBI",
                    "III", 'I', Items.iron_ingot, 'B', RedstonicItems.energizedBattery});
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonicBlocks.GlowSteel),
                    "III", "III", "III", 'I', RedstonicItems.ingotGlowSteel));

            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RedstonicBlocks.Vibrantium),
                    "III", "III", "III", 'I', RedstonicItems.ingotVibrantium));
        }
        GameRegistry.addRecipe(new HotswapSet());
        GameRegistry.addRecipe(new ContainerSet());
    }

}
package com.raizunne.redstonic.Item;

import cofh.api.energy.IEnergyContainerItem;
import com.raizunne.redstonic.Redstonic;
import com.raizunne.redstonic.Util.DrillUtil;
import com.raizunne.redstonic.Util.Lang;
import com.raizunne.redstonic.Util.Util;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by Raizunne as a part of Redstonic
 * on 03/02/2015, 09:52 PM.
 */
public class RedstonicDrill extends ItemPickaxe implements IEnergyContainerItem {

    int head; int boderino; int maxEnergy;

    public RedstonicDrill() {
        super(Item.ToolMaterial.EMERALD);
        setCreativeTab(Redstonic.redTab);
        setUnlocalizedName("RedstonicDrill");
        setNoRepair();
        setMaxDamage(80);
        setTextureName("redstonic:Drill/PlaceHolder");
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean yowtf) {
        if (stack.stackTagCompound == null) {
            list.add("Will not work.");
            list.add("Crafted in the" + EnumChatFormatting.YELLOW + " Drill Modifier");
            if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                list.add("Needed to craft: " + EnumChatFormatting.YELLOW + "Drill Head, ");
                list.add(EnumChatFormatting.YELLOW + "Drill Body, Battery");
                list.add(EnumChatFormatting.YELLOW + "(Flux Capacitor or Battery)");
            } else {
                list.add(Lang.translate("drill.hold") + EnumChatFormatting.ITALIC + EnumChatFormatting.RED + " Shift " + EnumChatFormatting.RESET + EnumChatFormatting.GRAY + Lang.translate("drill.formoreinfo"));
            }
        } else {
            NBTTagCompound tag = stack.stackTagCompound;
            list.clear();
            String prefix; String sufix; String head, body, capacitor=""; String speed; boolean creative=false;

            float multi = tag.getFloat("energyMulti");
            if (multi == 0) {
                multi = 1;
            }
            if(tag.getInteger("body")==3){
                multi=3;
            }

            float multiplier = tag.getFloat("speedMulti");
            if (multiplier == 0) {
                multiplier = 1;
            }
            if(tag.getInteger("maxEnergy")==-1 || tag.getInteger("maxEnergy")==-2){
                creative = true;
            }
            prefix = DrillUtil.getDrillHeadName(tag.getInteger("head"));
            head = DrillUtil.getDrillHeadName(tag.getInteger("head")) + " Head";
            sufix = DrillUtil.getDrillBodyName(tag.getInteger("body"));
            body = DrillUtil.getDrillBodyName(tag.getInteger("body")) + " Body";
            speed = "" + this.getDigSpeed(stack, Blocks.stone, 0) + "F";

            list.add(prefix + " " + sufix + EnumChatFormatting.WHITE + " Redstonic Drill");
            list.add(EnumChatFormatting.GREEN + "Energy: " + EnumChatFormatting.GRAY + (creative?EnumChatFormatting.OBFUSCATED + "Creative" +
                    EnumChatFormatting.RESET + EnumChatFormatting.GRAY: NumberFormat.getNumberInstance(Locale.US).format(tag.getInteger("energy"))) + "/" + (creative?"Creative":NumberFormat.getNumberInstance(Locale.US).format(tag.getInteger("maxEnergy"))) + " RF");
            if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                list.add(EnumChatFormatting.GRAY + Lang.translate("drill.head") + ": " + EnumChatFormatting.DARK_GRAY + head);
                list.add(EnumChatFormatting.GRAY + Lang.translate("drill.body") + ": " + EnumChatFormatting.DARK_GRAY + body);
                list.add(EnumChatFormatting.GRAY + Lang.translate("drill.battery") + ": " + EnumChatFormatting.DARK_GRAY + (creative ? "Creative":DrillUtil.getBatteryName(tag.getInteger("battery"))));
                list.add(EnumChatFormatting.GRAY + Lang.translate("drill.digspeed") + ": " + EnumChatFormatting.DARK_GRAY + (tag.getInteger("head")==7 ? EnumChatFormatting.OBFUSCATED + "1000000F":speed));
                list.add(EnumChatFormatting.GRAY + Lang.translate("drill.energyusage") + ": " + EnumChatFormatting.YELLOW + calcEnergy(stack) + " RF");
                list.add(EnumChatFormatting.GRAY + "Blocks Destroyed: " + EnumChatFormatting.RED + NumberFormat.getNumberInstance(Locale.US).format(tag.getInteger("blocks")));
            }else if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) && !hasNOAugment(stack)) {
                if(tag.getInteger("aug1")!=0){ list.add(EnumChatFormatting.YELLOW + Lang.translate("drill.augment") + ": " + EnumChatFormatting.DARK_GRAY + DrillUtil.getAugName(tag.getInteger("aug1"))); }
                if(tag.getInteger("aug2")!=0){ list.add(EnumChatFormatting.YELLOW + Lang.translate("drill.augment") + ": " + EnumChatFormatting.DARK_GRAY + DrillUtil.getAugName(tag.getInteger("aug2"))); }
                if(tag.getInteger("aug3")!=0){ list.add(EnumChatFormatting.YELLOW + Lang.translate("drill.augment") + ": " + EnumChatFormatting.DARK_GRAY + DrillUtil.getAugName(tag.getInteger("aug3"))); }
            }else{
                list.add(Util.ItemShiftInfo);
                if(!hasNOAugment(stack)){list.add(Util.ItemCtrlInfo);}
            }
        }
    }

    public void gratzMessage(EntityPlayer player, int i, ItemStack stack){
        if(!player.worldObj.isRemote) {
            player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + "Redstonic Drill: " + EnumChatFormatting.WHITE + "You've reached " + i + " mined blocks! Way to go!"));
            stack.stackTagCompound.setInteger("blocks", stack.stackTagCompound.getInteger("blocks")+1);
        }
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
        NBTTagCompound nbt = stack.stackTagCompound;
        if (nbt == null) {
            stack.stackTagCompound = new NBTTagCompound();
            stack.stackTagCompound.setInteger("head", head);
            stack.stackTagCompound.setInteger("body", boderino);
            stack.stackTagCompound.setInteger("maxEnergy", maxEnergy);
        } else {
            EntityPlayer player = null;
            if(entity instanceof EntityPlayer){
                player = (EntityPlayer)entity;
            }
            if(nbt.getInteger("milestone")==0){
                nbt.setInteger("milestone", 500);
            }
            if(nbt.getInteger("blocks")>=nbt.getInteger("milestone")){
                gratzMessage(player, nbt.getInteger("milestone"), stack);
                nbt.setInteger("milestone", nbt.getInteger("milestone")+500);
            }
            if (nbt.getInteger("head") == 4 && !stack.isItemEnchanted()) {
                stack.addEnchantment(Enchantment.fortune, 4);
            }
            if (nbt.getInteger("head") == 5 && !stack.isItemEnchanted()) {
                stack.addEnchantment(Enchantment.silkTouch, 1);
            }
            if (nbt.getInteger("head") != 4 && nbt.getInteger("head") != 5) {
                stack.stackTagCompound.removeTag("ench");
            }
        }
    }

    IIcon[] heads;
    IIcon[] body;
    IIcon[] augments;

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        System.out.println(OreDictionary.getOres("ingotPhasedGold").get(0));
        NBTTagCompound nbt = stack.stackTagCompound;
        if(!player.isSneaking()) {
            if(hasAugment(4, stack)) {
                placeBlock(Block.getBlockById(nbt.getInteger("placeBlock")), nbt.getInteger("placeBlockMeta"), world, player);
            }else{
                placeTorch(world, player);
            }
        }
        if (player.isSneaking()) {
            MovingObjectPosition pos = this.getMovingObjectPositionFromPlayer(world, player, true);
            if(pos!=null && pos.typeOfHit== MovingObjectPosition.MovingObjectType.BLOCK) {
                if(Block.getBlockById(nbt.getInteger("placeBlock"))!=world.getBlock(pos.blockX, pos.blockX, pos.blockZ)){
                    player.playSound("fire.ignite", 0.5F, 1F);
                }
                nbt.setInteger("placeBlock", Block.getIdFromBlock(world.getBlock(pos.blockX, pos.blockY, pos.blockZ)));
                nbt.setInteger("placeBlockMeta", world.getBlockMetadata(pos.blockX, pos.blockY, pos.blockZ));
            }
            if (hasAugment(3, stack) && checkEnergy(stack, 1500) && pos==null) {
                if (nbt.getInteger("hotswapHead") != -1) {
                    int temp = nbt.getInteger("head");
                    nbt.setInteger("head", nbt.getInteger("hotswapHead"));
                    nbt.setInteger("hotswapHead", temp);
                    takeEnergy(stack, 1500);
                    player.playSound("random.pop", 1.0F, 1.0F);
                }
            }
        }
        return stack;
    }

    public void placeBlock(Block block, int meta, World world, EntityPlayer player){
        MovingObjectPosition pos = this.getMovingObjectPositionFromPlayer(world, player, true);
        if(player.inventory.hasItemStack(new ItemStack(block, 0, meta))){
            String sound = block.stepSound.func_150496_b();
            if(pos!=null && pos.typeOfHit== MovingObjectPosition.MovingObjectType.BLOCK){
                int posX = pos.blockX; int posY = pos.blockY; int posZ = pos.blockZ;
                switch (pos.sideHit) {
                    case 0:
                        if(world.getBlock(posX, posY-1, posZ)==Blocks.air) {
                            if (!world.isRemote) {
                                world.setBlock(posX, posY - 1, posZ, block, meta, 2);
                            }
                            player.inventory.consumeInventoryItem(new ItemStack(block).getItem());
                            player.playSound(sound, 1F, 1F);
                            player.swingItem();
                        }
                        break;
                    case 1:
                        if(world.getBlock(posX, posY+1, posZ)==Blocks.air) {
                            if (!world.isRemote) {
                                world.setBlock(posX, posY + 1, posZ, block, meta, 2);
                            }
                            player.inventory.consumeInventoryItem(new ItemStack(block).getItem());
                            player.playSound(sound, 1F, 1F);
                            player.swingItem();
                        }
                        break;
                    case 2:
                        if(world.getBlock(posX, posY, posZ-1)==Blocks.air) {
                            if (!world.isRemote) {
                                world.setBlock(posX, posY, posZ - 1, block, meta, 2);
                            }
                            player.inventory.consumeInventoryItem(new ItemStack(block).getItem());
                            player.playSound(sound, 1F, 1F);
                            player.swingItem();
                        }
                        break;
                    case 3:
                        if(world.getBlock(posX, posY, posZ+1)==Blocks.air){
                            if (!world.isRemote) {
                                world.setBlock(posX, posY, posZ + 1, block, meta, 2);
                            }
                            player.inventory.consumeInventoryItem(new ItemStack(block).getItem());
                            player.playSound(sound, 1F, 1F);
                            player.swingItem();
                        }
                        break;
                    case 4:
                        if(world.getBlock(posX-1, posY, posZ)==Blocks.air) {
                            if (!world.isRemote) {
                                world.setBlock(posX - 1, posY, posZ, block, meta, 2);
                            }
                            player.inventory.consumeInventoryItem(new ItemStack(block).getItem());
                            player.playSound(sound, 1F, 1F);
                            player.swingItem();
                        }
                        break;
                    case 5:
                        if(world.getBlock(posX+1, posY, posZ)==Blocks.air) {
                            if (!world.isRemote) {
                                world.setBlock(posX + 1, posY, posZ, block, meta, 2);
                            }
                            player.inventory.consumeInventoryItem(new ItemStack(block).getItem());
                            player.playSound(sound, 1F, 1F);
                            player.swingItem();
                        }
                        break;
                }
            }
        }
    }

    public void placeTorch(World world, EntityPlayer player){
        MovingObjectPosition pos = this.getMovingObjectPositionFromPlayer(world, player, true);
        Block block = null;
        Block torchVanilla = Blocks.torch;
        Block torchTCon = null;
        if(Loader.isModLoaded("TConstruct")){
            torchTCon = GameRegistry.findBlock("TConstruct", "decoration.stonetorch");
            if(player.inventory.hasItemStack(new ItemStack(Blocks.torch))){
                block = Blocks.torch;
            }else if(player.inventory.hasItemStack(GameRegistry.findItemStack("TConstruct", "decoration.stonetorch", 1))){
                block = GameRegistry.findBlock("TConstruct", "decoration.stonetorch");
            }
        }else{
            block = Blocks.torch;
            torchTCon = Blocks.torch;
        }
        if(player.inventory.hasItemStack(new ItemStack(block))){
            if(pos!=null && pos.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                int posX = pos.blockX; int posY = pos.blockY; int posZ = pos.blockZ;
                if(world.getBlock(posX, posY, posZ)!=torchVanilla && world.getBlock(posX, posY, posZ)!=torchTCon && pos.sideHit!=0) {
                    switch (pos.sideHit) {
                        case 1:
                            if(world.getBlock(posX, posY+1, posZ)!=torchVanilla && world.getBlock(posX, posY+1, posZ)!=torchTCon) {
                                if(!world.isRemote) {
                                    world.setBlock(posX, posY+1, posZ, block, 5, 2);
                                }
                                player.inventory.consumeInventoryItem(new ItemStack(block).getItem());
                                player.playSound("dig.wood", 1F, 1F);
                                player.swingItem();
                            }
                            break;
                        case 2:
                            if(world.getBlock(posX, posY, posZ-1)!=torchVanilla && world.getBlock(posX, posY, posZ-1)!=torchTCon) {
                                if(!world.isRemote) {
                                    world.setBlock(posX, posY, posZ-1, block, 4, 2);
                                }
                                player.inventory.consumeInventoryItem(new ItemStack(block).getItem());
                                player.playSound("dig.wood", 1F, 1F);
                                player.swingItem();
                            }
                            break;
                        case 3:
                            if(world.getBlock(posX, posY, posZ+1)!=torchVanilla && world.getBlock(posX, posY, posZ+1)!=torchTCon) {
                                if (!world.isRemote) {
                                    world.setBlock(posX, posY, posZ+1, block, 3, 2);
                                }
                                player.inventory.consumeInventoryItem(new ItemStack(block).getItem());
                                player.playSound("dig.wood", 1F, 1F);
                                player.swingItem();
                            }
                            break;
                        case 4:
                            if(world.getBlock(posX-1, posY, posZ)!=torchVanilla && world.getBlock(posX-1, posY, posZ)!=torchTCon) {
                                if (!world.isRemote) {
                                    world.setBlock(posX - 1, posY, posZ, block, 2, 2);
                                }
                                player.inventory.consumeInventoryItem(new ItemStack(block).getItem());
                                player.playSound("dig.wood", 1F, 1F);
                                player.swingItem();
                            }
                            break;
                        case 5:
                            if(world.getBlock(posX+1, posY, posZ)!=torchVanilla && world.getBlock(posX+1, posY, posZ)!=torchTCon) {
                                if (!world.isRemote) {
                                    world.setBlock(posX+1, posY, posZ, block, 1, 2);
                                }
                                player.inventory.consumeInventoryItem(new ItemStack(block).getItem());
                                player.playSound("dig.wood", 1F, 1F);
                                player.swingItem();
                            }
                            break;
                    }

                }
            }
        }
    }

    public boolean hasAugment(int i, ItemStack stack){
        NBTTagCompound nbt = stack.stackTagCompound;
        return nbt.getInteger("aug1") == i || nbt.getInteger("aug2") == i || nbt.getInteger("aug3") == i;
    }

    public boolean hasNOAugment(ItemStack stack){
        NBTTagCompound nbt = stack.stackTagCompound;
        return nbt.getInteger("aug1")==0 && nbt.getInteger("aug2")==0 && nbt.getInteger("aug3")==0;
    }

    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
        return super.onEntitySwing(entityLiving, stack);
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase entityLiving) {
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityLiving;
            if (stack.stackTagCompound.getInteger("head") == 3 && checkEnergy(stack, calcEnergy(stack)) && block != Blocks.dirt && block != Blocks.sand && block != Blocks.grass) {
                threebythree(player, world, block, x, y, z, stack);
            }
            if(stack.stackTagCompound.getInteger("head")==6){
                player.playSound("fire.ignite", 0.5F, -1F);
                world.spawnParticle("flame", player.posX, player.posY+1, player.posZ,1, 1, 1);
            }
        }
        takeEnergy(stack, calcEnergy(stack));
        if(stack.stackTagCompound.getInteger("head")!=3){stack.stackTagCompound.setInteger("blocks", stack.stackTagCompound.getInteger("blocks")+1);}
        int energy = stack.stackTagCompound.getInteger("energy");
        int maxEnergy = stack.stackTagCompound.getInteger("maxEnergy");
        double modifier = (double) 80 / maxEnergy;
        stack.setItemDamage((int) (80 - energy * modifier));
        return true;
    }

    @Override
    public float getDigSpeed(ItemStack stack, Block block, int meta) {
        NBTTagCompound tag = stack.stackTagCompound;
        float multiplier = 1;
        if(tag.getInteger("aug1")==1 || tag.getInteger("aug2")==1 || tag.getInteger("aug3")==1){
            multiplier = multiplier + 0.5F;
        }

        if(!checkEnergy(stack, calcEnergy(stack))){
            return 0.1F;
        }

        if((block==Blocks.sand || block==Blocks.dirt || block==Blocks.grass || block==Blocks.glass )&& tag.getInteger("head")!=7){
            return 10F;
        }
        if(stack.stackTagCompound.getInteger("head")==6 && block==Blocks.netherrack){
            return 5F;
        }

        int head = tag.getInteger("head");
        switch(head){
            case 0: return 10F * multiplier;
            case 1: return 25F * multiplier;
            case 2: return 15F * multiplier;
            case 3: return 8F * multiplier;
            case 4: return 5F * multiplier;
            case 5: return 8F * multiplier;
            case 6: return 10F * multiplier;
            case 7: return 100000F;
            default: return 1F;
        }
    }

    public void threebythree(EntityPlayer player, World world, Block block, int x, int y, int z, ItemStack stack){
        int ogx = x; int ogy = y; int ogz = z;

        MovingObjectPosition mop = this.getMovingObjectPositionFromPlayer(world, player, true);
        if(mop !=null && !world.isRemote){
            if(mop.sideHit==0 || mop.sideHit==1){
                x = x-1; z = z-1;
                for(int i=0; i<=2; i++){ for(int r=0; r<=2; r++){
                    if(checkHeavy(world, block, ogx, ogy, ogz, x+i, y, z+r)) {
                        stack.stackTagCompound.setInteger("blocks", stack.stackTagCompound.getInteger("blocks")+1);
                        world.func_147480_a(x + i, y, z + r, true);
                    }
                }}
            }else if(mop.sideHit==2 || mop.sideHit==3){
                x=x+1; y=y+1;
                for(int i=0; i<=2; i++){ for(int r=0; r<=2; r++){
                    if(checkHeavy(world, block, ogx, ogy, ogz, x-i, y-r, z)) {
                        stack.stackTagCompound.setInteger("blocks", stack.stackTagCompound.getInteger("blocks")+1);
                        world.func_147480_a(x - i, y - r, z, true);
                    }
                }}
            }else if(mop.sideHit==4 || mop.sideHit==5){
                z=z+1; y=y+1;
                for(int i=0; i<=2; i++){ for(int r=0; r<=2; r++){
                    if(checkHeavy(world, block, ogx, ogy, ogz, x, y-r, z-i)) {
                        stack.stackTagCompound.setInteger("blocks", stack.stackTagCompound.getInteger("blocks")+1);
                        world.func_147480_a(x, y - r, z - i, true);
                    }
                }}
            }
        }
    }

    @Override
    public void registerIcons(IIconRegister i) {
        super.registerIcons(i);
        heads = new IIcon[8];
        body = new IIcon[6];
        augments = new IIcon[5];

        heads[0] = i.registerIcon("redstonic:Drill/Heads/Render/Iron");
        heads[1] = i.registerIcon("redstonic:Drill/Heads/Render/Gold");
        heads[2] = i.registerIcon("redstonic:Drill/Heads/Render/Diamond");
        heads[3] = i.registerIcon("redstonic:Drill/Heads/Render/Heavy");
        heads[4] = i.registerIcon("redstonic:Drill/Heads/Render/Fortuitous");
        heads[5] = i.registerIcon("redstonic:Drill/Heads/Render/Silky");
        heads[6] = i.registerIcon("redstonic:Drill/Heads/Render/Blazer");
        heads[7] = i.registerIcon("redstonic:Drill/Heads/Render/End");

        body[0] = i.registerIcon("redstonic:Drill/Bodies/Render/Iron");
        body[1] = i.registerIcon("redstonic:Drill/Bodies/Render/Electrum");
        body[2] = i.registerIcon("redstonic:Drill/Bodies/Render/Enderium");
        body[3] = i.registerIcon("redstonic:Drill/Bodies/Render/End");
        body[4] = i.registerIcon("redstonic:Drill/Bodies/Render/Energetic");
        body[5] = i.registerIcon("redstonic:Drill/Bodies/Render/Vibrant");

        augments[0] = i.registerIcon("redstonic:Drill/Augment/Render/Null");
        augments[1] = i.registerIcon("redstonic:Drill/Augment/Render/Speed");
        augments[2] = i.registerIcon("redstonic:Drill/Augment/Render/Energy");
        augments[3] = i.registerIcon("redstonic:Drill/Augment/Render/Null");
        augments[4] = i.registerIcon("redstonic:Drill/Augment/Render/Null");
    }

    @Override
    public IIcon getIcon(ItemStack stack, int pass) {
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_BLEND);
        NBTTagCompound nbt = stack.stackTagCompound;
        if(nbt!=null){
            if(pass==0){
                return heads[nbt.getInteger("head")];
            }
            if(pass==1){
                return body[nbt.getInteger("body")];
            }
            if(pass==2){
                return augments[nbt.getInteger("aug1")];
            }
            if(pass==3){
                return augments[nbt.getInteger("aug2")];
            }
            if(pass==4){
                return augments[nbt.getInteger("aug3")];
            }
        }else if(nbt==null){
            if(pass==0){
                return heads[0];
            }
            if(pass==1){
                return body[0];
            }
            if(pass==2){
                return augments[0];
            }
            if(pass==3){
                return augments[0];
            }
            if(pass==4){
                return augments[0];
            }
        }
        return null;
    }

    public boolean checkHeavy(World world, Block block, int x, int y, int z, int bx, int by, int bz){
        Block blocktwo = world.getBlock(bx,by,bz);
        if(blocktwo==Blocks.bedrock || blocktwo==Blocks.dirt || blocktwo==Blocks.grass || blocktwo==Blocks.glass || blocktwo==Blocks.mob_spawner){
            return false;
        }
        if((world.getTileEntity(bx, by, bz)==null || block==Blocks.redstone_ore)
                && world.getBlock(x, y, z).getBlockHardness(world, x, y, z) <= block.getBlockHardness(world, bx, by, bz)+5F && !world.getBlock(bx, by, bz).isFlammable(world, bx, by, bz, null) &&
                !(world.getBlock(bx, by, bz) instanceof BlockCrops && world.getBlock(bx, by, bz)!=Blocks.bedrock) && block.getRenderType()==0 && world.getBlock(bx,by,bz).getRenderType()==0){
            return true;
        }
        return false;
    }

    @Override
    public int getRenderPasses(int metadata) {
        return 5;
    }

    @Override
    public boolean requiresMultipleRenderPasses() {
        return true;
    }

    public boolean checkEnergy(ItemStack stack, int i){
        if(stack.stackTagCompound.getInteger("maxEnergy")==-1 || stack.stackTagCompound.getInteger("maxEnergy")==-2){
            return true;
        }else if(stack.stackTagCompound.getInteger("energy")>=i){
            return true;
        }else{
            return false;
        }
    }

    public int calcEnergy(ItemStack stack){
        NBTTagCompound tag = stack.stackTagCompound;
        float multiplier = 1;
        if(tag.getInteger("aug1")==1 || tag.getInteger("aug2")==1 || tag.getInteger("aug3")==1){
            multiplier = multiplier + 0.2F;
        }
        if(tag.getInteger("aug1")==2 || tag.getInteger("aug2")==2 || tag.getInteger("aug3")==2){
            multiplier = multiplier + 0.2F;
        }
        int head = tag.getInteger("head");
        switch(head){
            case 0:	return (int)(200 * multiplier);
            case 1: return (int)(400 * multiplier);
            case 2: return (int)(300 * multiplier);
            case 3: return (int)(1500 * multiplier);
            case 4: return (int)(800 * multiplier);
            case 5: return (int)(500 * multiplier);
            case 6: return (int)(800 * multiplier);
            case 7: return (int)(2000 * multiplier);
            default: return 0;
        }
    }

    public int getInput(ItemStack stack){
        int body = (stack.stackTagCompound.getInteger("body"));
        switch(body){
            case 0: return 1000;
            case 1:case 4: return 10000;
            case 2:case 5: return 32000;
            case 3: return 50000;
            default: return 0;
        }
    }

    public void takeEnergy(ItemStack stack, int i){
        stack.stackTagCompound.setInteger("energy", stack.stackTagCompound.getInteger("energy")-i);
        double modifier = (double)80/stack.stackTagCompound.getInteger("maxEnergy");
        stack.setItemDamage((int)(80 - stack.stackTagCompound.getInteger("energy")*modifier));
    }

    @Override
    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
        if (container.stackTagCompound == null) {
            container.stackTagCompound = new NBTTagCompound();
        }
        int energy = container.stackTagCompound.getInteger("energy");
        int maxEnergy = container.stackTagCompound.getInteger("maxEnergy");
        if(container.stackTagCompound.getInteger("energy")>=container.stackTagCompound.getInteger("maxEnergy")){
            energy += 0;
        }else{
            if(energy+getInput(container)>maxEnergy){
                energy = maxEnergy;
            }else{
                energy += getInput(container);
            }
        }
        container.stackTagCompound.setInteger("energy", energy);
        double modifier = (double)80/maxEnergy;
        container.setItemDamage((int)(80 - energy*modifier));
        return getInput(container);
    }

    @Override
    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
        return 0;
    }

    @Override
    public boolean hasEffect(ItemStack par1ItemStack, int pass) {
        return false;
    }

    @Override
    public int getEnergyStored(ItemStack container) {
        return container.stackTagCompound.getInteger("energy");
    }

    @Override
    public int getMaxEnergyStored(ItemStack container) {
        return container.stackTagCompound.getInteger("maxEnergy");
    }
}
package net.silly511.viewemc;

import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockOre;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.silly511.core.helpers.FormatHelper;
import net.silly511.core.helpers.WorldHelper;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import moze_intel.projecte.api.ProjectEAPI;
import cpw.mods.fml.common.Optional;

@Optional.Interface(iface = "mcp.mobius.waila.api.IWailaDataProvider", modid = "Waila")
public class WailaHandler implements IWailaDataProvider {

	@Optional.Method(modid = "Waila")
    public static void callbackRegister(IWailaRegistrar register) {

        register.registerBodyProvider(new WailaHandler(), Block.class);

        register.addConfig("EMC", "emc.showEMC", true);
        register.addConfig("EMC", "emc.shiftToggleEMC", false);
    }
	
	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
		return null;
	}

	@Override
	public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		return currenttip;
	}

	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		int emc = 0;
		
		Item item = accessor.getBlock().getItem(accessor.getWorld(), accessor.getPosition().blockX,  accessor.getPosition().blockY,  accessor.getPosition().blockZ);
    	
    	if (!(item == null)) {
    		Block block = item instanceof ItemBlock && !accessor.getBlock().isFlowerPot() ? Block.getBlockFromItem(item) : accessor.getBlock();
    		emc = ProjectEAPI.getEMCProxy().getValue(new ItemStack(item, 1, block.getDamageValue(accessor.getWorld(), accessor.getPosition().blockX,  accessor.getPosition().blockY,  accessor.getPosition().blockZ)));
    	}
		
		if (config.getConfig("emc.showEMC")) {
			if (config.getConfig("emc.shiftToggleEMC")) {
				if (accessor.getPlayer().isSneaking())
					if (emc != 0) 
						currenttip.add(EnumChatFormatting.YELLOW + "EMC: " + EnumChatFormatting.WHITE + FormatHelper.numberWithCommas.format(emc) + EnumChatFormatting.RESET);
			} else {
				if (emc != 0)
					currenttip.add(EnumChatFormatting.YELLOW + "EMC: " + EnumChatFormatting.WHITE + FormatHelper.numberWithCommas.format(emc) + EnumChatFormatting.RESET);
			}
		}
		
		return currenttip;
	}

	@Override
	public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		return currenttip;
	}

	@Override
	public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, int x, int y, int z) {
		return tag;
	}
	
	
}
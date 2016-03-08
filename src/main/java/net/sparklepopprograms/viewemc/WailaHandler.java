package net.sparklepopprograms.viewemc;

import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockOre;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
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

        register.addConfig("EMC", "emc.showEmc", true);
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
		
		if (accessor.getBlock() == Blocks.skull) {
			emc = ProjectEAPI.getEMCProxy().getValue(new ItemStack(Items.skull, 1, accessor.getMetadata()));
		} else if (accessor.getBlock() == Blocks.redstone_wire) {
			emc = ProjectEAPI.getEMCProxy().getValue(new ItemStack(Items.redstone));
		} else {
			emc = ProjectEAPI.getEMCProxy().getValue(accessor.getBlock());
		}
		
		if (config.getConfig("emc.showEmc")) {
			if (config.getConfig("emc.shiftToggleEMC")) {
				if (accessor.getPlayer().isSneaking())
					if (emc == 0) {
						currenttip.add("No EMC");
					} else {
						currenttip.add("EMC: " + emc);
					}
			} else {
				if (emc == 0) {
					currenttip.add("No EMC");
				} else {
					currenttip.add("EMC: " + emc);
				}
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
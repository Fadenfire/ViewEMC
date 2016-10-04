package silly511.viewemc;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import moze_intel.projecte.api.ProjectEAPI;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class WailaHandler implements IWailaDataProvider {
	
	public static final NumberFormat commanator = new DecimalFormat("#,###");

	public static void register(IWailaRegistrar register) {
    	register.registerBodyProvider(new WailaHandler(), Block.class);
    }

	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
		return null;
	}

	@Override
	public List<String> getWailaHead(ItemStack item, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		return currenttip;
	}

	@Override
	public List<String> getWailaBody(ItemStack item, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		int emc = ProjectEAPI.getEMCProxy().getValue(item);
		if (emc > 0)
			currenttip.add(TextFormatting.YELLOW + "EMC: " + TextFormatting.WHITE + commanator.format(emc) + TextFormatting.RESET);

		return currenttip;
	}

	@Override
	public List<String> getWailaTail(ItemStack item, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		return currenttip;
	}

	@Override
	public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, BlockPos pos) {
		return tag;
	}
	
	
}
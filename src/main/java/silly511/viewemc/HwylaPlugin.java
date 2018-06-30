package silly511.viewemc;

import java.util.List;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaPlugin;
import mcp.mobius.waila.api.IWailaRegistrar;
import mcp.mobius.waila.api.WailaPlugin;
import moze_intel.projecte.utils.Constants;
import moze_intel.projecte.utils.EMCHelper;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

@WailaPlugin
public class HwylaPlugin implements IWailaPlugin, IWailaDataProvider {
	
	@Override
	public void register(IWailaRegistrar registrar) {
		registrar.addConfig("EMC", "emc.shiftToggleEMC", false);
		
		registrar.registerBodyProvider(this, Block.class);
	}
	
	@Override
	public List<String> getWailaBody(ItemStack item, List<String> tooltip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		if ((!config.getConfig("emc.shiftToggleEMC") || accessor.getPlayer().isSneaking()) && EMCHelper.doesItemHaveEmc(item))
			tooltip.add(TextFormatting.YELLOW + I18n.format("pe.emc.emc_tooltip_prefix") + " " +
					TextFormatting.WHITE + Constants.EMC_FORMATTER.format(EMCHelper.getEmcValue(item)) +
					TextFormatting.BLUE + EMCHelper.getEmcSellString(item, 1));

		return tooltip;
	}

}

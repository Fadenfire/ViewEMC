package net.sparklepopprograms.viewemc;

import scala.Int;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.sparklepopprograms.core.DimensionalCore;
import net.sparklepopprograms.core.updatechecker.UpdateManager;
import cpw.mods.fml.common.Mod.EventHandler;

@Mod(modid = "ViewEMC", name = "View EMC", version = ViewEMCMod.version, dependencies = ViewEMCMod.dependencies)
public class ViewEMCMod {
	
	public static final String dependencies = "required-after:DimensionalCore@[1.0.7,);required-after:Waila@[1.5.10,);required-after:ProjectE@[PE1.9.5,)";
	public static final String version = "v2";

	@EventHandler
	public void load(FMLInitializationEvent event) {
		FMLInterModComms.sendMessage("Waila", "register", "net.sparklepopprograms.viewemc.WailaHandler.callbackRegister");
		
		UpdateManager.register("View EMC", DimensionalCore.updateURL + "view-emc/files", 4, this.version);
		
	}
}
package silly511.viewemc;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;

@Mod(modid = "ViewEMC", name = "View EMC", version = "v5", dependencies = "required-after:Waila@[1.7.0,);required-after:ProjectE@[1.10.2-PE1.0.6B,)")
public class ViewEMCMod {

	@EventHandler
	public void load(FMLInitializationEvent event) {
		FMLInterModComms.sendMessage("Waila", "register", "silly511.viewemc.WailaHandler.register");
	}
}
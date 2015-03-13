package vanish;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	VanishListener vl;

	@Override
	public void onEnable() {

		vl = new VanishListener(this);
		VanishCMD vcmd = new VanishCMD(vl);
		getCommand("vanish").setExecutor(vcmd);
		getCommand("v").setExecutor(vcmd);
		super.onEnable();
	}

	public void onDisable() {
		vl.stop();

	}

}

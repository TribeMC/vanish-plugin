package vanish;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCMD implements CommandExecutor {

	private VanishListener vl;

	public VanishCMD(VanishListener vl) {
		this.vl = vl;
	}

	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2,
			String[] args) {
		if (!(cs instanceof Player)) {
			cs.sendMessage("§7Du musst ein Spieler sein!");
			return true;
		}
		if (!cs.hasPermission("vanish.vanish")) {
			cs.sendMessage("§cKeine Rechte!");
			return true;
		}

		if (args.length == 0) {

			cs.sendMessage("§7Dein Vanish-Status: "
					+ vl.changeVanishStatus((Player) cs));
		} else {
			try {
				Player p = Bukkit.getPlayer(args[0]);

				cs.sendMessage("§7Vanish-Status von " + p.getName() + ": "
						+ vl.changeVanishStatus(p));
			} catch (Exception e) {
				cs.sendMessage("§cPlayer not Online!");

			}
		}
		return true;
	}
}

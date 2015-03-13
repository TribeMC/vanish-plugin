package vanish;

import java.util.LinkedList;
import java.util.List;

import net.alpenblock.bungeeperms.bukkit.BungeePerms;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class VanishListener implements Listener {

	public VanishListener(Main main) {

		main.getServer().getPluginManager().registerEvents(this, main);
		vanished = new LinkedList<>();

	}

	List<Player> vanished;

	public void stop() {
		for (Player tar : vanished) {
			show(tar);
		}
	}

	public boolean changeVanishStatus(Player tar) {

		if (vanished.contains(tar)) {
			vanished.remove(tar);
			show(tar);
			return false;
		} else {
			vanished.add(tar);
			hide(tar);
			return true;
		}
	}

	public void hide(Player tar) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (!canSee(p.getName(), tar.getName())) {
				p.hidePlayer(tar);
			}
		}
	}

	public void show(Player tar) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.showPlayer(tar);
		}
	}

	public boolean canSee(String tar, String name) {
		try {
			int i = BungeePerms.getInstance().getPermissionsManager()
					.getUser(tar).getGroups().get(0).getRank();
			int i1 = BungeePerms.getInstance().getPermissionsManager()
					.getUser(name).getGroups().get(0).getRank();
			if (i1 <= i) {

				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		for (Player p : vanished) {
			if (!canSee(e.getPlayer().getName(), p.getName())) {
				e.getPlayer().hidePlayer(p);
			}
		}
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		for (Player p : vanished) {
			e.getPlayer().showPlayer(p);
		}
	}
}

package VjezbeXII;

import java.util.*;
import java.io.*;

public class Game {

	private Player player;
	private ArrayList<Enemy> enemies = new ArrayList<>();
	private ArrayList<String> log = new ArrayList<>();

	public Game(Player player) {
		this.player = player;
	}

	public boolean checkCollision(Player p, Enemy e) {
		return p.intersects(e);
	}

	public void decreaseHealth(Player p, Enemy e) {
		p.decreaseHealth(e.getEffectiveDamage());
		log.add(e.getDisplayName() + " hit player for " + e.getEffectiveDamage());
	}

	public void addEnemy(Enemy e) {
		enemies.add(e);
		log.add("Enemy added: " + e.getDisplayName());
	}

	public ArrayList<Enemy> collidingWithPlayer() {
		ArrayList<Enemy> list = new ArrayList<>();
		for (Enemy e : enemies) {
			if (checkCollision(player, e))
				list.add(e);
		}
		return list;
	}

	public void resolveCollisions() {
		for (Enemy e : enemies) {
			if (checkCollision(player, e)) {
				decreaseHealth(player, e);
			}
		}
	}

	public static ArrayList<Enemy> loadEnemiesFromCSV(String path) throws Exception {

		ArrayList<Enemy> list = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(path));
		String line;

		while ((line = br.readLine()) != null) {
			String[] d = line.split(",");

			String type = d[0];
			String cls = d[1]; // melee / boss
			int x = Integer.parseInt(d[2]);
			int y = Integer.parseInt(d[3]);
			String shape = d[4]; // rect / circle
			int damage = Integer.parseInt(d[5]);
			int health = Integer.parseInt(d[6]);

			Collidable c;

			if (shape.equals("rect")) {
				c = new RectangleCollider(x, y, 32, 32);
			} else {
				c = new CircleCollider(x, y, 16);
			}

			Enemy e = cls.equals("boss") ? new BossEnemy(type, damage, health, x, y, c)
					: new MeleeEnemy(type, damage, health, x, y, c);

			list.add(e);
		}
		br.close();
		return list;
	}

	public Player getPlayer() {
		return player;
	}

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public ArrayList<String> getLog() {
		return log;
	}
}

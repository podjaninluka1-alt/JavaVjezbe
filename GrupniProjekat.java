package grupniProjkat;

import java.util.ArrayList;

public class Main {

	static class Player {
		private String name;
		private int x;
		private int y;
		private int width;
		private int height;
		private int health;
		private int score;

		public Player(String name, int x, int y, int width, int height, int health) {
			this.name = name.toUpperCase();
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
			this.health = health;
			this.score = 0;
		}

		public String getName() {
			return name;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public int getWidth() {
			return width;
		}

		public int getHeight() {
			return height;
		}

		public int getHealth() {
			return health;
		}

		public int getScore() {
			return score;
		}

		public void setHealth(int health) {
			this.health = health;
		}

		public void setScore(int score) {
			this.score = score;
		}

		public void move(int dx, int dy) {
			this.x += dx;
			this.y += dy;
		}

		public String toString() {
			return String.format("Player{name='%s', x=%d, y=%d, HP=%d, Score=%d}", name, x, y, health, score);
		}
	}

	static class Enemy {
		private String type;
		private int x;
		private int y;
		private int width;
		private int height;
		private int damage;

		public Enemy(String type, int x, int y, int width, int height, int damage) {
			this.type = type;
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
			this.damage = damage;
		}

		public String getType() {
			return type;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public int getWidth() {
			return width;
		}

		public int getHeight() {
			return height;
		}

		public int getDamage() {
			return damage;
		}

		public String toString() {
			return String.format("Enemy{type='%s', x=%d, y=%d, DMG=%d}", type, x, y, damage);
		}
	}

	static class Game {
        private Player player;
        private ArrayList<Enemy> enemies;
        private ArrayList<String> eventLog;

        public Game(Player player) {
            this.player = player;
            this.enemies = new ArrayList<>();
            this.eventLog = new ArrayList<>();
        }

        public void addEnemy(Enemy e) {
            enemies.add(e);
            eventLog.add("Dodat neprijatelj tipa: " + e.getType());
        }

        public boolean checkCollision(Player p, Enemy e) {
            return p.getX() < e.getX() + e.getWidth() &&
                   p.getX() + p.getWidth() > e.getX() &&
                   p.getY() < e.getY() + e.getHeight() &&
                   p.getY() + p.getHeight() > e.getY();
  }
    


        public void decreaseHealth(Player p, Enemy e) {
        }
        }

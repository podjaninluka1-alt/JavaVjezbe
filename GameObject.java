import java.util.ArrayList;

public class GameObject {
        protected int x, y, width, height;

        public GameObject(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public boolean intersects(GameObject other) {
            return this.x < other.x + other.width &&
                    this.x + this.width > other.x &&
                    this.y < other.y + other.height &&
                    this.y + this.height > other.y;
        }

        @Override
        public String toString() {
            return String.format("(%d,%d) %dx%d", x, y, width, height);
        }
    }

    class Player extends GameObject {
        private String name;
        private int health;

        public Player(String name, int x, int y, int width, int height, int health) {
            super(x, y, width, height);
            this.name = name.trim();
            this.health = (health >= 0 && health <= 100) ? health : 100;
        }

        public String getName() {
            return name;
        }

        public int getHealth() {
            return health;
        }

        public void setHealth(int health) {
            this.health = Math.max(0, health);
        }

        @Override
        public String toString() {
            return String.format("Player[%s] @%s HP=%d", name, super.toString(), health);
        }
    }

 
  class Enemy extends GameObject {
        protected String type;
        protected int damage;
        protected int health;

        public Enemy(String type, int x, int y, int width, int height, int damage, int health) {
            super(x, y, width, height);
            this.type = type;
            this.damage = damage;
            this.health = health;
        }

        public String getType() {
            return type;
        }

        public int getDamage() {
            return damage;
        }

        public int getHealth() {
            return health;
        }

        @Override
        public String toString() {
            return String.format("Enemy[%s] @%s DMG=%d HP=%d", type, super.toString(), damage, health);
        }
    }

   class MeleeEnemy extends Enemy {
        public MeleeEnemy(String type, int x, int y, int width, int height, int damage, int health) {
            super(type, x, y, width, height, damage, health);
        }
    }

    class BossEnemy extends Enemy {
        public BossEnemy(String type, int x, int y, int width, int height, int damage, int health) {
            super(type, x, y, width, height, damage * 2, health * 2); 
        }

        @Override
        public String toString() {
            return String.format("BossEnemy[%s] @%s DMG=%d HP=%d", type, super.toString(), damage, health);
        }
    }

    class Game {
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
            eventLog.add("Dodat neprijatelj: " + e.getType());
        }

        public boolean checkCollision(Player p, Enemy e) {
            return p.intersects(e);
        }

        public void decreaseHealth(Player p, Enemy e) {
            int dmg = e.getDamage();
            if (e instanceof BossEnemy) dmg *= 2; 
            p.setHealth(p.getHealth() - dmg);
            eventLog.add("HIT: Player napadnut od " + e.getType() + " -> HP=" + p.getHealth());
        }

        public ArrayList<Enemy> findByType(String query) {
            ArrayList<Enemy> found = new ArrayList<>();
            for (Enemy e : enemies) {
                if (e.getType().toLowerCase().contains(query.toLowerCase())) {
                    found.add(e);
                    eventLog.add("Pronađen neprijatelj tipa: " + e.getType());
                }
            }
            return found;
        }

        public ArrayList<Enemy> collidingWithPlayer() {
            ArrayList<Enemy> colliding = new ArrayList<>();
            for (Enemy e : enemies) {
                if (checkCollision(player, e)) colliding.add(e);
            }
            return colliding;
        }

        public void resolveCollisions() {
            for (Enemy e : enemies) {
                if (checkCollision(player, e)) {
                    decreaseHealth(player, e);
                }
            }
        }

        public void printEventLog() {
            System.out.println("\nEVENT LOG");
            for (String ev : eventLog) {
                System.out.println(ev);
            }
        }
    
    public static void main(String[] args) {
        Player player = new Player("Petar Petrovic", 10, 10, 32, 32, 100);
        Game game = new Game(player);

        Enemy e1 = new MeleeEnemy("Goblin", 12, 5, 16, 16, 20, 50);
        Enemy e2 = new BossEnemy("Goblin King", 8, 12, 32, 32, 25, 60);

        game.addEnemy(e1);
        game.addEnemy(e2);

        System.out.println(player);
        System.out.println(e1);
        System.out.println(e2);

        ArrayList<Enemy> goblins = game.findByType("gob");
        System.out.println("\nPronađeni neprijatelji koji sadrže 'gob':");
        for (Enemy g : goblins) System.out.println(g);

        game.resolveCollisions();

        System.out.println("\nStanje igrača nakon kolizije:");
        System.out.println(player);

        game.printEventLog();
    }
}
  
    	
 


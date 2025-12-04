package VjezbeXII;

public class Player extends GameObject {

	private String name;
	private int health;

	public Player(String name, int health, int x, int y, Collidable collider) {
		super(x, y, collider);

		name = name.trim();
		if (name.isEmpty())
			throw new IllegalArgumentException("Invalid name");

		this.name = name.substring(0, 1).toUpperCase() + name.substring(1);
		if (health < 0 || health > 100)
			throw new IllegalArgumentException("Invalid health");

		this.health = health;
	}

	@Override
	public String getDisplayName() {
		return name;
	}

	public void decreaseHealth(int amount) {
		this.health = Math.max(0, this.health - amount);
	}

	public int getHealth() {
		return health;
	}

	@Override
	public String toString() {
		return "Player: " + name + " | Health: " + health;
	}
}

package VjezbeXII;

public class Enemy extends GameObject implements Attacker {

	protected String type;
	protected int damage;
	protected int health;

	public Enemy(String type, int damage, int health, int x, int y, Collidable collider) {
		super(x, y, collider);

		if (damage < 0 || damage > 100 || health < 0 || health > 100)
			throw new IllegalArgumentException();

		this.type = type;
		this.damage = damage;
		this.health = health;
	}

	@Override
	public int getEffectiveDamage() {
		return damage;
	}

	public String getType() {
		return type;
	}

	@Override
	public String getDisplayName() {
		return type;
	}

	@Override
	public String toString() {
		return "Enemy: " + type + " (DMG: " + damage + ", HP: " + health + ")";
	}
}

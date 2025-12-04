package VjezbeXII;

public abstract class GameObject {
	private int x, y;
	private Collidable collider;

	public GameObject(int x, int y, Collidable collider) {
		if (collider == null)
			throw new IllegalArgumentException("Collider cannot be null.");

		this.x = x;
		this.y = y;
		this.collider = collider;
	}

	public abstract String getDisplayName();

	public boolean intersects(GameObject other) {
		return this.collider.intersects(other.collider);
	}

	@Override
	public String toString() {
		return "GameObject at (" + x + "," + y + ")";
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}

package VjezbeXII;

public class RectangleCollider implements Collidable {

	private int x, y, width, height;

	public RectangleCollider(int x, int y, int width, int height) {
		if (width <= 0 || height <= 0) {
			throw new IllegalArgumentException("Invalid rectangle size.");
		}

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public boolean intersects(Collidable other) {
		if (other instanceof RectangleCollider rc) {
			return this.x < rc.x + rc.width && this.x + this.width > rc.x && this.y < rc.y + rc.height
					&& this.y + this.height > rc.y;
		}

		if (other instanceof CircleCollider) {
			return other.intersects(this);
		}

		return false;
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
}

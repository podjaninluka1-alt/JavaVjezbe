package VjezbeXII;

public class CircleCollider implements Collidable {

	private int x, y, radius;

	public CircleCollider(int x, int y, int radius) {
		if (radius <= 0)
			throw new IllegalArgumentException("Invalid radius.");

		this.x = x;
		this.y = y;
		this.radius = radius;
	}

	@Override
	public boolean intersects(Collidable other) {
		if (other instanceof CircleCollider cc) {
			double distance = Math.sqrt(Math.pow(x - cc.x, 2) + Math.pow(y - cc.y, 2));
			return distance <= (this.radius + cc.radius);
		}

		if (other instanceof RectangleCollider rc) {
			int closestX = clamp(this.x, rc.getX(), rc.getX() + rc.getWidth());
			int closestY = clamp(this.y, rc.getY(), rc.getY() + rc.getHeight());

			double distance = Math.sqrt(Math.pow(this.x - closestX, 2) + Math.pow(this.y - closestY, 2));
			return distance <= this.radius;
		}

		return false;
	}

	private int clamp(int value, int min, int max) {
		return Math.max(min, Math.min(max, value));
	}
}

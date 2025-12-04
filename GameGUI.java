package VjezbeXII;

import javax.swing.*;

public class GameGUI extends JFrame {

	public GameGUI() {

		JTextField name = new JTextField();
		JTextField health = new JTextField();
		JTextField x = new JTextField();
		JTextField y = new JTextField();

		JRadioButton rect = new JRadioButton("Rectangle", true);
		JRadioButton circle = new JRadioButton("Circle");

		ButtonGroup group = new ButtonGroup();
		group.add(rect);
		group.add(circle);

		JButton start = new JButton("Pokreni igru");
		JTextArea output = new JTextArea(15, 30);

		start.addActionListener(e -> {
			try {
				int px = Integer.parseInt(x.getText());
				int py = Integer.parseInt(y.getText());
				int hp = Integer.parseInt(health.getText());

				Collidable c = rect.isSelected() ? new RectangleCollider(px, py, 32, 32)
						: new CircleCollider(px, py, 16);

				Player player = new Player(name.getText(), hp, px, py, c);

				Game game = new Game(player);

				for (Enemy en : Game.loadEnemiesFromCSV("enemies.csv"))
					game.addEnemy(en);

				game.resolveCollisions();

				output.setText(player + "\n\nEnemies:\n");

				for (Enemy en : game.getEnemies())
					output.append(en + "\n");

				output.append("\nIn collision:\n");
				for (Enemy en : game.collidingWithPlayer())
					output.append(en + "\n");

				output.append("\nLOG:\n");
				for (String log : game.getLog())
					output.append(log + "\n");

				if (player.getHealth() <= 0)
					JOptionPane.showMessageDialog(this, "Player is DEAD!");

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
			}
		});

		JPanel panel = new JPanel(new java.awt.GridLayout(6, 2, 10, 10));

		panel.add(new JLabel("Ime"));
		panel.add(name);

		panel.add(new JLabel("Health (0-100)"));
		panel.add(health);

		panel.add(new JLabel("X pozicija"));
		panel.add(x);

		panel.add(new JLabel("Y pozicija"));
		panel.add(y);

		panel.add(new JLabel("Kolajder"));

		String[] options = { "Rectangle", "Circle" };
		JComboBox<String> combo = new JComboBox<>(options);
		panel.add(combo);

		panel.add(new JLabel(""));
		panel.add(start);

		add(panel, "North");
		add(new JScrollPane(output), "Center");

		setTitle("Simple Game");
		setSize(400, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new GameGUI();
	}
}

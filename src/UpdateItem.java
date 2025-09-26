import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UpdateItem extends JFrame {
    private JTextField searchField, nameField, quantityField, priceField;
    private JButton updateButton, cancelButton;

    public UpdateItem() {
        ImageIcon update = new ImageIcon("icons/update.png");

        setTitle("Update Inventory Item");
        setSize(600, 400); // Adjusted height to match AddItem.java
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);
        setIconImage(update.getImage());

        // ✅ Title Label
        JLabel titleLabel = new JLabel("Update Inventory Item", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        // ✅ Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Search Label and Field
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel searchLabel = new JLabel("Enter Product Name/ID:");
        searchLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(searchLabel, gbc);

        gbc.gridx = 1;
        searchField = new RoundedTextField(20);
        searchField.setPreferredSize(new Dimension(200, 30));
        formPanel.add(searchField, gbc);

        // New Name Label and Field
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel nameLabel = new JLabel("New Product Name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(nameLabel, gbc);

        gbc.gridx = 1;
        nameField = new RoundedTextField(20);
        nameField.setPreferredSize(new Dimension(200, 30));
        formPanel.add(nameField, gbc);

        // New Quantity Label and Field
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel quantityLabel = new JLabel("New Quantity:");
        quantityLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(quantityLabel, gbc);

        gbc.gridx = 1;
        quantityField = new RoundedTextField(20);
        quantityField.setPreferredSize(new Dimension(200, 30));
        formPanel.add(quantityField, gbc);

        // New Price Label and Field
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel priceLabel = new JLabel("New Price:");
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(priceLabel, gbc);

        gbc.gridx = 1;
        priceField = new RoundedTextField(20);
        priceField.setPreferredSize(new Dimension(200, 30));
        formPanel.add(priceField, gbc);

        add(formPanel, BorderLayout.CENTER);

        // ✅ Button Panel
        JPanel buttonPanel = new JPanel();
        updateButton = new JButton("Update");
        cancelButton = new JButton("Cancel");

        // Apply button styling
        updateButton.setFont(new Font("Arial", Font.BOLD, 14));
        updateButton.setBackground(new Color(0, 96, 192));
        updateButton.setForeground(Color.WHITE);

        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.setBackground(new Color(200, 0, 0));
        cancelButton.setForeground(Color.WHITE);

        buttonPanel.add(updateButton);
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // ✅ Button Actions
        updateButton.addActionListener(e -> updateItem());
        cancelButton.addActionListener(e -> {
            dispose(); // Close the window
            HomePage.getInstance().setVisible(true); // Reuse the existing HomePage instance
        });

        // ✅ Add Enter Key Navigation
        addEnterKeyNavigation();

        setVisible(true);
    }

    // ✅ Rounded TextField for improved UI
    private static class RoundedTextField extends JTextField {
        public RoundedTextField(int columns) {
            super(columns);
            setOpaque(false);
            setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Add padding
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw rounded background
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15); // Rounded corners

            super.paintComponent(g2);
            g2.dispose();
        }

        @Override
        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw rounded border
            g2.setColor(Color.GRAY);
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15); // Rounded corners

            g2.dispose();
        }
    }

    private void addEnterKeyNavigation() {
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    nameField.requestFocus();
                }
            }
        });

        nameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    quantityField.requestFocus();
                }
            }
        });

        quantityField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    priceField.requestFocus();
                }
            }
        });

        priceField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    updateItem();
                }
            }
        });
    }

    private void updateItem() {
        String searchTerm = searchField.getText().trim();
        String newName = nameField.getText().trim();
        String quantityText = quantityField.getText().trim();
        String priceText = priceField.getText().trim();

        // Validate inputs (same as previous code)
        if (searchTerm.isEmpty() || newName.isEmpty() || quantityText.isEmpty() || priceText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int newQuantity = Integer.parseInt(quantityText);
            double newPrice = Double.parseDouble(priceText);

            if (newQuantity < 0 || newPrice < 0) {
                throw new NumberFormatException();
            }

            boolean success = Database.updateFile(searchTerm, newName, newQuantity, newPrice);
            ImageIcon updated = new ImageIcon("icons/updated.png");

            if (success) {
                JOptionPane.showMessageDialog(this, "✅ Product updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE, updated);
                dispose();
                HomePage.getInstance().setVisible(true); // Reuse the existing HomePage instance
            } else {
                JOptionPane.showMessageDialog(this, "❌ Product not found or The name Already exists under a different product!", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid quantity or price!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UpdateItem::new);
    }
}
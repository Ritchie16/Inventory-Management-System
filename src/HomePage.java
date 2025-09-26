import org.jetbrains.annotations.NotNull;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame {
    private final RemoveItem removeItem; // Store RemoveItem instance
    private boolean splashScreenShown = false; // Flag to track if splash screen has been shown
    private static HomePage instance; // Singleton instance

    // Private constructor to enforce singleton pattern
    private HomePage() {
        ImageIcon ico = new ImageIcon("icons/inventory.png"); // Frame icon
        Database inventoryManager = new Database();
        removeItem = new RemoveItem(inventoryManager);

        // Set frame properties
        setTitle("Inventory Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setIconImage(ico.getImage()); // Set frame icon
        getContentPane().setBackground(new Color(200, 200, 200)); // Grey background

        // Show the splash screen only if it hasn't been shown before
        if (!splashScreenShown) {
            showSplashScreen();
            splashScreenShown = true; // Mark splash screen as shown
        } else {
            // If splash screen has already been shown, directly show the homepage
            showHomePage();
        }
    }

    // Singleton instance accessor
    public static HomePage getInstance() {
        if (instance == null) {
            instance = new HomePage();
        }
        return instance;
    }

    private void showSplashScreen() {
        // Create a panel to hold the splash content
        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(new Color(200, 200, 200)); // Grey background

        // Header panel with gradient background (Blue)
        JPanel headerPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, new Color(0, 102, 204), getWidth(), 0, new Color(0, 76, 153)); // Blue gradient
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        headerPanel.setPreferredSize(new Dimension(getWidth(), 100));

        // Title label
        JLabel titleLabel = new JLabel("Inventory Management System", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE); // White text
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));

        // Panel to hold the title
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setOpaque(false);
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        // Add title to the header panel
        headerPanel.add(titlePanel, BorderLayout.CENTER);

        // Load the splash image
        ImageIcon splashImage = new ImageIcon("icons/inventory.png");
        JLabel splashLabel = new JLabel(splashImage);
        splashLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Add the header and splash image to the content panel
        content.add(headerPanel, BorderLayout.NORTH);
        content.add(splashLabel, BorderLayout.CENTER);

        // Footer panel with gradient background (Blue)
        JPanel footerPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, new Color(0, 102, 204), getWidth(), 0, new Color(0, 76, 153));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        footerPanel.setPreferredSize(new Dimension(getWidth(), 60));

        JLabel footerLabel = new JLabel("Loading...", SwingConstants.CENTER);
        footerLabel.setForeground(Color.WHITE);
        footerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        footerPanel.add(footerLabel, BorderLayout.CENTER);

        // Add footer to the content panel
        content.add(footerPanel, BorderLayout.SOUTH);

        // Add the content panel to the frame
        add(content);
        setVisible(true);

        // After 2 seconds, show the homepage content
        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getContentPane().removeAll(); // Remove all components from the frame
                showHomePage(); // Show homepage content
                revalidate(); // Refresh the UI
                repaint(); // Repaint the frame
            }
        });
        timer.setRepeats(false); // Ensure the timer only runs once
        timer.start(); // Start the timer
    }

    private void showHomePage() {
        // Header panel with gradient background (Blue)
        JPanel headerPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, new Color(0, 102, 204), getWidth(), 0, new Color(0, 76, 153)); // Blue gradient
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        headerPanel.setPreferredSize(new Dimension(getWidth(), 100));

        // Title label
        JLabel titleLabel = new JLabel("Inventory Management System", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE); // White text
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));

        // Panel to hold the title
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setOpaque(false);
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        // Panel for search button below the title
        JPanel searchPanel = new JPanel();
        searchPanel.setOpaque(false);
        JButton searchButton = getSearchButton();
        searchPanel.add(searchButton);

        // Add title and search button below it
        headerPanel.add(titlePanel, BorderLayout.NORTH);
        headerPanel.add(searchPanel, BorderLayout.CENTER);

        // Left panel for buttons
        JPanel leftPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        leftPanel.setBackground(new Color(240, 240, 240)); // Light gray background
        leftPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String[] labels = {"Remove Item", "Add Item", "Update Item", "View Reports"};

        for (String label : labels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            button.setBackground(new Color(255, 255, 255)); // White background
            button.setForeground(new Color(51, 51, 51)); // Dark gray text
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200), 1), // Light gray border
                    BorderFactory.createEmptyBorder(10, 20, 10, 20)
            ));

            // Add hover effect
            button.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    button.setBackground(new Color(230, 230, 230)); // Light gray hover
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    button.setBackground(new Color(255, 255, 255)); // White background
                }
            });

            // Set icons for buttons
            ImageIcon remove = new ImageIcon("icons/remove.png");
            ImageIcon add = new ImageIcon("icons/addItem.png");
            ImageIcon update = new ImageIcon("icons/update.png");
            ImageIcon viewReport = new ImageIcon("icons/viewReport.png");

            switch (label) {
                case "View Reports":
                    button.setIcon(viewReport);
                    button.addActionListener(e -> ViewReport.showStockTables());
                    break;
                case "Remove Item":
                    button.setIcon(remove);
                    button.addActionListener(e -> removeItem.showRemoveDialog()); // Show RemoveItem dialog
                    break;
                case "Add Item":
                    button.setIcon(add);
                    button.addActionListener(e -> new AddItem());
                    break;
                case "Update Item":
                    button.setIcon(update);
                    button.addActionListener(e -> new UpdateItem());
                    break;
            }

            button.setHorizontalTextPosition(SwingConstants.RIGHT);
            button.setVerticalTextPosition(SwingConstants.CENTER);

            leftPanel.add(button);
        }

        // Footer panel with Exit button (Blue)
        JPanel footerPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, new Color(0, 102, 204), getWidth(), 0, new Color(0, 76, 153));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        footerPanel.setPreferredSize(new Dimension(getWidth(), 60));

        JLabel footerLabel = new JLabel("© 2025 Inventory App", SwingConstants.CENTER);
        footerLabel.setForeground(Color.WHITE);
        footerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        footerPanel.add(footerLabel, BorderLayout.CENTER);

        JButton exitButton = getExitButton();
        JPanel exitPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        exitPanel.setOpaque(false);
        exitPanel.add(exitButton);
        footerPanel.add(exitPanel, BorderLayout.EAST);

        // Add the inventory image to the center of the frame
        ImageIcon inventoryImage = new ImageIcon("icons/inventory.png");
        JLabel imageLabel = new JLabel(inventoryImage);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);

        // Add panels to frame
        add(headerPanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
        add(imageLabel, BorderLayout.CENTER); // Add the image to the center
        add(footerPanel, BorderLayout.SOUTH);
    }

    private @NotNull JButton getExitButton() {
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        exitButton.setForeground(Color.BLACK);
        exitButton.setBackground(new Color(255, 51, 51));
        exitButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        exitButton.setFocusPainted(false);

        exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitButton.setBackground(new Color(204, 0, 0));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitButton.setBackground(new Color(255, 51, 51));
            }
        });

        exitButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    HomePage.this,
                    "Are you sure you want to exit?",
                    "Exit Confirmation",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        return exitButton;
    }

    private @NotNull JButton getSearchButton() {
        ImageIcon search = new ImageIcon("icons/search.png");

        JButton searchButton = new JButton("Search Item", search);
        searchButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        searchButton.setPreferredSize(new Dimension(200, 50));
        searchButton.setBackground(new Color(0, 102, 204));
        searchButton.setForeground(Color.WHITE);
        searchButton.setBorder(BorderFactory.createLineBorder(new Color(0, 76, 153), 2));
        searchButton.setFocusPainted(false);

        searchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                searchButton.setBackground(new Color(0, 76, 153));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                searchButton.setBackground(new Color(0, 102, 204));
            }
        });

        searchButton.addActionListener(e -> openItemSearchApp());
        return searchButton;
    }

    private void openItemSearchApp() {
        SwingUtilities.invokeLater(ItemSearchApp::new);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> HomePage.getInstance().setVisible(true));
    }
}
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ViewReport {

    // Method to display the stock report window
    public static void showStockTables() {
        // Read inventory data from the database file
        ArrayList<List<String>> inventoryList = Database.readAllEntries();

        // Create lists to classify items into high stock and low stock categories
        List<Inventory> highStock = new ArrayList<>();
        List<Inventory> lowStock = new ArrayList<>();

        // Iterate through each inventory entry from the database
        for (List<String> item : inventoryList) {
            String id = item.get(0); // Get item ID
            String name = item.get(1); // Get item name
            int quantity = Integer.parseInt(item.get(2)); // Convert quantity to integer

            // Remove currency symbols (e.g., "K" and "each") and convert price to double
            double price = Double.parseDouble(item.get(3).replace("K", "").replace(" each", "").trim());

            // Create an Inventory object for the item
            Inventory inventoryItem = new Inventory(id, name, quantity, price);

            // Categorize item based on quantity
            if (quantity > 800) {
                highStock.add(inventoryItem); // High stock items
            } else {
                lowStock.add(inventoryItem); // Low stock items
            }
        }

        ImageIcon viewReport = new ImageIcon("icons/viewReport.png");
        // Create the stock report window
        JFrame frame = new JFrame("Stock Report");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null); // Center the window
        frame.setResizable(false);
        frame.setIconImage(viewReport.getImage());
        // Create a tabbed pane for high and low stock reports
        JTabbedPane tabbedPane = new JTabbedPane();

        // Create JTable components for both stock categories
        JTable highStockTable = createTable(highStock);
        JTable lowStockTable = createTable(lowStock);

        // Wrap tables inside scroll panes
        JScrollPane highStockScroll = new JScrollPane(highStockTable);
        JScrollPane lowStockScroll = new JScrollPane(lowStockTable);

        // Add tabs to the tabbed pane
        tabbedPane.addTab("High Stock Items", highStockScroll);
        tabbedPane.addTab("Low Stock Items", lowStockScroll);

        // Add tabbed pane to the frame
        frame.add(tabbedPane);
        frame.setVisible(true);
    }

    // Method to create a JTable with improved UI
    private static JTable createTable(List<Inventory> stockList) {
        String[] columnNames = {"ID", "Name", "Quantity", "Price"};
        Object[][] data = new Object[stockList.size()][4];

        for (int i = 0; i < stockList.size(); i++) {
            Inventory item = stockList.get(i);
            data[i][0] = item.getId();
            data[i][1] = item.getName();
            data[i][2] = item.getQuantity();
            data[i][3] = "K" + String.format("%.2f", item.getPrice()); // Format price
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);

        // 🔹 Improve table UI
        table.setFillsViewportHeight(true);
        table.setRowHeight(25); // Increase row height
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.setGridColor(Color.LIGHT_GRAY);

        return table;
    }
}

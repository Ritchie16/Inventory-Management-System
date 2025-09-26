import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class RemoveItem {
    private final JButton removeButton;
    private final Database inventoryManager;

    public RemoveItem(Database inventoryManager) {
        this.inventoryManager = inventoryManager;
        removeButton = new JButton("Remove Item");

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRemoveDialog(); // Open the remove dialog
            }
        });
    }

    // Method to display the remove dialog
    public void showRemoveDialog() {
        Icon remove = new ImageIcon("icons/remove.png");

        while (true) { // Loop to keep showing the dialog until valid input or cancel
            // First dialog: Ask for item ID or name
            String itemToRemove = JOptionPane.showInputDialog(
                    null, // Center the dialog on the screen
                    "Enter item ID or name to remove:",
                    "Remove Item",
                    JOptionPane.QUESTION_MESSAGE,remove,null,null
            ).toString();

            // If the user presses "Cancel", exit the loop and method
            if (itemToRemove == null) {
                System.out.println("Operation canceled.");
                break;
            }

            // Check if the input is empty or just whitespace
            if (itemToRemove.trim().isEmpty()) {
                // Show a warning if the input is empty
                JOptionPane.showMessageDialog(
                        null,
                        "Please enter an item ID or name. The input cannot be empty.",
                        "Invalid Input",
                        JOptionPane.WARNING_MESSAGE
                );
                // The loop will repeat, showing the input dialog again
            } else {
                // If the input is valid, proceed with the removal
                boolean isRemoved = Database.deleteItem(itemToRemove.trim());

                ImageIcon removed=new ImageIcon("icons/removed.png");


                if (isRemoved) {
                    JOptionPane.showMessageDialog(null,"Item removed successfully", "Item removed successfully!", JOptionPane.INFORMATION_MESSAGE,removed);
                } else {
                    JOptionPane.showMessageDialog(null, "Item not found!", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break; // Exit the loop after processing valid input
            }
        }
    }

    public JButton getRemoveButton() {
        return removeButton;
    }
}
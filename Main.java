
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class Main extends JFrame {

    private ArrayList<RealEstateProperty> properties = new ArrayList<>();

    private JTextField addressField = new JTextField();
    private JTextField priceField = new JTextField();
    private JTextField sizeField = new JTextField();
    private JTextField specialField = new JTextField();

    private JComboBox<String> typeBox =
            new JComboBox<>(new String[]{"House", "Condo"});

    private JComboBox<String> searchBox =
            new JComboBox<>(new String[]{
                "Address", "Price", "Size", "Floors", "Floor Number"
            });

    private JTextField searchField = new JTextField();
    private JTextArea output = new JTextArea();

    public Main() {
        setTitle("Real Estate Property Management System");
        setSize(650, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();

        JPanel home = new JPanel(new GridLayout(3, 1));
        home.add(new JLabel("Welcome to Real Estate Management!",
                SwingConstants.CENTER));
        home.add(new JLabel("Add and search for properties.",
                SwingConstants.CENTER));
        home.add(new JLabel("Houses and Condos",
                SwingConstants.CENTER));

        JPanel entry = new JPanel(new GridLayout(6, 2, 5, 5));

        entry.add(new JLabel("Property Type:"));
        entry.add(typeBox);
        entry.add(new JLabel("Address:"));
        entry.add(addressField);
        entry.add(new JLabel("Price:"));
        entry.add(priceField);
        entry.add(new JLabel("Size:"));
        entry.add(sizeField);
        entry.add(new JLabel("Floors / Floor Number:"));
        entry.add(specialField);

        JButton addButton = new JButton("Add Property");
        entry.add(addButton);

        addButton.addActionListener(e -> addProperty());

        JPanel searchPanel = new JPanel(new BorderLayout(5, 5));
        JPanel searchTop = new JPanel(new GridLayout(2, 2, 5, 5));

        searchTop.add(new JLabel("Search By:"));
        searchTop.add(searchBox);
        searchTop.add(new JLabel("Search Value:"));
        searchTop.add(searchField);

        JButton searchButton = new JButton("Search");
        searchPanel.add(searchTop, BorderLayout.NORTH);
        searchPanel.add(searchButton, BorderLayout.SOUTH);
        searchPanel.add(new JScrollPane(output), BorderLayout.CENTER);

        searchButton.addActionListener(e -> searchProperties());

        tabs.addTab("Home", home);
        tabs.addTab("Data Entry", entry);
        tabs.addTab("Search", searchPanel);

        add(tabs);
        setVisible(true);
    }

    private void addProperty() {
        try {
            String address = addressField.getText().trim();
            double price = Double.parseDouble(priceField.getText());
            double size = Double.parseDouble(sizeField.getText());
            int special = Integer.parseInt(specialField.getText());

            if (address.isEmpty() || price <= 0
                    || size <= 0 || special <= 0) {
                JOptionPane.showMessageDialog(this,
                        "Enter an address and positive numbers.");
                return;
            }

            RealEstateProperty property;

            if (typeBox.getSelectedItem().equals("House")) {
                property = new House(address, price, size, special);
            } else {
                property = new Condo(address, price, size, special);
            }

            properties.add(property);

            JOptionPane.showMessageDialog(this, "Property added!");

            addressField.setText("");
            priceField.setText("");
            sizeField.setText("");
            specialField.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter valid numbers.");
        }
    }

    private void searchProperties() {
        String criterion = (String) searchBox.getSelectedItem();
        String value = searchField.getText().trim();
        output.setText("");

        if (value.isEmpty()) {
            output.setText("Please enter a search value.");
            return;
        }

        boolean found = false;

        try {
            for (RealEstateProperty p : properties) {
                boolean match = false;

                switch (criterion) {
                    case "Address":
                        match = p.getAddress().toLowerCase()
                                .contains(value.toLowerCase());
                        break;
                    case "Price":
                        match = p.getPrice() == Double.parseDouble(value);
                        break;
                    case "Size":
                        match = p.getSize() == Double.parseDouble(value);
                        break;
                    case "Floors":
                        if (p instanceof House) {
                            match = ((House) p).getNumFloors()
                                    == Integer.parseInt(value);
                        }
                        break;
                    case "Floor Number":
                        if (p instanceof Condo) {
                            match = ((Condo) p).getFloorNumber()
                                    == Integer.parseInt(value);
                        }
                        break;
                }

                if (match) {
                    output.append(p.getPropertyInfo() + "\n\n");
                    found = true;
                }
            }
        } catch (NumberFormatException ex) {
            output.setText("Please enter a valid number for this search.");
            return;
        }

        if (!found) {
            output.setText("No matching properties found.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}
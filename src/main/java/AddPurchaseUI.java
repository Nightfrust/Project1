import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Calendar;
import java.util.Date;
public class AddPurchaseUI {
    public JFrame view;

    public JButton btnAdd = new JButton("Add");
    public JButton btnCancel = new JButton("Cancel");

    public JTextField txtPurchaseID = new JTextField(10);
    public JTextField txtCustomerID = new JTextField(10);
    public JTextField txtProductID = new JTextField(10);
    public JTextField txtQuantity = new JTextField(10);

    public JLabel labPrice = new JLabel("Product Price: ");
    public JLabel labDate = new JLabel("Date of Purchase: ");

    public JLabel labCustomerName = new JLabel("Customer Name: ");
    public JLabel labProductName = new JLabel("Product Name: ");

    public JLabel labCost = new JLabel("Cost: $0.00 ");
    public JLabel labTax = new JLabel("Tax: $0.00");
    public JLabel labTotalCost = new JLabel("Total Cost: $0.00");

    ProductModel product;
    CustomerModel customer;
    PurchaseModel purchase;
    int quantity = 0;
    double totalCost = 0;
    Date date = null;

    public AddPurchaseUI() {
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Add Purchase");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel line = new JPanel(new FlowLayout());
        line.add(new JLabel("PurchaseID "));
        line.add(txtPurchaseID);
        line.add(labDate);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(new JLabel("CustomerID "));
        line.add(txtCustomerID);
        line.add(labCustomerName);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(new JLabel("ProductID "));
        line.add(txtProductID);
        line.add(labProductName);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(new JLabel("Quantity "));
        line.add(txtQuantity);
        line.add(labPrice);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(labCost);
        line.add(labTax);
        line.add(labTotalCost);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(btnAdd);
        line.add(btnCancel);
        btnAdd.addActionListener(new AddButtonListener());
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                view.dispose();
            }
        });
        view.getContentPane().add(line);

        txtProductID.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {

            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                process();
            }

            private void process() {
                String s = txtProductID.getText();

                if (s.length() == 0) {
                    labProductName.setText("Product Name: [not specified!]");
                    return;
                }

                System.out.println("ProductID = " + s);

                int id;

                try {
                    id = Integer.parseInt(s);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Error: Invalid ProductID", "Error Message",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                product = StoreManager.getInstance().getDataAdapter().loadProduct(id);

                if (product == null) {
                    JOptionPane.showMessageDialog(null,
                            "Error: No product with id = " + id + " in store!", "Error Message",
                            JOptionPane.ERROR_MESSAGE);
                    labProductName.setText("Product Name: ");

                    return;
                }

                labProductName.setText("Product Name: " + product.mName);
                labPrice.setText("Product Price: " + product.mPrice);

            }

        });

        txtQuantity.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {

            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                process();
            }

            private void process() {
                String s = txtQuantity.getText();

                if (s.length() == 0) {
                    // labQ.setText("Quantity: [not specified!]");
                    return;
                }

                System.out.println("Quantity = " + s);

                try {
                    quantity = Integer.parseInt(s);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Error: Invalid Quantity", "Error Message",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (product != null) {
                    double cost = quantity * product.mPrice;
                    totalCost = (cost * .05) + cost;
                    labCost.setText("Cost: $" + cost);
                    labTax.setText("Tax: $" + (cost * .05));
                    labTotalCost.setText("Total Cost: $" + ((cost * .05) + cost));
                }
            }
        });

        txtCustomerID.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {

            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                process();
            }

            private void process() {
                String s = txtCustomerID.getText();

                if (s.length() == 0) {
                    labCustomerName.setText("Customer Name: [not specified!]");
                    return;
                }

                System.out.println("CustomerID = " + s);

                int id;

                try {
                    id = Integer.parseInt(s);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Error: Invalid CustomerID", "Error Message",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                customer = StoreManager.getInstance().getDataAdapter().loadCustomer(id);

                if (customer == null) {
                    JOptionPane.showMessageDialog(null,
                            "Error: No customer with id = " + id + " in store!", "Error Message",
                            JOptionPane.ERROR_MESSAGE);
                    labCustomerName.setText("Customer Name: ");

                    return;
                }

                labCustomerName.setText("Customer Name: " + customer.mName);
            }
        });
    }

    public void run() {
        date = Calendar.getInstance().getTime();
        labDate.setText("Date of purchase: " + date);
        view.setVisible(true);
    }

    class AddButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            purchase = new PurchaseModel();
            // reload customer, product, and quantity info to make sure purchase has up to date info
            customer = new CustomerModel();
            product = new ProductModel();

            String id = txtPurchaseID.getText();
            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "CustomerID cannot be null!");
                return;
            }
            try {
                purchase.mPurchaseID = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Purchase ID is invalid!");
                return;
            }

            String customerId = txtCustomerID.getText();
            if (customerId.length() == 0) {
                JOptionPane.showMessageDialog(null, "CustomerID cannot be null!");
                return;
            }
            try {
                customer.mCustomerID = Integer.parseInt(customerId);
                customer = StoreManager.getInstance().adapter.loadCustomer(customer.mCustomerID);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "CustomerID is invalid!");
                return;
            }
            if (customer == null) {
                JOptionPane.showMessageDialog(null,
                        "Error: No customer with id = " + customerId + " in store!", "Error Message",
                        JOptionPane.ERROR_MESSAGE);
                labProductName.setText("Customer Name: ");

                return;
            }

            String productId = txtProductID.getText();
            if (productId.length() == 0) {
                JOptionPane.showMessageDialog(null, "ProductID cannot be null!");
                return;
            }
            try {
                product.mProductID = Integer.parseInt(productId);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "CustomerID is invalid!");
                return;
            }
            product = StoreManager.getInstance().adapter.loadProduct(product.mProductID);
            if (product == null) {
                JOptionPane.showMessageDialog(null,
                        "Error: No product with id = " + productId + " in store!", "Error Message",
                        JOptionPane.ERROR_MESSAGE);
                labProductName.setText("Product Name: ");

                return;
            }


            String quantitySr = txtQuantity.getText();
            if (quantitySr.length() == 0) {
                JOptionPane.showMessageDialog(null, "Quantity cannot be null!");
                return;
            }
            try {
                quantity = Integer.parseInt(quantitySr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Quantity is invalid!");
                return;
            }
            if (quantity == 0) {
                JOptionPane.showMessageDialog(null, "Quantity is zero!");
                return;
            }
            if (quantity > product.mQuantity) {
                JOptionPane.showMessageDialog(null, "Quantity is more than the amount int stock!: " + (product.mQuantity));
                return;
            }

            purchase.mCustomerID = customer.mCustomerID;
            purchase.mProductID = product.mProductID;
            purchase.mQuantity = quantity;
            purchase.mPrice = product.mPrice + (product.mPrice * .05);
            purchase.mDate = date.toString();

            switch (StoreManager.getInstance().getDataAdapter().savePurchase(purchase)) {
                case SQLiteDataAdapter.PRODUCT_DUPLICATE_ERROR:
                    JOptionPane.showMessageDialog(null, "Purchase NOT added successfully! Duplicate purchase ID!");
                    return;
                default:
                    TXTReceiptBuilder receipt = new TXTReceiptBuilder();
                    receipt.appendHeader("Purchase added successfully!");
                    receipt.appendCustomer(customer);
                    receipt.appendProduct(product);
                    receipt.appendPurchase(purchase);
                    receipt.appendFooter("");
                    JOptionPane.showMessageDialog(null, receipt.sb);
                    view.dispose();
            }
        }
    }
}

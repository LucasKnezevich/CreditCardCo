import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Represents a Customer Search window.
 */
public class CustomerSearchDialog extends JFrame {

    /**
     * Full constructor.
     */
    public CustomerSearchDialog() {
        TreeMap<String,Customer> custMap = new TreeMap<>();
        addData(custMap,"custData.csv");


        setTitle("Customer Search");
        setSize(500, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation((size.width - getWidth())/2, (size.height - getHeight())/2);

        JPanel mainPanel = new JPanel();
        getContentPane().add(mainPanel);
        mainPanel.setLayout(null);


        JLabel ccLabel = new JLabel("Credit Card:");
        ccLabel.setBounds(25,25,100,40);
        mainPanel.add(ccLabel);

        JTextField ccField = new JTextField();
        ccField.setBounds(135,25,240,40);
        mainPanel.add(ccField);


        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(25,80,100,40);
        mainPanel.add(lastNameLabel);

        JTextField lastNameField = new JTextField();
        lastNameField.setBounds(135,80,240,40);
        lastNameField.setEditable(false);
        lastNameField.setBackground(Color.LIGHT_GRAY);
        mainPanel.add(lastNameField);


        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(25,140,100,40);
        mainPanel.add(firstNameLabel);

        JTextField firstNameField = new JTextField();
        firstNameField.setBounds(135,140,240,40);
        firstNameField.setEditable(false);
        firstNameField.setBackground(Color.LIGHT_GRAY);
        mainPanel.add(firstNameField);


        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(25,200,100,40);
        mainPanel.add(addressLabel);

        JTextField addressField = new JTextField();
        addressField.setBounds(135,200,240,40);
        addressField.setEditable(false);
        addressField.setBackground(Color.LIGHT_GRAY);
        mainPanel.add(addressField);


        JLabel cityLabel = new JLabel("City:");
        cityLabel.setBounds(25,260,100,40);
        mainPanel.add(cityLabel);

        JTextField cityField = new JTextField();
        cityField.setBounds(135,260,240,40);
        cityField.setEditable(false);
        cityField.setBackground(Color.LIGHT_GRAY);
        mainPanel.add(cityField);


        JLabel stateLabel = new JLabel("State:");
        stateLabel.setBounds(25,320,100,40);
        mainPanel.add(stateLabel);

        JTextField stateField = new JTextField();
        stateField.setBounds(135,320,240,40);
        stateField.setEditable(false);
        stateField.setBackground(Color.LIGHT_GRAY);
        mainPanel.add(stateField);


        JLabel zipCodeLabel = new JLabel("Zip Code:");
        zipCodeLabel.setBounds(25,380,100,40);
        mainPanel.add(zipCodeLabel);

        JTextField zipCodeField = new JTextField();
        zipCodeField.setBounds(135,380,240,40);
        zipCodeField.setEditable(false);
        zipCodeField.setBackground(Color.LIGHT_GRAY);
        mainPanel.add(zipCodeField);


        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(25,440,100,40);
        mainPanel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(135,440,240,40);
        emailField.setEditable(false);
        emailField.setBackground(Color.LIGHT_GRAY);
        mainPanel.add(emailField);


        JLabel balanceLabel = new JLabel("Balance:");
        balanceLabel.setBounds(25,500,100,40);
        mainPanel.add(balanceLabel);

        JTextField balanceField = new JTextField();
        balanceField.setBounds(135,500,240,40);
        balanceField.setEditable(false);
        balanceField.setBackground(Color.LIGHT_GRAY);
        mainPanel.add(balanceField);


        JButton submitBtn = new JButton("Search");
        submitBtn.setBounds(395,25,80,40);
        mainPanel.add(submitBtn);
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customer cust = custMap.get(ccField.getText());
                if (cust != null) {
                    lastNameField.setText(cust.lastName());
                    firstNameField.setText(cust.firstName());
                    addressField.setText(cust.address());
                    cityField.setText(cust.city());
                    stateField.setText(cust.state());
                    zipCodeField.setText(cust.postalCode());
                    emailField.setText(cust.email());
                    balanceField.setText("$" + cust.balance());
                } else {
                    lastNameField.setText("");
                    firstNameField.setText("");
                    addressField.setText("");
                    cityField.setText("");
                    stateField.setText("");
                    zipCodeField.setText("");
                    emailField.setText("");
                    balanceField.setText("");
                }
            }
        });

        setVisible(true);
    }

    /**
     * Retrieves customer data from a CSV file and stores data in a TreeMap.
     * @param tree      The TreeMap the will store customer data.
     * @param file      The input file containing customer data.
     */
    private void addData(TreeMap<String, Customer> tree, String file) {
        try {
            Scanner scan = new Scanner(new File(file));
            scan.nextLine();
            while(scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] data = line.split(",");
                Customer cust = new Customer(data[0],data[1],data[2],data[3],data[4],data[5],data[6],data[7],data[8]);
                tree.put(data[0],cust);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Sorry, \"" + file + "\" could not be found.");
            JOptionPane.showMessageDialog(null,
                    "Data file \"" + file + "\" not found.\nExiting program.","Data Error",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

}

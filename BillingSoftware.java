import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BillingSoftware extends JFrame implements ActionListener, Printable {
    private JTextField nameField, mobileField, jobField;
    private JSpinner dateSpinner;
    private JCheckBox cinematicVideo, traditionalVideo, album;
    private JButton printButton;

    public BillingSoftware() {
        // Frame setup
        setTitle("The Magical Album & Films");
        setSize(800, 600); // Larger frame size for better usability
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding between components
        
        // Help Desk Information
        JPanel helpDeskPanel = new JPanel();
        helpDeskPanel.setBorder(BorderFactory.createTitledBorder("Help Desk"));
        helpDeskPanel.setLayout(new GridLayout(1, 2)); // Adjusted for two rows
        helpDeskPanel.add(new JLabel("Mobile: 9511904315"));
        helpDeskPanel.add(new JLabel("Email: jadhavajinkya95@yahoo.com"));

        // Input fields
        nameField = new JTextField(20);
        mobileField = new JTextField(20);
        jobField = new JTextField(20);

        // Date picker using JSpinner
        dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(dateEditor);
        dateSpinner.setValue(new Date());

        // Checkboxes
        cinematicVideo = new JCheckBox("Cinematic Video");
        traditionalVideo = new JCheckBox("Traditional Video");
        album = new JCheckBox("Album");

        // Print button
        printButton = new JButton("Print");
        printButton.addActionListener(this);

        // Adding components to the frame with GridBagLayout
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST; add(new JLabel("Name:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST; add(nameField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST; add(new JLabel("Date:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST; add(dateSpinner, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.EAST; add(new JLabel("Mobile No:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST; add(mobileField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.anchor = GridBagConstraints.EAST; add(new JLabel("Job No:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST; add(jobField, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.anchor = GridBagConstraints.EAST; add(new JLabel("Services:"), gbc);
        gbc.gridx = 1; add(new JPanel());  // Empty placeholder
        
        gbc.gridx = 0; gbc.gridy = 5; gbc.anchor = GridBagConstraints.WEST; add(cinematicVideo, gbc);
        gbc.gridy = 6; add(traditionalVideo, gbc);
        gbc.gridy = 7; add(album, gbc);

        gbc.gridx = 0; gbc.gridy = 8; gbc.gridwidth = 2; add(helpDeskPanel, gbc);
        gbc.gridx = 0; gbc.gridy = 9; gbc.gridwidth = 2; add(printButton, gbc);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == printButton) {
            String name = nameField.getText().trim();
            if (!name.isEmpty()) {
                // Save the printed output to a text file in the background
                saveInvoice(name);
                // Print the invoice
                PrinterJob printerJob = PrinterJob.getPrinterJob();
                printerJob.setPrintable(this);
                if (printerJob.printDialog()) {
                    try {
                        printerJob.print();
                    } catch (PrinterException ex) {
                        ex.printStackTrace();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a name.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveInvoice(String name) {
        String fileName = name + "_invoice.txt"; // Save as text file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("The Magical Album & Films\n");
            writer.write("Shop No. A-24, Motiwala Trade Center, Opp. HDFC Bank,\n");
            writer.write("Nirala Bazar, Aurangabad - 431001\n\n");
            writer.write("Name: " + name + "\n");
            writer.write("Date: " + new SimpleDateFormat("dd/MM/yyyy").format(dateSpinner.getValue()) + "\n");
            writer.write("Mobile No: " + mobileField.getText() + "\n");
            writer.write("Job No: " + jobField.getText() + "\n\n");
            writer.write("Services:\n");
            if (cinematicVideo.isSelected()) {
                writer.write("- Cinematic Video\n");
            }
            if (traditionalVideo.isSelected()) {
                writer.write("- Traditional Video\n");
            }
            if (album.isSelected()) {
                writer.write("- Album\n");
            }
            writer.write("\nTerms & Conditions:\n");
            writer.write("1. After job delivery, cinematic correction will be done within 5 days.\n");
            writer.write("2. After job delivery, traditional correction will be done within 5 days.\n");
            writer.write("3. After job delivery, photo album correction will be done within 5 days.\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
        if (page > 0) return NO_SUCH_PAGE;

        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        // Set margins for A4 size paper
        int margin = 50;
        int width = (int) pf.getImageableWidth();

        // Set fonts and styles
        Font boldFont = new Font("Serif", Font.BOLD, 18);
        Font normalFont = new Font("Serif", Font.PLAIN, 12);

        // Header and shop details
        g2d.setFont(boldFont);
        g.drawString("The Magical Album & Films", width / 2 - 100, margin); // Center the header

        g2d.setFont(normalFont);
        g.drawString("Shop No. A-24, Motiwala Trade Center, Opp. HDFC Bank,", 50, margin + 30);
        g.drawString("Nirala Bazar, Aurangabad - 431001", 50, margin + 45);
        
        // Add contact numbers
        g.drawString("Contact: 7066227027, 8408832690", 50, margin + 60); // Added contact numbers

        g.drawLine(margin, margin + 70, width - margin, margin + 70); // Draw a line below the header

        // User input fields
        g2d.setFont(normalFont);
        int yPosition = margin + 90;
        g.drawString("Name: " + nameField.getText(), 50, yPosition);
        g.drawString("Date: " + new SimpleDateFormat("dd/MM/yyyy").format(dateSpinner.getValue()), width - 250, yPosition);

        yPosition += 20;
        g.drawString("Mobile No: " + mobileField.getText(), 50, yPosition);
        g.drawString("Job No: " + jobField.getText(), width - 250, yPosition);

        // Services
        yPosition += 30;
        g.drawString("Services:", 50, yPosition);
        yPosition += 20;

        if (cinematicVideo.isSelected()) {
            g.drawString("- Cinematic Video", 50, yPosition);
            yPosition += 20;
        }
        if (traditionalVideo.isSelected()) {
            g.drawString("- Traditional Video", 50, yPosition);
            yPosition += 20;
        }
        if (album.isSelected()) {
            g.drawString("- Album", 50, yPosition);
            yPosition += 20;
        }

        // Terms and conditions
        yPosition += 20;
        g.drawString("Terms & Conditions:", 50, yPosition);
        yPosition += 20;
        g.drawString("1. After job delivery, cinematic correction will be done within 5 days.", 50, yPosition);
        yPosition += 20;
        g.drawString("2. After job delivery, traditional correction will be done within 5 days.", 50, yPosition);
        yPosition += 20;
        g.drawString("3. After job delivery, photo album correction will be done within 5 days.", 50, yPosition);

        return PAGE_EXISTS;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BillingSoftware::new);
    }
}

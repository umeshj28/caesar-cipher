import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EncryptionDecryption extends JFrame {
	 private JTextField inputField;   
	 private JTextField keyField;     
	 private JTextArea outputArea;    
	 private JRadioButton encryptRadio;   
	 private JRadioButton decryptRadio;  
	 private ButtonGroup radioGroup;  

	 
    public EncryptionDecryption() {
        setTitle("Encryption/Decryption");
        setSize(600, 300);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
     
       
        JPanel inputPanel = new JPanel();
        inputField = new JTextField(20);
        keyField = new JTextField(20);
       
        inputPanel.add(new JLabel("Input: "));
        inputPanel.add(inputField);
        inputPanel.add(new JLabel("Key: "));
        inputPanel.add(keyField);
        add(inputPanel, BorderLayout.NORTH);

      
        JPanel radioPanel = new JPanel();
        encryptRadio = new JRadioButton("Encrypt");
        decryptRadio = new JRadioButton("Decrypt");
        radioGroup = new ButtonGroup();
        radioGroup.add(encryptRadio);
        radioGroup.add(decryptRadio);
        radioPanel.add(encryptRadio);
        radioPanel.add(decryptRadio);
        add(radioPanel, BorderLayout.WEST);

       
        outputArea = new JTextArea();
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

      
        JButton performOperationButton = new JButton("Perform Selected Operation" );
        performOperationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText();
                int key = Integer.parseInt(keyField.getText());
                String output = "";
                if (key < 1 || key > 25) {
                    JOptionPane.showMessageDialog(null, "Key must be between 1 and 25", "Invalid Key", JOptionPane.ERROR_MESSAGE);
                } else if (encryptRadio.isSelected()) {
                    output = encrypt(input, key);
               
                } else if (decryptRadio.isSelected()) {
                    output = decrypt(input, key);
                }
                outputArea.setText(output);
            }
        });
        add(performOperationButton, BorderLayout.SOUTH);
    }

  //Encrypt method
    private String encrypt(String input, int key) {
        String output = "";
 
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            
            if (Character.isLetter(c)) {
                c = (char)(c + key);
                if (Character.isUpperCase(input.charAt(i)) && c > 'Z' ||
                    Character.isLowerCase(input.charAt(i)) && c > 'z') {
                    c = (char)(c - 26);
                }
            }
            output += c;
        }
        return output;
    }
  
  // Decrypt method
    private String decrypt(String input, int key) {
        return encrypt(input, 26 - key);
    }
  //Main method creates an instance of the EncryptionDecryption class and sets it to be visible
    public static void main(String[] args) {
        EncryptionDecryption ed = new EncryptionDecryption();
        ed.setVisible(true);
    }
}

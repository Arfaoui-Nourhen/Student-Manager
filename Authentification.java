import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

public class Authentification extends JDialog {
    public JTextField adressemailField = new JTextField();
    public JPasswordField passwordField = new JPasswordField();
     String password;
     String adressemail;
     int count =0;
    JButton ok = new JButton("OK");
    JButton annuler = new JButton("Annuler");
    JPanel s=new JPanel(new GridLayout(0, 2));

    public Authentification(JFrame parent) {
        super(parent);
        s.add(new JLabel("adress"));
        s.add(adressemailField);
        s.add(new JLabel("password"));
        s.add(passwordField);
        s.add(ok);
        s.add(annuler);
        setContentPane(s);

ok.addActionListener(new ActionListener(){
     public void actionPerformed(ActionEvent e) {
 
                dispose();
            
          
        
    }});
    annuler.addActionListener(new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e) {
             System.exit(0);
            
        }

    });
       
        setTitle("authentification");
        setSize(100,100);
        pack();
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);


    };

   
   
}
import java.awt.*;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;


import java.awt.event.*;


class ModifierEtudiantDialog extends JDialog {
    public JTextField tfName = new JTextField();
    public JTextField tfPrenom = new JTextField();
    public JTextField tfEmail = new JTextField();
    public JTextField tfPhone = new JTextField();
    private JTextField tfAdresse = new JTextField();
    private JTextField tfdNumFix = new JTextField();
   
    private JTextField tfNiveau = new JTextField();

    public String Nom_m;
    public String Email_m;
    public String Prenom_m;
    public String Niveau_m;
   
    public String numPortable_m;
    public String numFix_m;
    public String Adresse_m;



    public ModifierEtudiantDialog(JTree ar, JFrame parent, DefaultMutableTreeNode i, DefaultTreeModel treeModel,
            DefaultMutableTreeNode racine,DefaultMutableTreeNode father) {
        super(parent);
        setTitle("Modifier Etudiant");

        JButton btnOk = new JButton("OK");
        JButton btnCancel = new JButton("Cancel");

        JPanel addEmployeePanel = new JPanel(new GridLayout(0, 2, 5, 5));
        addEmployeePanel.add(new JLabel("Name"));
        addEmployeePanel.add(tfName);
        addEmployeePanel.add(new JLabel("Email"));
        addEmployeePanel.add(tfEmail);
        addEmployeePanel.add(new JLabel("Prenom"));
        addEmployeePanel.add(tfPrenom);
        addEmployeePanel.add(new JLabel("Phone"));
        addEmployeePanel.add(tfPhone);

        addEmployeePanel.add(new JLabel("Adresse"));
        addEmployeePanel.add(tfAdresse);
        addEmployeePanel.add(new JLabel("numero fixe"));
        addEmployeePanel.add(tfdNumFix);
     
        addEmployeePanel.add(new JLabel("niveau"));
        addEmployeePanel.add(tfNiveau);

        addEmployeePanel.add(btnOk);
        addEmployeePanel.add(btnCancel);
        setContentPane(addEmployeePanel);
        Etudiant oldEtudiant=(Etudiant)i.getUserObject();
        tfName.setText(oldEtudiant.Nom);
        tfPrenom.setText(oldEtudiant.Prenom);
        tfEmail.setText(oldEtudiant.Email);
        tfPhone.setText(oldEtudiant.numPortable);
        tfAdresse.setText(oldEtudiant.Adresse);
    
        tfNiveau.setText(oldEtudiant.Niveau);
        tfdNumFix.setText(oldEtudiant.numFix);

        // click ok
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Nom_m = tfName.getText();
                Prenom_m = tfPrenom.getText();
                Email_m = tfEmail.getText();
                numPortable_m = tfPhone.getText();
                Adresse_m = tfAdresse.getText();
            
                Niveau_m = tfNiveau.getText();
                numFix_m = tfdNumFix.getText();
              
                Etudiant newEtudiant=new Etudiant(Nom_m, Email_m, Prenom_m, Niveau_m, oldEtudiant.Classe,
                numPortable_m, numFix_m, Adresse_m);
                 
                DefaultMutableTreeNode n2 = new DefaultMutableTreeNode(newEtudiant);


                int rowIndex = father.getIndex(i);
                father.remove(i);
                father.insert(n2, rowIndex);
                treeModel.reload();
          
                dispose();
            }
        });

        // click cancel
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        pack();
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

   


}

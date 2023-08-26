import java.awt.*;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

// class extends JFrame
class GestionEtudiants extends JFrame {

    // vars globales
    public Notes nte;
    // creation du fields du jformulaire
    JTextField fieldEmail = new JTextField();
    JTextField fieldPrenom = new JTextField();
    JTextField fieldNom = new JTextField();
    JTextField fieldAdresse = new JTextField();
    JTextField fieldNumP = new JTextField();
    JTextField fieldNumFix = new JTextField();
    JTextField fieldClassS = new JTextField();
    JTextField fieldNiveau = new JTextField();

    JMenuBar menuBar = new JMenuBar();
    JMenu Menu = new JMenu("File");
    JMenuItem ItemOuvrir = new JMenuItem("Ouvrir", 'N');
    JMenuItem ItemEregister = new JMenuItem("Enregistrer", 'E');
    JMenuItem ItemQuitter = new JMenuItem("Quitter", 'Q');

    JPanel jpFormButton = new JPanel();
    JPanel jpArbreInfo = new JPanel();
    JPanel jpArbre = new JPanel();
    JPanel jpInfo = new JPanel();
    JPanel jpButton = new JPanel();
    JPanel jpFormulaire = new JPanel();
    JPanel jpButtons2 = new JPanel();

    DefaultMutableTreeNode racine = new DefaultMutableTreeNode("FSM");
    JTree myArbre = new JTree(racine);
    DefaultTreeModel treeModel = (DefaultTreeModel) myArbre.getModel();

    String[] columnNames = { "Prenom", "Nom", "Telephone" };
    String[] columnNamesClass = { "class", "numbre Etudiants" };
    Object[][] tableData;
    Object[][] tableDataClass;
    DefaultTableModel dtm = new DefaultTableModel(tableData, columnNames);
    DefaultTableModel dtmClass = new DefaultTableModel(tableDataClass, columnNamesClass);
    JTable tableEtudiants = new JTable(dtm);
    JTable tableEtudiantsClass = new JTable(dtmClass);
    JScrollPane tableScrollPaneEtudiantInfo = new JScrollPane(tableEtudiants);
    JScrollPane tableScrollPaneclassNumEtudiant = new JScrollPane(tableEtudiantsClass);

    JTabbedPane tabbedPane = new JTabbedPane();
    JPanel jpTabEtudiant = new JPanel(new BorderLayout(30, 30));
    JPanel jpTabNotes = new JPanel(new BorderLayout());
    JPanel jpTabNotesNord = new JPanel(new GridLayout(0, 4, 20, 20));
    JPanel jpTabNotesSud = new JPanel(new GridLayout(1, 6));

    GestionEtudiants() {

        // creation du button (ok) et (annuler)
        JButton okButton = new JButton("ok");
        JButton annulerButton = new JButton("annuler");
        JButton supprimerButton = new JButton("supprimer");
        JButton modifierButton = new JButton("modifier");
        JPanel supp_mod = new JPanel(new GridLayout(0, 2));

        // creation buttons ajouter et vider et annuler
        JButton ajoutButton = new JButton("Ajouter");
        JButton annulButton = new JButton("annuler");
        JButton viderButton = new JButton("vider");
        GestionnaireExamens gex = new GestionnaireExamens();

        // creation menu bar
        Menu.add(ItemEregister);
        Menu.add(ItemOuvrir);
        Menu.add(ItemQuitter);
        menuBar.add(Menu);
        setJMenuBar(menuBar);

        // add panel note et etudiant

        // paramtres de layout des sous-jpFormButton
        jpFormButton.setLayout(new BorderLayout(10, 10));

        // paramtres de layout des sous-jpFormButton
        jpFormulaire.setLayout(new GridLayout(0, 4));
        jpArbreInfo.setLayout(new GridLayout(0, 2));
        jpButton.setLayout(new GridLayout(0, 4));

        // creation du labels du formulaire
        JLabel email = new JLabel("Email*");
        JLabel prenom = new JLabel("Prénom*");
        JLabel nom = new JLabel("Nom*");
        JLabel adresse = new JLabel("Adresse*");
        JLabel numPortable = new JLabel("numéero potable*");
        JLabel numFix = new JLabel("numéero fix");
        JLabel classe = new JLabel("classe*");
        JLabel niveau = new JLabel("niveau*");
        JLabel champObligatoire = new JLabel("champ  oligatoire*");

        // creation du panel dans jptabnotesNorth

        // add les composants du jformulaire
        jpFormulaire.add(email);
        jpFormulaire.add(fieldEmail);
        jpFormulaire.add(prenom);
        jpFormulaire.add(fieldPrenom);
        jpFormulaire.add(nom);
        jpFormulaire.add(fieldNom);
        jpFormulaire.add(adresse);
        jpFormulaire.add(fieldAdresse);
        jpFormulaire.add(numPortable);
        jpFormulaire.add(fieldNumP);
        jpFormulaire.add(numFix);
        jpFormulaire.add(fieldNumFix);
        jpFormulaire.add(classe);
        jpFormulaire.add(fieldClassS);
        jpFormulaire.add(niveau);
        jpFormulaire.add(fieldNiveau);
        jpFormulaire.add(champObligatoire);

        // ceate a scolling table
        JScrollPane treePane = new JScrollPane(myArbre);

        // add les composants dy jbutton
        // empty label
        jpButton.add(new JLabel());

        jpButton.add(annulerButton);
        jpButton.add(okButton);
        // empty label
        jpButton.add(new JLabel());

        // add les composants du jparbre
        jpArbre.setLayout(new BorderLayout());
        jpArbre.add(treePane, BorderLayout.CENTER);

        // creation du groupe (actualiser) et (enregistrer)
        JButton actualiserButton = new JButton("Actualiser");
        JButton enregistrerButton = new JButton("Enregistrer");

        // add les composants sous-panel du jpFormButton
        jpButtons2.setLayout(new GridLayout(0, 2));
        jpButtons2.add(actualiserButton);
        jpButtons2.add(enregistrerButton);
        jpArbre.add(jpButtons2, BorderLayout.SOUTH);
        jpFormButton.add(jpFormulaire, BorderLayout.NORTH);
        jpFormButton.add(jpButton, BorderLayout.SOUTH);
        jpArbreInfo.add(jpArbre);
        jpArbreInfo.add(jpInfo);

        // creation du jinfo pour l'affichage d'informations
        JLabel email1 = new JLabel("Email :");
        JLabel getTheEmail1 = new JLabel();
        JLabel prenom1 = new JLabel("Prénom :");
        JLabel getThePrenom1 = new JLabel();
        JLabel nom1 = new JLabel("Nom :");
        JLabel getTheNom1 = new JLabel();
        JLabel adresse1 = new JLabel("Adresse :");
        JLabel getTheAdresse1 = new JLabel();
        JLabel numPortable1 = new JLabel("numéero potable :");
        JLabel getTheNumPortable1 = new JLabel();
        JLabel numFix1 = new JLabel("numéero fix :");
        JLabel getTheNumFix1 = new JLabel();
        JLabel classeS1 = new JLabel("classe :");
        JLabel getTheClasseS1 = new JLabel();
        JLabel niveau1 = new JLabel("niveau :");
        JLabel getTheNiveau1 = new JLabel();

        // parametres du panel jinfo
        jpInfo.setLayout(new GridLayout(0, 2));

        // ajouter les composantsdu jinfo
        jpInfo.add(email1);
        jpInfo.add(getTheEmail1);
      
        jpInfo.add(nom1);
        jpInfo.add(getTheNom1);

         jpInfo.add(prenom1);
        jpInfo.add(getThePrenom1);
        
        jpInfo.add(adresse1);
        jpInfo.add(getTheAdresse1);
        jpInfo.add(numPortable1);
        jpInfo.add(getTheNumPortable1);
        jpInfo.add(numFix1);
        jpInfo.add(getTheNumFix1);
        jpInfo.add(classeS1);
        jpInfo.add(getTheClasseS1);
        jpInfo.add(niveau1);
        jpInfo.add(getTheNiveau1);
        jpInfo.setBorder(BorderFactory.createEmptyBorder(5, 10, 300, 0));
        supp_mod.add(supprimerButton);
        supp_mod.add(modifierButton);
        jpInfo.add(supp_mod);

        // jptabnotes add components
        jpTabNotes.add(jpTabNotesNord, BorderLayout.NORTH);
        jpTabNotes.add(jpTabNotesSud, BorderLayout.SOUTH);

        // jptabnotessud add components
        jpTabNotesSud.add(ajoutButton);
        jpTabNotesSud.add(viderButton);
        jpTabNotesSud.add(annulButton);
        jpTabNotesSud.add(new JLabel());
        jpTabNotesSud.add(new JLabel());
        jpTabNotesSud.add(new JLabel());

        // jptabnotesNorth add components
        JLabel mail = new JLabel("Email :");
        JLabel full_name = new JLabel("Nom et Prénom :");
        JLabel getTheFirstName = new JLabel();
        JLabel getTheLastName = new JLabel();
        JLabel getTheMail = new JLabel();
        JLabel securite = new JLabel("Sécurité :");
        JTextField getTheSecurity = new JTextField();
        JLabel java = new JLabel("Java :");
        JTextField getTheJava = new JTextField();
        JLabel reseau = new JLabel("Réseau :");
        JTextField getTheReseau = new JTextField();
        JLabel php = new JLabel("PHP :");
        JTextField getThePhp = new JTextField();
        JLabel android = new JLabel("Andoid :");
        JTextField getTheAndroid = new JTextField();
        JLabel sgbd = new JLabel("SGBD :");
        JTextField getTheSgbd = new JTextField();
        JLabel math = new JLabel("Math :");
        JTextField getTheMath = new JTextField();
        JLabel francais = new JLabel("Français :");
        JTextField getTheFrancais = new JTextField();
        JLabel anglais = new JLabel("Anglais :");
        JTextField getTheAnglais = new JTextField();
        JLabel pfe = new JLabel("PFE :");
        JTextField getThePfe = new JTextField();

        jpTabNotesNord.add(full_name);
        jpTabNotesNord.add(getTheFirstName);
        jpTabNotesNord.add(getTheLastName);
        jpTabNotesNord.add(new JLabel());
        jpTabNotesNord.add(mail);
        jpTabNotesNord.add(getTheMail);
        jpTabNotesNord.add(new JLabel());
        jpTabNotesNord.add(new JLabel());
        jpTabNotesNord.add(securite);
        jpTabNotesNord.add(getTheSecurity);
        jpTabNotesNord.add(new JLabel());
        jpTabNotesNord.add(new JLabel());
        jpTabNotesNord.add(java);
        jpTabNotesNord.add(getTheJava);
        jpTabNotesNord.add(new JLabel());
        jpTabNotesNord.add(new JLabel());
        jpTabNotesNord.add(reseau);
        jpTabNotesNord.add(getTheReseau);
        jpTabNotesNord.add(new JLabel());
        jpTabNotesNord.add(new JLabel());
        jpTabNotesNord.add(php);
        jpTabNotesNord.add(getThePhp);
        jpTabNotesNord.add(new JLabel());
        jpTabNotesNord.add(new JLabel());
        jpTabNotesNord.add(android);
        jpTabNotesNord.add(getTheAndroid);
        jpTabNotesNord.add(new JLabel());
        jpTabNotesNord.add(new JLabel());
        jpTabNotesNord.add(sgbd);
        jpTabNotesNord.add(getTheSgbd);
        jpTabNotesNord.add(new JLabel());
        jpTabNotesNord.add(new JLabel());
        jpTabNotesNord.add(math);
        jpTabNotesNord.add(getTheMath);
        jpTabNotesNord.add(new JLabel());
        jpTabNotesNord.add(new JLabel());
        jpTabNotesNord.add(francais);
        jpTabNotesNord.add(getTheFrancais);
        jpTabNotesNord.add(new JLabel());
        jpTabNotesNord.add(new JLabel());
        jpTabNotesNord.add(anglais);
        jpTabNotesNord.add(getTheAnglais);
        jpTabNotesNord.add(new JLabel());
        jpTabNotesNord.add(new JLabel());
        jpTabNotesNord.add(pfe);
        jpTabNotesNord.add(getThePfe);
        jpTabNotesNord.add(new JLabel());
        jpTabNotesNord.add(new JLabel());

        // jpTabNotesNord.add(pre_nom);
        ajoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("sauvgarder");
                    gex.sauvegarderNotesEtudiant(getTheMail.getText(),
                            getTheLastName.getText() + " " + getTheFirstName.getText(),
                            getTheSecurity.getText(), getTheReseau.getText(), getTheJava.getText(),
                            getThePhp.getText(), getTheAndroid.getText(), getTheSgbd.getText(), getTheMath.getText(),
                            getTheFrancais.getText(), getTheAnglais.getText(), getThePfe.getText());
                } catch (SQLException e1) {

                    e1.printStackTrace();
                }
            }

        });

        // click button ok
        okButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String lastname = fieldNom.getText();
                String firstname = fieldPrenom.getText();
                String mail = fieldEmail.getText();
                String adr = fieldAdresse.getText();
                String niv = fieldNiveau.getText();
                String numpor = fieldNumP.getText();
                String cla = fieldClassS.getText();
                String numfixe = fieldNumFix.getText();
                if (lastname.length() <= 0 || mail.length() <= 0 || adr.length() <= 0 || firstname.length() <= 0 ||
                        numpor.length() <= 0 || niv.length() <= 0 || cla.length() <= 0) {
                    JOptionPane.showMessageDialog(null, " check required fields .they can't be empty");
                    return;
                }
                ;

                boolean isFound = false;
                boolean isEtudiantFound = false;
                for (int i = 0; i < racine.getChildCount(); i++) {
                    DefaultMutableTreeNode classeChild = (DefaultMutableTreeNode) racine.getChildAt(i);
                    if (cla.equals(classeChild.toString())) {

                        isFound = true;
                        for (int j = 0; j < classeChild.getChildCount(); j++) {
                            DefaultMutableTreeNode etudiantChild = (DefaultMutableTreeNode) classeChild.getChildAt(j);
                            Etudiant ancienEtudiantObject = (Etudiant) etudiantChild.getUserObject();

                            if (mail.equals(ancienEtudiantObject.Email)) {
                                JOptionPane.showMessageDialog(null, "Email already exists");
                                isEtudiantFound = true;
                                break;
                            }
                            ;
                        }

                        if (isEtudiantFound == false) {
                            Etudiant addedStudent = new Etudiant(lastname, mail, firstname, niv, cla, numpor, numfixe,
                                    adr);
                            DefaultMutableTreeNode newEtudiant = new DefaultMutableTreeNode(addedStudent);
                            classeChild.add(newEtudiant);
                            treeModel.reload();

                        }

                    }
                }
                ;

                if (isFound == false) {
                    DefaultMutableTreeNode newClasse = new DefaultMutableTreeNode(cla);
                    Etudiant addedStudent = new Etudiant(lastname, mail, firstname, niv, cla, numpor, numfixe, adr);

                    DefaultMutableTreeNode newEtudiant = new DefaultMutableTreeNode(addedStudent);
                    newClasse.add(newEtudiant);
                    racine.add(newClasse);
                    treeModel.reload();

                }

            }
        });
        // modifier button
        modifierButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultMutableTreeNode nodeE = (DefaultMutableTreeNode) myArbre.getLastSelectedPathComponent();

                if (nodeE != null && nodeE.isLeaf()) {
                    DefaultMutableTreeNode father = (DefaultMutableTreeNode) nodeE.getParent();

                    new ModifierEtudiantDialog(myArbre, null, nodeE, treeModel,
                            racine, father);
                }
            }

        });
        supprimerButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                DefaultMutableTreeNode s = (DefaultMutableTreeNode) myArbre.getLastSelectedPathComponent();
                if (s != null && s.isLeaf()) {
                    DefaultMutableTreeNode parent = (DefaultMutableTreeNode) s.getParent();
                    parent.remove(s);
                    treeModel.reload();

                }

            }

        });

        // click annuler button
        ActionListener Annulation = new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                getTheEmail1.setText(null);
                getTheNom1.setText(null);
                getThePrenom1.setText(null);
                getTheAdresse1.setText(null);
                getTheNumPortable1.setText(null);
                getTheClasseS1.setText(null);
                getTheNiveau1.setText(null);
                getTheAdresse1.setText(null);
                getTheNumFix1.setText(null);
                fieldEmail.setText(null);
                fieldNom.setText(null);
                fieldPrenom.setText(null);
                fieldAdresse.setText(null);
                fieldNumP.setText(null);
                fieldClassS.setText(null);
                fieldNiveau.setText(null);
                fieldAdresse.setText(null);
                fieldNumFix.setText(null);
            }
        };
        annulerButton.addActionListener(Annulation);
        // click actualiser button
        actualiserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.print("button actualiser");

                System.out.println(racine.toString() + " :"); // texte de la racine

                for (int i = 0; i < myArbre.getRowCount(); i++) {
                    myArbre.expandRow(i);
                }
                for (int i = 0; i < racine.getChildCount(); i++) {
                    DefaultMutableTreeNode NodeRacineClasse = (DefaultMutableTreeNode) racine.getChildAt(i);
                    System.out.println(" - " + NodeRacineClasse.toString());
                    for (int j = 0; j < NodeRacineClasse.getChildCount(); j++) {
                        DefaultMutableTreeNode NodeRacineEtudiant = (DefaultMutableTreeNode) NodeRacineClasse
                                .getChildAt(j);
                        System.out.println(" * " + NodeRacineEtudiant.toString());
                    }
                }
                ;

            }
        });

        // click enregistrer button
        enregistrerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                IOEtudiants foo = new IOEtudiants();
                System.out.print("button enregistrer\n");
                File myFile = new File("fsm.txt");
                if (myFile.exists())
                    myFile.delete();

                foo.ajouterOuModifierEtudiant(myArbre, racine, treeModel);

                foo.AfficherEtudiant();

            }
        });

        // --question numero 9
        TreeSelectionListener arbreListener = new TreeSelectionListener() {

            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) myArbre.getLastSelectedPathComponent();

                if (node == null) {
                    return;
                }
                // if etudiant is leaf
                if (node.isLeaf()) {
                    jpArbreInfo.remove(tableScrollPaneEtudiantInfo);
                    jpArbreInfo.remove(tableScrollPaneclassNumEtudiant);
                    jpArbreInfo.add(jpInfo);
                    jpArbreInfo.revalidate();
                    jpArbreInfo.repaint();

                    Etudiant info = (Etudiant) node.getUserObject();

                    String nameEtudiantLeaf = info.Nom;
                    String lastnameEtudiantLeaf = info.Prenom;
                    String EmailEtudiantLeaf = info.Email;
                    String NiveauEtudiantLeaf = info.Niveau;
                    String AdresseEtudiantLeaf = info.Adresse;
                    String ClasseEtudiantLeaf = info.Classe;
                    String npEtudiantLeaf = info.numPortable;
                    String nfEtudiantLeaf = info.numFix;

                    getTheNom1.setText(nameEtudiantLeaf);
                    getThePrenom1.setText(lastnameEtudiantLeaf);
                    getTheEmail1.setText(EmailEtudiantLeaf);
                    getTheAdresse1.setText(AdresseEtudiantLeaf);
                    getTheNiveau1.setText(NiveauEtudiantLeaf);
                    getTheClasseS1.setText(ClasseEtudiantLeaf);
                    getTheNumPortable1.setText(npEtudiantLeaf);
                    getTheNumFix1.setText(nfEtudiantLeaf);

                    // notes tab
                    getTheLastName.setText(lastnameEtudiantLeaf);
                    getTheFirstName.setText(nameEtudiantLeaf);
                    getTheMail.setText(EmailEtudiantLeaf);
                    try {
                        nte = gex.lireNotes(getTheMail.getText());
                        if (nte != null) {
                            getTheSecurity.setText(nte.securite);
                            getTheReseau.setText(nte.reseau);
                            getThePhp.setText(nte.php);
                            getTheAnglais.setText(nte.anglais);
                            getTheAndroid.setText(nte.android);
                            getThePfe.setText(nte.pfe);
                            getTheFrancais.setText(nte.francais);
                            getTheMath.setText(nte.math);
                            getTheSgbd.setText(nte.sgbd);
                            getTheJava.setText(nte.Java);
                        }
                    } catch (SQLException even) {
                        even.printStackTrace();
                    }

                }
                // if node is classe

                else if (node.isRoot()) {
                    // if node is root

                    dtmClass.setRowCount(0);
                    for (int i = 0; i < racine.getChildCount(); i++) {
                        DefaultMutableTreeNode child_Class = (DefaultMutableTreeNode) racine.getChildAt(i);
                        dtmClass.addRow(new Object[] { child_Class.getUserObject(), child_Class.getChildCount() });
                    }

                    jpArbreInfo.remove(jpInfo);
                    jpArbreInfo.remove(tableScrollPaneEtudiantInfo);

                    jpArbreInfo.add(tableScrollPaneclassNumEtudiant, BorderLayout.NORTH);
                    jpArbreInfo.revalidate();
                    jpArbreInfo.repaint();

                } else {

                    dtm.setRowCount(0);
                    for (int j = 0; j < racine.getChildCount(); j++) {
                        DefaultMutableTreeNode childrenEtudiant = (DefaultMutableTreeNode) racine.getChildAt(j);
                        if (childrenEtudiant == node) {

                            for (int i = 0; i < childrenEtudiant.getChildCount(); i++) {
                                DefaultMutableTreeNode selectedEtudiant = (DefaultMutableTreeNode) childrenEtudiant
                                        .getChildAt(i);
                                Etudiant infoEtudiant = (Etudiant) selectedEtudiant.getUserObject();
                                dtm.addRow(
                                        new Object[] { infoEtudiant.Prenom, infoEtudiant.Nom,
                                                infoEtudiant.numPortable });
                            }
                        }
                    }

                    jpArbreInfo.remove(jpInfo);
                    jpArbreInfo.remove(tableScrollPaneclassNumEtudiant);
                    jpArbreInfo.add(tableScrollPaneEtudiantInfo);
                    jpArbreInfo.revalidate();
                    jpArbreInfo.repaint();

                }
            }
        };
        /**    */
        myArbre.addTreeSelectionListener(arbreListener);
        // add listener to the menu bar
        ActionListener menuAction = new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                String itemName = ev.getActionCommand();
                System.out.println(itemName + "----> clicked.");
                switch (itemName) {
                    case "Ouvrir":
                        initialiserArbre(myArbre);
                        treeModel.reload();
                        break;

                    case "Enregistrer":
                        for (int i = 0; i < racine.getChildCount(); i++) {
                            DefaultMutableTreeNode NodeRacineOrdre1 = (DefaultMutableTreeNode) racine.getChildAt(i);
                            System.out.println(" - " + NodeRacineOrdre1.toString());
                            for (int j = 0; j < NodeRacineOrdre1.getChildCount(); j++) {
                                DefaultMutableTreeNode leaf = (DefaultMutableTreeNode) NodeRacineOrdre1.getChildAt(j);
                                System.out.println(" * " + leaf.toString());
                            }
                        }
                        ;

                        break;

                    case "Quitter":
                        System.exit(0);
                        break;

                    default:
                        break;
                }
            }

        };
        ItemOuvrir.addActionListener(menuAction);
        ItemEregister.addActionListener(menuAction);
        ItemQuitter.addActionListener(menuAction);

        // click annuler button de l'onglet notes
        annulButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                if (nte != null) {
                    try {
                        nte = gex.lireNotes(getTheMail.getText());
                        getTheSecurity.setText(nte.securite);
                        getTheReseau.setText(nte.reseau);
                        getThePhp.setText(nte.php);
                        getTheAnglais.setText(nte.anglais);
                        getTheAndroid.setText(nte.android);
                        getThePfe.setText(nte.pfe);
                        getTheFrancais.setText(nte.francais);
                        getTheMath.setText(nte.math);
                        getTheSgbd.setText(nte.sgbd);
                        getTheJava.setText(nte.Java);
                    } catch (SQLException even) {
                        even.printStackTrace();
                    }
                }
            }
        });
        // click vider button
        ActionListener viderAction = new ActionListener() {
            public void actionPerformed(ActionEvent ev) {

                getTheMail.setText(null);
                getTheLastName.setText(null);
                getTheFirstName.setText(null);
                getTheSecurity.setText(null);
                getTheSgbd.setText(null);
                getTheFrancais.setText(null);
                getTheAnglais.setText(null);
                getThePfe.setText(null);
                getTheJava.setText(null);
                getTheReseau.setText(null);
                getTheMath.setText(null);
                getTheAndroid.setText(null);
                getThePhp.setText(null);
            }
        };
        viderButton.addActionListener(viderAction);

        // add the panels to the new main panel
        jpTabEtudiant.add(jpFormButton, BorderLayout.NORTH);
        jpTabEtudiant.add(jpArbreInfo, BorderLayout.CENTER);

        // add the ongle
        tabbedPane.add("Etudiants", jpTabEtudiant);
        tabbedPane.add("Notes", jpTabNotes);

        // parametres du frame
        setTitle("Sestion des etudiants");
        setLayout(new BorderLayout(20, 20));

        add(tabbedPane);
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    };

    // initialiserArbre methode
    public void initialiserArbre(JTree tree) {

        racine.removeAllChildren();

        try {
            FileReader fileReader = new FileReader("fsm.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String ligne;
            ArrayList<String> lignes = new ArrayList<String>();
            while ((ligne = bufferedReader.readLine()) != null) {
                lignes.add(ligne);
            }
            for (int i = 0; i < lignes.size(); i++) {
                String[] classe = lignes.get(i).split(":");
                DefaultMutableTreeNode case_classe = new DefaultMutableTreeNode(classe[0]);
                String[] studen = classe[1].split(";");
                for (int k = 0; k < studen.length; k++) {
                    String[] valuesOfMyEtudiant = studen[k].split(",");
                    Etudiant newEtudiant = new Etudiant(
                            valuesOfMyEtudiant[0], valuesOfMyEtudiant[1], valuesOfMyEtudiant[2],
                            valuesOfMyEtudiant[3], valuesOfMyEtudiant[4],
                            valuesOfMyEtudiant[5], valuesOfMyEtudiant[6],
                            valuesOfMyEtudiant[7]);
                    DefaultMutableTreeNode newValuePrenom = new DefaultMutableTreeNode(newEtudiant);
                    case_classe.add(newValuePrenom);
                    racine.add(case_classe);
                }
            }
            treeModel.reload();
            fileReader.close();
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        ;

    };

}

class MainFrame {

    // Driver code
    public static void main(String[] args) {
        // new Authentification(null);
        // calling the constructor
        new GestionEtudiants();

    }
}

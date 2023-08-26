import java.io.BufferedReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * IOEtudiants
 */
public class IOEtudiants {
    public IOEtudiants() {

    }

    public void ajouterOuModifierEtudiant(JTree arbre, DefaultMutableTreeNode racine, DefaultTreeModel treeModel) {

        try {

            FileWriter fileWriter = new FileWriter("fsm.txt", true);

            for (int i = 0; i < racine.getChildCount(); i++) {
                DefaultMutableTreeNode cllass = (DefaultMutableTreeNode) racine.getChildAt(i);
                fileWriter.write(cllass + ":");
                for (int j = 0; j < cllass.getChildCount(); j++) {
                    DefaultMutableTreeNode et = (DefaultMutableTreeNode) cllass.getChildAt(j);
                    Etudiant addedEtudiantToFile = (Etudiant) et.getUserObject();

                    fileWriter.write(addedEtudiantToFile.fullInformation());

                }
                fileWriter.write("\n");
            }
            fileWriter.close();

        } catch (IOException e) {

            e.printStackTrace();
        }
        ;

    }

    public void AfficherEtudiant() {
        try {
            FileReader fileReader = new FileReader("fsm.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String ligne;
            while ((ligne = bufferedReader.readLine()) != null) {

                String[] info = ligne.split(":");
                String[] myEtudiant = info[1].split(";");

                for (int i = 0; i < myEtudiant.length; i++) {

                    String[] valuesOfMyEtudiant = myEtudiant[i].split(",");
                    System.out.println(" * " + valuesOfMyEtudiant[0] + " " + valuesOfMyEtudiant[1] + ":");
                    System.out.println("      Email  = " + valuesOfMyEtudiant[2]);
                    System.out.println("        Niveau= " + valuesOfMyEtudiant[3]);
                    System.out.println("      classe = " + valuesOfMyEtudiant[4]);
                    System.out.println("      numero portable  = " + valuesOfMyEtudiant[5]);
                    System.out.println("      numero fix= " + valuesOfMyEtudiant[6]);
                    System.out.println("      Adresse = " + valuesOfMyEtudiant[7]);

                }

            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    };

}
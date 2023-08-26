
public class Etudiant {
    public String Nom;
    public String Email;
    public String Prenom;
    public String Niveau;
    public String Classe;
    public String numPortable;
    public String numFix;
    public String Adresse;

    public Etudiant(String nom,String email ,String prenom, String niveau, String classe, String numportable, String numfix,
            String adresse) {
        this.Nom = nom;
        this.Email = email;
        this.Prenom = prenom;
        this.Niveau = niveau;
        this.Classe = classe;
        this.numPortable = numportable;
        this.numFix = numfix;
        this.Adresse = adresse;
    };

    public String getName(){
        return Nom;
    }

    public String toString() {
        return Nom + " " + Prenom;
    }
    public String fullInformation(){
        return Nom + "," + Prenom + ","+ Email + "," +Niveau + "," + Classe + "," + numPortable + ","+ numFix + ","+ Adresse+";";
    };

}




package ClientWeb.client;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thibaud on 08/04/2014.
 */
@XmlRootElement(name = "cv")
public class CV {
    public static int numCreation = 0;
    private int id;
    private int age;
    private String nom;
    private String prenom;
    private String metier;

    @XmlElementWrapper(name = "formations")
    @XmlElement(name = "formation")
    public List<Formation> formations;
    @XmlElementWrapper(name = "competences")
    @XmlElement(name = "competence")
    public List<Competence> competences;
    @XmlElementWrapper(name = "langues")
    @XmlElement(name = "langue")
    public List<Langue> langues;

    public CV() {
        id = numCreation;
        nom = "";
        prenom = "";
        metier = "";
        formations = new ArrayList<Formation>();
        competences = new ArrayList<Competence>();
        langues = new ArrayList<Langue>();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMetier() {
        return metier;
    }

    public void setMetier(String metier) {
        this.metier = metier;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void ajouterFormation(Formation f) {
        formations.add(f);
    }

    public static int getNumCreation() {
        return numCreation;
    }

    public static void incrementeNumCreation() {
        numCreation++;
    }

}

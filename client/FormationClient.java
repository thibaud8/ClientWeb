package ClientWeb.client;



import javax.swing.*;

/**
 * Created by Thibaud on 03/05/2014.
 */
public class FormationClient {
    private JTextField lieu;
    private JTextField nom;
    private JTextField anneeDebut;
    private JTextField anneeFin;

    public FormationClient() {
        lieu = new JTextField(15);
        nom = new JTextField(15);
        anneeDebut = new JTextField(5);
        anneeFin = new JTextField(5);
    }

    public JTextField getLieu() {
        return lieu;
    }

    public void setLieu(JTextField lieu) {
        this.lieu = lieu;
    }

    public JTextField getNom() {
        return nom;
    }

    public void setNom(JTextField nom) {
        this.nom = nom;
    }

    public JTextField getAnneeDebut() {
        return anneeDebut;
    }

    public void setAnneeDebut(JTextField anneeDebut) {
        this.anneeDebut = anneeDebut;
    }

    public JTextField getAnneeFin() {
        return anneeFin;
    }

    public void setAnneeFin(JTextField anneeFin) {
        this.anneeFin = anneeFin;
    }


}

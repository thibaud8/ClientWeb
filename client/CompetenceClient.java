package ClientWeb.client;

import javax.swing.*;

/**
 * Created by Thibaud on 03/05/2014.
 */
public class CompetenceClient {
    private JTextField nom;
    private JSlider niveau;

    public CompetenceClient() {
        nom = new JTextField(10);
        niveau = new JSlider(0,100);
    }

    public JTextField getNom() {
        return nom;
    }

    public void setNom(JTextField nom) {
        this.nom = nom;
    }

    public JSlider getNiveau() {
        return niveau;
    }

    public void setNiveau(JSlider niveau) {
        this.niveau = niveau;
    }
}

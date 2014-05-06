package ClientWeb.client;

import javax.swing.*;

/**
 * Created by Thibaud on 03/05/2014.
 */
public class LangueClient {
    private JTextField langue;
    private JSlider niveau;

    public LangueClient() {
        langue = new JTextField(10);
        niveau = new JSlider(0,100);
    }


    public JSlider getNiveau() {
        return niveau;
    }

    public void setNiveau(JSlider niveau) {
        this.niveau = niveau;
    }

    public JTextField getLangue() {
        return langue;
    }

    public void setLangue(JTextField langue) {
        this.langue = langue;
    }
}

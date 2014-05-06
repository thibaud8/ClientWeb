package ClientWeb.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Thibaud on 08/04/2014.
 */

@XmlRootElement(name = "langue")
public class Langue {

    private String intitule;
    private int pourcentage;

    public Langue() {
        intitule = new String();
        pourcentage = 50;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public int getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(int pourcentage) {
        this.pourcentage = pourcentage;
    }


}

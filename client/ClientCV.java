package ClientWeb.client;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.util.JAXBSource;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.http.HTTPBinding;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Created by Thibaud on 02/05/2014.
 */
public class ClientCV {

    //Frame principale
    private JFrame frame;

    //Menu de l'application
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem ajouterCV;
    private JMenuItem voirCVs;
    private JMenuItem chercherCV;

    //Affichage principal
    private JTextArea principal;
    private JTextField chercher;
    private JButton chercherOK;
    private JLabel chercherLabel;
    private JScrollPane scrollPane;

    //Formulaire Ajout CV
    private JFrame frameAjout;
    private JTextField prenom;
    private JTextField nom;
    private JTextField age;
    private JTextField metier;
    private List<FormationClient> formations;
    private JButton ajouterFormation;
    private JButton supprimerFormation;
    private List<LangueClient> langues;
    private JButton ajouterLangue;
    private JButton supprimerLangue;
    private List<CompetenceClient> competences;
    private JButton ajouterCompetence;
    private JButton supprimerCompetence;
    private JButton ok;
    private JButton annuler;

    private static final String URL_CONNEXION = "http://tp-web-xml-monmertthibaud.thibaud7.cloudbees.net/rest/cv";

    public ClientCV(){
        createView();
        placeComponents();
        createController();
    }

    public void display() {
        frame.pack();
        frame.setVisible(true);
        frame.setSize(new Dimension(500,500));
        frame.setLocationRelativeTo(null);
        frameAjout.pack();
        frameAjout.setVisible(false);
        frameAjout.setSize(new Dimension(1100,500));
        frameAjout.setLocationRelativeTo(null);
    }

    public void createView() {
        frame = new JFrame("Application : Projet Languages Web : Monmert");
        scrollPane = new JScrollPane();
        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        menu = new JMenu("Actions");
        menuBar.add(menu);
        ajouterCV = new JMenuItem("Ajouter un CV");
        voirCVs = new JMenuItem("Voir tous les cv");
        chercherCV = new JMenuItem("Chercher un cv");
        menu.add(ajouterCV);
        menu.add(voirCVs);
        menu.add(chercherCV);
        principal = new JTextArea();
        principal.setEditable(false);
        chercher = new JTextField(3);
        chercher.setVisible(false);
        chercherOK = new JButton("chercher");
        chercherOK.setVisible(false);
        chercherLabel = new JLabel("Numero de CV cherché : ");
        chercherLabel.setVisible(false);
        // Frame Ajout CV
        frameAjout = new JFrame("Ajouter un CV");
        frameAjout.setPreferredSize(new Dimension(200,200));
        annuler = new JButton("Annuler");
        ok = new JButton("OK");
        prenom = new JTextField(10);
        nom = new JTextField(10);
        metier = new JTextField(10);
        age = new JTextField(3);
        formations = new ArrayList<FormationClient>();
        formations.add(new FormationClient());
        ajouterFormation = new JButton("Ajouter une Formation");
        supprimerFormation = new JButton("Supprimer toutes les formations");
        langues = new ArrayList<LangueClient>();
        langues.add(new LangueClient());
        ajouterLangue = new JButton("Ajouter une langue");
        supprimerLangue = new JButton("Supprimer toutes les langues");
        competences = new ArrayList<CompetenceClient>();
        competences.add(new CompetenceClient());
        ajouterCompetence = new JButton("Ajouter une competence");
        supprimerCompetence = new JButton("Supprimer toutes les competences");

    }


    public void placeComponents(){
        frame.setLayout(new BorderLayout());
        JPanel z = new JPanel(new FlowLayout(FlowLayout.CENTER)); {
            z.add(chercherLabel);
            z.add(chercher);
            z.add(chercherOK);
        }
        frame.add(z, BorderLayout.NORTH);
        scrollPane = new JScrollPane(principal);
        frame.add(scrollPane, BorderLayout.CENTER);
        frameAjout.setLayout(new BorderLayout());
        JPanel o = new JPanel(new BorderLayout());
        {
            JPanel q = new JPanel(new FlowLayout(FlowLayout.LEFT));
            {
                JTextField p = new JTextField("Prenom :");
                p.setEditable(false);
                q.add(p);
                q.add(prenom);
                p = new JTextField("Nom :");
                p.setEditable(false);
                q.add(p);
                q.add(nom);
                p = new JTextField("Metier :");
                p.setEditable(false);
                q.add(p);
                q.add(metier);
                p = new JTextField("Age :");
                p.setEditable(false);
                q.add(p);
                q.add(age);
            }
            o.add(q, BorderLayout.NORTH);

            q = new JPanel(new GridLayout(1,3));
            {
                JPanel t = new JPanel(new BorderLayout());
                {
                    JPanel u = new JPanel(new GridLayout(0,1));
                    {
                        for (FormationClient f : formations) {
                            JPanel r = new JPanel(new GridLayout(2, 2));
                            {

                                JPanel s = new JPanel(new FlowLayout(FlowLayout.CENTER));
                                {
                                    JLabel l = new JLabel("Nom :");
                                    s.add(l);
                                    s.add(f.getNom());
                                }
                                r.add(s);
                                s = new JPanel(new FlowLayout(FlowLayout.CENTER));
                                {
                                    JLabel l = new JLabel("Lieu :");
                                    s.add(l);
                                    s.add(f.getLieu());
                                }
                                r.add(s);
                                s = new JPanel(new FlowLayout(FlowLayout.CENTER));
                                {
                                    JLabel l = new JLabel("Debut :");
                                    s.add(l);
                                    s.add(f.getAnneeDebut());
                                }
                                r.add(s);
                                s = new JPanel(new FlowLayout(FlowLayout.CENTER));
                                {
                                    JLabel l = new JLabel("Fin :");
                                    s.add(l);
                                    s.add(f.getAnneeFin());
                                }
                                r.add(s);
                            }
                            r.setBorder(BorderFactory.createEtchedBorder());
                            u.add(r);
                        }
                    }
                    t.add(u, BorderLayout.CENTER);
                    JPanel zz = new JPanel(new GridLayout(1,2));
                    {
                        zz.add(ajouterFormation);
                        zz.add(supprimerFormation);
                    }
                    t.add(zz, BorderLayout.SOUTH);
                }
                t.setBorder(BorderFactory.createTitledBorder("Formation"));
                q.add(t);
                t = new JPanel(new BorderLayout());
                {
                    JPanel u = new JPanel(new GridLayout(0,1));
                    {
                        for (LangueClient langueClient : langues) {
                            JPanel r = new JPanel(new GridLayout(1, 2));
                            {

                                JPanel s = new JPanel(new FlowLayout(FlowLayout.CENTER));
                                {
                                    JLabel l = new JLabel("Langue :");
                                    s.add(l);
                                    s.add(langueClient.getLangue());
                                }
                                r.add(s);
                                s = new JPanel(new FlowLayout(FlowLayout.CENTER));
                                {
                                    JLabel l = new JLabel("Niveau :");
                                    s.add(l);
                                    s.add(langueClient.getNiveau());
                                }
                                r.add(s);
                            }
                            r.setBorder(BorderFactory.createEtchedBorder());
                            u.add(r);
                        }
                    }
                    t.add(u, BorderLayout.CENTER);
                    JPanel zz = new JPanel(new GridLayout(1,2));
                    {
                        zz.add(ajouterLangue);
                        zz.add(supprimerLangue);
                    }
                    t.add(zz, BorderLayout.SOUTH);
                }
                t.setBorder(BorderFactory.createTitledBorder("Langues"));
                q.add(t);

                t = new JPanel(new BorderLayout());
                {
                    JPanel u = new JPanel(new GridLayout(0,1));
                    {
                        for (CompetenceClient competenceClient : competences) {
                            JPanel r = new JPanel(new GridLayout(1, 2));
                            {

                                JPanel s = new JPanel(new FlowLayout(FlowLayout.CENTER));
                                {
                                    JLabel l = new JLabel("Nom :");
                                    s.add(l);
                                    s.add(competenceClient.getNom());
                                }
                                r.add(s);
                                s = new JPanel(new FlowLayout(FlowLayout.CENTER));
                                {
                                    JLabel l = new JLabel("Niveau :");
                                    s.add(l);
                                    s.add(competenceClient.getNiveau());
                                }
                                r.add(s);
                            }
                            r.setBorder(BorderFactory.createEtchedBorder());
                            u.add(r);
                        }
                    }
                    t.add(u, BorderLayout.CENTER);
                    JPanel zz = new JPanel(new GridLayout(1,2));
                    {
                        zz.add(ajouterCompetence);
                        zz.add(supprimerCompetence);
                    }
                    t.add(zz, BorderLayout.SOUTH);
                }
                t.setBorder(BorderFactory.createTitledBorder("Compétences"));
                q.add(t);
            }
            o.add(q, BorderLayout.CENTER);
        }
        frameAjout.add(o, BorderLayout.CENTER);
        JPanel q = new JPanel(new FlowLayout(FlowLayout.LEFT)); {
            q.add(ok);
            q.add(annuler);
        }
        frameAjout.add(q, BorderLayout.SOUTH);
    }

    public void replaceComponents() {
        frameAjout.getContentPane().removeAll();
        frameAjout.setLayout(new BorderLayout());
        JPanel o = new JPanel(new BorderLayout());
        {
            JPanel q = new JPanel(new FlowLayout(FlowLayout.LEFT));
            {
                JTextField p = new JTextField("Prenom :");
                p.setEditable(false);
                q.add(p);
                q.add(prenom);
                p = new JTextField("Nom :");
                p.setEditable(false);
                q.add(p);
                q.add(nom);
                p = new JTextField("Metier :");
                p.setEditable(false);
                q.add(p);
                q.add(metier);
                p = new JTextField("Age :");
                p.setEditable(false);
                q.add(p);
                q.add(age);
            }
            o.add(q, BorderLayout.NORTH);

            q = new JPanel(new GridLayout(1,3));
            {
                JPanel t = new JPanel(new BorderLayout());
                {
                    JPanel u = new JPanel(new GridLayout(0,1));
                    {
                        for (FormationClient f : formations) {
                            JPanel r = new JPanel(new GridLayout(2, 2));
                            {

                                JPanel s = new JPanel(new FlowLayout(FlowLayout.CENTER));
                                {
                                    JLabel l = new JLabel("Nom :");
                                    s.add(l);
                                    s.add(f.getNom());
                                }
                                r.add(s);
                                s = new JPanel(new FlowLayout(FlowLayout.CENTER));
                                {
                                    JLabel l = new JLabel("Lieu :");
                                    s.add(l);
                                    s.add(f.getLieu());
                                }
                                r.add(s);
                                s = new JPanel(new FlowLayout(FlowLayout.CENTER));
                                {
                                    JLabel l = new JLabel("Debut :");
                                    s.add(l);
                                    s.add(f.getAnneeDebut());
                                }
                                r.add(s);
                                s = new JPanel(new FlowLayout(FlowLayout.CENTER));
                                {
                                    JLabel l = new JLabel("Fin :");
                                    s.add(l);
                                    s.add(f.getAnneeFin());
                                }
                                r.add(s);
                            }
                            r.setBorder(BorderFactory.createEtchedBorder());
                            u.add(r);
                        }
                    }
                    t.add(u, BorderLayout.CENTER);
                    JPanel zz = new JPanel(new GridLayout(1,2));
                    {
                        zz.add(ajouterFormation);
                        zz.add(supprimerFormation);
                    }
                    t.add(zz, BorderLayout.SOUTH);
                }
                t.setBorder(BorderFactory.createTitledBorder("Formation"));
                q.add(t);
                t = new JPanel(new BorderLayout());
                {
                    JPanel u = new JPanel(new GridLayout(0,1));
                    {
                        for (LangueClient langueClient : langues) {
                            JPanel r = new JPanel(new GridLayout(1, 2));
                            {

                                JPanel s = new JPanel(new FlowLayout(FlowLayout.CENTER));
                                {
                                    JLabel l = new JLabel("Langue :");
                                    s.add(l);
                                    s.add(langueClient.getLangue());
                                }
                                r.add(s);
                                s = new JPanel(new FlowLayout(FlowLayout.CENTER));
                                {
                                    JLabel l = new JLabel("Niveau :");
                                    s.add(l);
                                    s.add(langueClient.getNiveau());
                                }
                                r.add(s);
                            }
                            r.setBorder(BorderFactory.createEtchedBorder());
                            u.add(r);
                        }
                    }
                    t.add(u, BorderLayout.CENTER);
                    JPanel zz = new JPanel(new GridLayout(1,2));
                    {
                        zz.add(ajouterLangue);
                        zz.add(supprimerLangue);
                    }
                    t.add(zz, BorderLayout.SOUTH);
                }
                t.setBorder(BorderFactory.createTitledBorder("Langues"));
                q.add(t);

                t = new JPanel(new BorderLayout());
                {
                    JPanel u = new JPanel(new GridLayout(0,1));
                    {
                        for (CompetenceClient competenceClient : competences) {
                            JPanel r = new JPanel(new GridLayout(1, 2));
                            {

                                JPanel s = new JPanel(new FlowLayout(FlowLayout.CENTER));
                                {
                                    JLabel l = new JLabel("Nom :");
                                    s.add(l);
                                    s.add(competenceClient.getNom());
                                }
                                r.add(s);
                                s = new JPanel(new FlowLayout(FlowLayout.CENTER));
                                {
                                    JLabel l = new JLabel("Niveau :");
                                    s.add(l);
                                    s.add(competenceClient.getNiveau());
                                }
                                r.add(s);
                            }
                            r.setBorder(BorderFactory.createEtchedBorder());
                            u.add(r);
                        }
                    }
                    t.add(u, BorderLayout.CENTER);
                    JPanel zz = new JPanel(new GridLayout(1,2));
                    {
                        zz.add(ajouterCompetence);
                        zz.add(supprimerCompetence);
                    }
                    t.add(zz, BorderLayout.SOUTH);
                }
                t.setBorder(BorderFactory.createTitledBorder("Compétences"));
                q.add(t);
            }
            o.add(q, BorderLayout.CENTER);
        }
        frameAjout.add(o, BorderLayout.CENTER);
        JPanel q = new JPanel(new FlowLayout(FlowLayout.LEFT)); {
            q.add(ok);
            q.add(annuler);
        }
        frameAjout.add(q, BorderLayout.SOUTH);
    }

    public void createController() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameAjout.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        annuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(true);
                frameAjout.setVisible(false);
            }
        });

        voirCVs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chercher.setVisible(false);
                chercherOK.setVisible(false);
                chercherLabel.setVisible(false);
                try {
                    getCVs();
                } catch (Exception ee) {
                    ee.printStackTrace();
                    System.out.println(ee.toString());
                    principal.setText("Impossible de récupérer l'ensemble des cvs ..");
                }
            }
        });


        supprimerCompetence.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nettoyerCompetences();
                replaceComponents();
                frameAjout.validate();
            }
        });
        supprimerFormation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nettoyerFormations();
                replaceComponents();
                frameAjout.validate();
            }
        });
        supprimerLangue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nettoyerLangues();
                replaceComponents();
                frameAjout.validate();
            }
        });

        chercherCV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chercher.setVisible(true);
                chercherOK.setVisible(true);
                chercherLabel.setVisible(true);
                principal.setText("");
                frame.validate();
            }
        });

        ajouterCV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameAjout.setVisible(true);
                frame.setVisible(false);
                chercher.setVisible(false);
                chercherOK.setVisible(false);
                chercherLabel.setVisible(false);
            }
        });


        ajouterFormation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("Debut\n");
                formations.add(new FormationClient());
                replaceComponents();
                frameAjout.validate();
                System.out.print("Fin\n");
            }
        });

        ajouterCompetence.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("Debut\n");
                competences.add(new CompetenceClient());
                replaceComponents();
                frameAjout.validate();
                System.out.print("Fin\n");
            }
        });

        ajouterLangue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("Debut\n");
                langues.add(new LangueClient());
                replaceComponents();
                frameAjout.validate();
                System.out.print("Fin\n");
            }
        });

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameAjout.setVisible(false);
                frame.setVisible(true);
                try {
                    putCV();
                } catch (JAXBException e1) {
                    e1.printStackTrace();
                }
            }
        });

        chercherOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = -1;
                try {
                    i = Integer.parseInt(chercher.getText());
                    getCVId(i);
                } catch (Exception ee) {
                    principal.setText("Vérifiez le numéro que vous avez indiqué ..");
                }

            }
        });
    }



    private String lectureCV(Document d) {
        String cvsResultat = "";

            NodeList cvs = d.getElementsByTagName("cv");
            int nombreCV = cvs.getLength();
        if (nombreCV > 0) {
            //Lecture de l'ensemble des cv
            for (int n = 0; n < nombreCV; n++) {
                Node cv = cvs.item(n);
                NodeList fils = cv.getChildNodes();
                //Recup numero du cv
                cvsResultat += "Numero de cv : " + fils.item(4).getTextContent() + "\n\n";
                //Recup prenom
                cvsResultat += "" + fils.item(7).getTextContent() + "\n";
                //Recup nom
                cvsResultat += "" + fils.item(6).getTextContent() + "\n";
                //Recup age
                cvsResultat += fils.item(3).getTextContent() + " ans\n";
                //Recup metier
                cvsResultat += "Métier : " + fils.item(5).getTextContent() + "\n\n ---FORMATIONS ---\n";
                NodeList form = fils.item(0).getChildNodes();
                for (int m = 0; m < form.getLength(); m++) {
                    Node formation = form.item(m);
                    NodeList liste = formation.getChildNodes();
                    cvsResultat += "\nFormation n° : " + m + "\n" + "-    Nom : " + liste.item(2).getTextContent() + "\n";
                    cvsResultat += "-    Lieu : " + liste.item(3).getTextContent() + "\n";
                    cvsResultat += "-    Debut : " + liste.item(0).getTextContent() + "\n";
                    cvsResultat += "-    Fin : " + liste.item(1).getTextContent() + "\n";
                }
                cvsResultat += "\n\n ---COMPETENCES ---\n";
                NodeList comp = fils.item(1).getChildNodes();
                for (int m = 0; m < comp.getLength(); m++) {
                    Node competence = comp.item(m);
                    if (competence != null) {
                        NodeList liste = competence.getChildNodes();
                        cvsResultat += "Compétence n° : " + m + "\n";
                        cvsResultat += "-    Nom : " + liste.item(0).getTextContent() + " -->";
                        cvsResultat += "-    Note : " + liste.item(1).getTextContent() + "/100\n";
                    }
                }

                NodeList lang = fils.item(2).getChildNodes();
                cvsResultat += "\n\n ---LANGUES ---\n";
                for (int m = 0; m < lang.getLength(); m++) {
                    Node langue = lang.item(m);
                    if (langue != null) {
                        NodeList liste = langue.getChildNodes();
                        cvsResultat += "Langue n° : " + m + "\n";
                        cvsResultat += "-    Nom : " + liste.item(0).getTextContent() + " -->";
                        cvsResultat += "-    Note : " + liste.item(1).getTextContent() + "/100\n";
                    }
                }
                cvsResultat += "\n_____________________________________________\n\n";
            }
        } else {
            cvsResultat += "Rien à récupérer ! Pas de cv dans la liste .. ";
        }
        return cvsResultat;
    }

    private void nettoyerFormations() {
        formations = new ArrayList<FormationClient>();
        formations.add(new FormationClient());
    }

    private void nettoyerCompetences() {
        competences = new ArrayList<CompetenceClient>();
        competences.add(new CompetenceClient());
    }

    private void nettoyerLangues() {
        langues = new ArrayList<LangueClient>();
        langues.add(new LangueClient());
    }


    private void getCVs() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        factory.setNamespaceAware(true);

        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.out.println("1");
            e.printStackTrace();
        }
        Document document = null;
        try {
            document = builder.parse(URL_CONNEXION);
        } catch (SAXException e) {
            System.out.println("2");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("3");
            e.printStackTrace();
        }
        String resultatLecture = "";
        if (document == null) {
            resultatLecture = "Erreur : impossible de se connecter à cloudbees !";
        } else {
            resultatLecture = lectureCV(document);
        }
        principal.setText(resultatLecture);
    }

    private void getCVId(int id) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        factory.setNamespaceAware(true);

        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.out.println("1");
            e.printStackTrace();
        }
        Document document = null;
        try {
            document = builder.parse(URL_CONNEXION+"/"+id);
        } catch (SAXException e) {
            System.out.println("2");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("3");
            e.printStackTrace();
        }
        if (document == null) {
            System.out.println("NULL POINTER");
        }
        String resultatLecture = lectureCV(document);

        principal.setText(resultatLecture);
    }


    private void putCV() throws JAXBException {
        QName qname = new QName("","");
        Service service = Service.create(qname);
        service.addPort(qname, HTTPBinding.HTTP_BINDING, URL_CONNEXION);
        Dispatch<Source> dispatcher = service.createDispatch(qname,
                Source.class, Service.Mode.MESSAGE);
        Map<String, Object> requestContext = dispatcher.getRequestContext();
        requestContext.put(MessageContext.HTTP_REQUEST_METHOD, "PUT");

        CV cv = new CV();
        CV.incrementeNumCreation();
        cv.setId(CV.getNumCreation());
        cv.setPrenom(prenom.getText());
        cv.setNom(nom.getText());
        cv.setMetier(metier.getText());
        int n = 0;
        try {
            n = Integer.parseInt(age.getText());
        } catch (Exception e) {

        }
        cv.setAge(n);

        for (int i = 0; i < formations.size(); i++) {
            String nomFormation = formations.get(i).getNom().getText();
            String lieuFormation = formations.get(i).getLieu().getText();
            int anneeDebutFormation = 0;
            int anneeFinFormation = 0;
            try {
                anneeDebutFormation = Integer.parseInt(formations.get(i).getAnneeDebut().getText());
                anneeFinFormation = Integer.parseInt(formations.get(i).getAnneeFin().getText());
            } catch (Exception e) {

            }
            if (!nomFormation.equals("")) {
                Formation f = new Formation();
                f.setAnneeDebut(anneeDebutFormation);
                f.setAnneeFin(anneeFinFormation);
                f.setLieu(lieuFormation);
                f.setNom(nomFormation);
                cv.formations.add(f);
            }
        }
        for (int i = 0; i < langues.size(); i++) {
            String nomLangue = langues.get(i).getLangue().getText();
            int noteLangue = langues.get(i).getNiveau().getValue();
            if (!nomLangue.equals("")) {
                Langue l = new Langue();
                l.setIntitule(nomLangue);
                l.setPourcentage(noteLangue);
                cv.langues.add(l);
            }
        }
        for (int i = 0; i < competences.size(); i++) {
            String nomCompetence = competences.get(i).getNom().getText();
            int noteCompetence = competences.get(i).getNiveau().getValue();
            if (!nomCompetence.equals("")) {
                Competence l = new Competence();
                l.setNomCompetence(nomCompetence);
                l.setPourcentage(noteCompetence);
                cv.competences.add(l);
            }
        }
        JAXBContext jc;
        try {
            jc = JAXBContext.newInstance(CV.class);
            dispatcher.invoke(new JAXBSource(jc, cv));
        } catch (JAXBException je) {
            System.out.println("Probleme avec JAXBContext");
        }

        getCVs();
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ClientCV().display();
            }
        });
    }

}

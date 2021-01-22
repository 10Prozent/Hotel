import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;


public class Buchungssystem {

    public static Buchungssystem[] alleBuchungen = new Buchungssystem[10];
    public static int buchungzaehler;
    public int buchungsID = 0;

    int anzahlTage;
    Room r;
    Date zielDate;
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    boolean einzelPB;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";

    //Leere Konstruktur
    public Buchungssystem() {
    }

    //Einzel
    public Buchungssystem(EinzelRoom a, boolean fruehstueck, boolean balkon, int anzahlTage, Date zielDate) {
        buchungzaehler++;
        this.buchungsID = buchungzaehler;

        this.zielDate = zielDate;
        this.r = a;
        this.anzahlTage = anzahlTage;

        a.balkon = balkon;
        a.belegt = true;
        a.fruehstueck = fruehstueck;
        a.preisProZimmer += anzahlTage * 20;

        //Preis anpassen
        if (balkon) {
            a.preisProZimmer += 50;
        }

        if (a.fruehstueck) {
            a.preisProZimmer += anzahlTage * 15;
        }
    }

    //Doppel
    public Buchungssystem(DoppelRoom a, boolean fruehstueck, boolean balkon, int anzahlTage, Date zielDate, boolean einzelP) {
        buchungzaehler++;
        this.buchungsID = buchungzaehler;

        this.zielDate = zielDate;
        this.r = a;
        this.anzahlTage = anzahlTage;
        this.einzelPB = einzelP;

        a.balkon = balkon;
        a.belegt = true;
        a.preisProZimmer += anzahlTage * 30;

        //Preis anpassen
        if (balkon) {
            a.preisProZimmer += 50;
        }

        a.fruehstueck = fruehstueck;
        if (a.fruehstueck) {
            a.preisProZimmer += anzahlTage * 15;
        }

        if (einzelP) {
            a.preisProZimmer += 50;
        }
    }

    //Triple
    public Buchungssystem(TripleRoom a, boolean fruehstueck, boolean anzahlWC, int anzahlTage, Date zielDate) {
        buchungzaehler++;
        this.buchungsID = buchungzaehler;

        this.zielDate = zielDate;
        this.r = a;
        this.anzahlTage = anzahlTage;

        a.belegt = true;
        a.preisProZimmer += anzahlTage * 40;
        a.anzahlWC = anzahlWC;
        a.fruehstueck = fruehstueck;

        if (a.anzahlWC) {
            a.preisProZimmer = 2 * 50.0;
        } else {
            a.preisProZimmer = 1 * 50.0;
        }

        if (a.fruehstueck) {
            a.preisProZimmer += anzahlTage * 15;
        }
    }

    //Appartements
    public Buchungssystem(Appartements a, boolean zimmerservice, boolean haustier, int anzahlTage, int anzahlPersonen, Date zielDate) {
        buchungzaehler++;
        this.buchungsID = buchungzaehler;

        this.zielDate = zielDate;
        this.r = a;
        this.anzahlTage = anzahlTage;

        a.belegt = true;
        a.preisProZimmer += anzahlTage * 50;
        a.preisProZimmer += anzahlPersonen * 15;

        a.anzahlPersonen = anzahlPersonen;
        a.zimmerservice = zimmerservice;
        a.haustier = haustier;

        if (a.zimmerservice) {
            a.preisProZimmer += anzahlTage * 25;
        }

        if (a.haustier) {
            a.preisProZimmer += 30;
        }
    }

    public Buchungssystem(Wellnessbereich a, int buchungszeit) {
        buchungzaehler++;
        this.buchungsID = buchungzaehler;
        this.r = a;

        a.buchungszeit = buchungszeit;
        a.prostundekosten = buchungszeit * 5;

    }


    public void rechnung(Buchungssystem b) throws InterruptedException {

        for (int i = 0; i < alleBuchungen.length; i++) {
            if (alleBuchungen[i] == null) {
                alleBuchungen[i] = b;
                break;
            }
        }

        Thread.sleep(250);
        System.out.println(ANSI_CYAN + "-----------------------" + ANSI_RESET + "Rechnung" + ANSI_CYAN + "-----------------------" + ANSI_RESET);
        System.out.println("Buchungs ID: " + buchungsID);

        if (!(b.r.name.equals("Wellnessbad") || (b.r.name.equals("Saunabereich")))) {
            System.out.println("Anfangs Datum: " + sdf.format(zielDate));
            System.out.println("Zimmer Bezeichung: " + b.r.name);
            System.out.println("Anzahl Zimmer: " + b.r.anzahlZimmer);
            System.out.println("MWST: " + b.r.preisProZimmer * 0.19 + "€");
            System.out.println("Gesamtpreis: " + b.r.preisProZimmer + "€");
            System.out.println("Anzahl Tage: " + b.anzahlTage);
        }

        switch (b.r.name) {
            case "Einzel-Zimmer" -> {


                EinzelRoom einzel = (EinzelRoom) b.r;
                System.out.println(((einzel.fruehstueck) ? "Sie haben Ihren Aufenthalt mit Frühstück gebucht." : "Sie haben Ihren Aufenthalt ohne Frühstück gebucht."));
                System.out.println(((einzel.balkon) ? "Sie haben Ihr Zimmer mit Balkon gebucht." : "Sie haben Ihr Zimmer ohne Balkon gebucht."));
                System.out.println(ANSI_CYAN + "----------------------------------------------------------" + ANSI_RESET);
            }

            case "Doppel-Zimmer" -> {
                DoppelRoom doppel = (DoppelRoom) b.r;
                System.out.print((einzelPB) ? "Sie haben das Zimmer als Einzelperson gebucht. \n" : "");
                System.out.println(((doppel.fruehstueck) ? "Sie haben Ihren Aufenthalt mit Frühstück gebucht." : "Sie haben Ihren Aufenthalt ohne Frühstück gebucht."));
                System.out.println(((doppel.balkon) ? "Sie haben Ihr Zimmer mit Balkon gebucht." : "Sie haben Ihr Zimmer ohne Balkon gebucht."));
                System.out.println(ANSI_CYAN + "----------------------------------------------------------" + ANSI_RESET);
            }

            case "Triple-Zimmer" -> {
                TripleRoom triple = (TripleRoom) b.r;
                int anzahlwc = 0;
                if (triple.anzahlWC) {
                    anzahlwc = 2;
                } else {
                    anzahlwc = 1;
                }


                System.out.println(((triple.fruehstueck) ? "Sie haben Ihren Aufenthalt mit Frühstück gebucht." : "Sie haben Ihren Aufenthalt ohne Frühstück gebucht."));
                System.out.println("Anzahl Wcs : " + anzahlwc);
                System.out.println(ANSI_CYAN + "----------------------------------------------------------" + ANSI_RESET);
            }


            case "Appartements" -> {
                Appartements aparte = (Appartements) b.r;
                System.out.println("Anzahl Personen: " + aparte.anzahlPersonen);
                System.out.println(((aparte.zimmerservice) ? "Sie haben Ihr Appartement mit Zimmerservice gebucht." : "Sie haben Ihr Appartement ohne Zimmerservice gebucht."));
                System.out.println(((aparte.haustier) ? "Sie habe ein Haustier-freundliches Appartement gebucht." : "Sie habe ein Appartement ohne Haustier gebucht."));
                System.out.println(ANSI_CYAN + "----------------------------------------------------------" + ANSI_RESET);
            }
            case "Wellnessbad", "Saunabereich" -> {
                Wellnessbereich wellness = (Wellnessbereich) b.r;
                System.out.println("Wellnessbereich: " + wellness.name);
                System.out.println("Buchungszeit: " + wellness.buchungszeit + " std");
                System.out.println("Gesamtpreis: " + wellness.prostundekosten + " €");
                System.out.println(ANSI_CYAN + "----------------------------------------------------------" + ANSI_RESET);
            }
        }
    }


    public void stornieren(int id) {

        Date secondDate = new Date();
        for (int i = 0; i < alleBuchungen.length; i++) {
            boolean contains = IntStream.of(alleBuchungen[i].buchungsID).anyMatch(x -> x == id);
            if (contains) {
                long diffInMillies;
                long days = 0;
                int weeks = 0;

                if (alleBuchungen[i].r.name.equals("Wellnessbad") | alleBuchungen[i].r.name.equals("Saunabereich")) {
                    System.out.println("Erfolgreich BuchungsID: " + id + " Stoniert");
                } else {
                    System.out.println("Erfolgreich BuchungsID: " + id + " Stoniert");
                    Date firstDate = alleBuchungen[i].zielDate;
                    diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
                    days = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                    weeks = (int) Math.abs(diffInMillies / (24 * 60 * 60 * 1000 * 7));

                    if (days < 2) {
                        System.out.println("Sie haben 2 Tage vorher Stoniert deswegen müssen Sie 100% vom Gesamtbetrag bezahlen: " + alleBuchungen[i].r.preisProZimmer + "€");
                    } else if (weeks < 2) {
                        System.out.println("Sie haben 2 Wochen vorher Stoniert deswegen müssen Sie 50% vom Gesamtbetrag bezahlen: " + alleBuchungen[i].r.preisProZimmer * 0.50 + "€");
                    } else if (weeks < 4) {
                        System.out.println("Sie haben 4 Wochen vorher Stoniert deswegen müssen Sie 20% vom Gesamtbetrag bezahlen: " + alleBuchungen[i].r.preisProZimmer * 0.20 + "€");
                    }
                }


                if (alleBuchungen[i].r.name.equals("Einzel-Zimmer")) {
                    EinzelRoom r = (EinzelRoom) alleBuchungen[i].r;
                    r.preisProZimmer = 0.0;
                    r.fruehstueck = false;
                    r.balkon = false;
                    r.belegt = false;
                }
                if (alleBuchungen[i].r.name.equals("Doppel-Zimmer")) {

                    DoppelRoom r = (DoppelRoom) alleBuchungen[i].r;
                    r.preisProZimmer = 0.0;
                    r.fruehstueck = false;
                    r.balkon = false;
                    r.belegt = false;
                }
                if (alleBuchungen[i].r.name.equals("Triple-Zimmer")) {

                    TripleRoom r = (TripleRoom) alleBuchungen[i].r;
                    r.preisProZimmer = 0.0;
                    r.fruehstueck = false;
                    r.anzahlWC = false;
                    r.belegt = false;
                }
                if (alleBuchungen[i].r.name.equals("Appartements")) {

                    Appartements r = (Appartements) alleBuchungen[i].r;
                    r.preisProZimmer = 0.0;
                    r.haustier = false;
                    r.zimmerservice = false;
                    r.belegt = false;
                }

                Buchungssystem[] allbzwischen = new Buchungssystem[alleBuchungen.length];
                for(int x = 0; x < alleBuchungen.length;x++){
                    if(alleBuchungen[i] != null){
                        allbzwischen[x] = alleBuchungen[x];
                    }
                }
                alleBuchungen = allbzwischen;
                break;
            }
        }
    }

    public void onlineTicket(int id) {
        for (Buchungssystem bs : alleBuchungen) {
            if (r == null || bs == null) {
                System.out.println("Buchungs ID: " + id + " gibt es nicht.");
                break;
            } else {
                if (bs.r.name.equals("Wellnessbad") || bs.r.name.equals("Saunabereich")) {
                    if (bs.buchungsID == id) {
                        Wellnessbereich wellness = (Wellnessbereich) bs.r;
                        System.out.print(wellness.name + " wurde gebucht für " + wellness.buchungszeit + " Stunden.");
                        break;
                    }
                }
            }
        }
    }
}





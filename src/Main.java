import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

@SuppressWarnings({"InfiniteLoopStatement", "BusyWait"})

public class Main {

    //Standard wiederherstellen
    public static final String ANSI_RESET = "\u001B[0m";

    //Farben
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BLACK = "\u001B[30m";

    public static void main(String[] args) throws ParseException, InterruptedException {

        Room[] array = new Room[20];

        array[0] = new EinzelRoom(1, 0.0, true, "Einzel-Zimmer", false, false);
        array[1] = new EinzelRoom(1, 0.0, true, "Einzel-Zimmer", false, true);

        array[2] = new DoppelRoom(2, 0.0, false, "Doppel-Zimmer", false, false);
        array[3] = new DoppelRoom(2, 0.0, false, "Doppel-Zimmer", false, false);

        array[4] = new TripleRoom(3, 0.0, false, "Triple-Zimmer", false, false);
        array[5] = new TripleRoom(3, 0.0, false, "Triple-Zimmer", false, false);

        array[6] = new Appartements(4, 0.0, false, "Appartements", false, false, 0);
        array[7] = new Appartements(4, 0.0, false, "Appartements", false, false, 0);

        array[8] = new Wellnessbereich("Wellnessbad", false, 0, 20);
        array[9] = new Wellnessbereich("Wellnessbad", false, 0, 20);

        array[10] = new Wellnessbereich("Saunabereich", false, 0, 25);
        array[11] = new Wellnessbereich("Saunabereich", false, 0, 25);


        //Variablen zum umlegen
        int maxbuchungen;
        boolean balkon = false;
        boolean fruh = false;
        int anzahlWC;
        boolean anzahlWCe = false;
        boolean einzelP = false;
        boolean haustier = false;
        boolean zimmerservice = false;
        int anzahlTage = 0;
        int buchungszeit = 0;
        int anzahlPersonen = 0;
        boolean notfall = false;


        //region Zeichnen
        System.out.println(ANSI_CYAN + "__________________________________________________________________" + ANSI_RESET);
        System.out.println(ANSI_RED + "      ___           ___           ___           ___           ___ ");
        System.out.println(ANSI_CYAN + "     /\\__\\         /\\__\\         /\\__\\         /\\__\\         /\\__\\");
        System.out.println(ANSI_RED + "    /:/  /        /::\\  \\        \\:\\  \\       /::\\  \\       /:/  /");
        System.out.println(ANSI_CYAN + "   /:/__/        /:/\\:\\  \\        \\:\\  \\     /:/\\:\\  \\     /:/  / ");
        System.out.println(ANSI_RED + "  /::\\  \\ ___   /:/  \\:\\  \\       /::\\  \\   /::\\~\\:\\  \\   /:/  /  ");
        System.out.println(ANSI_CYAN + " /:/\\:\\  /\\__\\ /:/__/ \\:\\__\\     /:/\\:\\__\\ /:/\\:\\ \\:\\__\\ /:/__/  ");
        System.out.println(ANSI_RED + " \\/__\\:\\/:/  / \\:\\  \\ /:/  /    /:/  \\/__/ \\:\\~\\:\\ \\/__/ \\:\\  \\   ");
        System.out.println(ANSI_CYAN + "      \\::/  /   \\:\\  /:/  /    /:/  /       \\:\\ \\:\\__\\    \\:\\  \\  ");
        System.out.println(ANSI_RED + "      /:/  /     \\:\\/:/  /     \\/__/         \\:\\ \\/__/     \\:\\  \\ ");
        System.out.println(ANSI_CYAN + "     /:/  /       \\::/  /                     \\:\\__\\        \\:\\__\\");
        System.out.println(ANSI_RED + "     \\/__/         \\/__/                       \\/__/         \\/__/" + ANSI_RESET);
        System.out.println("© Created by: " + ANSI_GREEN + "Farshad," + ANSI_RESET + ANSI_BLUE + " Justus," + ANSI_RESET + ANSI_PURPLE + " Raphael," + ANSI_RESET + ANSI_YELLOW + " Behyad," + ANSI_RED + ANSI_CYAN + " Patrick," + ANSI_RESET + ANSI_RED + " Kiyan," + ANSI_RESET + ANSI_BLACK + " Thorsten" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "__________________________________________________________________" + ANSI_RESET);
        //endregion


        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date firstDate = new Date();

        //Buchung
        Buchungssystem b = new Buchungssystem();
        Scanner sc = new Scanner(System.in);
        System.out.println("Wollen Sie ein Zimmer (" + ANSI_RED + "Buchen" + ANSI_RESET + "), (" + ANSI_RED + "Stornieren" + ANSI_RESET + ") oder Ihr (" + ANSI_RED + "Online-Ticket" + ANSI_RESET + ") überprüfen? ");

        do {
            maxbuchungen = 0;
            String eingabe = sc.nextLine();

            switch (eingabe) {
                case "Buchen", "buchen" -> {
                    while (maxbuchungen < 3) {
                        sc = new Scanner(System.in);
                        //Einzel-Zimmer, Doppel-Zimmer, Triple-Zimmer, Appartements, Wellnessbad ,Saunabereich:
                        System.out.println("Was für ein Zimmer möchten Sie buchen?");
                        System.out.println("Auswahlmöglichkeiten: " + ANSI_RED + "Einzel-Zimmer" + ANSI_RESET + ", " + ANSI_RED + "Doppel-Zimmer" + ANSI_RESET + ", " + ANSI_RED + "Triple-Zimmer" + ANSI_RESET + ", " + ANSI_RED + "Appartements" + ANSI_RESET + ", " + ANSI_RED + "Wellnessbad" + ANSI_RESET + ", " + ANSI_RED + "Saunabereich" + ANSI_RESET);
                        eingabe = sc.nextLine();
                        //Switch case Eingabe für spezifische buchung

                        switch (eingabe) {
                            case "Einzel-Zimmer", "einzel-Zimmer", "einzel-zimmer", "Einzel-zimmer" -> {
                                System.out.println("Zum welchen Zeitpunkt wollen Sie buchen? Format(" + ANSI_RED + "dd.mm.yyyy" + ANSI_RESET + ")");
                                String anfangDate = sc.nextLine();
                                firstDate = sdf.parse(anfangDate);

                                System.out.println("Soll Ihr Zimmer einen Balkon haben?(" + ANSI_RED + "Ja" + ANSI_RESET + "/" + ANSI_RED + "Nein" + ANSI_RESET + ")");
                                balkon = sc.next().equalsIgnoreCase("Ja");

                                System.out.println("Möchten  Sie Frühstück dazu buchen?(" + ANSI_RED + "Ja" + ANSI_RESET + "/" + ANSI_RED + "Nein" + ANSI_RESET + ")");
                                fruh = sc.next().equalsIgnoreCase("Ja");

                                try {
                                    System.out.println("Für wie viele Tage möchten  Sie buchen?");
                                    anzahlTage = sc.nextInt();
                                } catch (InputMismatchException e) {
                                    sc = new Scanner(System.in);
                                    System.out.println("Bitte Tage als Zahl angeben.");
                                    System.out.println("Für wie viele Tage möchten  Sie buchen?");
                                    anzahlTage = sc.nextInt();
                                }

                            }
                            case "Doppel-Zimmer", "doppel-Zimmer", "Doppel-zimmer", "doppel-zimmer" -> {
                                System.out.println("Zum welchen Zeitpunkt wollen Sie buchen? Format(" + ANSI_RED + "dd.mm.yyyy" + ANSI_RESET + ")");
                                String anfangDate = sc.nextLine();
                                firstDate = sdf.parse(anfangDate);

                                System.out.println("Wollen Sie das Zimmer als einzel Person buchen? (" + ANSI_RED + "Ja" + ANSI_RESET + "/" + ANSI_RED + "Nein" + ANSI_RESET + ")");
                                einzelP = sc.next().equalsIgnoreCase("Ja");

                                System.out.println("Soll Ihr Zimmer einen Balkon haben?(" + ANSI_RED + "Ja" + ANSI_RESET + "/" + ANSI_RED + "Nein" + ANSI_RESET + ")");
                                balkon = sc.next().equalsIgnoreCase("Ja");

                                System.out.println("Möchten  Sie Frühstück dazu buchen? (" + ANSI_RED + "Ja" + ANSI_RESET + "/" + ANSI_RED + "Nein" + ANSI_RESET + ")");
                                fruh = sc.next().equalsIgnoreCase("Ja");

                                try {
                                    System.out.println("Für wie viele Tage möchten  Sie buchen?");
                                    anzahlTage = sc.nextInt();
                                } catch (InputMismatchException e) {
                                    sc = new Scanner(System.in);
                                    System.out.println("Bitte Tage als Zahl angeben.");
                                    System.out.println("Für wie viele Tage möchten  Sie buchen?");
                                    anzahlTage = sc.nextInt();
                                }
                            }
                            case "Triple-Zimmer", "triple-Zimmer", "triple-zimmer", "Triple-zimmer" -> {
                                System.out.println("Zum welchen Zeitpunkt möchten Sie buchen? Format(" + ANSI_RED + "dd.mm.yyyy" + ANSI_RESET + ")");
                                String anfangDate = sc.nextLine();
                                firstDate = sdf.parse(anfangDate);

                                System.out.println("Wie viele Wc`s soll ihr Triple-Zimmer haben (" + ANSI_RED + "1 oder 2" + ANSI_RESET + ")");
                                anzahlWC = sc.nextInt();
                                if (anzahlWC == 2) {
                                    anzahlWCe = true;
                                }

                                System.out.println("Möchten Sie Frühstück dazu buchen? (" + ANSI_RED + "Ja" + ANSI_RESET + "/" + ANSI_RED + "Nein" + ANSI_RESET + ")");
                                fruh = sc.next().equalsIgnoreCase("Ja");

                                try {
                                    System.out.println("Für wie viele Tage möchten Sie buchen?");
                                    anzahlTage = sc.nextInt();
                                } catch (InputMismatchException e) {
                                    sc = new Scanner(System.in);
                                    System.out.println("Bitte Tage als Zahl angeben.");
                                    System.out.println("Für wie viele Tage möchten Sie buchen?");
                                    anzahlTage = sc.nextInt();
                                }
                            }
                            case "Appartements", "appartements" -> {
                                System.out.println("Zum welchen Zeitpunkt möchten Sie buchen? Format(" + ANSI_RED + "dd.mm.yyyy" + ANSI_RESET + ")");
                                String anfangDate = sc.nextLine();
                                firstDate = sdf.parse(anfangDate);

                                System.out.println("Nehmen Sie ein Haustier mit? (" + ANSI_RED + "Ja" + ANSI_RESET + "/" + ANSI_RED + "Nein" + ANSI_RESET + ")");
                                haustier = sc.next().equalsIgnoreCase("Ja");

                                System.out.println("Möchten Sie einen Zimmerservice dazu buchen? (" + ANSI_RED + "Ja" + ANSI_RESET + "/" + ANSI_RED + "Nein" + ANSI_RESET + ")");
                                zimmerservice = sc.next().equalsIgnoreCase("Ja");

                                System.out.println("Für wie viele Tage möchten Sie buchen? Der Mindestaufenthalt für ein Appartement liegt bei 3 Tagen.");
                                do {
                                    try {
                                        anzahlTage = sc.nextInt();
                                    } catch (InputMismatchException e) {
                                        sc = new Scanner(System.in);
                                        System.out.println("Bitte Tage als Zahl angeben.");
                                        System.out.println("Für wie viele Tage möchten Sie buchen?");
                                        anzahlTage = sc.nextInt();
                                    }

                                } while (anzahlTage < 3);

                                System.out.println("Mit wie vielen Personen möchten Sie das Appartement buchen? (" + ANSI_RED + "max. 6 Personen" + ANSI_RESET + ")");
                                do {
                                    anzahlPersonen = sc.nextInt();
                                } while (anzahlPersonen > 6);
                            }
                            case "Wellnessbad", "Saunabereich", "wellnessbad", "saunabereich" -> {
                                System.out.println("Für wie lange möchten Sie buchen? (in Stunden)");
                                buchungszeit = sc.nextInt();
                            }
                            default -> {
                                System.out.println(ANSI_RED + "Ihre Eingabe :" + eingabe + " ist falsch." + ANSI_RESET);
                                System.out.println(ANSI_RED + "Bitte Starten Sie das Programm neu." + ANSI_RESET);
                                notfall = true;
                            }
                        }
                        if (notfall) {
                            break;
                        }


                        System.out.println("Warten Sie einen Moment Ihre Angaben werden überprüft.");


                        for (int k = 0; k <= 31; k++) {
                            System.out.print("-");
                        }
                        System.out.println();
                        System.out.print(" ");
                        System.out.print("1%");
                        for (int i = 1; i < 25; i++) {
                            System.out.print("|");
                            Thread.sleep(125);
                        }
                        System.out.print("100%");
                        System.out.println(" ");

                        for (int j = 0; j <= 31; j++) {
                            System.out.print("-");
                        }


                        System.out.println();
                        //Eingaben überprüfen und Buchung durchführen
                        try {
                            for (Room room : array) {
                                if (room.name.equalsIgnoreCase(eingabe)) {
                                    if (eingabe.equalsIgnoreCase("Einzel-Zimmer")) {
                                        EinzelRoom a = (EinzelRoom) room;
                                        if (!a.belegt) {
                                            b = new Buchungssystem(a, fruh, balkon, anzahlTage, firstDate);
                                            System.out.println("Buchung Erfolgreich von einem " + eingabe + ".");
                                            b.rechnung(b);
                                            maxbuchungen++;

                                        } else {
                                            System.out.println("Leider sind alle Einzel-Zimmer belegt.");
                                            System.out.println(ANSI_CYAN + "Für eine kleine Preiserhöhung wird ein Doppelzimmer gebucht. Bestätigen Sie mit: " + ANSI_RESET + "(" + ANSI_RED + "Ja" + ANSI_RESET + "/" + ANSI_RED + "Nein" + ANSI_RESET + ")");
                                            if (sc.next().equalsIgnoreCase("Ja")) {
                                                for (Room value : array) {
                                                    eingabe = "Doppel-Zimmer";
                                                    if (value.name.equalsIgnoreCase(eingabe)) {
                                                        assert value instanceof DoppelRoom;
                                                        DoppelRoom ab = (DoppelRoom) value;
                                                        if (!ab.belegt) {
                                                            b = new Buchungssystem(ab, fruh, balkon, anzahlTage, firstDate, true);
                                                            b.rechnung(b);

                                                            maxbuchungen++;
                                                            break;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        break;
                                    }

                                    if (eingabe.equalsIgnoreCase("Doppel-Zimmer")) {
                                        assert room instanceof DoppelRoom;
                                        DoppelRoom a = (DoppelRoom) room;
                                        if (!a.belegt) {
                                            b = new Buchungssystem(a, fruh, balkon, anzahlTage, firstDate, einzelP);
                                            b.rechnung(b);
                                            maxbuchungen++;
                                            break;
                                        }
                                    }

                                    if (eingabe.equalsIgnoreCase("Triple-Zimmer")) {
                                        assert room instanceof TripleRoom;
                                        TripleRoom a = (TripleRoom) room;
                                        if (!a.belegt) {
                                            b = new Buchungssystem(a, fruh, anzahlWCe, anzahlTage, firstDate);
                                            b.rechnung(b);
                                            maxbuchungen++;
                                            break;
                                        }
                                    }

                                    if (eingabe.equalsIgnoreCase("Appartements")) {
                                        assert room instanceof Appartements;
                                        Appartements a = (Appartements) room;
                                        if (!a.belegt) {
                                            if (anzahlTage > 3) {
                                                b = new Buchungssystem(a, zimmerservice, haustier, anzahlTage, anzahlPersonen, firstDate);
                                                b.rechnung(b);
                                                maxbuchungen++;
                                                break;
                                            }
                                        }
                                    }

                                    if (eingabe.equalsIgnoreCase("Saunabereich") || eingabe.equalsIgnoreCase("Wellnessbad")) {
                                        assert room instanceof Wellnessbereich;
                                        Wellnessbereich a = (Wellnessbereich) room;
                                        if (!a.belegt) {
                                            b = new Buchungssystem(a, buchungszeit);
                                            b.rechnung(b);
                                            maxbuchungen++;
                                            break;
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) {
                            System.out.println(" ");
                            System.out.println("###########################################################");
                            System.out.println("Leider können wir keine weitere : " + eingabe + " mehr buchen.");
                            System.out.println("###########################################################");
                        }

                        System.out.println(" ");
                        System.out.println(" ");
                        System.out.println("Sie können noch " + (3 - maxbuchungen) + " mal buchen.");

                        boolean nextBuchung = false;
                        sc = new Scanner(System.in);
                        if (maxbuchungen != 3) {
                            System.out.println(ANSI_CYAN + "Wollen Sie eine weitere Buchung durchführen? " + ANSI_RESET + "(" + ANSI_RED + "Ja" + ANSI_RESET + "/" + ANSI_RED + "Nein" + ANSI_RESET + ")");
                            nextBuchung = sc.next().equalsIgnoreCase("nein");
                        }


                        if (nextBuchung || maxbuchungen == 3) {
                            maxbuchungen = 3;
                            System.out.println(" ");
                            System.out.println(" ");
                            System.out.println("Neue Buchung: ");
                            System.out.println("Wollen Sie ein Zimmer (" + ANSI_RED + "Buchen" + ANSI_RESET + "), (" + ANSI_RED + "Stornieren" + ANSI_RESET + ") oder Ihr (" + ANSI_RED + "Online-Ticket" + ANSI_RESET + ") überprüfen? ");
                        }
                    }
                }
                case "Stornieren", "stornieren" -> {
                    System.out.println("Bitte geben Sie Ihre Buchungs ID an: ");
                    int i = sc.nextInt();
                    b.stornieren(i);
                }
                case "Online-Ticket", "online-ticket", "online-Ticket", "Online-ticket" -> {
                    System.out.println("Bitte geben Sie Ihre Buchungs ID an: ");
                    int i = sc.nextInt();
                    b.onlineTicket(i);
                }
            }
        } while (true);
    }

}

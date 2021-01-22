public class Appartements extends Room
{
    Boolean haustier;
    Boolean zimmerservice;
    int anzahlPersonen;


    public Appartements (int anzahlZimmer, double preisProZimmer, boolean belegt, String name, boolean haustier, boolean zimmerservice, int anzahlPersonen)
    {
        super(anzahlZimmer,  preisProZimmer, belegt,name);
        this.haustier = haustier;
        this.zimmerservice = zimmerservice;
        this.anzahlPersonen  = anzahlPersonen;
    }
}

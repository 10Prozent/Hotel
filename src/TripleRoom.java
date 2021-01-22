public class TripleRoom extends Room
{
    boolean fruehstueck;
    boolean anzahlWC;

    public TripleRoom(int anzahlZimmer, double preisProZimmer, boolean belegt, String name, boolean fruehstueck, boolean anzahlWC)
    {
        super(anzahlZimmer,  preisProZimmer, belegt,name);
        this.anzahlWC = anzahlWC;
        this.fruehstueck = fruehstueck;
    }
}

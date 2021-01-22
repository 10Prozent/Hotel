public class EinzelRoom extends Room
{
    boolean fruehstueck;
    boolean balkon;

    public EinzelRoom(int anzahlZimmer, double preisProZimmer, boolean belegt, String name, boolean fruehstueck, Boolean balkon)
    {
        //Super übergabe an Hauptklasse
        super(anzahlZimmer,  preisProZimmer, belegt, name);
        this.fruehstueck = fruehstueck;
        this.balkon = balkon;
    }
}

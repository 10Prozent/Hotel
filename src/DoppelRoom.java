public class DoppelRoom extends Room
{
    boolean fruehstueck;
    boolean balkon;

    public DoppelRoom(int anzahlZimmer, double preisProZimmer, boolean belegt, String name, boolean fruehstueck, Boolean balkon)
    {
        super(anzahlZimmer,  preisProZimmer, belegt,name);
        this.fruehstueck = fruehstueck;
        this.balkon = balkon;
    }
}

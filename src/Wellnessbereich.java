public class Wellnessbereich extends Room
{
    int buchungszeit;
    double prostundekosten;

    public Wellnessbereich(String name, boolean belegt,int buchungszeit,double prostundekosten)
    {
        super(name,belegt);
        this.buchungszeit = buchungszeit;
        this.prostundekosten = prostundekosten;
    }
}

//Superclass
public class Room
{
    //alle attribute werden vererbt an andere unterclassen
    String name;
    int anzahlZimmer;
    Double preisProZimmer ;
    boolean belegt;


    //Konstruktur unter class
    public Room(int anzahlZimmer, double preisProZimmer, boolean belegt , String name)
    {
        this.anzahlZimmer = anzahlZimmer;
        this.preisProZimmer = preisProZimmer * anzahlZimmer;
        this.belegt = belegt;
        this.name = name;
    }

    //Konstruktur Wellnessbereich
    public Room(String name, boolean belegt){
        this.name = name;
        this.belegt = belegt;
    }
}

package tp2.tpalt.upmc.quizzforfriend;

/**
 * Created by macbookpro on 10/10/2016.
 */

public class Joker {
    /** Constructeur privé */
    private Joker(){
        this.elemineMauvaiseReponse = true;
        this.fiftyFify = true;
    }

    /** Instance unique non préinitialisée */
    private static Joker INSTANCE = null;

    /** Point d'accès pour l'instance unique du singleton */
    public static Joker getInstance()
    {
        if (INSTANCE == null)
        { 	INSTANCE = new Joker();
        }
        return INSTANCE;
    }
    private boolean elemineMauvaiseReponse = false;
    private boolean fiftyFify = false;

    public boolean isElemineMauvaiseReponse() {
        return elemineMauvaiseReponse;
    }
    public boolean isFiftyFify() {
        return fiftyFify;
    }
}

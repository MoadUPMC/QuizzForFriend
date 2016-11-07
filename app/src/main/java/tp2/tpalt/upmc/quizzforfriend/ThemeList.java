package tp2.tpalt.upmc.quizzforfriend;

/**
 * Created by alexmaxime on 06/11/2016.
 */
public class ThemeList {

    public String titre;

    public String description;

    public ThemeList() {
        this("","");
    }

    public ThemeList(String titre, String description) {
        this.titre = titre;
        this.description = description;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

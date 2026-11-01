package model.core;

public enum MenuOption {
    NOUVELLE_PARTIE("Nouvelle Partie"),
    PARAMETRES("Paramètres"),
    QUITTER("Quitter");

    private final String label;

    MenuOption(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

package model.core;

public enum PauseOption {
    REPRENDRE("Reprendre"),
    MENU("Retour au menu principal");

    private final String label;

    PauseOption(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

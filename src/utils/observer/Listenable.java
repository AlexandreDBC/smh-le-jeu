package utils.observer;

/**
 * Interface du patron de conception Observateur (sujet observable).
 * Toute classe souhaitant être observée doit implémenter cette interface
 * afin de permettre l'abonnement et le désabonnement de {@link Listener}.
 */
public interface Listenable {

    /**
     * Ajoute un observateur à la liste des abonnés.
     *
     * @param listener l'observateur à enregistrer
     */
    void addListener(Listener listener);

    /**
     * Retire un observateur de la liste des abonnés.
     *
     * @param listener l'observateur à supprimer
     */
    void removeListener(Listener listener);
}

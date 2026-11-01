package utils.observer;

/**
 * Interface du patron de conception Observateur.
 * Les classes souhaitant réagir aux changements d'un {@link Listenable}
 * doivent implémenter cette interface.
 */
public interface Listener {

    /**
     * Appelée par le sujet observé lorsqu'un changement d'état survient.
     */
    void update();
}

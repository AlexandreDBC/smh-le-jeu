package utils.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstraite fournissant une implémentation de base de {@link Listenable}.
 */
public abstract class AbstractListenable implements Listenable {

    /** 
     *  Liste des observateurs abonnés à ce sujet.
     */
    private final List<Listener> listeners = new ArrayList<>();

    @Override
    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    /**
     * Notifie tous les observateurs enregistrés d'un changement d'état.
     * Doit être appelée par les sous-classes après toute modification d'état.
     */
    protected void notifyListeners() {
        for (Listener listener : listeners) {
            listener.update();
        }
    }
}

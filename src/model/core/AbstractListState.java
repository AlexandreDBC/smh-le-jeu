package model.core;

import java.util.List;

public abstract class AbstractListState<T> implements GameState {
    private int selectedIndex;

    public AbstractListState() {
        this.selectedIndex = 0;
    }

    protected abstract List<T> getItems();

    @Override
    public void enter() {
        selectedIndex = 0;
    }

    @Override
    public void update() {
    }

    @Override
    public void exit() {
        selectedIndex = 0;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void nextOption() {
        if (getItems().isEmpty()) return;
        selectedIndex = (selectedIndex + 1) % getItems().size();
    }

    public void previousOption() {
        if (getItems().isEmpty()) return;
        selectedIndex = (selectedIndex - 1 + getItems().size()) % getItems().size();
    }

    protected void resetSelectedIndex() {
        selectedIndex = 0;
    }

    public T getSelectedOption() {
        return getItems().get(selectedIndex);
    }
}

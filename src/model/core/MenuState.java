package model.core;

import java.util.Arrays;
import java.util.List;

public class MenuState extends AbstractListState<MenuOption> {

    private final List<MenuOption> options = Arrays.asList(MenuOption.values());

    @Override
    protected List<MenuOption> getItems() {
        return options;
    }

    public MenuOption[] getOptions() {
        return MenuOption.values();
    }
}

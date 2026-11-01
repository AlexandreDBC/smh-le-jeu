package model.core;

import java.util.Arrays;
import java.util.List;

public class PauseState extends AbstractListState<PauseOption> {

    private final List<PauseOption> options = Arrays.asList(PauseOption.values());

    @Override
    protected List<PauseOption> getItems() {
        return options;
    }

    public PauseOption[] getOptions() {
        return PauseOption.values();
    }
}

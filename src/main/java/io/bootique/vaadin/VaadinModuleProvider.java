package io.bootique.vaadin;

import com.google.inject.Module;
import io.bootique.BQModuleProvider;

public class VaadinModuleProvider implements BQModuleProvider {
    @Override
    public Module module() {
        return new VaadinModule();
    }
}

package org.example.services;


import org.example.helpers.AppHelper;
import org.example.model.Registry;
import org.example.repository.Repository;

public class RegistryService implements Service<Registry> {
    private final AppHelper<Registry> appHelperRegistry;
    private final Repository<Registry> repositoryRegistry;


    public RegistryService(AppHelper<Registry> appHelperRegistry,Repository<Registry> repositoryRegistry) {
        this.appHelperRegistry = appHelperRegistry;
        this.repositoryRegistry = repositoryRegistry;
    }

    @Override
    public boolean add() {
        Registry registry = appHelperRegistry.create();
        if(registry != null) {
            repositoryRegistry.save(registry);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean print() {
        return appHelperRegistry.printList(getRepository().load());
    }

    @Override
    public boolean edit() {
        return false;
    }

    @Override
    public Repository<Registry> getRepository() {
        return repositoryRegistry;
    }
}
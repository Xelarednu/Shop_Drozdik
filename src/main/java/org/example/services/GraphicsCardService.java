package org.example.services;

import org.example.helpers.AppHelper;
import org.example.model.GraphicsCard;
import org.example.repository.Repository;

public class GraphicsCardService implements Service<GraphicsCard> {
    private final AppHelper<GraphicsCard> graphicsCardAppHelper ;
    private final Repository<GraphicsCard> graphicsCardRepository;

    public GraphicsCardService(AppHelper<GraphicsCard> graphicsCardAppHelper, Repository<GraphicsCard> graphicsCardRepository) {
        this.graphicsCardAppHelper = graphicsCardAppHelper;
        this.graphicsCardRepository = graphicsCardRepository;
    }

    @Override
    public boolean add() {
        GraphicsCard graphicsCard = graphicsCardAppHelper.create();

        if (graphicsCard != null) {
            graphicsCardRepository.save(graphicsCard);
            return true;
        }

        return false;
    }

    @Override
    public boolean edit() {
        GraphicsCard graphicsCard = graphicsCardAppHelper.edit(this.getRepository().load());

        if (graphicsCard != null) {
            graphicsCardRepository.save(graphicsCard);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete() {
        GraphicsCard graphicsCard = graphicsCardAppHelper.delete(this.getRepository().load());

        if (graphicsCard != null) {
            graphicsCardRepository.delete(graphicsCard);
            return true;
        }

        return false;
    }

    @Override
    public boolean print() {
        return graphicsCardAppHelper.printList(this.getRepository().load());
    }

    @Override
    public Repository<GraphicsCard> getRepository() {
        return graphicsCardRepository;
    }
}
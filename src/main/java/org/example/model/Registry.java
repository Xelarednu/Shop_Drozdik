package org.example.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Registry implements Serializable {
    private UUID id;
    private User user;
    private GraphicsCard graphicsCard;
    private LocalDate sellDate;

    public Registry() {
    }

    public Registry(User user, GraphicsCard graphicsCard, LocalDate sellDate) {
        this.user = user;
        this.graphicsCard = graphicsCard;
        this.sellDate = sellDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public GraphicsCard getGraphicsCard() {
        return graphicsCard;
    }

    public void setGraphicsCard(GraphicsCard graphicsCard) {
        this.graphicsCard = graphicsCard;
    }

    public LocalDate getSellDate() {
        return sellDate;
    }

    public void setSellDate(LocalDate sellDate) {
        this.sellDate = sellDate;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Registry registry)) return false;

        return Objects.equals(id, registry.id) && Objects.equals(user, registry.user) && Objects.equals(graphicsCard, registry.graphicsCard) && Objects.equals(sellDate, registry.sellDate);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(user);
        result = 31 * result + Objects.hashCode(graphicsCard);
        result = 31 * result + Objects.hashCode(sellDate);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Registry{");
        sb.append("id=").append(id);
        sb.append(", user=").append(user);
        sb.append(", graphicsCard=").append(graphicsCard);
        sb.append(", sellDate=").append(sellDate);
        sb.append('}');
        return sb.toString();
    }
}
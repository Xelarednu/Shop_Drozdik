package org.example.model;

import java.io.Serializable;
import java.util.Objects;

public class GraphicsCard implements Serializable {
    private long id;
    private String name;
    private String manufacturer;
    private int memorySize;
    private int releaseYear;
    private double price;

    public GraphicsCard() {
        id += 1;
    }

    public GraphicsCard(String name, int price ,String manufacturer, int memorySize, int releaseYear) {
        id += 1;
        this.name = name;
        this.price = price;
        this.manufacturer = manufacturer;
        this.memorySize = memorySize;
        this.releaseYear = releaseYear;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(int memorySize) {
        this.memorySize = memorySize;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GraphicsCard that)) return false;

        return id == that.id && memorySize == that.memorySize && releaseYear == that.releaseYear && Double.compare(price, that.price) == 0 && Objects.equals(name, that.name) && Objects.equals(manufacturer, that.manufacturer);
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(manufacturer);
        result = 31 * result + memorySize;
        result = 31 * result + releaseYear;
        result = 31 * result + Double.hashCode(price);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GraphicsCard{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", manufacturer='").append(manufacturer).append('\'');
        sb.append(", memorySize=").append(memorySize);
        sb.append(", releaseYear=").append(releaseYear);
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }
}
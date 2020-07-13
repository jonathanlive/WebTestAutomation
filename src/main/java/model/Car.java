package model;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Car {

    private String brand;
    private String model;
    private String price;
    private String year;
    private String km;
    private String color;
    private String exchange;

    public Car(String brand, String model, String price, String year, String km, String color, String exchange) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.year = year;
        this.km = km;
        this.color = color;
        this.exchange = exchange;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", price='" + price + '\'' +
                ", year='" + year + '\'' +
                ", km='" + km + '\'' +
                ", color='" + color + '\'' +
                ", exchange='" + exchange + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(brand, car.brand) &&
                Objects.equals(model, car.model) &&
                Objects.equals(price, car.price) &&
                Objects.equals(year, car.year) &&
                Objects.equals(km, car.km) &&
                Objects.equals(color, car.color) &&
                Objects.equals(exchange, car.exchange);
    }

}

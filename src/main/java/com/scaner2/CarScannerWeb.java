package com.scaner2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

class Car {
    private String make;
    private String model;

    public Car(String make, String model) {
        this.make = make;
        this.model = model;
    }

    public String getMake() {
        return make;
    }
    public String getModel() {
        return model;
    }

    public String toString() {
        return make + " " + model;
    }
}

public class CarScannerWeb {
    public static void main(String[] args) {
        //Web site location
        final String url = "https://www.kbb.com/car-make-model-list/new/a/model/";
        final List<Car> cars = new ArrayList<>();
    try {
        final Document document = Jsoup.connect(url).get();

        // Extracted data from the website this took a day 
        for (Element row : document.select("table.css-1q107tk.ee33uo30 tr")) {
            final String model = row.select(".css-irk93x:nth-of-type(2)").text();
            final String make = row.select(".css-irk93x:nth-of-type(3)").text();
            Car car = new Car(make, model);
            cars.add(car);
        }

        // Displaying all read data
        for (Car car : cars) {
            System.out.println(car);
        }

        // Scanner what else easy also searches by model
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a model to search: ");
        String searchModel = scanner.nextLine().trim().toLowerCase();

        for (Car car : cars) {
            if (car.getModel().toLowerCase().contains(searchModel)) {
                System.out.println("Found: " + car);
            }
        }
    }
    catch (IOException e) {
        e.printStackTrace();
    }
}
}
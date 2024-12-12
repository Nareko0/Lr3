package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<Sneakers> sneakersList = new ArrayList<>();

        sneakersList.add(new FootballSneakers("Nike Mercurial", 150.0, new Producer("Nike", "USA")));
        sneakersList.add(new BasketballSneakers("Adidas Superstar", 100.0, new Producer("Adidas", "Germany")));
        sneakersList.add(new FootballSneakers("Puma Future", 140.0, new Producer("Puma", "Germany")));
        sneakersList.add(new BasketballSneakers("Reebok Classic", 70.0, new Producer("Reebok", "USA")));
        sneakersList.add(new FootballSneakers("Nike Phantom", 200.0, new Producer("Nike", "USA")));
        sneakersList.add(new BasketballSneakers("Air Jordan 1", 180.0, new Producer("Nike", "USA")));
        sneakersList.add(new FootballSneakers("Adidas Copa", 120.0, new Producer("Adidas", "Germany")));
        sneakersList.add(new BasketballSneakers("New Balance 574", 90.0, new Producer("New Balance", "USA")));
        sneakersList.add(new FootballSneakers("Asics Gel-Peake", 110.0, new Producer("Asics", "Japan")));
        sneakersList.add(new BasketballSneakers("Brooks Ghost", 110.0, new Producer("Brooks", "USA")));

        // количество производителей
        int numProducers = countProducers(sneakersList);
        System.out.println("Количество производителей: " + numProducers);

        // средняя стоимость кроссовок для каждого производителя
        Map<String, Double> avgCostByProducer = calculateAvgCostByProducer(sneakersList);
        for (Map.Entry<String, Double> entry : avgCostByProducer.entrySet()) {
            System.out.println("Средняя стоимость для производителя " + entry.getKey() + ": " + entry.getValue());
        }

        // Рассчитайте среднюю стоимость кроссовок для каждого типа
        Map<String, Double> avgCostByType = calculateAvgCostByType(sneakersList);
        for (Map.Entry<String, Double> entry : avgCostByType.entrySet()) {
            System.out.println("Средняя стоимость для типа " + entry.getKey() + ": " + entry.getValue());
        }
    }

    private static int countProducers(List<Sneakers> sneakersList) {
        List<String> producers = new ArrayList<>();
        for (Sneakers sneakers : sneakersList) {
            if (!producers.contains(sneakers.getProducer().getName())) {
                producers.add(sneakers.getProducer().getName());
            }
        }
        return producers.size();
    }

    private static Map<String, Double> calculateAvgCostByProducer(List<Sneakers> sneakersList) {
        Map<String, List<Double>> producerCosts = new HashMap<>();
        for (Sneakers sneakers : sneakersList) {
            String producerName = sneakers.getProducer().getName();
            producerCosts.putIfAbsent(producerName, new ArrayList<>());
            producerCosts.get(producerName).add(sneakers.getCost());
        }

        Map<String, Double> avgCostByProducer = new HashMap<>();
        for (Map.Entry<String, List<Double>> entry : producerCosts.entrySet()) {
            double sum = 0;
            for (Double cost : entry.getValue()) {
                sum += cost;
            }
            avgCostByProducer.put(entry.getKey(), sum / entry.getValue().size());
        }
        return avgCostByProducer;
    }

    private static Map<String, Double> calculateAvgCostByType(List<Sneakers> sneakersList) {
        Map<String, List<Double>> typeCosts = new HashMap<>();
        for (Sneakers sneakers : sneakersList) {
            String type = sneakers.getType();
            typeCosts.putIfAbsent(type, new ArrayList<>());
            typeCosts.get(type).add(sneakers.getCost());
        }

        Map<String, Double> avgCostByType = new HashMap<>();
        for (Map.Entry<String, List<Double>> entry : typeCosts.entrySet()) {
            double sum = 0;
            for (Double cost : entry.getValue()) {
                sum += cost;
            }
            avgCostByType.put(entry.getKey(), sum / entry.getValue().size());
        }
        return avgCostByType;
    }
}
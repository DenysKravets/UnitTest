package org.example;

import java.util.*;

public class Main {

    private static final double EPS = 1e-6;

    public static void main(String[] args) {
        new Main().start();
    }

    private void start() {
        double start = 0.5;
        double end = 2.0;
        double step = 5e-3;
        double a = 20.3;
        showMinValue(start, end, step, a);
        showMaxValue(start, end, step, a);
    }

    public void showMinValue(double start, double end, double step, double a) {
        int index = findMin(start, end, step, a);
        mapLogOrSin(start, end, step, a).forEach(function -> {
            if (function.index() == index)
                System.out.println(function);
        });
    }

    public void showMaxValue(double start, double end, double step, double a) {
        int index = findMax(start, end, step, a);
        mapLogOrSin(start, end, step, a).forEach(function -> {
            if (function.index() == index)
                System.out.println(function);
        });
    }

    public double logOrSin(double x, double a) {
        if (x > 1.2 + EPS)
            return Math.log(x + 1.0);
        else
            return sinusSqrt(x, a) * sinusSqrt(x, a);
    }

    private double sinusSqrt(double x, double a) {
        return Math.sin(Math.sqrt(a * x));
    }

    public int getNumberOfElements(double start, double end, double step) {
        return (int) Math.round((end - start) / step) + 1;
    }

    public List<Function> mapLogOrSin(double start, double end, double step, double a) {
        List<Function> returnList = new ArrayList<>();
        int size = getNumberOfElements(start, end, step);
        for (int i = 0; i < size; i++) {
            double x = start + step * i;
            returnList.add(new Function(x ,logOrSin(x, a), i));
        }
        return returnList;
    }

    public int findMax(double start, double end, double step, double a) {
        List<Function> doubleList = mapLogOrSin(start, end, step, a);
        Optional<Function> funcOptional = doubleList.stream().max(Comparator.comparingDouble(Function::y)).stream().findFirst();
        if (funcOptional.isEmpty())
            return -1;
        return funcOptional.get().index();
    }

    public int findMin(double start, double end, double step, double a) {
        List<Function> doubleList = mapLogOrSin(start, end, step, a);
        Optional<Function> funcOptional = doubleList.stream().min(Comparator.comparingDouble(Function::y)).stream().findFirst();
        if (funcOptional.isEmpty())
            return -1;
        return funcOptional.get().index();
    }

    public double sum(double start, double end, double step, double a) {
        List<Function> funcList = mapLogOrSin(start, end, step, a);
        return funcList.stream().mapToDouble(Function::y).sum();
    }

    public double average(double start, double end, double step, double a) {
        List<Function> funcList = mapLogOrSin(start, end, step, a);
        OptionalDouble optionalDouble = funcList.stream().mapToDouble(Function::y).average();
        if (optionalDouble.isEmpty())
            return 0.0;
        return optionalDouble.getAsDouble();
    }

    public record Function(Double x, Double y, Integer index) {
        @Override
        public String toString() {
            return "f(" + x +
                    ", " + y +
                    ", " + index +
                    ')';
        }
    }
}
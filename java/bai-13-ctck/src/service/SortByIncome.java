package service;

import java.util.Comparator;

public class SortByIncome implements Comparator<IEmployee> {
    @Override
    public int compare(IEmployee o1, IEmployee o2) {
        return Double.compare(o1.getIncome(), o2.getIncome());
    }
}

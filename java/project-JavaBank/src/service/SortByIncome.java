package service;

import java.util.Comparator;

public class SortByIncome implements Comparator<IUser> {
    @Override
    public int compare(IUser o1, IUser o2) {
        return Double.compare(o1.getIncome(), o2.getIncome());
    }
}

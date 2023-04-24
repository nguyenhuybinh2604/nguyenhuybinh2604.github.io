package service;

import java.util.Comparator;

public class SortByName implements Comparator<IEmployee>{

    @Override
    public int compare(IEmployee o1, IEmployee o2) {
        return o1.getName().compareTo(o2.getName());
    }
    
}

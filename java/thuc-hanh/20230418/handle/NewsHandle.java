package handle;

import java.util.Scanner;

import entity.News;

public class NewsHandle {
    public News addNews(Scanner sc) {
        String title;
        String author;
        String publicDate;
        double rate;
        System.out.println("Nhap title:");
        title = sc.nextLine();
        System.out.println("Nhap author:");
        author = sc.nextLine();
        System.out.println("Nhap ngay xuat ban:");
        publicDate = sc.nextLine();
        System.out.println("Nhap rate:");
        rate = Double.parseDouble(sc.nextLine());
        return new News(title, author, publicDate, rate);
    }
}
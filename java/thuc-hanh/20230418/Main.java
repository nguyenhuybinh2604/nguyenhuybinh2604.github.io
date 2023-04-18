import java.util.Scanner;

import entity.News;
import handle.NewsHandle;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        NewsHandle newsHandle = new NewsHandle();
        News news = newsHandle.addNews(sc);      
        System.out.println(news.display());

    }
}
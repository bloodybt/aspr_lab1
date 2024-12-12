import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.Semaphore;

public class Kitchen implements Runnable {
    private int count = 0;
    // кількість плит
    private final int pices;
    final Semaphore semaphore;
    // флаг що кухня працює
    private boolean isWork = true;
    // список страв що готувались
    private List<Thread> foods = new ArrayList<>();

    public Kitchen(int pices) {
        this.pices = pices;
        this.semaphore = new Semaphore(pices);

    }
    // додавання страви в чергу
    public    void addFood(String foodName){
        count++;
        System.out.printf("Отримали замовлення на \"%s_%d \" o %s \n", foodName, count, LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        Thread  food  =  new Thread(new Food(String.format("%s_%d",foodName,count),semaphore));
        food.start();
        this.foods.add(food);
    }

    @Override
    public void run() {

        System.out.println("Кухня починає роботу o "+LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));

        while (isWork){
              // якщо поток прервано - то заврешимо роботу кухні
             if  (Thread.currentThread().isInterrupted()) break;


           }
           System.out.println("Кухня закінчила роботу o "+LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
           // терміново заваршимо всі наші страви які ще готуються
          foods.forEach(f->{
              if (f.isAlive()) f.interrupt();;
           });


    }
    public synchronized void stop(){
        this.isWork = false;
    }

}

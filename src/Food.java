import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;

class Food implements Runnable {
    final private String name;
    private Semaphore sem;
    int time = 1000;


    private boolean done = false;

    public Food(String name,Semaphore semaphore) {
        this.name = name;
        this.sem = semaphore;
        this.time = this.time + new Random().nextInt(4500);
    }





    @Override
    public void run() {
        try
        {

            if (!done) {

                sem.acquire();
                System.out.printf ("Страва %s починає готуватись o %s \n",name, LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));


                sleep(time);
                done = true;

                System.out.printf("Страва %s закінчилась готуватись o %s\n",name,LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                sem.release();


                sleep(100);
            }

        }
        catch(InterruptedException e) {
            System.out.printf("Страву %s припинили готувати о %s\n",name,LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        }
    }

}
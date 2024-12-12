

import java.util.Timer;
import java.util.TimerTask;



public class Main {

    public static void main(String[] args) throws InterruptedException {

        final Cafe cafe = new Cafe();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                cafe.terminate();
            }

        },10000); // через який час завершимо роботу кухні

        Thread work = new Thread(cafe);
        work.run();
        // відмінемо таймер
        timer.cancel();


    }








}
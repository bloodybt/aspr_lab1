import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;


public class Cafe implements Runnable {
    private boolean work = true;
    static String[] menu = {"борщ","салат","відбивна","смажена риба","гарнір","печене м'ясо"};
    public synchronized void terminate(){

        System.out.println("Кафе завершує роботу! "+ LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        this.work=false;
    }

    @Override
    public void run() {

      Kitchen kitchen = new Kitchen(4); new Thread(kitchen).start();

      while (work){
           int r  = new Random().nextInt(menu.length);
           String food = menu[r];
          try {
              Thread.sleep(new Random().nextInt(100)+500);
          } catch (InterruptedException e) {
              throw new RuntimeException(e);
          }
           kitchen.addFood(food);



      }
      kitchen.stop();
      Thread.currentThread().interrupt();
    }
}

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(100);
        Semaphore ticketWindow = new Semaphore(4);

        System.out.println("Автовокзал. Рейс Бишкек-ОШ. ");

        for (int i = 1; i <= 100; i++) {
            new PassengerThread(ticketWindow, i, countDownLatch).start();
        }
        while (countDownLatch.getCount() >= 100) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Водитель автобуса ждет, пока автобус полностью не набереться");
            break;
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Автобус с пассажирами выезжает в ОШ. ");
    }
}

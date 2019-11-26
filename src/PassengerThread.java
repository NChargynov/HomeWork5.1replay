import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class PassengerThread extends Thread {
    Semaphore cashBox;
    CountDownLatch countDownLatch;
    int idPassenger;

    PassengerThread(Semaphore cashBox, int idPassenger, CountDownLatch countDownLatch) {
        this.cashBox = cashBox;
        this.idPassenger = idPassenger;
        this.countDownLatch = countDownLatch;
    }

    public void run() {
        try {
            sleep(500);
            cashBox.acquire();

            System.out.println("Пассажир " + idPassenger + " заходит в кассу ");
            sleep(500);

            System.out.println("Пассажир " + idPassenger + " купил билет и выходит из кассы");
            cashBox.release();
            sleep(500);

            System.out.println("Пассажир " + idPassenger + " сел в автобус");
            sleep(500);

            System.out.println("Пассажир " + idPassenger + " ждет в автобусе");
            sleep(500);

            countDownLatch.countDown();

        } catch (InterruptedException e) {
            System.out.println("У пассажира " + idPassenger + " нету денег для покупки билета");
        }
    }
}

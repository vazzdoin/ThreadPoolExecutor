import com.threalpool.executor.ThreadPool;

public class Main {
    public static void main(String[] args) {

        ThreadPool threadPool = new ThreadPool(3,5);

        for(int i=1; i<6; i++) {
            int finalI = i;
            threadPool.execute(() -> method1("task"+finalI));
        }

        System.out.println("Hello world!");
    }

    private static void method1(String task) {
        for(int i=0; i< 5; i++) {
            System.out.println(Thread.currentThread().getName()+" : "+ task + " : "+i);
        }
    }
}
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import static java.lang.Math.*;

public class Model {


    public static void main(String[] args) throws FileNotFoundException {

        TRocket rocket1 =  new TRocket(0, 0, 0.01, toRadians(45), 200, 500, 1);
        TRocket rocket2 =  new TRocket(0, 0, 0.01, toRadians(60), 200, 100, 2);
        TRocket rocket3 =  new TRocket(0, 0, 0.01, toRadians(30), 200, 1000, 3);

        RLS rls = new RLS(50, 50, 40, rocket1, rocket2, rocket3);

        Thread miTr = new Thread(rls);
        Thread miT1 = new Thread(rocket1);
        Thread miT2 = new Thread(rocket2);
        Thread miT3 = new Thread(rocket3);

        miTr.start();
        miT1.start();
        miT2.start();
        miT3.start();

    }
}


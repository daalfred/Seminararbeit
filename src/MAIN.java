import java.io.IOException;

/**
 * Created by Michael on 05.11.2015.
 */
public class MAIN {

    public static void main(String[] args) throws IOException {
        EKG.calcDFT(EKG.readBMP("C:\\Users\\Michael\\Documents\\W-Seminar\\EKG.txt", 153, 542), 5*60/58, 42);
        /*double[] bla = EKG.readBMP("C:\\Users\\Michael\\Documents\\W-Seminar\\EKG.txt", 153, 542);
        System.out.println(bla.length);
        for (int i = 0; i < bla.length; i++) {
            System.out.print(bla[i]+" , ");
        }*/
    }

}

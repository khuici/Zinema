import java.util.Scanner;

public class michael {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String BERDEA = "\u001B[32m";
        String URDINA = "\u001B[34m";
        String HORIA = "\u001B[33m";
        String reset = "\u001B[0m";
        String GORRIA = "\u001B[31m";   

        System.out.println(
                BERDEA + "Ongi etorri ZinemaUsurbil-ko aplikaziora, mesedez hauetako bat aukeratu zenbakia ipiniz:");
        System.out.println(URDINA + "1.Aste eguna ");
        System.out.println("2.Kokapena ");
        System.out.println("3.Irekiera ordutegia ");
        System.out.println("4.Informazio orokorra ");
        System.out.println("5.Irten" + reset);

        int aukera1 = sc.nextInt();

        switch (aukera1) {
            case 1:

                System.out.println(BERDEA + "Aste eguna aukeratu:" + reset);
                System.out.println(URDINA + "1.Astelehena");
                System.out.println("2.Osteguna");
                System.out.println("3.Asteazkena ");
                System.out.println("4.Osteguna");
                System.out.println("5.Ostirala");
                System.out.println("6.Larunbata");
                System.out.println("7.Igandea" + reset);

                int eguna = sc.nextInt();

                System.out.println( HORIA +"Gelak eta pelikulak ikusi nahi dituzu sarrerak erosteko?"+ reset);
                System.out.println(BERDEA+"1.BAI");
                System.out.println(GORRIA+"2.EZ");

                int baiez=sc.nextInt();

               if(baiez==1){

                System.out.println("Mesedez gela aukeratu:");

                System.out.println("1.Umeen gela");
                System.out.println("2.Superheroien gela ");
                System.out.println("3.Thriller gela");
                System.out.println("4.Zientzia fikziozko gela");
                System.out.println("5.Komedia gela");

                int gela=sc.nextInt();

                switch(gela){

                    case 1:

                    System.out.println("Gela honetarako:");

                    break;

                    case 2:

                    break;

                    case 3:

                    break;

                    case 4:

                    break;
                    
                    
                    case 5:

                    break;


                }

               }

                break;

            case 2:

                break;

            case 3:
                break;

            case 4:
                break;

            case 5:
                break;
        }

    }

}

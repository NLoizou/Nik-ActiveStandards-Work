import java.util.Scanner;

public class Crons {

    static Boolean contL = false, Hash = false, Ranger = false, NormNum = true, Invalid = false, Commas = false, Slashd = false, validinput = false;
    static String OftheMonth, DayOfWeek, DayOfWeek2;
    static int redo = 0;

    public static void Contain() {
        String Dayofweeksp[] = DayOfWeek.split(" ");
        if (Dayofweeksp[0].equals("last")) {
            DayOfWeek = Dayofweeksp[1];
            contL = true;
        }
        if (contL && !Hash && !Ranger && !Commas) {
            DayOfWeek = CheckVariable(DayOfWeek);
            System.out.println(DayOfWeek + "L");
            contL = false;
            NormNum = false;
        }
    }

    public static void Hasht() {

        if (DayOfWeek.contains("of")) {
            Hash = true;

            if (DayOfWeek.contains("of1")) {
                OftheMonth = "#1 ";
            }
            if (DayOfWeek.contains("of2")) {
                OftheMonth = "#2";
            }
            if (DayOfWeek.contains("of3")) {
                OftheMonth = "#3";
            }

            String[] ss = DayOfWeek.split("");
            DayOfWeek = ss[1] + ss[2] + ss[3];
        }
        if (Hash && !Ranger && !contL && !Commas) {
            DayOfWeek = CheckVariable(DayOfWeek);
            System.out.println(DayOfWeek + OftheMonth);
            Hash = false;
            NormNum = false;
        }
    }

    public static Boolean Valid(String str) {
        str = str.toLowerCase();
        String sp[] = str.split(" ");
        if (str.length() > 27) {
            return false;
        }
        if (sp[0].equals("range") || sp[0].equals("every") || sp[0].equals("last") || sp[0].equals("all")) {
            return true;
        }
        if (sp[0].contains("mon") || sp[0].contains("tue") || sp[0].contains("wed") || sp[0].contains("thu") || sp[0].contains("fri")
                || sp[0].contains("sat") || sp[0].contains("sun")) {
            return true;
        }
        if (sp[0].equals("yes") || sp[0].equals("no") && redo == 1) {
            return true;
        } else {
            return false;
        }

    }

    public static void Comma() {
        String Dayofweeksp[] = DayOfWeek.split(" ");
        for (int i = 0; i < Dayofweeksp.length; i++) {
            String s = Dayofweeksp[i];
            s = CheckVariable(s);
            Dayofweeksp[i] = s;
        }

        if (DayOfWeek.length() > 3) {
            if (!Hash && !Ranger && !contL && !Slashd && !Dayofweeksp[0].equals("every") && !validinput) {
                Commas = true;
            }
        }

        if (Commas && !Hash && !Ranger && !contL && !Slashd) {
            for (int i = 0; i < Dayofweeksp.length; i++) {
                System.out.print(Dayofweeksp[i] + ", ");
            }
            System.out.println("\n");
            Commas = false;
            NormNum = false;
        }
    }


    public static void Slash() {
        String DayofWeekSp[] = DayOfWeek.split(" ");
        if (DayofWeekSp[0].equals("every") && DayofWeekSp[2].equals("from")) {
            Slashd = true;
        }
        if (Slashd && !Ranger && !Hash & !contL && !Commas) {
            DayOfWeek = DayofWeekSp[3];
            DayOfWeek2 = DayofWeekSp[1];
            DayOfWeek = CheckVariable(DayOfWeek);
            System.out.println(DayOfWeek2 + "/" + DayOfWeek);
            Slashd = false;
            NormNum = false;

        }
    }


    public static void Range() {
        if (DayOfWeek.contains("range")) {
            String splitter[] = DayOfWeek.split(" ");
            DayOfWeek = splitter[1];
            DayOfWeek2 = splitter[2];
            Ranger = true;
        }
        if (Ranger && !Hash & !contL && !Commas) {
            DayOfWeek = CheckVariable(DayOfWeek);
            DayOfWeek2 = CheckVariable(DayOfWeek2);
            System.out.println(DayOfWeek + "-" + DayOfWeek2);
            Ranger = false;
            NormNum = false;
        }
    }

    public static String CheckVariable(String c) {

        if (c.equals("mon")) {
            c = "1";

        } else if (c.equals("tue")) {
            c = "2";

        } else if (c.equals("wed")) {
            c = "3";

        } else if (c.equals("thu")) {
            c = "4";

        } else if (c.equals("fri")) {
            c = "5";

        } else if (c.equals("sat")) {
            c = "6";

        } else if (c.equals("sun")) {
            c = "7";

        } else if (c.equals("all")) {
            c = "*";
        }
        return c;
    }

    public static String Input(String c) {
        Scanner scan = new Scanner(System.in);
        if (redo == 0) {
            System.out.println("Please Enter a day of the week: ");
        }
        if (redo == 1) {
            System.out.println("Would you like to enter another day of the week? (Yes/No)");
        }
        c = scan.nextLine();
        c = c.toLowerCase();
        return c;
    }

    public static void main(String args[]) {

        while (!Invalid) {
            DayOfWeek = Input(DayOfWeek);
            while(!Valid(DayOfWeek)){
                System.out.println("Invalid Input" + "\n");
                DayOfWeek = Input(DayOfWeek);
                redo=0;
            }

            redo = 1;
            Contain();
            Range();
            Hasht();
            Comma();
            Slash();

            if (!Hash && !Ranger && !contL && NormNum) {
                DayOfWeek = CheckVariable(DayOfWeek);
                System.out.println(DayOfWeek);
            }

            DayOfWeek = Input(DayOfWeek);
            NormNum = true;
            if (DayOfWeek.equals("no")) {
                Invalid = true;
            } else {
                redo = 0;
            }
        }
    }
}


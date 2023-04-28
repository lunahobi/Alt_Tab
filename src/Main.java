import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        L1List l = new L1List();
        Scanner in = new Scanner(System.in);
        int count = 0;
        while (in.hasNextLine()) {
            String s = in.nextLine();
            if (s.equals("end")) break;
            if (s.startsWith("Run")) {
                String p = s.replaceAll("Run", "").trim();
                l.insert(p);
                count++;
            } else if (s.startsWith("Alt")) {
                if (s.split("\\+")[1].equals("Tab")) {
                    int c = s.split("\\+").length - 1;
                    for (int k = 0; k < c; k++) {
                        try {
                            l.forward();
                        } catch (Exception e) {
                            l.toFront();
                        }
                    }
                    String p = l.after();
                    l.erase();
                    l.toFront();
                    for (int i = 0; i < count; i++) {
                        for (int j = 0; j < (count - i); j++) {
                            try {
                                l.forward();
                            } catch (Exception ex) {
                                l.toFront();
                            }
                        }
                        try {
                            l.after();
                        } catch (Exception ex) {
                            l.toFront();
                        }
                        s = l.after();
                        l.erase();
                        l.insert(s);
                        l.toFront();
                    }
                    l.insert(p);
                }
                else if (s.split("\\+")[1].equals("Delete")) {
                    int c = s.split("\\+").length - 1;
                    for (int i = 0; i < c; i++) {
                        try {
                            l.erase();
                        } catch (Exception e) {;}
                    }
                }
            } else {
                System.out.println("Введена неправильная команда");
                continue;
            }
            if (!l.empty())
                System.out.println(l.after());
            else
                System.out.println("Нет открытых приложений");
        }
    }
}
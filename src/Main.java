import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws Exception {
//        Pattern run = Pattern.compile("Run" + ".+");
//        Pattern alt_tab = Pattern.compile("Alt\\+Tab" + ".*");
//        Pattern alt_del = Pattern.compile("Alt\\+Delete" + ".*");
        L1List l = new L1List();
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()){
            String s = in.nextLine();
            if (s.equals("end")) break;
            if (s.startsWith("Run")){
                String p = s.replaceAll("Run", "").trim();
                l.insert(p);
            }
            else if (s.startsWith("Alt")){
                String[] m = s.split("\\+");
                for (int i = 0; i < m.length; i++){
                    if (m[i].equals("Delete")){
                        try {
                            l.erase();
                        }catch (Exception e){
                            l.toFront();
                        }
                    }
                    else if (m[i].equals("Tab")){
                        int c = 0;
                        for (int j = 0; j < m.length; j++) {
                            if (m[i].equals("Tab")) {
                                c++;
                            }
                        }
                        for (int j = 0; j < c; j++){
                            try {
                                l.forward();
                            }catch (Exception e){
                                l.toFront();
                            }
                            try {
                                String p = l.after();
                                l.erase();
                                l.insert(p);
                            }catch (Exception e){
                                l.toFront();
                            }
                        }
                    }
                }
            }
            else{
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
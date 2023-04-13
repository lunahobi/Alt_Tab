import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws Exception {
        Pattern run = Pattern.compile("Run" + ".+");
        Pattern alt_tab = Pattern.compile("Alt\\+Tab" + ".*");
        Pattern alt_del = Pattern.compile("Alt\\+Delete" + ".*");
        L1List l = new L1List();
        Scanner in = new Scanner(System.in);
        while (true){
            String s = in.nextLine();
            Matcher matcher = run.matcher(s);
            Matcher matcher1 = alt_tab.matcher(s);
            Matcher matcher2 = alt_del.matcher(s);
            if (matcher.find()){
                String p = s.split("Run")[1].trim();
                l.insert(p);
                System.out.println(p);
            }
            else if (matcher1.find()){
                int cnt = 0;
                String[] m = s.split("\\+");
                for (int i = 0; i < m.length; i++){
                    if(m[i].equals("Tab"))
                        cnt += 1;
                }
                for (int i = 0; i < cnt; i++){
                    try {
                        l.forward();
                    }catch (Exception e){
                        l.toFront();
                    }
                    try {
                        String e = l.after();
                        l.erase();
                        l.insert(e);
                    }catch (Exception e){
                        l.toFront();
                    }
                }
                if (l.empty())
                    System.out.println("Нет открытых приложений");
                else if (!l.empty())
                    try {
                        System.out.println(l.after());
                    }catch (Exception e){
                        l.toFront();
                        System.out.println(l.after());
                    }
            }
            else if(matcher2.find()){
                int cnt = 0;
                String[] m = s.split("\\+");
                for (int i = 0; i < m.length; i++){
                    if(m[i].equals("Delete"))
                        cnt += 1;
                }
                for (int i = 0; i<cnt; i++){
                    l.toFront();
                    try {
                        l.erase();
                    }catch (Exception e){;}
                }
                if (l.empty())
                    System.out.println("Нет открытых приложений");
                else System.out.println(l.after());
            }
            else
                System.out.println("Введена неверная команда, проигнорировать");
        }
    }
}
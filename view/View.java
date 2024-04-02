package view;

import person.PersonBase;
import controller.Main;

import java.util.Collections;

public class View {
    private static int step = 1;
    private static int l = 0;
    private static final String top10 = formatDiv("a") + String.join("", Collections.nCopies(9, formatDiv("-b"))) + formatDiv("-c");
    private static final String midl10 = formatDiv("d") + String.join("", Collections.nCopies(9, formatDiv("-e"))) + formatDiv("-f");
    private static final String bottom10 = formatDiv("g") + String.join("", Collections.nCopies(9, formatDiv("-h"))) + formatDiv("-i");
    private static void tabSetter(int cnt, int max){
        int dif = max - cnt + 2;
        if (dif>0) System.out.printf("%" + dif + "s", ":\t"); else System.out.print(":\t");
    }
    private static String formatDiv(String str) {
        return str.replace('a', '┌')
                .replace('b', '┬')
                .replace('c', '┐')
                .replace('d', '├')
                .replace('e', '┼')
                .replace('f', '┤')
                .replace('g', '└')
                .replace('h', '┴')
                .replace('i', '┘')
                .replace('-', '─');
    }

    private static String getChar(int x, int y){
        String out = "| ";
        for (PersonBase human: Main.all) {
            if (human.getCoords()[0] == x && human.getCoords()[1] == y){
                if (human.getHealth() == 0)
                {
                    out = "|" + (AnsyView.ANSI_RED + human.getInfo().charAt(0) + AnsyView.ANSI_RESET);
                    break;
                }
                if (Main.green.contains(human)) out = "|" + (AnsyView.ANSI_GREEN + human.getInfo().charAt(0) + AnsyView.ANSI_RESET);
                if (Main.blue.contains(human)) out = "|" + (AnsyView.ANSI_BLUE + human.getInfo().charAt(0) + AnsyView.ANSI_RESET);
                break;
            }
        }
        return out;
    }
    public static void view() {
        if (step == 1 ){
            System.out.print(AnsyView.ANSI_RED + "First step" + AnsyView.ANSI_RESET);
        } else {
            System.out.print(AnsyView.ANSI_RED + "Step:" + step + AnsyView.ANSI_RESET);
        }
        step++;
        Main.all.forEach((v) -> l = Math.max(l, v.toString().length()));
        System.out.print("_".repeat(l*2));
        System.out.println();
        System.out.print(top10 + "    ");
        System.out.print("Blue side");
        //for (int i = 0; i < l[0]-9; i++)
        System.out.print(" ".repeat(l-9));
        System.out.println(":\tGreen side");
        for (int i = 1; i < 11; i++) {
            System.out.print(getChar(1, i));
        }
        System.out.print("|    ");
        System.out.print(Main.blue.get(0));
        tabSetter(Main.blue.get(0).toString().length(), l);
        System.out.println(Main.green.get(0));
        System.out.println(midl10);

        for (int i = 2; i < 10; i++) {
            for (int j = 1; j < 11; j++) {
                System.out.print(getChar(i, j));
            }
            System.out.print("|    ");
            System.out.print(Main.blue.get(i-1));
            tabSetter(Main.blue.get(i-1).toString().length(), l);
            System.out.println(Main.green.get(i-1));
            System.out.println(midl10);
        }
        for (int j = 1; j < 11; j++) {
            System.out.print(getChar(10, j));
        }
        System.out.print("|    ");
        System.out.print(Main.blue.get(9));
        tabSetter(Main.blue.get(9).toString().length(), l);
        System.out.println(Main.green.get(9));
        System.out.println(bottom10);
    }
}
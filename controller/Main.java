package controller;
import behavior.CoordXY;
import behavior.HeroesNames;
import person.*;

import java.util.ArrayList;
import java.util.Random;
import view.View;
import java.util.Scanner;
public class Main {

    public static ArrayList<PersonBase> green = new ArrayList<>();
    public static ArrayList<PersonBase> blue = new ArrayList<>();
    public static ArrayList<PersonBase> all = new ArrayList<>();

    public static void main(String[] args) {
        createTeam(green, 10, 0);
        createTeam(blue, 10, 3);
        all.addAll(blue);
        all.addAll(green);
        all.sort((o1, o2) -> Integer.compare(o2.priority, o1.priority));

            Scanner in = new Scanner(System.in);
            while (true)
            {
                View.view();
                for (PersonBase p : all) {
                    if (green.contains(p)) {
                        p.step(blue, green);
                    } else {
                        p.step(green, blue);
                    }
                    // System.out.println(p.getInfo());
            }
            in.nextLine();  
            if (!isLiving(green))
            {
                System.out.println("Blue wins!");
                break;
                }
            if (!isLiving(blue))
            {
                System.out.println("Green wins!");
                break;
            }
        }

    }
    private static boolean isLiving(ArrayList<PersonBase> team)
    {
        for (PersonBase personBase : team) {
            if (personBase.getHealth() > 0)
                return true;
        }
        return false;
    }

    public static void createTeam(ArrayList<PersonBase> team, int num, int start) {
        Random rnd = new Random();
        int cy = 0;
        while (num-- > 0) {
            int n = start + rnd.nextInt(4);
            switch (n) {
                case 0:
                    team.add(new Crossbowman(HeroesNames.getRandomName(), new CoordXY(9, cy)));
                    break;
                case 1:
                    team.add(new Spearman(HeroesNames.getRandomName(), new CoordXY(9, cy)));
                    break;
                case 2:
                    team.add(new Wizard(HeroesNames.getRandomName(), new CoordXY(9, cy)));
                    break;
                case 3:
                    team.add(new Peasant(HeroesNames.getRandomName(), new CoordXY((3-start) * 3, cy)));
                    break;
                case 4:
                    team.add(new Sniper(HeroesNames.getRandomName(), new CoordXY(0, cy)));
                    break;
                case 5:
                    team.add(new Monk(HeroesNames.getRandomName(), new CoordXY(0, cy)));
                    break;
                case 6:
                    team.add(new Robber(HeroesNames.getRandomName(), new CoordXY(0, cy)));
                    break;
                default:
                    System.out.println("!error!");
            }
            cy++;
        }
    }

}

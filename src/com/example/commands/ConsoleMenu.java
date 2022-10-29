package com.example.commands;

import com.example.taxopark.Taxopark;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleMenu {
    public static void mainMenu() {
        Taxopark tp = new Taxopark();

        Command create = new TaxoparkCreate(tp);
        Command addAuto = new TaxoparkAddAuto(tp);
        Command removeAuto = new TaxoparkRemoveAuto(tp);
        Command showList = new TaxoparkShowList(tp);
        Command sortList = new TaxoparkSortList(tp);
        Command searchAuto = new TaxoparkSearchAuto(tp);
        Command totalCost = new TaxoparkShowCost(tp);

        ConsoleMenuExecutor myConsoleMenuExecutor = new ConsoleMenuExecutor();
        myConsoleMenuExecutor.register("create", create);
        myConsoleMenuExecutor.register("addAuto", addAuto);
        myConsoleMenuExecutor.register("delAuto", removeAuto);
        myConsoleMenuExecutor.register("showList", showList);
        myConsoleMenuExecutor.register("sortList", sortList);
        myConsoleMenuExecutor.register("searchAuto", searchAuto);
        myConsoleMenuExecutor.register("totalCost", totalCost);

        boolean commandInterface = false;

        while (true) {
            Scanner sc = new Scanner(System.in);
            if(!commandInterface) {
                System.out.println("Виберіть дію:");
                System.out.println("\t1 - створити таксопарк.");
                System.out.println("\t2 - додати автомобіль.");
                System.out.println("\t3 - вилучити автомобіль.");
                System.out.println("\t4 - показати список автомобілів.");
                System.out.println("\t5 - показати вартість таксопарку.");
                System.out.println("\t6 - відсортувати автомобілі за зростанням витрати пального.");
                System.out.println("\t7 - знайти автомобіль.");
                System.out.println("\t8 - перейти у командний інтерфейс.");
                System.out.println("\t9- закінчити роботу.");
                int choice = sc.nextInt();

                switch (choice) {
                    case 9:
                        return;
                    case 1:
                        myConsoleMenuExecutor.execute("create");
                        break;
                    case 2:
                        myConsoleMenuExecutor.execute("addAuto");
                        break;
                    case 3:
                        myConsoleMenuExecutor.execute("delAuto");
                        break;
                    case 4:
                        myConsoleMenuExecutor.execute("showList");
                        break;
                    case 5:
                        myConsoleMenuExecutor.execute("totalCost");
                        break;
                    case 6:
                        myConsoleMenuExecutor.execute("sortList");
                        break;
                    case 7:
                        myConsoleMenuExecutor.execute("searchAuto");
                        break;
                    case 8:
                        commandInterface = true;
                        break;
                    default:
                        System.out.println("Необхідно ввести 1-9 варіанти\n");
                }
            }
            else {
                System.out.println("Введіть команду (help - підказка):");
                String txt = sc.nextLine();
                String[] array = txt.split("\s");
                String command = array[0];
                String[] parameters = new String[array.length - 1];
                for(int i = 1; i < array.length; i++) {
                    parameters[i-1] = array[i];
                }
                switch (command) {
                    case "exit":
                        return;
                    case "menu":
                        commandInterface = false;
                        break;
                    case "help":
                        ArrayList<String> commands = myConsoleMenuExecutor.availableCommands();
                        for(String cmd: commands) {
                            System.out.println(cmd);
                        }
                        System.out.println("menu");
                        System.out.println("help");
                        System.out.println("exit");
                        break;
                    default:
                        myConsoleMenuExecutor.execute(command, parameters);
                }
            }
        }
    }
}
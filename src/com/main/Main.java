package com.main;

import view.file.Window;
import view.view.ConsoleView;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Select input method: 1 - Console, 2 - File");
        int numMethod = scanner.nextInt();

        switch(numMethod){
            case 1:
                int num = 1;
                while(num<2){
                    System.out.println("Select input type number: 1-BINARY, 2-DECIMAL, 3-HEXDECIMAL, 0-exit");
                    try{
                        int typeNumber = scanner.nextInt();
                        if(typeNumber==0){  System.exit(0); }

                        System.out.println("Select input your arithmetic expression:");
                        String inputLine = scanner.next();

                        if(!inputLine.equals(null)){
                            ConsoleView consoleView = new ConsoleView(typeNumber,inputLine);
                            System.out.println("result = "+consoleView.getResultLine());
                        }
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                }
                break;
            case 2:
                Window window = new Window();
                break;
            default:  System.out.println("Input error!");
                break;
        }

    }

}

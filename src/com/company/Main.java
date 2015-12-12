package com.company;

public class Main {

    public static void main(String[] args) {


        if ((args.length == 2)) {
            int widthMultiplier = Integer.parseInt(args[0]);
            int heightMultiplier = Integer.parseInt(args[1]);
            if (widthMultiplier > 0 && heightMultiplier > 0) {
                Table table = new Table();
                table.readCells();
                table.writeGeneratedTableToFile(widthMultiplier, heightMultiplier);
            }
            else{
                System.out.println("Width and height of new table should be >0 ");
            }
        } else {
            System.out.println("Wrong params");
        }
    }
}

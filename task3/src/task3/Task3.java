package task3;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import static javax.xml.bind.DatatypeConverter.printHexBinary;

public class Task3 {
    
    //функция вывода меню
    public static void menu(String[] args){
        System.out.println("Available moves:");
        for (int i = 0; i < args.length; i++){
            System.out.println(String.format("%d - %s", i+1, args[i]));
        }
        System.out.println("0 - exit");
        System.out.println("? - help");
    }
    //функция проверки на повторяющие значения в аргументах, true - есть повторения, false -повторений нет
    static boolean  isPepetitions(String[] args){
        for (int i = 0; i < args.length; i++){
            for (int j = i+1; j < args.length; j++){
                if (args[i].equals(args[j])){
                    return true;
                }
            }
        }
        return false;
    }
    
    
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        Table t = new Table();//экземпляр вывода таблицы
        IsWin iw = new IsWin();//экземпляр проверки победы
        Scanner in = new Scanner(System.in);
        
        if (args.length >= 3 && args.length % 2 == 1 && !isPepetitions(args)){
            boolean check2 = true;//"метка" для повторения игры в случае если ответ компьютера и игрока совпадут
            while (check2){
                GenerationKey keyBytes = new GenerationKey();
                byte[] key = keyBytes.getKey();//генирируем ключ

                int messageInt = (key[0] + 255) % args.length + 1;
                String message = args[messageInt - 1];
            
                GenerationHMAC gHMAC = new GenerationHMAC();
                byte[] hmac = gHMAC.getHMAC(key, message);//генирируем HMAC

                System.out.println("HMAC: " + printHexBinary(hmac));

                String move = null;//ход игрока
                boolean check = true;
                int moveInt = -1;//индекс хода игрока

                while (check){
                    menu(args);
                    System.out.print("Enter your move: ");
                    move = in.next();
                    if (move.equals("?")){
                        System.out.println("Your move: help");
                        t.asciiTable(args);//вывод таблицы, определяющую правила победы
                    }else{
                        try{
                            moveInt = Integer.parseInt(move.trim());
                        }
                        catch (NumberFormatException e){

                        }
                        if (moveInt < 0 || moveInt > args.length){
                            System.out.println("Bad input, try again");
                        }else{
                            move = move.equals("0") ? "exit" : args[moveInt - 1];
                            check = false;
                        }
                    }
                }
                
                System.out.println("Your move: " + move);
                if (move.equals("exit")){
                    check2 = false;
                }
                else{
                    System.out.println("Computer move: " + message);
                    if (move.equals(message)){
                        System.out.println("No one won, try again!");
                    }
                    else{
                        String res = iw.isWin(args.length, moveInt, messageInt) ? "You win!" : "You lose!";
                        System.out.println(res);
                        System.out.println("Key: " + printHexBinary(key));
                        check2 = false;
                    }
                }
            }
        }
        else{
            System.out.println("Bad input (number of moves must be odd and >= 3), example of correct input - rock paper scissors");
        }
        in.close();
    }
}

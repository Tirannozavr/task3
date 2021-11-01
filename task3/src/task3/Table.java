package task3;

import com.bethecoder.ascii_table.ASCIITable;

public class Table {
    String [] header = null;//заголовки столбцов
    String[][] data = null;//данные
    IsWin iw = new IsWin();
    
    void asciiTable(String[] args){
        int size = args.length;
        header = new String[size + 1];
        data = new String[size][size + 1];
 
        header[0] = "pc-user";
        
        for(int i = 0; i < size; i++){
            header[i+1] = args[i];
            data[i][0] = args[i];
        }
        
        int n = size / 2;
            
        for (int i = 0; i < size; i++){
            for (int j = 1; j <= size; j++){
                if (i+1 == j){
                    data[i][j]="---";
                }
                else{
                    String s = iw.isWin(args.length, i+1, j) ? "win" : "lose";
                    data[i][j]= s;
                }
            }
        }
      
        ASCIITable.getInstance().printTable(header, data);
    }
}

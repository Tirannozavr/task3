package task3;

public class IsWin {
    //move1 выигрывает ли у move2?
    boolean isWin(int length, int move1, int move2){//счет ходов начинается с 1 (1- возможный ход1, 2 - возможный ход 2 и т.д)
        int n = length/2;//половина следующих по кругу выигрывает, половина предыдущих по кругу проигрывает
        if (move2 >= move1 + 1 && move2 <= move1 + n  ||  (move1 + n)/length > 0 && move2 <= (move1 + n) % length){//
            return true;
        }
        return false;
    }
}

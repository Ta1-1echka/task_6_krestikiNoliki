package entity;

/**
 * Created by Tanya on 26.10.2016.
 */
public class Player {
    int masUser[][];
    int masComputer[][];
    int allMovement[][];
    boolean end = false;

    public Player() {
        masUser = new int[3][3];
        masComputer = new int[3][3];
        allMovement = new int[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                masUser[i][j] = 0;
                masComputer[i][j] = 0;
                allMovement[i][j] = 0;
            }
    }

    public int[][] getMasUser() {
        return masUser;
    }

    public void setMasUser(int i, int j) {
        masUser[i][j] = 1;
        allMovement[i][j] = 1;
    }

    public int[][] getMasComputer() {
        return masComputer;
    }

    public void setMasComputer(int i, int j) {
        masComputer[i][j] = 1;
        allMovement[i][j] = 1;
    }

    public int[][] getAllMovement() {
        return allMovement;
    }

    public void setAllMovement(int[][] allMovement) {
        this.allMovement = allMovement;
    }

    public boolean checkViktory(int mas[][]) {
        for (int i = 0; i < 3; i++)
            if (mas[i][0] == 1 & mas[i][0] == mas[i][1] & mas[i][1] == mas[i][2])
                return true;
        for (int i = 0; i < mas.length; i++)
            if (mas[0][i] == 1 & mas[0][i] == mas[1][i] & mas[1][i] == mas[2][i])
                return true;
        if (mas[0][0] == 1 & mas[0][0] == mas[1][1] & mas[1][1] == mas[2][2])
            return true;
        else if (mas[0][2] == 1 & mas[0][2] == mas[1][1] & mas[1][1] == mas[2][0])
            return true;
        else return false;

    }

    public void movementComputer() {
        if (allMovement[1][1] != 1) {
            setMasComputer(1, 1);
        } else {
            int i, j;
            do {
                i = (int) (Math.random() * 3);
                j = (int) (Math.random() * 3);

            } while (allMovement[i][j] == 1);
            setMasComputer(i, j);

        }

    }

    public boolean isEndOfGame() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (allMovement[i][j] == 0)
                    return false;

        return true;
    }

    public boolean isEnd() {
        return end;
    }

    public boolean isExist(int i, int j) {
        if (allMovement[i][j] == 1)
            return true;
        else return false;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

}

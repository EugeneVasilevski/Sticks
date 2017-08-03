package com.lis.sticks;

public class GameLogic {
    public static boolean[][][] arr;
    public static int[][] arrr;
    public static int field_size;
    public static int x, y, z;
    public int a = 0;
    public int b = 0;
    private int num_remaining_cells;

    public GameLogic() {}

    public GameLogic(int num_cells, int num_ext_cells) {
        arr = new boolean[num_cells][num_cells][4];
        arrr = new int[num_cells][num_cells];
        field_size = num_cells;
        num_remaining_cells = num_cells * num_cells - (num_cells - num_ext_cells) * ((num_cells - num_ext_cells) / 2 + 1);

        int control_point1 = (num_cells - num_ext_cells) / 2;
        int control_point2 = num_cells - (control_point1 + 1);
        int a = control_point1, b = control_point2;

        for (int i = 0; i <= control_point1; i++) {
            fff(num_cells, i, a, b);
            arr[i][a][0] = true;
            arr[i][a][1] = true;
            arr[i][b][1] = true;
            arr[i][b][2] = true;
            arrr[i][a] = 2;
            arrr[i][b] = 2;
            a--;
            b++;
        }
        for (int i = control_point2; i < num_cells; i++) {
            a++;
            b--;
            fff(num_cells, i, a, b);
            arr[i][a][0] = true;
            arr[i][a][3] = true;
            arr[i][b][2] = true;
            arr[i][b][3] = true;
            arrr[i][a] = 2;
            arrr[i][b] = 2;
        }
        a = 0;
        b = num_cells - 1;
        for (int i = control_point1 + 1; i < control_point2; i++) {
            fff(num_cells, i, a, b);
            arr[i][0][0] = true;
            arr[0][i][1] = true;
            arr[i][num_cells - 1][2] = true;
            arr[num_cells - 1][i][3] = true;
            arrr[i][0] = 1;
            arrr[0][i] = 1;
            arrr[i][num_cells - 1] = 1;
            arrr[num_cells - 1][i] = 1;
        }
    }

    private void fff(int num_cells, int i, int a, int b) {
        for (int j = 0; j < num_cells; j++) {
            if (j < a || j > b) {
                arr[i][j] = null;
                arrr[i][j] = -1;
            } else {
                for (int k = 0; k < 4; k++) {
                    arr[i][j][k] = false;
                }
                arrr[i][j] = 0;
            }
        }
    }

    public boolean isGameOver() {
        if (num_remaining_cells == 0) {
            return true;
        }
        return false;
    }

    public void setCoordAdjacentCell() { // нужнен обрабочик исключений если игровое поле будет без границ
        switch (z) {
            case 0:
                y--; z = 2;
                break;
            case 1:
                x--; z = 3;
                break;
            case 2:
                y++; z = 0;
                break;
            case 3:
                x++; z = 1;
                break;
            default:
                break;
        }
    }

    public boolean checkStrokeWithInitialization() { // нужнен обрабочик исключений
        if (arr[x][y] != null) {
            if (!arr[x][y][z]) {
                arr[x][y][z] = true;
                arrr[x][y]++;
                if (arrr[x][y] == 4) {
                    num_remaining_cells--;
                }
                return true;
            }
        }
        return false;
    }

}

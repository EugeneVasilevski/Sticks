package com.lis.sticks;

import android.graphics.Point;
import android.util.Log;

import java.util.ArrayList;

public class BotLogic extends GameLogic {
    private ArrayList<Integer> listLines;
    private int ggg = 1;
    private ArrayList<Point>previousListPoints = new ArrayList<>();
    private ArrayList<Point>currentListPoints = new ArrayList<>();
    private Point point = new Point();
    private boolean gameMode = false;

    public BotLogic() {
        initializationListLines();
    }

    /*public BotLogic(int x, int y) {
        super(x, y);
        initializationListLines();
    }*/

    public boolean sss() {
        for (int i = 0; i < field_size; i++) {
            for (int j = 0; j < field_size; j++) {
                if (arrr[i][j] == 3) {
                    x = i;
                    y = j;
                    for (int k = 0; k < 4; k++) {
                        if (!arr[i][j][k]) {
                            z = k;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void yyy() {
        while (true) {
            if (!gameMode) {
                int f = (int) (Math.random() * listLines.size());
                int a = listLines.get(f);
                Log.i("Выбрана строка", Integer.toString(a));
                int[] arr2 = new int[field_size];
                int length = 0;
                for (int j = 0; j < field_size; j++) {
                    if (arrr[a][j] != -1 && arrr[a][j] <= ggg) {
                        for (int k = 0; k < 4; k++) {
                            if (!arr[a][j][k]) {
                                x = a; y = j; z = k;
                                setCoordAdjacentCell();
                                if (arrr[x][y] <= ggg) {
                                    arr2[length] = j;
                                    Log.i("Столбец", Integer.toString(j) + " подходит");
                                    length++;
                                    break;
                                }
                            }
                        }
                    }
                }
                if (length != 0) {
                    int b = (int) (Math.random() * length);
                    b = arr2[b];
                    Log.i("Выбран столбец", Integer.toString(b));
                    int[] arr3 = new int[4];
                    length = 0;
                    for (int k = 0; k < 4; k++) {
                        if (!arr[a][b][k]) {
                            x = a; y = b; z = k;
                            setCoordAdjacentCell();
                            if (arrr[x][y] <= ggg) {
                                arr3[length] = k;
                                Log.i("Грань", Integer.toString(k) + " подходит");
                                length++;
                            }
                        }
                    }
                    int c = (int) (Math.random() * length);
                    c = arr3[c];
                    Log.i("Выбрана грань", Integer.toString(c));
                    x = a; y = b; z = c;
                    Log.i("x = ", Integer.toString(a));
                    Log.i("y = ", Integer.toString(b));
                    Log.i("z = ", Integer.toString(c));
                    break;
                } else {
                    listLines.remove(f);
                    Log.i("Удалена строка", Integer.toString(f));
                    if (listLines.isEmpty()) {
                        initializationListLines();
                        ggg++;
                        //gameMode = true;
                        Log.i("Отпимальных ходов нет", "активация GameMod!");
                    }
                }
            } else ggg();
        }
    }

    public void ggg() {
        for (int i = 0; i < 1; i++) {
            Log.i("i = ", Integer.toString(i));
            for (int j = 0; j < field_size; j++) {
                point.x = i; point.y = j;
                Log.i("j = ", Integer.toString(j));
                if (arrr[i][j] == 2 && !previousListPoints.contains(point)) {
                    Log.i("Цепочка запущена", "координаты:");
                    Log.i(Integer.toString(i), Integer.toString(j));
                    x = i; y = j;
                    currentListPoints.add(new Point(point));
                    for (int k = 0; k < 4; k++) {
                        if (!arr[i][j][k]) {
                            z = k;
                            setCoordAdjacentCell();
                            point.x = x; point.y = y;
                            fff();
                        }
                    }
                    Log.i("Размер предыдушего", Integer.toString(previousListPoints.size()));
                    Log.i("Размер текущего", Integer.toString(currentListPoints.size()));
                    if (previousListPoints.isEmpty()
                            || previousListPoints.size() > currentListPoints.size()) {
                            previousListPoints = new ArrayList<>(currentListPoints);
                    }
                    currentListPoints.clear();
                }
            }
        }
        Log.i("Выход из ", "цикла");
        point = previousListPoints.get((int) (Math.random() * previousListPoints.size()));
        x = point.x;
        y = point.y;
        //int index = (int) Math.random() * previousListPoints.size();
        //x = previousListPoints.get(index).x;
        //y = previousListPoints.get(index).y;

        Log.i("Итоговые", "координаты:");
        Log.i(Integer.toString(x), Integer.toString(y));
        for (int k = 0; k < 4; k++) {
            if (!arr[x][y][k]) {
                z = k;
                break;
            }
        }
    }

    public void fff() {
        if (arrr[x][y] == 2 && !currentListPoints.contains(point)) {
            point.x = x; point.y = y;
            currentListPoints.add(new Point(point));
            Log.i("Функция", "fff");
            Log.i(Integer.toString(x), Integer.toString(y));
            for (int k = 0; k < 4; k++) {
                if (!arr[x][y][k] && (k != z)) {
                    z = k;
                    setCoordAdjacentCell();
                    fff();
                }
            }
        }


    }

    public void initializationListLines() {
        listLines = new ArrayList(field_size);
        for (int i = 0; i < field_size; i++) {
            listLines.add(i);
        }
    }

}

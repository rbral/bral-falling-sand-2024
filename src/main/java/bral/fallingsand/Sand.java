package bral.fallingsand;

import java.util.ArrayList;
import java.util.Random;

public class Sand {

    private final Random random;
    private final int[][] field; // [height] [width]
    private int height;
    private int width;

    public Sand(int width, int height) {
        field = new int[height][width];
        this.random = new Random();
        this.width = width;
        this.height = height;
    }

    // a 2nd constructor: to use for tests:
    public Sand(int width, int height, Random random) {
        field = new int[height][width];
        this.random = random;
        this.width = width;
        this.height = height;
    }

    public static void main(String[] args) {
        args = new String[]{"50"};
        int n = Integer.parseInt(args[0]);

        Sand sand = new Sand(50, 10);
        sand.randomSand(n);
        System.out.println(sand.toString());

    }


    public String toString() {
        // StringBuilder is more memory efficient than doing += with strings
        StringBuilder builder = new StringBuilder();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                builder.append(field[y][x]);
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    /**
     * @return the value in field
     */
    public int get(int x, int y) {
        return field[y][x];
    }

    /**
     * Sets the value in field to be 1
     */
    public void put(int x, int y) {
        field[y][x] = 1;
    }

    public void fall() {
        //moves all sand down one square

        // check every cell
        boolean moved = true;
        // while moved is true, needs to continue rechecking all of them
        while (moved) {
            moved = false;
            for (int y = field.length - 2; y >= 0; y--) {
                for (int x = 0; x < field[y].length; x++) {

                    if (field[y][x] == 1) {
                        if (field[y + 1][x] == 0) {
                            // does the sand fall straight down?
                            field[y][x] = 0;
                            field[y + 1][x] = 1;
                            moved = true;
                            continue;
                        }

                        boolean rightFirst = random.nextBoolean();
                        int direction1 = rightFirst ? +1 : -1;
                        int direction2 = rightFirst ? -1 : +1;

                        if (field[y + 1][x + direction1] == 0) {
                            field[y][x] = 0;
                            field[y + 1][x + direction1] = 1;
                        } else if (field[y + 1][x + direction2] == 0) {
                            field[y][x] = 0;
                            field[y + 1][x + direction2] = 1;
                        }
                    }

                }
            }
        }

    }

    // old code
                /*if (field[y + 1][x] == 0) {
                    // does the sand fall straight down?
                    if (field[y][x] == 1 && field[y + 1][x] == 0) {
                        field[y][x] = 0;
                        field[y + 1][x] = 1;
                    }

                    // does the sand fall to the right?
                    if (field[y][x] == 1 && field[y + 1][x + 1] == 0) {
                        field[y][x] = 0;
                        field[y + 1][x + 1] = 1;
                    }

                    // does the sand fall to the left?
                    if (field[y][x] == 1 && field[y + 1][x + -1] == 0) {
                        field[y][x] = 0;
                        field[y + 1][x - 1] = 1;
                    }
                }*/

    public void randomSand(int n) {
        //This will add n pieces of sand into the field in random positions
        /*
        needs to check where there are empty boxes on top
        of the empty boxes, choose where to put sand
        fall
        repeat
         */
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> emptySpotsArray = new ArrayList<>();
            // make list of all the empty spots
            for (int x = 0; x < field[0].length; x++) {
                if (field[0][x] == 0) {
                    emptySpotsArray.add(x); // add index to emptySpotsArray
                }
            }

            if (emptySpotsArray.size() != 0) {
                int randomIndexOfEmptySpotsArray = random.nextInt(0, emptySpotsArray.size());
                int randomEmptySpot = emptySpotsArray.get(randomIndexOfEmptySpotsArray);
                // place sand at that index:
                put(randomEmptySpot, 0);
                // remove from array:
                emptySpotsArray.remove(randomEmptySpot);
                fall();
            } else // screen is full, no more spots
            {
                return;
            }
        }


    }


}

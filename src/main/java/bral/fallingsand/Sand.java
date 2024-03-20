package bral.fallingsand;

import java.util.Random;
import java.util.Scanner;

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
        Scanner kb = new Scanner(System.in);

        args = new String[]{"50"};
        int n = Integer.parseInt(args[0]);
        Sand sand = new Sand(50, 10);
        sand.randomSand(n);

        System.out.println("Press enter to make sand fall and display sand. ");

        while (true) {
            String input = kb.nextLine();
            if (input.isEmpty()) {
                sand.fall();
                System.out.println(sand.toString());
            } else {
                break; // exit the loop if user provides any input
            }
        }


    }

    // getters:
    public int[][] getField() {
        return field;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
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

        for (int y = field.length - 2; y >= 0; y--) {
            for (int x = 0; x < field[y].length; x++) {

                if (field[y][x] == 1) {
                    if (field[y + 1][x] == 0) {
                        // does the sand fall straight down?
                        field[y][x] = 0;
                        field[y + 1][x] = 1;
                        continue;
                    }

                    boolean rightFirst = random.nextBoolean();
                    int direction1 = rightFirst ? +1 : -1;
                    int direction2 = rightFirst ? -1 : +1;

                    if (x + direction1 >= 0 && x + direction1 < field[y].length && field[y + 1][x + direction1] == 0) {
                        field[y][x] = 0;
                        field[y + 1][x + direction1] = 1;
                    } else if (x + direction2 >= 0 && x + direction2 < field[y].length && field[y + 1][x + direction2] == 0) {
                        // Check if moving left would exceed bounds
                        field[y][x] = 0;
                        field[y + 1][x + direction2] = 1;
                    }



                    /*
                    past attempts: just here to show the effort I invested,
                    but I will delete these after.

                    if (field[y + 1][x + direction1] == 0) {

                        // check if moving right would exceed bounds
                        if (x + direction1 >= 0 && x + direction1 < field[y].length) {
                            field[y][x] = 0;
                            field[y + 1][x + direction1] = 1;
                        }

                        *//*if ((x + direction1) < field[y].length) {
                            field[y][x] = 0;
                            field[y + 1][x + direction1] = 1;
                        }*//*

                     *//*if (x != field.length - 1) // if NOT at right-most index
                        {
                            field[y][x] = 0;
                            field[y + 1][x + direction1] = 1;
                        }*//*

                    } else if (field[y + 1][x + direction2] == 0) {
                        // check if moving left would exceed bounds:
                        if (x + direction2 >= 0 && x + direction2 < field[y].length) {
                            field[y][x] = 0;
                            field[y + 1][x + direction2] = 1;
                        }

                        *//*if ((x + direction2) > 0) {
                            field[y][x] = 0;
                            field[y + 1][x + direction2] = 1;
                        }*//*


                        // check if left bound
                        *//*if (x != 0)  // if NOT at left-most index
                        {
                            field[y][x] = 0;
                            field[y + 1][x + direction2] = 1;
                        }*//*
                    }*/
                }

            }
        }


    }


    public void randomSand(int n) {
        //This will add n pieces of sand into the field in random positions
        // NEW ATTEMPT
        for (int i = 0; i < n; i++) {
            int randomPositionX = random.nextInt(0, field[0].length);
            int randomPositionY = random.nextInt(0, field.length);
            put(randomPositionX, randomPositionY);

        }

        /*
        OLD ATTEMPT: (more complicated than instructions asked apparently...)
        needs to check where there are empty boxes on top
        of the empty boxes, choose where to put sand
        fall
        repeat
         */
        /*for (int i = 0; i < n; i++) {
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
        }*/


    }


}

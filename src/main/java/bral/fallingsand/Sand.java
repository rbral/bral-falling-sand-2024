package bral.fallingsand;

import java.util.Random;
import java.util.Scanner;

import static java.lang.Math.min;

public class Sand {

    private final Random random;
    private int[][] field; // [height] [width]
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

    /**
     * moves the sand from x1, y1, to x2, y2
     *
     * @return true if the move was successful, otherwise false
     */

    /**
     * starting at x, y to x+width and y+height, set each item in field to be a sand
     * if random.nextDouble() <= probability
     */
    public void put(int x, int y, int width, int height, double probability) {

        for (int i = y; i < y + height; i++) {
            for (int j = x; j < x + width; j++) {

                if (random.nextDouble() <= probability) {
                    put(j, i);
                }

            }
        }

    }

    public boolean move(int x1, int y1, int x2, int y2) {
        if (isSand(x1, y1) && !isSand(x2, y2)) {
            field[y1][x1] = 0;
            field[y2][x2] = 1;
            return true;
        }

        return false;
    }

    public boolean inBounds(int x, int y) {
        return true;
    }

    public void fall() {
        //moves all sand down one square

        for (int y = field.length - 2; y >= 0; y--) {
            for (int x = 0; x < field[y].length; x++) {

                if (isSand(x, y)) {
                    if (!isSand(x, y + 1)) {
                        // does the sand fall straight down?
                        move(x, y, x, y + 1);
                        /*field[y][x] = 0;
                        field[y + 1][x] = 1;*/
                        continue;
                    }

                    boolean rightFirst = random.nextBoolean();
                    int direction1 = rightFirst ? +1 : -1;
                    int direction2 = rightFirst ? -1 : +1;

                    if (x + direction1 >= 0 && x + direction1 < field[y].length && field[y + 1][x + direction1] == 0) {
                        move(x, y, x + direction1, y + 1);
                        /*field[y][x] = 0;
                        field[y + 1][x + direction1] = 1;*/
                    } else if (x + direction2 >= 0 && x + direction2 < field[y].length && field[y + 1][x + direction2] == 0) {
                        // Check if moving left would exceed bounds
                        move(x, y, x + direction2, y + 1);
                        /*field[y][x] = 0;
                        field[y + 1][x + direction2] = 1;*/
                    }


                }

            }
        }


    }

    public boolean isSand(int x, int y) {
        return field[y][x] == 1;
    }

    public void randomSand(int n) {
        //This will add n pieces of sand into the field in random positions
        // NEW ATTEMPT
        for (int i = 0; i < n; i++) {
            int randomPositionX = random.nextInt(0, field[0].length);
            int randomPositionY = random.nextInt(0, field.length);
            put(randomPositionX, randomPositionY);

        }
    }

    public void resize(int width, int height) {
        int[][] newField = new int[height][width];
        // iterate over the new field size
        for (int y = 0; y < min(field.length, newField.length); y++) {
            for (int x = 0; x < min(field[y].length, newField[y].length); x++) {
                newField[y][x] = field[y][x];
            }
        }
        // update values:
        this.width = width;
        this.height = height;
        field = newField;
//        System.arraycopy(newField, 0, field, 0, height);


    }

    /**
     * @param sandString is a string of the field with \n in between each row
     *                   example: "000\n010\n010"
     */
    public void load(String sandString) {
        String[] substrings = sandString.split("\n"); // substrings = {"000", "010", "000"}
        int positionY = 0;
        for (int i = 0; i < substrings.length; i++) {
            int positionX = 0;
            // for each of the "###" sets:
            String currString = substrings[i];
            for (int j = 0; j < currString.length(); j++) {
                char currChar = currString.charAt(j);
                if (currChar == '1') {
                    put(positionX, positionY);
                } else {
                    field[positionY][positionX] = 0;
                }
                positionX++;
            }
            positionY++;
        }

    }


}

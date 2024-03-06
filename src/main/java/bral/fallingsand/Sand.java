package bral.fallingsand;

public class Sand
{
    private int[][] field = new int[3][3];

    public String toString()
    {
        // StringBuilder is more memory efficient than doing += with strings
        StringBuilder builder = new StringBuilder();
        for (int y = 0; y < 3; y++)
        {
            for (int x = 0; x < 3; x++)
            {
                builder.append(field[y][x]);
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    /**
     * @return the value in field
     */
    public int get(int x, int y)
    {
        return field[y][x];
    }

    /**
     * Sets the value in field to be 1
     */
    public void put(int x, int y)
    {
        field[y][x] = 1;
    }

    public void fall()
    {
        //moves all sand down one square
        // create 2D array for next frame of animation
        int[][] nextField = new int[3][3];

        // check every cell
        for (int y = 0; y < 3; y++)
        {
            for (int x = 0; x < 3; x++)
            {
                // what is the state?
                int currState = field[y][x];
                // if it's sand:
                if (currState > 0)
                {
                    // what's below?
                    int below = field[y][x + 1];

                    // if it's empty below and not at bottom edge,
                    // let it fall
                    if (below == 0 && y < 2) {
                        nextField[y + 1][x] = currState;
                    }
                    else // stay put
                    {
                        nextField[y][x] = currState;
                    }
                }
            }
        }
        // update the field with the new state
        field = nextField;
    }


}

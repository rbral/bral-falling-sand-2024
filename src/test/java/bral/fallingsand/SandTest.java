package bral.fallingsand;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SandTest {
    @Test
    public void string() {
        // given
        Sand sand = new Sand(3, 3);

        // when
        String actual = sand.toString();

        // then
        assertEquals("000\n000\n000\n", actual);
    }

    @Test
    public void put() {
        //given
        Sand sand = new Sand(3, 3);

        //when
        sand.put(1, 0);

        //then
        assertEquals("010\n000\n000\n", sand.toString());
    }

    @Test
    public void fall() {
        //given
        Sand sand = new Sand(3, 3);
        sand.put(1, 0);

        // when
        sand.fall();

        // then
        assertEquals("000\n010\n000\n", sand.toString());
    }

    @Test
    public void fallOnGround() {
        //given
        Sand sand = new Sand(3, 3);
        sand.put(1, 2);

        //when
        sand.fall();

        //then
        assertEquals("000\n000\n010\n", sand.toString());
    }

    @Test
    public void fallOnOtherSand() {
        //given
        Sand sand = new Sand(3, 3);
        sand.put(1, 1);
        sand.put(1, 2);

        //when
        sand.fall();

        //then
        assertEquals("000\n010\n010\n", sand.toString());
    }

    @Test
    public void fallSimultaneously() {
        /*
        Makes sure that if you have 010 010 000
        after middle row falls it will still go back up and
        check that the top row falls
         */
        // given
        Sand sand = new Sand(3, 3);
        sand.put(1, 0);
        sand.put(1, 1);

        // when
        sand.fall();

        // then
        assertEquals("000\n010\n010\n", sand.toString());
    }

}

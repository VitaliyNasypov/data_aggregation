package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VideoCameraTest {
    private final VideoCamera videoCameraOne = new VideoCamera("1", "1", "1", "1", "1");
    private final VideoCamera videoCameraTwo = new VideoCamera("1", "1", "1", "1", "1");
    private final VideoCamera videoCameraThree = new VideoCamera("2", "2", "2", "2", "2");

    @Test
    public void objectsShouldEquals() {
        Assertions.assertEquals(videoCameraOne, videoCameraTwo);
    }

    @Test
    public void objectsShouldEqualsHashCode() {
        Assertions.assertEquals(videoCameraOne.hashCode(), videoCameraTwo.hashCode());
    }

    @Test
    public void objectsNoShouldEquals() {
        Assertions.assertNotEquals(videoCameraOne, videoCameraThree);
    }
}

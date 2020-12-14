package servicepool;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ServiceThreadPoolTest {
    @Test
    public void shouldReturnCorrectJson() {
        String[] response = new String[]{"1 http://www.mocky.io/v2/5c51b230340000094f129f5d"
                + " http://www.mocky.io/v2/5c51b5b6340000554e129f7b?mocky-delay=1s"};
        String expected = "[{\"id\":\"1\",\"urlType\":\"LIVE\",\"videoUrl\":\"rtsp://127.0.0.1/1\","
                + "\"value\":\"fa4b588e-249b-11e9-ab14-d663bd873d93\",\"ttl\":\"120\"}]";
        String actual = new ServiceThreadPool().getJson(response);
        Assertions.assertEquals(expected, actual);
    }
}

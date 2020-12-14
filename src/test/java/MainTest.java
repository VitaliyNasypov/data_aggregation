import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MainTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainTest.class.getName());

    @Test
    public void shouldReturnCorrectResponse() {
        Main.main(new String[0]);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("logging.log"))) {
            String expected = "[main] INFO  Main - [{\"id\":\"1\",\"urlType\":\"LIVE\","
                    + "\"videoUrl\":\"rtsp://127.0.0.1/1\",\"value\":"
                    + "\"fa4b588e-249b-11e9-ab14-d663bd873d93\","
                    + "\"ttl\":\"120\"},{\"id\":\"20\",\"urlType\":\"ARCHIVE\","
                    + "\"videoUrl\":\"rtsp://127.0.0.1/2\","
                    + "\"value\":\"fa4b5b22-249b-11e9-ab14-d663bd873d93\",\"ttl\":"
                    + "\"60\"},{\"id\":\"3\","
                    + "\"urlType\":\"ARCHIVE\",\"videoUrl\":\"rtsp://127.0.0.1/3\","
                    + "\"value\":\"fa4b5d52-249b-11e9-ab14-d663bd873d93\",\"ttl\":"
                    + "\"120\"},{\"id\":\"2\","
                    + "\"urlType\":\"LIVE\",\"videoUrl\":\"rtsp://127.0.0.1/20\","
                    + "\"value\":\"fa4b5f64-249b-11e9-ab14-d663bd873d93\",\"ttl\":\"180\"}]";
            String fileTextActual;
            do {
                fileTextActual = bufferedReader.readLine();
            } while (!fileTextActual.contains(expected));
            Assertions.assertEquals(expected, fileTextActual.split(" ", 3)[2]);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}

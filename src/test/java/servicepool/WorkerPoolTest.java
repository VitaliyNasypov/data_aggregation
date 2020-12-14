package servicepool;

import model.VideoCamera;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class WorkerPoolTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerPoolTest.class.getName());

    @Test
    public void shouldReturnCorrectObjectVideoCamera() {
        String dataUrl = "1 http://www.mocky.io/v2/5c51b230340000094f129f5d "
                + "http://www.mocky.io/v2/5c51b5b6340000554e129f7b?mocky-delay=1s";
        WorkerPool workerPool = new WorkerPool(dataUrl);
        FutureTask<VideoCamera> futureTask = new FutureTask<>(workerPool);
        futureTask.run();
        VideoCamera videoCameraExpected = new VideoCamera("1", "LIVE",
                "rtsp://127.0.0.1/1", "fa4b588e-249b-11e9-ab14-d663bd873d93", "120");
        try {
            Assertions.assertEquals(videoCameraExpected, futureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}

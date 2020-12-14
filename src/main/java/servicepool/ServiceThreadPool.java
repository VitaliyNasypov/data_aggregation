package servicepool;

import model.VideoCamera;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import parser.JsonParserConnect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.*;

public class ServiceThreadPool {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceThreadPool.class.getName());
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();
    private static final ConcurrentLinkedQueue<Future<VideoCamera>> FUTURES =
            new ConcurrentLinkedQueue<>();

    public String getJson(String[] response) {
        Arrays.stream(response).map(dataUrl -> EXECUTOR_SERVICE
                .submit(new WorkerPool(dataUrl))).forEach(FUTURES::offer);
        ArrayList<VideoCamera> arrayList = new ArrayList<>();
        try {
            while (!FUTURES.isEmpty()) {
                arrayList.add(FUTURES.poll().get());
            }
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.error(e.getMessage(), e);
        }
        EXECUTOR_SERVICE.shutdown();
        return new JsonParserConnect().getJson(arrayList.toArray(VideoCamera[]::new));
    }
}

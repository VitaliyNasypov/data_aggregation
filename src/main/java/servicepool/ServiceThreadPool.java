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
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    private final ConcurrentLinkedQueue<Future<VideoCamera>> futures =
            new ConcurrentLinkedQueue<>();

    public String getJson(String[] response) {
        Arrays.stream(response).map(dataUrl -> executorService
                .submit(new WorkerPool(dataUrl))).forEach(futures::offer);
        ArrayList<VideoCamera> arrayList = new ArrayList<>();
        try {
            while (!futures.isEmpty()) {
                arrayList.add(futures.poll().get());
            }
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.error(e.getMessage(), e);
        }
        executorService.shutdown();
        return new JsonParserConnect().getJson(arrayList.toArray(VideoCamera[]::new));
    }
}

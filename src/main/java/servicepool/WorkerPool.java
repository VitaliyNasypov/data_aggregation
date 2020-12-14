package servicepool;

import model.VideoCamera;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import parser.JsonParserConnect;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

public class WorkerPool implements Callable<VideoCamera> {
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerPool.class.getName());
    private final String[] dataUrl;

    public WorkerPool(String dataUrl) {
        this.dataUrl = dataUrl.split(" ");
    }

    @Override
    public VideoCamera call() throws Exception {
        CompletableFuture<String> sourceDataUrl = CompletableFuture.supplyAsync(() ->
                connect(dataUrl[1], "urlType", "videoUrl"));
        CompletableFuture<String> tokenDataUrl = CompletableFuture.supplyAsync(() ->
                connect(dataUrl[2], "value", "ttl"));
        String[] getSourceDataUrl = sourceDataUrl.get().split(" ");
        String[] getTokenDataUrl = tokenDataUrl.get().split(" ");
        String id = dataUrl[0];
        String urlType = getSourceDataUrl[0];
        String videoUrl = getSourceDataUrl[1];
        String value = getTokenDataUrl[0];
        String ttl = getTokenDataUrl[1];
        return new VideoCamera(id, urlType, videoUrl, value, ttl);
    }

    private String connect(String urlText, String... params) {
        try {
            URL url = new URL(urlText);
            InputStream input = url.openStream();
            byte[] buffer = input.readAllBytes();
            String response = new String(buffer);
            return new JsonParserConnect().getData(response, params);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return "";
    }
}

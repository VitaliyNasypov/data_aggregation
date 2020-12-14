package parser;

import model.VideoCamera;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JsonParserConnectTest {
    @Test
    public void shouldReturnCorrectDataUrl() {
        String request = "[\n"
                + "    {\n"
                + "        \"id\": 1,\n"
                + "        \"sourceDataUrl\": \"http://www.mocky.io/v2/"
                + "5c51b230340000094f129f5d\",\n"
                + "        \"tokenDataUrl\": \"http://www.mocky.io/v2/"
                + "5c51b5b6340000554e129f7b?mocky-delay=1s\"\n"
                + "    },\n"
                + "    {\n"
                + "        \"id\": 20,\n"
                + "        \"sourceDataUrl\": \"http://www.mocky.io/v2/"
                + "5c51b2e6340000a24a129f5f?mocky-delay=100ms\",\n"
                + "        \"tokenDataUrl\": \"http://www.mocky.io/v2/"
                + "5c51b5ed340000554e129f7e\"\n"
                + "    },\n"
                + "    {\n"
                + "        \"id\": 3,\n"
                + "        \"sourceDataUrl\": \"http://www.mocky.io/v2/"
                + "5c51b4b1340000074f129f6c\",\n"
                + "        \"tokenDataUrl\": \"http://www.mocky.io/v2/"
                + "5c51b600340000514f129f7f?mocky-delay=2s\"\n"
                + "    },\n"
                + "    {\n"
                + "        \"id\": 2,\n"
                + "        \"sourceDataUrl\": \"http://www.mocky.io/v2/"
                + "5c51b5023400002f4f129f70\",\n"
                + "        \"tokenDataUrl\": \"http://www.mocky.io/v2/"
                + "5c51b623340000404f129f82\"\n"
                + "    }\n"
                + "]";
        String[] params = new String[]{"id", "sourceDataUrl", "tokenDataUrl"};
        String expected = "2 http://www.mocky.io/v2/5c51b5023400002f4f129f70 "
                + "http://www.mocky.io/v2/5c51b623340000404f129f82";
        Assertions.assertEquals(expected, new JsonParserConnect().getDataUrl(request, params)[3]);
    }

    @Test
    public void shouldReturnCorrectData() {
        String request = "{\n"
                + "    \"value\": \"fa4b588e-249b-11e9-ab14-d663bd873d93\",\n"
                + "    \"ttl\": 120\n"
                + "}";
        String[] params = new String[]{"value", "ttl"};
        String expected = "fa4b588e-249b-11e9-ab14-d663bd873d93 120";
        Assertions.assertEquals(expected, new JsonParserConnect().getData(request, params));
    }

    @Test
    public void shouldReturnCorrectJson() {
        VideoCamera videoCamera1 = new VideoCamera("1", "1", "1",
                "1", "1");
        VideoCamera videoCamera2 = new VideoCamera("2", "2", "2",
                "2", "2");
        VideoCamera videoCamera3 = new VideoCamera("3", "3", "3",
                "3", "3");
        JsonParserConnect jsonParserConnect = new JsonParserConnect();
        String expected = "[{\"id\":\"1\",\"urlType\":\"1\",\"videoUrl\":\"1\","
                + "\"value\":\"1\","
                + "\"ttl\":\"1\"},{\"id\":\"2\",\"urlType\":\"2\",\"videoUrl\":"
                + "\"2\",\"value\":\"2\","
                + "\"ttl\":\"2\"},{\"id\":\"3\",\"urlType\":\"3\",\"videoUrl\":\"3\","
                + "\"value\":\"3\",\"ttl\":\"3\"}]";
        Assertions.assertEquals(expected, jsonParserConnect.getJson(videoCamera1,
                videoCamera2, videoCamera3));
    }
}

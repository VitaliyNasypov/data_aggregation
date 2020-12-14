package parser;

import com.google.gson.*;
import model.VideoCamera;

public class JsonParserConnect {
    public String[] getDataUrl(String response, String... params) {
        JsonArray jsonArray = JsonParser.parseString(response).getAsJsonArray();
        String[] arrayDataUrl = new String[jsonArray.size()];
        for (int i = 0; i < arrayDataUrl.length; i++) {
            StringBuilder dataUrl = new StringBuilder();
            for (String param : params) {
                dataUrl.append(jsonArray.get(i).getAsJsonObject().get(param)).append(" ");
            }
            arrayDataUrl[i] = dataUrl.toString().replaceAll("\"", "").trim();
        }
        return arrayDataUrl;
    }

    public String getData(String response, String... params) {
        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
        StringBuilder data = new StringBuilder();
        for (String param : params) {
            data.append(jsonObject.get(param)).append(" ");
        }
        return data.toString().replaceAll("\"", "").trim();
    }

    public String getJson(VideoCamera... videoCameras) {
        return new Gson().toJson(videoCameras);
    }
}

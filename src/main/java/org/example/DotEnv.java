package org.example;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class DotEnv {
    private Map<String, String> env;

    public DotEnv(String fileName) {
        Path path;
        try {
            URL resourceUrl = DotEnv.class.getClassLoader().getResource(fileName);
            path = Paths.get(resourceUrl.toURI());
            List<String> list = Files.readAllLines(path);

            list.removeIf(line -> line.length() == 0);

            this.env = new HashMap<>();
            list.forEach(l -> {
                String[] kv = l.split("=");
                kv[0] = kv[0].replace(" ", "");
                kv[1] = kv[1].strip().replace("\"", "").replace("'", "");
                env.put(kv[0], kv[1]);
            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public String getVariable(String key) {
        return env.get(key.strip());
    }
}

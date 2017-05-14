package net.infstudio.foodcraftreloaded.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.*;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class JsonSettings implements ISettings, Flushable {
    private Map<String, JsonObject> domainConfigMap = new ConcurrentHashMap<>();
    private final File configFolder;

    public JsonSettings(File configFolder, String... domains) {
        if (configFolder.isFile())
            configFolder.delete();
        configFolder.mkdirs();
        Gson gson = new Gson();
        for (File file : configFolder.listFiles((dir, name) -> name.endsWith(".json")))
            try {
                domainConfigMap.put(file.getName().split("\\.")[0], gson.fromJson(new FileReader(file), JsonObject.class));
            } catch (FileNotFoundException ignored) {}
        if (!domainConfigMap.keySet().containsAll(Arrays.asList(domains)))
            for (String domain : domains)
                if (!domainConfigMap.keySet().contains(domain))
                    domainConfigMap.put(domain, new JsonObject());
        this.configFolder = configFolder;
    }

    @Override
    public File getConfigFolder() {
        return configFolder;
    }

    @Override
    public File getConfigFile(String domain) {
        return new File(configFolder, domain + ".json");
    }

    @Override
    public Object remove(String domain, String property) {
        return domainConfigMap.get(domain).remove(property);
    }

    @Override
    public void addProperty(String domain, String property, String value) {
        JsonObject object = domainConfigMap.get(domain);
        object.addProperty(property, value);
        domainConfigMap.put(domain, object);
    }

    @Override
    public void addProperty(String domain, String property, Number value) {
        JsonObject object = domainConfigMap.get(domain);
        object.addProperty(property, value);
        domainConfigMap.put(domain, object);
    }

    @Override
    public void addProperty(String domain, String property, boolean value) {
        JsonObject object = domainConfigMap.get(domain);
        object.addProperty(property, value);
        domainConfigMap.put(domain, object);
    }

    @Override
    public void addProperty(String domain, String property, char value) {
        JsonObject object = domainConfigMap.get(domain);
        object.addProperty(property, value);
        domainConfigMap.put(domain, object);
    }

    @Override
    public JsonElement getProperty(String domain, String property) {
        return domainConfigMap.get(domain).get(property);
    }

    @Override
    public Set<Map.Entry<String, JsonElement>> entrySet(String domain) {
        return domainConfigMap.get(domain).entrySet();
    }

    @Override
    public void flush() throws IOException {
        for (String domain : domainConfigMap.keySet()) {
            File file = new File(configFolder, domain);
            if (!file.exists())
                file.createNewFile();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(domainConfigMap.get(domain), new FileWriter(file));
        }
    }
}

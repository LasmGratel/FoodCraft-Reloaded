package net.infstudio.foodcraftreloaded.utils;

import com.google.gson.JsonElement;

import java.io.File;
import java.util.Map;
import java.util.Set;

public interface ISettings {
    File getConfigFolder();
    File getConfigFile(String domain);
    Object remove(String domain, String property);
    void addProperty(String domain, String property, String value);
    void addProperty(String domain, String property, Number value);
    void addProperty(String domain, String property, boolean value);
    void addProperty(String domain, String property, char value);
    Object getProperty(String domain, String property);
    Set<Map.Entry<String, JsonElement>> entrySet(String domain);
}

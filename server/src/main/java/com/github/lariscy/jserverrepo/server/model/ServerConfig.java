package com.github.lariscy.jserverrepo.server.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Steven Lariscy
 */
public class ServerConfig {
    
    private static final Map<String, String> CONFIG_MAP = new HashMap<>();
    private static ServerConfig instance;
    
    private ServerConfig(){
        loadConfig();
    }
    
    private void loadConfig(){
        //@TODO config pulled from server_config db table
    }
    
    public static ServerConfig get(){
        if (instance==null){
            instance = new ServerConfig();
        }
        return instance;
    }
    
    public void addConfig(String key, String value){
        CONFIG_MAP.put(key, value);
    }
    
    public String getConfig(String key){
        return CONFIG_MAP.get(key);
    }
    
}

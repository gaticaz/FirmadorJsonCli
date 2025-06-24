/**
 * CLI para firmar archivos JSON usando JWS.
 * clase de configuración
 * 
 * Uso libre.
 *
 * @author gaticaz
 */

package ar.edu.firmadorjson.firmadorjsoncli;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private final Properties props = new Properties();

    public Config() {
        try (InputStream input = new FileInputStream("config.properties")) {
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error cargando configuración", e);
        }
    }

    public String getCertPath() {
        return props.getProperty("cert.path");
    }

    public String getCertPassword() {
        return props.getProperty("cert.password");
    }

    public String getTsaUrl() {
        return props.getProperty("tsa.url");
    }
}


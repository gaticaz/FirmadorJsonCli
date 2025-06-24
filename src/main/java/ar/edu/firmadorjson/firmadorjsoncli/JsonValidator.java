/**
 * CLI para firmar archivos JSON usando JWS.
 * 
 * Validador de JSON
 * Uso libre.
 *
 * @author gaticaz
 */

package ar.edu.firmadorjson.firmadorjsoncli;


import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;

public class JsonValidator {
    public static JSONObject validateAndLoad(String path) throws Exception {
        try (FileReader reader = new FileReader(path)) {
            return new JSONObject(new JSONTokener(reader));
        } catch (Exception e) {
            throw new IllegalArgumentException("JSON inválido: " + e.getMessage(), e);
        }
    }
}

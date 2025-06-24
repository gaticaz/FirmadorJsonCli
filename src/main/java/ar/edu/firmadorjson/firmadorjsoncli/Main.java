/**
 * CLI para firmar archivos JSON usando JWS.
 * Uso libre.
 *
 * @author gaticaz
 */

package ar.edu.firmadorjson.firmadorjsoncli;

import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        if (args.length < 2 || !args[0].equals("--json")) {
            System.out.println("Uso: java -jar FirmadorJsonCli.jar --json archivo.json");
            return;
        }

        String jsonPath = args[1];
        //boolean detached = Arrays.asList(args).contains("--detached") && args[Arrays.asList(args).indexOf("--detached") + 1].equalsIgnoreCase("true");

        Config config = new Config();

        JSONObject json = JsonValidator.validateAndLoad(jsonPath);
        Signer signer = new Signer(config.getCertPath(), config.getCertPassword());
        String firma = signer.sign(json);

        // TSA
        byte[] dataToStamp = firma.getBytes();
        var tsr = TSAClient.getTimestamp(config.getTsaUrl(), dataToStamp);

        System.out.println("Firma válida: " + SignatureVerifier.verify(firma, signer.getCertificate()));


        Files.write(Path.of("json_firmado.jws"), firma.getBytes());
        Files.write(Path.of("timestamp.tsr"), tsr.getEncoded());

        System.out.println("Firma completa.");
    }
}


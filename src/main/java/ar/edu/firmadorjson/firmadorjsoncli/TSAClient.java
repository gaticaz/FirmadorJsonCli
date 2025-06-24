/**
 * CLI para firmar archivos JSON usando JWS.
 * clase de sello de tiempo
 * 
 * Uso libre.
 *
 * @author gaticaz
 */
package ar.edu.firmadorjson.firmadorjsoncli;

import org.bouncycastle.tsp.*;
import org.bouncycastle.util.encoders.Base64;

import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Date;

public class TSAClient {
    public static TimeStampResponse getTimestamp(String tsaUrl, byte[] data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest(data);

        TimeStampRequestGenerator reqGen = new TimeStampRequestGenerator();
        TimeStampRequest request = reqGen.generate(TSPAlgorithms.SHA256, digest, BigInteger.valueOf(System.currentTimeMillis()));

        byte[] reqData = request.getEncoded();
        URL url = new URL(tsaUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/timestamp-query");
        con.connect();

        try (OutputStream out = con.getOutputStream()) {
            out.write(reqData);
        }

        try (InputStream in = con.getInputStream()) {
            return new TimeStampResponse(in);
        }
    }
}

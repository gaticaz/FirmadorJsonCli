/**
 * CLI para firmar archivos JSON usando JWS.
 * clase de firmas
 * 
 * Uso libre.
 *
 * @author gaticaz
 */

package ar.edu.firmadorjson.firmadorjsoncli;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.util.Base64URL;
import org.json.JSONObject;

import java.io.*;
import java.security.*;
import java.security.cert.Certificate;

public class Signer {
    private final PrivateKey privateKey;
    private final Certificate cert;

    public Signer(String certPath, String password) throws Exception {
        KeyStore ks = KeyStore.getInstance("PKCS12");
        try (FileInputStream fis = new FileInputStream(certPath)) {
            ks.load(fis, password.toCharArray());
        }
        String alias = ks.aliases().nextElement();
        this.privateKey = (PrivateKey) ks.getKey(alias, password.toCharArray());
        this.cert = ks.getCertificate(alias);
    }

    public Certificate getCertificate() {
        return this.cert;
    }

    public String sign(JSONObject json) throws JOSEException {
        JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.RS256)
        .type(new JOSEObjectType("application/json"))
        .build();

        Payload payload = new Payload(json.toString());
        JWSObject jws = new JWSObject(header, payload);
        jws.sign(new RSASSASigner(privateKey));

        return jws.serialize();
    }
}
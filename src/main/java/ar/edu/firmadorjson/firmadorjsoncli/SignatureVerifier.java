/**
 * CLI para firmar archivos JSON usando JWS.
 * clase de verificacion
 * 
 * Uso libre.
 *
 * @author gaticaz
 */
package ar.edu.firmadorjson.firmadorjsoncli;

import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.util.Base64URL;

import java.security.PublicKey;
import java.security.cert.Certificate;

public class SignatureVerifier {
    public static boolean verify(String jwsString, Certificate cert) throws Exception {
        JWSObject jws = JWSObject.parse(jwsString);
        PublicKey publicKey = cert.getPublicKey();
        JWSVerifier verifier = new RSASSAVerifier((java.security.interfaces.RSAPublicKey) publicKey);
        return jws.verify(verifier);
    }
}

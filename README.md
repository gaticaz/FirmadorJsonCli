# FirmadorJsonCli

Aplicación CLI en Java para firmar digitalmente archivos JSON utilizando firmas en formato JWS (JSON Web Signature) con algoritmo RS256. Además, agrega un sello de tiempo mediante un servidor TSA externo y valida la firma antes de guardar el resultado.

---

## ✨ Características

- Firma digital tipo **JWS embebida** (`header.payload.signature`).
- Agregado de **sello de tiempo** RFC 3161 usando TSA externo.
- Verificación de firma antes de guardar el resultado.
- Uso de certificados en formato `.p12` (PKCS#12).
- Interfaz de línea de comandos (CLI).
- Archivo de configuración externo (`config.properties`).

---

## 🧰 Requisitos

- Java 17+
- Maven 3+
- Un certificado digital válido en formato `.p12`
- Un servidor TSA accesible (por ejemplo: `https://freetsa.org/tsr`)

---

## 🔧 Instalación

```bash
git clone https://github.com/usuario/FirmadorJsonCli.git
cd FirmadorJsonCli
mvn clean package
```

El ejecutable se generará en: target/FirmadorJsonCli-1.0-SNAPSHOT.jar

---

## ⚙️ Configuración

Crear un archivo llamado config.properties en el mismo directorio que el .jar, con el siguiente contenido:

cert.path=certificado.p12
cert.password=tu_password
tsa.url=https://freetsa.org/tsr

---

## 🚀 Uso

java -jar FirmadorJsonCli-1.0-SNAPSHOT.jar --json datos.json

Archivos generados:

    json_firmado.jws → JSON firmado digitalmente en formato JWS

    timestamp.tsr → Sello de tiempo RFC 3161

    Firma verificada antes de guardar

---

## 📄 Ejemplo de archivo JSON

{
  "id": 12345,
  "nombre": "Juan Pérez",
  "activo": true
}

---

## 🔐 Consideraciones legales

    El uso de firmas digitales con certificados emitidos por una autoridad certificante reconocida puede tener validez legal.

    Este firmador no altera el contenido del JSON original (firma embebida).

    Para cumplimiento normativo (ej. eIDAS, ley 25.506 en Argentina), puede ser necesario usar estándares como XAdES o CAdES.

---

## 📚 Dependencias principales

    Nimbus JOSE + JWT – Para generar JWS

    Bouncy Castle – Para manejo de certificados y sello de tiempo

---

## 🧪 Testing

Podés probar el firmado sobre un archivo de prueba (datos.json) y verificar los archivos generados con un visor de JWS o con el mismo CLI.

---

## 🛠️ To Do

Soporte para múltiples algoritmos de firma

Opción de salida personalizada (--out)

Validación estructural del JSON contra schema

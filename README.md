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

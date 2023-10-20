package LogicaDeBingo;

import java.util.Properties;
import java.util.List;
import java.util.Arrays;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.Session;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.MessagingException;

public class CuentaCorreo {
  private String usuario;
  private String clave = "ckerpjofqvgxizkv";
  private String servidor = "smtp.gmail.com";
  private String puerto = "587";
  private Properties propiedades;
  
  public CuentaCorreo (String pCorreo) {
    propiedades = new Properties();
    propiedades.put("mail.smtp.host", servidor);
    propiedades.put("mail.smtp.port", puerto);
    propiedades.put("mail.smtp.auth", "true");
    propiedades.put("mail.smtp.starttls.enable", "true");
    usuario = pCorreo;
  }
  
  private Session abrirSession() {
    Session sesion = Session.getInstance(propiedades,
      new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
          return new PasswordAuthentication(usuario, clave);
        }
    });
    return sesion;
  }
  
  public void enviarCorreo(String destinatario, String tituloCorreo, String cuerpo, List<String> rutasImagenes) {
    Session sesion = abrirSession();

    try {
      Message message = new MimeMessage(sesion);
      message.setFrom(new InternetAddress(usuario));
      message.setRecipients(
        Message.RecipientType.TO,
        InternetAddress.parse(destinatario)
      );
      message.setSubject(tituloCorreo);

      // Create a multipart message
      MimeMultipart multipart = new MimeMultipart();

      // First part (the text)
      MimeBodyPart textBodyPart = new MimeBodyPart();
      textBodyPart.setText(cuerpo);
      multipart.addBodyPart(textBodyPart);

      // Add images
      for (String rutaImagen : rutasImagenes) {
        MimeBodyPart imageBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(rutaImagen);
        imageBodyPart.setDataHandler(new DataHandler(source));
        imageBodyPart.setFileName(rutaImagen);
        multipart.addBodyPart(imageBodyPart);
      }

      // Set the complete message parts
      message.setContent(multipart);

      // Send message
      Transport.send(message);
    } catch (MessagingException e) {
      e.printStackTrace();
    }
  }
  
  public static void main(String[] args) {
    CuentaCorreo cuenta = new CuentaCorreo("bingoteclimon@gmail.com");

    String destinatario = "heldyis1020@gmail.com";
    String titulo = "Prueba de Envío de Correo";
    String mensaje = "Hola mundo, enviando correo...";

    // Lista de imágenes que deseas enviar
    List<String> rutasImagenes = Arrays.asList(
      "/ruta/absoluta/de/la/imagen1.jpg",
      "/ruta/absoluta/de/la/imagen2.jpg",
      "/ruta/absoluta/de/la/imagen3.jpg"
      // ... puedes añadir más rutas si lo deseas
    );

    cuenta.enviarCorreo(destinatario, titulo, mensaje, rutasImagenes);
  }
}
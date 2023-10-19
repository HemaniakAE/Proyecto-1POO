import java.util.Properties;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
  
  public void enviarCorreo(String destinatario, String tituloCorreo, String cuerpo
                        ) {
    Session sesion = abrirSession();
    
    try {
      Message message = new MimeMessage (sesion);
      message.setFrom(new InternetAddress (usuario));
      message.setRecipients(
        Message.RecipientType.TO,
        InternetAddress.parse(destinatario)
      );
      message.setSubject(tituloCorreo);
      message.setText(cuerpo);
      
      Transport.send(message);
    }
    catch (MessagingException e) {
      e.printStackTrace();
    }
  }
}

/*
 * EmailSender.java (Correct)
 */
package connexion;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {

    

    private static final String MON_EMAIL = "lakhalsalma18@gmail.com"; 
    
    
    private static final String MON_PASSWORD = "hvnb qfdx ezvt yfwh"; 

    public static void sendPassword(String destinataire, String motDePasse) throws Exception {
        
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MON_EMAIL, MON_PASSWORD);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(MON_EMAIL));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinataire));
        message.setSubject("Récupération Mot de Passe");
        message.setText("Votre mot de passe est : " + motDePasse);

        Transport.send(message);
        System.out.println("Email envoyé !");
    }
    
    
}

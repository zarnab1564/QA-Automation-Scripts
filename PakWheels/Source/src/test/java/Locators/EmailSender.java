package Locators;

import java.net.Authenticator;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Properties;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;


public class EmailSender {
	public static void sendReport(String reportPath,String File1Path) throws Exception {
		
		File reportFile = new File(reportPath);
	    File executionFile = new File(File1Path);
	    
	    // FORCE FILE REFRESH - This is critical
        System.out.println("🔄 Force refreshing file handles...");
        forceFileRefresh(reportFile);
        forceFileRefresh(executionFile);
	     // Extended wait time to ensure files are completely written
        try {
            Thread.sleep(8000); // Increased to 8 seconds
            System.out.println("⏳ Waiting for report files to be ready...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } 	
	    waitForFileToStabilize(reportFile);
	    waitForFileToStabilize(executionFile);
	    
	    waitForFileReady(reportFile);
	    waitForFileReady(executionFile);
	    // Create fresh file handles (important for file system caching issues)
        reportFile = new File(reportPath);
        executionFile = new File(File1Path);
	    if (!reportFile.exists()) {
	        System.out.println("Report file not found: " + reportPath);
	        return;
	    }
      

	    if (!executionFile.exists()) {
	        System.out.println("Execution file not found: " + File1Path);
	        return;
	    }
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String executionTime = dateFormat.format(new Date());

	   // String to = "muhammad.umer@pakeventures.com";
	    String to = "engineering@pakeventures.com";
	   // String to = "anam.zahid@pakeventures.com";
	    String from = "anam.zahid@pakeventures.com";
	    final String username = "anam.zahid@pakeventures.com";
	    final String password = "srwq vwrx zfjj uyhh"; // use App Password
	    String host = "smtp.gmail.com";

	    Properties props = new Properties();
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.host", host);
	    props.put("mail.smtp.port", "587");

	    Session session = Session.getInstance(props,
	        new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(username, password);
	            }
	        });

	    // Create email message
	    Message message = new MimeMessage(session);
	    message.setFrom(new InternetAddress(from));
	    message.setRecipients(
	        Message.RecipientType.TO,
	        InternetAddress.parse(to)
	    );
	    
	    String cc = "hamza.saif@pakeventures.com,muhammad.taimoor@pakeventures.com";
	    message.setRecipients(
	        Message.RecipientType.CC,
	        InternetAddress.parse(cc)
	    );
	    message.setSubject("📋 Automated Test Report [Android]");
	    
	 // Create HTML-formatted message body
	    MimeBodyPart messageBodyPart = new MimeBodyPart();
	    messageBodyPart.setContent(
	            "<html><body>" +
	            "Hi, <br><br>" +
	            "Please find the attached Automated test report [Android].<br><br>" +
	            "<b>Testing Type:</b> " + Base.Testing + "<br>" +
	            "<b>Execution Time:</b> " + executionTime + "<br>" +
	            "<b>Automation Framework:</b> Appium (Mobile Automation Tool)<br>" +
	            "<b>Environment:</b> Real Device<br>" +
	            "<hr>" +
	            "<i>For detailed information, please open the attached files in Html View.</i>" +
	            "</body></html>",
	            "text/html");


	    // Read only the execution report for inline display
	    String executionHtml = new String(Files.readAllBytes(Paths.get(File1Path)), StandardCharsets.UTF_8);

	    String inlineHtml = "<div>" +
	            "<h2 style='color:#333;'><span style='font-size:20px;'>&#x1F9EA;</span> Execution Report Status</h2><hr>" + executionHtml +
	            "</div>";

	    // Create HTML body part for inline content
	    MimeBodyPart htmlBodyPart = new MimeBodyPart();
	    htmlBodyPart.setContent(inlineHtml, "text/html");

	    // Create attachments
	    MimeBodyPart attachmentPart1 = new MimeBodyPart();
	    attachmentPart1.attachFile(reportFile);

	    MimeBodyPart attachmentPart2 = new MimeBodyPart();
	    attachmentPart2.attachFile(executionFile);

	    // Combine everything into one multipart
	    Multipart multipart = new MimeMultipart("mixed");  // Use "mixed" type
	    multipart.addBodyPart(messageBodyPart);  // Text message
	    multipart.addBodyPart(htmlBodyPart);     // Inline HTML content
	    multipart.addBodyPart(attachmentPart1);  // Attachment 1
	    multipart.addBodyPart(attachmentPart2);  // Attachment 2

	    // Set the complete content only once
	    message.setContent(multipart);

	    // Send email
	    Transport.send(message);
	    //System.out.println("✅ Email sent!");
  }
	 private static void forceFileRefresh(File file) {
	        try {
	            if (file.exists()) {
	                // Force read a small portion to refresh file system cache
	                FileInputStream fis = new FileInputStream(file);
	                byte[] buffer = new byte[1024];
	                int bytesRead = fis.read(buffer);
	                fis.close();
	                
	                // Force metadata refresh
	                file.length();
	                file.lastModified();
	                
	                System.out.println("🔄 Refreshed: " + file.getName() + " (" + bytesRead + " bytes read)");
	            }
	        } catch (Exception e) {
	            System.out.println("⚠️ Could not refresh file: " + file.getName() + " - " + e.getMessage());
	        }
	    }
	   private static void waitForFileToStabilize(File file) throws InterruptedException {
	        if (!file.exists()) {
	            System.out.println("⚠️ File does not exist yet: " + file.getName());
	            return;
	        }
	        
	        long lastModified = file.lastModified();
	        long lastSize = file.length();
	        int maxAttempts = 10; // Increased attempts
	        int attempts = 0;
	        
	        System.out.println("⏳ Checking file stability: " + file.getName());
	        
	        while (attempts < maxAttempts) {
	            Thread.sleep(2000); // 2 seconds
	            long newModified = file.lastModified();
	            long newSize = file.length();
	            
	            if (newModified == lastModified && newSize == lastSize && newSize > 0) {
	                System.out.println("✅ File is stable: " + file.getName() + " (Size: " + newSize + " bytes)");
	                return; // File is stable
	            }
	            
	            lastModified = newModified;
	            lastSize = newSize;
	            attempts++;
	            System.out.println("⏳ File still changing... attempt " + (attempts + 1) + "/" + maxAttempts);
	        }
	        
	        System.out.println("⚠️ File may still be changing after " + maxAttempts + " attempts: " + file.getName());
	    }
	    
	    private static void waitForFileReady(File file) throws InterruptedException {
	        int retries = 15; // Increased retries
	        while ((!file.exists() || file.length() == 0) && retries-- > 0) {
	            System.out.println("⏳ Waiting for file to be ready: " + file.getName() + " (Retries left: " + retries + ")");
	            Thread.sleep(1000);
	        }
	        
	        if (!file.exists() || file.length() == 0) {
	            System.out.println("❌ File is still not ready: " + file.getName());
	        } else {
	            System.out.println("✅ File is ready: " + file.getName());
	        }
	    }
}



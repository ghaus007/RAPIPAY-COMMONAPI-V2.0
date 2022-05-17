package com.rapipay.commonapi.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Util {
	
    public static final Logger log = LogManager.getLogger(Util.class);
	public static String urlToBase64(String urlOfImage,String urn) {
        try {
           // URL url = new URL("https://crmeta.rapipayuat.com/KYCDOC/8888888888/8888888888_passportPhoto_20211108174646.png");
            URL url = new URL(urlOfImage);
            BufferedImage image = ImageIO.read(url);
            BufferedImage convertedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
            convertedImage.createGraphics().drawImage(image, 0, 0, Color.WHITE, null);
            String imageName = urlOfImage.substring(urlOfImage.lastIndexOf('/')+1,urlOfImage.length());
            FileOutputStream fileOutputStream = new FileOutputStream(imageName);
            ImageIO.write(convertedImage, "jpg", fileOutputStream);
            File f = new File(imageName);
           
            FileInputStream fileInputStreamReader = new FileInputStream(f);
            byte[] bytes = new byte[(int) f.length()];
            fileInputStreamReader.read(bytes);
            String result = new String(Base64.getEncoder().encodeToString(bytes));
            f.deleteOnExit();
            fileOutputStream.close();
            return result;

        } catch (IOException e) {
            log.error("[URN_{}]Error converting Url to base64 : {} {}",urn,e.getMessage(),e.toString());
            return "";
        } catch (NullPointerException e){
            log.error("[URN_{}]Error converting Url to base64 : {} {}",urn,e.getMessage(),e.toString());
            return "";
        }catch (Exception e){
            log.error("[URN_{}]Error converting Url to base64 : {} {}",urn,e.getMessage(),e.toString());
            return "";
        }


}
    public static String generateTransactionId(String urn) {
        log.info("Generating transaction id.");
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat(Constants.DATE_PATTERN);
        String datetime = ft.format(date);
        String transactionId = (datetime + (1 + Math.random()) * Constants.MULTY).substring(0, 20);
        log.info("[URN_{}] Generated transaction Id : {}",urn, transactionId);
        return transactionId;
    }
}

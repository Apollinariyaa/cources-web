package com.example.airport.services;

import com.example.airport.Application;
import com.example.airport.domain.Bought;
import com.ibm.icu.text.Transliterator;
import com.itextpdf.text.Image;
import com.itextpdf.text.*;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Objects;

@Service
public class PdfService {

    final UserService userService;

    public PdfService(UserService userService) {
        this.userService = userService;
    }

    private String transliteration(String strInCyrillic) {

        String CYRILLIC_TO_LATIN = "Russian-Latin/BGN";
        Transliterator toLatinTrans = Transliterator.getInstance(CYRILLIC_TO_LATIN);
        return toLatinTrans.transliterate(strInCyrillic);
    }

    public String createQR(String content, Long id){
        try {
            Document document = new Document(new Rectangle(135, 135));
            String filename = "src/main/java/com/example/airport/static/qr" + id + ".pdf";
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
            document.open();
            BarcodeQRCode my_code = new BarcodeQRCode(content, 1, 1, null);
            Image qr_image = my_code.getImage();
            qr_image.setAlignment(Element.ALIGN_CENTER);
            document.add(qr_image);
            document.close();
            return filename;
        } catch (Exception e) {
        }
        return "";
    }

    public void createText(String content, Long id){
        try {
            Document document = new Document(new Rectangle(135, 135));
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("src/main/java/com/example/airport/static/text" + id + ".pdf"));
            document.open();
            document.addSubject(content);
            document.close();
        } catch (Exception e) {
        }
    }
    public String createContent(Bought elem) {

        if (elem == null) return "";
        BaseFont baseFont=null;
        try {
            baseFont = BaseFont.createFont(Objects.requireNonNull(Application.class.getResource(
                            "/fonts/ChampagneAndLimousinesBoldItalic-dqex.ttf")).toString(),
                    BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.YYYY");

        String content = "Check #" + elem.getId() + '\n' +
                "Name: " + elem.getCard().getShopElement().getName() + '\n' +
                "Creation Date:" + fmt.format(elem.getCard().getShopElement().getDate()) + '\n' +
                "Cost: " + (elem.getCard().getCount() * (100.0 - userService.calculateDiscount(elem.getCard().getClientInfo())) /100.0
                * elem.getCard().getShopElement().getCost()) + '\n' +
                "Count: " + elem.getCard().getCount() + '\n' +
                "Credentials : " + elem.getCredentials()  + '\n' +
                "Adress : " + elem.getAdress();

        content = transliteration(content);

        return content;
    }
}

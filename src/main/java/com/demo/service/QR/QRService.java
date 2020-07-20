package com.demo.service.QR;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.demo.model.operacion.SolicitudServicioClienteMuestras;
import com.demo.service.operacion.SolicitudServicioClienteMuestrasService;
import com.demo.utils.EstructuraNombres;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QRService {
    public String generate(Long id) {
        EstructuraNombres estructuraNombres = new EstructuraNombres();

        //CAMBIAR CUANDO LAS PRUEBAS SEAN EN WINDOWS / LINUX (SERVIDOR)
        String archivo = System.getProperty("user.home") + "/" + estructuraNombres.getNombreQR() + ".png";
        //String archivo = "/home/adpmx/java/laboratorio.cecim.com.mx/QR/" + estructuraNombres.getNombreQR() + ".png";

        //CAMBIAR CUANDO LAS PRUEBAS SEAN EN WINDOWS / LINUX (SERVIDOR)
        String filePath = System.getProperty("user.home") + "/" + estructuraNombres.getNombreQR() + ".png";
        //String filePath = "/home/adpmx/java/laboratorio.cecim.com.mx/QR/" + estructuraNombres.getNombreQR() + ".png";

        String myCodeText = id.toString();
        int size = 250;
        String fileType = "png";
        File myFile = new File(filePath);

        try {
            Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
            hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            // Now with zxing version 3.2.1 you could change border size (white border size to just 1)
            hintMap.put(EncodeHintType.MARGIN, 1); /* default = 4 */

            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix byteMatrix = qrCodeWriter.encode(myCodeText, BarcodeFormat.QR_CODE, size,
                    size, hintMap);
            int CrunchifyWidth = byteMatrix.getWidth();
            BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth,
                    BufferedImage.TYPE_INT_RGB);
            image.createGraphics();

            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < CrunchifyWidth; i++) {
                for (int j = 0; j < CrunchifyWidth; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }

            ImageIO.write(image, fileType, myFile);

        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\nSe creó satisfactoriamente el QR.");

        //solicitudServicioClienteMuestras.setPathQRIdentificacion(archivo);
        //solicitudServicioClienteMuestrasService.save(solicitudServicioClienteMuestras);

        return archivo;
        //} else {
        //  System.out.println("El QR ya existe, se encuentra en: " + solicitudServicioClienteMuestras.getPathQRIdentificacion());
        //return solicitudServicioClienteMuestras.getPathQRIdentificacion();
        //}
    }
}
package com.clase.tarea.helper;

import org.springframework.stereotype.Service;
import java.util.Date;
import com.itextpdf.text.Document;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class Methods {

    public long sincePublication(Date date) {
        Date currentDate = new Date();
        long startTime = date.getTime();
        long endTime = currentDate.getTime();
        long daysSince = (long) Math.floor(startTime / (1000 * 60 * 60 * 24));
        long daysUntil = (long) Math.floor(endTime / (1000 * 60 * 60 * 24));
        return daysUntil - daysSince;
    }

    public void convertTextToPdf(String inputText, String outputPdfPath) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(outputPdfPath));
            document.open();
            document.add(new Paragraph(inputText));
            document.close();
            System.out.println("PDF generado correctamente en: " + outputPdfPath);
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

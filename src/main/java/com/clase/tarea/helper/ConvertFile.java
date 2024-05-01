package com.clase.tarea.helper;

import com.clase.tarea.repository.IConvertFileRepository;
import lombok.AllArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
@AllArgsConstructor
public class ConvertFile implements IConvertFileRepository {
    @Override
    public InputStream getFile(String path) {
        try {
            // Crea un objeto File con la ruta especificada
            File file = new File(path);

            // Verifica si el archivo existe
            if (!file.exists()) {
                System.out.println("El archivo no existe en la ruta proporcionada.");
                return null;
            }

            // Retorna un InputStream utilizando un FileInputStream
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            // Maneja la excepción si el archivo no se puede encontrar
            System.out.println("Error al abrir el archivo: " + e.getMessage());
            return null;
        }

    }

    @Override
    public void convert(InputStream inputStream, String pathDestination) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                // Configura la fuente y el tamaño del texto
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.beginText();

                // Lee el contenido del InputStream (archivo TXT)
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                    String line;
                    float yOffset = 700; // Posición vertical del texto en la página
                    while ((line = reader.readLine()) != null) {
                        contentStream.newLineAtOffset(50, yOffset);
                        contentStream.showText(line);
                        yOffset -= 15; // Espaciado entre líneas
                    }
                }

                contentStream.endText();
            }

            // Guarda el PDF generado en un archivo
            document.save(new File(pathDestination));
        }
    }

}

package com.education.service.fileConvertion.impl;

import com.education.service.fileConvertion.FileConversionService;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.docx4j.Docx4J;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Представляет реализацию конвертации файлов в формат pdf
 *
 * @author Дарья Лукьянова
 */


@Service
@RequiredArgsConstructor
public class FileConversionServiceImpl implements FileConversionService {

    @Override
    public byte[] convertFile(MultipartFile multipartFile) {
        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename()); //определяем расширение файла

//        конвертация файлов с расширениями doc, docx
        if ("doc".equals(extension) || "docx".equals(extension)) {
            try (BufferedInputStream buffIs = new BufferedInputStream(
                    new ByteArrayInputStream(multipartFile.getBytes()));
                 ByteArrayOutputStream os = new ByteArrayOutputStream()) {
                WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(buffIs);
                Docx4J.toPDF(wordMLPackage, os);
                os.flush();
                return os.toByteArray();
            } catch (Throwable e) {
                e.printStackTrace();
            }
//        конвертация файлов с расширениями jpg, png
        } else if ("jpg".equals(extension) || "png".equals(extension)) {
            try (BufferedInputStream buffIs = new BufferedInputStream(
                    new ByteArrayInputStream(multipartFile.getBytes()));
                 ByteArrayOutputStream os = new ByteArrayOutputStream()) {
                PdfWriter pdfWriter = new PdfWriter(os);
                PdfDocument pdfDocument = new PdfDocument(pdfWriter);
                Document document = new Document(pdfDocument);
                ImageData data = ImageDataFactory.create(buffIs.readAllBytes());
                Image image = new Image(data);
                document.add(image);
                document.close();
                return os.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new byte[0];
    }
}

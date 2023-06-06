package com.education.utils.fileConvertion.impl;

import com.education.utils.fileConvertion.FileConversionService;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.text.pdf.PdfReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.apache.commons.io.FilenameUtils;
import org.docx4j.Docx4J;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * Представляет реализацию конвертации файлов в формат pdf
 *
 * @author Дарья Лукьянова
 */
@Log
@Service
@RequiredArgsConstructor
public class FileConversionServiceImpl implements FileConversionService {

    @Override
    public Map<String, Object> convertFile(MultipartFile multipartFile) {
        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename()); //определяем расширение файла
//        конвертация файлов с расширениями doc, docx
        if ("doc".equals(extension) || "docx".equals(extension)) {
            try (BufferedInputStream buffIs = new BufferedInputStream(
                    new ByteArrayInputStream(multipartFile.getBytes()));
                 ByteArrayOutputStream os = new ByteArrayOutputStream()) {
                WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(buffIs);
                Docx4J.toPDF(wordMLPackage, os);
                PdfReader pdf = new PdfReader(os.toByteArray());
                int pageCount = pdf.getNumberOfPages();
                os.flush();
                return Map.of("pageCount", pageCount, "file", os.toByteArray());
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
                int pageCount = document.getPdfDocument().getNumberOfPages();
                document.close();
                return Map.of("pageCount", pageCount, "file", os.toByteArray());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Map.of("pageCount", 0, "file", new byte[0]);
    }
}

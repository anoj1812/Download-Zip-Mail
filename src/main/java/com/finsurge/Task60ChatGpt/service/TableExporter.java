package com.finsurge.Task60ChatGpt.service;

import com.finsurge.Task60ChatGpt.model.Student;
import com.finsurge.Task60ChatGpt.repository.StudentRepository;
import com.itextpdf.text.DocumentException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class TableExporter {

    private final StudentRepository studentRepository;

    public TableExporter(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void exportTableToExcel(List<Student> data, String filePath) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        CreationHelper creationHelper = workbook.getCreationHelper();
        Sheet sheet = workbook.createSheet("Table Data");
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue(" ID ");
        headerRow.createCell(1).setCellValue(" Name ");
        headerRow.createCell(2).setCellValue(" Department ");
        headerRow.createCell(3).setCellValue(" College ");
        // Add more cells for additional columns

        int rowNum = 1;
        for (Student entity : data) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(entity.getStudentId());
            row.createCell(1).setCellValue(entity.getStudentName());
            row.createCell(2).setCellValue(entity.getStudentDept());
            row.createCell(3).setCellValue(entity.getStudentClg());
            // Set values for additional columns
        }

        for (int i = 0; i < data.size() + 1; i++) {
            sheet.autoSizeColumn(i);
        }

        FileOutputStream fileOut = new FileOutputStream(filePath);
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();
    }

    public void exportTableToCSV(List<Student> data, String filePath) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT
                .withHeader(" ID ", " Name "," Department "," College "));
        for (Student entity : data) {
            csvPrinter.printRecord(entity.getStudentId(), entity.getStudentName(),entity.getStudentDept(),entity.getStudentClg());
        }
        csvPrinter.flush();
        csvPrinter.close();
        fileWriter.close();
    }

    public void exportTableToPDF(List<Student> data, String filePath) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();

        for (Student entity : data) {
            document.add(new Paragraph(entity.getStudentId() + " | " + entity.getStudentName()+" | "+entity.getStudentDept()+" | "+entity.getStudentClg()));
        }

        document.close();
    }
}

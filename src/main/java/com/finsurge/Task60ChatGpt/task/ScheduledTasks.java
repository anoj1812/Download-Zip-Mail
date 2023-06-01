package com.finsurge.Task60ChatGpt.task;

import com.finsurge.Task60ChatGpt.model.Student;
import com.finsurge.Task60ChatGpt.repository.StudentRepository;
import com.finsurge.Task60ChatGpt.service.EmailSender;
import com.finsurge.Task60ChatGpt.service.TableExporter;
import com.itextpdf.text.DocumentException;
import org.apache.commons.io.FileUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Component
public class ScheduledTasks {

    private final TableExporter tableExporter;
    private final EmailSender emailSender;
    private final StudentRepository studentRepository;

    public ScheduledTasks(TableExporter tableExporter, EmailSender emailSender, StudentRepository studentRepository) {
        this.tableExporter = tableExporter;
        this.emailSender = emailSender;
        this.studentRepository = studentRepository;
    }

    @Scheduled(cron = "0 */1 * ? * *") // Cron expression for every 5 minutes
    public void exportAndSendTableData() throws IOException, DocumentException ,Exception{
        String zipFilePath = "C:\\Users\\anoja\\Downloads\\Task60ChatGpt\\Task60ChatGpt\\src\\main\\java\\com\\finsurge\\Task60ChatGpt\\data\\send\\SendFile.zip";
        String excelFilePath = "C:\\Users\\anoja\\Downloads\\Task60ChatGpt\\Task60ChatGpt\\src\\main\\java\\com\\finsurge\\Task60ChatGpt\\data\\send\\excel.xlsx";
        String csvFilePath = "C:\\Users\\anoja\\Downloads\\Task60ChatGpt\\Task60ChatGpt\\src\\main\\java\\com\\finsurge\\Task60ChatGpt\\data\\send\\csv.csv";
        String pdfFilePath = "C:\\Users\\anoja\\Downloads\\Task60ChatGpt\\Task60ChatGpt\\src\\main\\java\\com\\finsurge\\Task60ChatGpt\\data\\send\\pdf.pdf";

        List<Student> tableData = studentRepository.findAll();

        tableExporter.exportTableToExcel(tableData, excelFilePath);
        tableExporter.exportTableToCSV(tableData, csvFilePath);
        tableExporter.exportTableToPDF(tableData, pdfFilePath);

        // Create zip file
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(FileUtils.openOutputStream(new File(zipFilePath)))) {
            addFileToZip(excelFilePath, zipOutputStream);
            addFileToZip(csvFilePath, zipOutputStream);
            addFileToZip(pdfFilePath, zipOutputStream);
        }

        // Send email with zip attachment
        emailSender.sendEmail("< receiver mail id >", "Table Data Export", "Please find the attached table data.",
                zipFilePath);
    }

    private void addFileToZip(String filePath, ZipOutputStream zipOutputStream) throws IOException {
        File file = new File(filePath);
        if (file.exists()) {
            ZipEntry zipEntry = new ZipEntry(file.getName());
            zipOutputStream.putNextEntry(zipEntry);
            zipOutputStream.write(FileUtils.readFileToByteArray(file));
            zipOutputStream.closeEntry();
        }
    }

    public static void main(String[] args) {
    }
}

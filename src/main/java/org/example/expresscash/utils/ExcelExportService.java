package org.example.expresscash.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.expresscash.model.TransactionHistoryModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

    @Service
    public class ExcelExportService {
        public ResponseEntity<byte[]> exportDataToExcel(List<TransactionHistoryModel> data) {
            try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
                // Create a new Excel workbook and sheet
                Workbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet("Transaction History");

                // Create a font style for the header
                Font headerFont = workbook.createFont();
                headerFont.setBold(true);
                headerFont.setFontHeightInPoints((short) 12);

                // Create a cell style for the header
                CellStyle headerCellStyle = workbook.createCellStyle();
                headerCellStyle.setFont(headerFont);
                headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
                headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

                // Set a background color for the header
                headerCellStyle.setFillForegroundColor(IndexedColors.DARK_GREEN.getIndex());
                headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

                // Create the header row
                Row headerRow = sheet.createRow(0);
                String[] columns = {"ID", "Sim Number", "User", "Branch", "Customer Sim Number", "Transaction Type",
                        "Amount", "Discount", "1 EGP", "5 EGP", "10 EGP", "20 EGP", "50 EGP",
                        "100 EGP", "200 EGP", "Notes", "Creation Date"};

                // Apply the header style and set column headers
                for (int i = 0; i < columns.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(columns[i]);
                    cell.setCellStyle(headerCellStyle); // Apply header cell style
                }

                // Add data rows
                int rowNum = 1;
                for (TransactionHistoryModel transaction : data) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(transaction.getId());
                    row.createCell(1).setCellValue(transaction.getSim().getPhoneNumber());
                    row.createCell(2).setCellValue(transaction.getUser().getUsername());
                    row.createCell(3).setCellValue(transaction.getBranch().getNameAr());
                    row.createCell(4).setCellValue(transaction.getCustomerPhoneNumber());
                    row.createCell(5).setCellValue(transaction.getTransactionType().name());
                    row.createCell(6).setCellValue(String.valueOf(transaction.getAmount()));
                    row.createCell(7).setCellValue(String.valueOf(transaction.getDiscount()));
                    row.createCell(8).setCellValue(String.valueOf(transaction.getOneEgp()));
                    row.createCell(9).setCellValue(String.valueOf(transaction.getFiveEgp()));
                    row.createCell(10).setCellValue(String.valueOf(transaction.getTenEgp()));
                    row.createCell(11).setCellValue(String.valueOf(transaction.getTwentyEgp()));
                    row.createCell(12).setCellValue(String.valueOf(transaction.getFiftyEgp()));
                    row.createCell(13).setCellValue(String.valueOf(transaction.getOneHundredEgp()));
                    row.createCell(14).setCellValue(String.valueOf(transaction.getTwoHundredEgp()));
                    row.createCell(15).setCellValue(transaction.getNotes());
                    row.createCell(16).setCellValue(transaction.getCreationDate().toString());
                }

                // Auto-size columns based on the content
                for (int i = 0; i < columns.length; i++) {
                    sheet.autoSizeColumn(i);
                }

                // Write the Excel content to the ByteArrayOutputStream
                workbook.write(byteArrayOutputStream);
                workbook.close();

                // Prepare the byte content for the response
                byte[] content = byteArrayOutputStream.toByteArray();

                // Prepare headers for file download
                HttpHeaders headers = new HttpHeaders();
                headers.add("Content-Disposition", "attachment; filename=transaction_history.xlsx");
                headers.setContentType(org.springframework.http.MediaType.APPLICATION_OCTET_STREAM);

                return new ResponseEntity<>(content, headers, HttpStatus.OK);

            } catch (IOException e) {
                // Handle any IOException that occurs during Excel generation
                throw new RuntimeException("Error generating Excel file: " + e.getMessage(), e);
            }
        }
    }

package com.mycompany.bancomalvander.Controller;


import com.mycompany.bancomalvander.Model.Relatorio;
import com.mycompany.bancomalvander.Model.Transacao;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class GeradorExcel {

    public void gerarExtratoExcel(List<Transacao> transacoes, String caminhoArquivo) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Extrato");

        // Cabeçalhos das colunas
        String[] colunas = {"ID Transação", "Tipo", "Valor", "Data/Hora"};
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 12);

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < colunas.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(colunas[i]);
            cell.setCellStyle(headerCellStyle);
        }

        // Populando a planilha com as transações
        int rowNum = 1;
        for (Transacao transacao : transacoes) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0).setCellValue(transacao.getIdTransacao());
            row.createCell(1).setCellValue(transacao.getTipoTransacao());
            row.createCell(2).setCellValue(transacao.getValor());
            Cell dateCell = row.createCell(3);
            dateCell.setCellValue((Date) transacao.getDataHora());
            dateCell.setCellStyle(getDateCellStyle(workbook));
        }

        // Ajustando a largura das colunas
        for (int i = 0; i < colunas.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Escrevendo o arquivo no disco
        try (FileOutputStream fileOut = new FileOutputStream(caminhoArquivo)) {
            workbook.write(fileOut);
        }

        workbook.close();
    }
    
    public void gerarRelatorioExcel(List<Relatorio> relatorios, String caminhoArquivo) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Relatório");

        // Cabeçalhos
        String[] colunas = {"ID Relatório", "Tipo", "Data Geração", "Conteúdo"};
        Row headerRow = sheet.createRow(0);

        for (int i = 0; i < colunas.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(colunas[i]);
        }

        // Dados
        int rowNum = 1;
        for (Relatorio relatorio : relatorios) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0).setCellValue(relatorio.getIdRelatorio());
            row.createCell(1).setCellValue(relatorio.getTipoRelatorio());
            row.createCell(2).setCellValue(relatorio.getDataGeracao().toString());
            row.createCell(3).setCellValue(relatorio.getConteudo());
        }

        // Auto tamanho das colunas
        for (int i = 0; i < colunas.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Salvando o arquivo
        try (FileOutputStream fileOut = new FileOutputStream(caminhoArquivo)) {
            workbook.write(fileOut);
        }
        workbook.close();
    }

    private CellStyle getDateCellStyle(XSSFWorkbook workbook) {
        CreationHelper createHelper = workbook.getCreationHelper();
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy HH:mm:ss"));
        return cellStyle;
    }
}

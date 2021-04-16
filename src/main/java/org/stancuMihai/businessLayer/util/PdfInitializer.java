package org.stancuMihai.businessLayer.util;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import java.util.stream.Stream;

/***
 * Init of pdf logic
 */
public class PdfInitializer {

    public static void addTableHeader(PdfPTable table) {
        Stream.of("Client", "Products", "Total Price")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    public static void addRows(PdfPTable table, String rowText) {
        table.addCell(rowText);
    }
}

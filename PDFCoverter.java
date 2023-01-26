import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFConverter {
    public static void main(String args[]) throws IOException {
        // Convertir PDF a Word
        PDDocument document = PDDocument.load(new File("document.pdf"));
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String text = pdfStripper.getText(document);
        document.close();
        // escribir el texto extraido a un archivo de word
        FileOutputStream fos = new FileOutputStream("document.doc");
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        osw.write(text);
        osw.flush();
        osw.close();

        // Convertir PDF a Excel
        // Creamos el libro de trabajo
        Workbook libro = new HSSFWorkbook();
        // Creamos la hoja
        Sheet hoja = libro.createSheet();
        // Creamos la fila
        Row fila = hoja.createRow(0);
        // Creamos las celdas
        Cell celda1 = fila.createCell(0);
        celda1.setCellValue(text);
        // Escribimos el archivo
        FileOutputStream fileOut = new FileOutputStream("document.xls");
        libro.write(fileOut);
        fileOut.close();

        // Impresion multiple de PDFs
        try {
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintable(new PDFPrintable(PDDocument.load(new File("document1.pdf"))), job.defaultPage());
            job.print();
            job.setPrintable(new PDFPrintable(PDDocument.load(new File("document2.pdf"))), job.defaultPage());
            job.print();
            job.setPrintable(new PDFPrintable(PDDocument.load(new File("document3.pdf"))), job.defaultPage());
            job.print();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

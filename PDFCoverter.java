import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFConverter {

    public static void main(String args[]) throws IOException {
        
        //Convertir PDF a Word
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
        
        //Convertir PDF a Excel
        //Puedes utilizar alguna libreria para crear un archivo excel desde el texto extraido
        
        //Impresion multiple de PDFs
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

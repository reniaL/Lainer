package demo.jse;

import java.io.FileOutputStream;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.rtf.RtfWriter2;
import com.lowagie.text.rtf.style.RtfParagraphStyle;

public class ItextDemo {
    
    private static final String PATH = "/data/temp/itext.doc";
    
    private static final String PATH_IMAGE = "/data/temp/img1.jpg";
    
    public static void main(String[] args) {
        write();
        System.out.println("done");
    }
    
    public static void write() {
        try {
            Document doc = new Document(PageSize.A4);
            RtfWriter2 writer = RtfWriter2.getInstance(doc, new FileOutputStream(PATH));
            writer.getDocumentSettings().setImagePDFConformance(false); // 设置不按照PDF而按照WORD的标准来处理图片
            doc.open();
            RtfParagraphStyle styleHeading1 = RtfParagraphStyle.STYLE_HEADING_1;
            styleHeading1.setAlignment(Element.ALIGN_CENTER);
            RtfParagraphStyle styleHeading2 = RtfParagraphStyle.STYLE_HEADING_2;

            Paragraph paraTitle = new Paragraph("Network", styleHeading1);
            doc.add(paraTitle);
            doc.add(new Paragraph("Bird Man", styleHeading2));
            doc.add(new Paragraph("And content here~\n忘了"));
            doc.newPage();

            doc.add(new Paragraph("Big big bid~", styleHeading2));
            Image img = Image.getInstance(PATH_IMAGE);
            img.setAlignment(Image.MIDDLE);
            img.disableBorderSide(Rectangle.BOTTOM);
            img.setSpacingAfter(250);
            doc.add(img);
            doc.add(new Paragraph("茶包～"));
            doc.add((new Chunk()).setNewPage());
            doc.add(paraTitle);

            doc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}

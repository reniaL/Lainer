package demo.jse;

import java.io.File;
import java.util.Random;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.CellFormat;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JxlDemo {
    
    private static final Log log = LogFactory.getLog(JxlDemo.class);
    
    private static final String EXCEL_FONT = "宋体"; // 字体
    
    private static final String PATH = "E:/home/test.xls";
    
    private static Workbook BOOK_TEMPLATE;
    
    public static void main(String[] args) throws Exception {
        write();
        System.out.println("done");
    }
    
    static {
        try {
            BOOK_TEMPLATE = Workbook.getWorkbook(new File(PATH));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 从模板文件读取表格，修改并另存
     */
    public static void readTemp(String outPath) throws Exception {
        File outFile = new File(outPath);
        WritableWorkbook book = Workbook.createWorkbook(outFile, BOOK_TEMPLATE);
        
        WritableSheet sheet = book.getSheet(0);
        Random rand = new Random();
        sheet.addCell(new Number(0, 1, rand.nextInt(100)));
        
        book.write();
        book.close();
    }
    
    public static void write() throws Exception {
        File file = new File(PATH);
        WritableWorkbook book = Workbook.createWorkbook(file);
        WritableSheet sheet = book.createSheet("duck", 0);
        WritableCellFormat formatLabel = getWritableCellFormat(Colour.WHITE, 10, false);
        formatLabel.setWrap(true);
        sheet.addCell(new Label(2, 3, "无忧岛\n测试", formatLabel));
        CellFormat cf = new WritableCellFormat(new NumberFormat("###,###")); // 格式化数值
        sheet.addCell(new Number(4, 6, 12345678901234d, cf));
        
        book.write();
        book.close();
    }
    
    /**
     * 根据参数获取单元格格式
     * @param backgroundColor 背景色
     * @param ps 字体大小
     * @param bold true 表示加粗
     */
    private static WritableCellFormat getWritableCellFormat(Colour backgroundColor, int ps, boolean bold) {
        WritableCellFormat wcf = null;
        try {
            WritableFont titleWf = new WritableFont(WritableFont.createFont(EXCEL_FONT),// 字体
                    ps, // 字号
                    bold ? WritableFont.BOLD : WritableFont.NO_BOLD, // 是否加粗
                    false, // 斜体
                    UnderlineStyle.NO_UNDERLINE, // 下划线
                    Colour.BLACK); // 字体颜色
            wcf = new WritableCellFormat(titleWf);
            wcf.setBackground(backgroundColor);// 设置单元格的背景颜色
            wcf.setAlignment(Alignment.CENTRE); // 水平居中
            wcf.setVerticalAlignment(VerticalAlignment.CENTRE);// 垂直居中
            wcf.setBorder(Border.ALL, BorderLineStyle.THIN); // 添加边框
        } catch (WriteException e) {
            log.info(e.getMessage(), e);
        }
        return wcf;
    }
    
}

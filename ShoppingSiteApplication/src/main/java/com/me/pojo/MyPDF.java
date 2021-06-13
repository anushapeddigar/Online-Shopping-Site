package com.me.pojo;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class MyPDF extends AbstractPdfView{
	


	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document pdfdoc, PdfWriter pdfwriter, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Font  helvetica_18_blue = new Font(Font.HELVETICA, 18, Font.BOLDITALIC, Color.GREEN);
		
		List<Cart> cartlist = (List<Cart>) model.get("cartitems");
		Paragraph title = new Paragraph("Order Summary", helvetica_18_blue);
		
		
		Phrase thank = new Phrase("Thank you for shopping and please continue to shop!!");
		
		PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[] {3.0f, 2.0f, 1.0f});
        table.setSpacingBefore(10);
		
		PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.WHITE);
        cell.setPadding(5);
		
        cell.setPhrase(new Phrase("Product Title", helvetica_18_blue));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Category", helvetica_18_blue));
        table.addCell(cell);
 
        cell.setPhrase(new Phrase("Price", helvetica_18_blue));
        table.addCell(cell);
        
        for (Cart ccc : cartlist) {
            table.addCell(ccc.getTitle());
            table.addCell(ccc.getCategory());
            table.addCell(String.valueOf(ccc.getTotalprice()));
        }
        
		pdfdoc.add(title);
		pdfdoc.add(table);
		pdfdoc.add(thank);
	}

	


}

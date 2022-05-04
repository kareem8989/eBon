package services;
import model.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class ProductServices {
	public static final String  PDF_PATH= "C:\\Users\\Mosli\\Desktop\\NBs\\Git\\eBon\\rewe_bon_web\\src\\main\\webapp\\WEB-INF\\pdf";

	public static ArrayList<Product> loadItems (String lin) throws IOException{
		
		  //i rod the text from the PDF file and i put in the textString 
		  File f = new File(PDF_PATH+lin);
		  PDDocument document = PDDocument.load(f);
		  PDFTextStripper pdfStripper = new PDFTextStripper();
		  
		  String text = pdfStripper.getText(document);
		  
		  document.close();
		  
		
		  
		  if(text.trim().contains("Ihre REWE PAYBACK Vorteile heute")) {
		 
		  // i got only the related String and i put it in the br
		  BufferedReader br = new BufferedReader(new StringReader(text.substring(text.indexOf("EUR") + 5 ,text.indexOf("---"))));

		  ArrayList<Product> arr = new ArrayList<Product>();
		  
		  String name = null;
		  String price =  null;
		  String line =  null;
		  int len = 1;
		  int idx = 0;
		  
		  // i got in this while statement only the articleName and the price and i passed for the list
		  while ((line = br.readLine()) != null) {
			 
			  if(line.endsWith("*")) {len =3;}
			  
			  if(!line.startsWith(" ") & !line.startsWith("PFAND")& !line.startsWith("LEERGUT")) {
				  
				  idx = line.indexOf("   ");	
				  
				  price = line.substring(idx,line.length() - len).trim().replace(',', '.');
				  name = line.substring(0,idx).trim();
				  
				  if(price.startsWith("X")) {
					  price = price.substring(5).trim();
					  
				  }
		
				  arr.add(new Product(name+"|"+price));
			  }
			  if(line.trim().startsWith("2")) {
				  arr.add(new Product(name+"|"+price));
			  }
				
		  }
		  
		   br.close();
		   return arr;
		  }
		  return null;
	}

	public static void deleteFile(String path) {
		
		File deletFile;
		if(path != null) {
		deletFile = new File(ProductServices.PDF_PATH+path); 
		deletFile.delete();
		System.out.println("File deleted");
	   }
	}

}

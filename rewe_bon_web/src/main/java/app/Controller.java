package app;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import model.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;



import services.ProductServices;

@WebServlet({ "/Controller", "/c" })
public class Controller extends HttpServlet {
	
	
       
   
    public Controller() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

     	    receivedFile(request, response);
     	    listArticles(request,response);
	    

   }


	private void receivedFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   
			ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
			List<FileItem> fileItem;
			
			 ArrayList<List<Product>>list = new ArrayList<List<Product>>();
			String foultFileName = "";
			boolean checkFile = false;
			try {
				
				fileItem = sf.parseRequest(request);
				for(FileItem item: fileItem) {
					
					item.write(new File(ProductServices.PDF_PATH+item.getName()));
					
					
					if(ProductServices.loadItems(item.getName()) != null) {
						
						list.addAll((Collection<? extends List<Product>>) ProductServices.loadItems(item.getName()));
						
					}else {
						foultFileName += item.getName()+"\n";
						checkFile = true;
					}
					
					System.out.println(item.getName());
					ProductServices.deleteFile(item.getName());
					
				}
				
			} catch ( Exception e ) {
				
				e.printStackTrace();
			}
			
		     request.setAttribute("LIST", list);
		     request.setAttribute("FOULT_FILE", foultFileName);
		     request.setAttribute("CHECK_FILE", checkFile);
		     
		     
		

	}


	private void listArticles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 	ServletContext sc = this.getServletContext();
		 sc.getRequestDispatcher("/list_article.jsp").forward(request, response);
		
	}


	
	
}

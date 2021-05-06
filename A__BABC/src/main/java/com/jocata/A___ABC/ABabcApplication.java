package com.jocata.A___ABC;

import java.awt.List;
import java.awt.PageAttributes.MediaType;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.FileHeader;

@SpringBootApplication
@RestController
public class ABabcApplication 
{
	public String base64String=null;
	public static void main(String[] args) throws FileNotFoundException, IOException, ParserConfigurationException, SAXException 
	{
		SpringApplication.run(ABabcApplication.class, args);		
	}
	
	public static byte[] decode(String base64String) {
		return Base64.getDecoder().decode(base64String);
	}
	
	/*@RequestMapping(value="/upload", method=RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) throws IOException 
	{
		File convertFile = new File("C:\\lokesh_data\\"+file.getOriginalFilename());
		convertFile.createNewFile();
		FileOutputStream fout = new FileOutputStream(convertFile); 
		
		fout.write(file.getBytes());
		fout.close();
		return new ResponseEntity<>("File is uploaded successfully", HttpStatus.OK);
	}*/
	
	
	@RequestMapping("/generateImage")
	public void generateImage() throws FileNotFoundException, IOException
	{
		try(FileOutputStream stream = new FileOutputStream(new File("C:\\lokesh_data\\output.png")))
		{
			stream.write(decode(base64String));
		}
	}	

	@RequestMapping("/ReadImageData")
	public void ReadImageData() throws ParserConfigurationException, SAXException, IOException
	{
		File file = new File("C:\\lokesh_data\\offlineaadhaar20210505111443599.xml");  
		DocumentBuilderFactory dbf1 = DocumentBuilderFactory.newInstance();  
		DocumentBuilder db = dbf1.newDocumentBuilder();  
		org.w3c.dom.Document docn = db.parse(file);
		docn.getDocumentElement().normalize();  
 
		NodeList nodeListpht = docn.getElementsByTagName("Pht");
		
		for (int itr = 0; itr < nodeListpht.getLength(); itr++)   
		{  
			Node node = nodeListpht.item(itr);  
			System.out.println("\nTag Name :" + node.getNodeName());  
			if (node.getNodeType() == Node.ELEMENT_NODE)   
			{  
				Element eElement = (Element) node;  
				base64String = eElement.getTextContent();
				
				System.out.println("pht_tag_DATA ->     "+ base64String);  
			} 
		}		
}

	@RequestMapping("/generatePDf")
	public void generatePDf()
	{
		String name,careof,dateofbirth,genderinfo,address1,address2,address3,address0;
    	name="";careof="";dateofbirth="";genderinfo="";address1="";address2="";address3="";address0="";
    	
    	try   
    	{  
    		
    		File file = new File("C:\\lokesh_data\\offlineaadhaar20210505111443599.xml");  
    		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
    		DocumentBuilder db = dbf.newDocumentBuilder();  
    		org.w3c.dom.Document doc = db.parse(file);
    		doc.getDocumentElement().normalize();  
    		System.out.println("Root element: " +  doc.getDocumentElement().getNodeName());  
    		NodeList nodeList = doc.getElementsByTagName("Poi");
    		
    			
    				for (int itr = 0; itr < nodeList.getLength(); itr++)   
    				{  
    					Node node = nodeList.item(itr);  
    					System.out.println("\nTag Name :" + node.getNodeName());  
    					if (node.getNodeType() == Node.ELEMENT_NODE)   
    					{  
    						Element eElement = (Element) node;  
    						String	Dob = eElement.getAttribute("dob");
    						String E = eElement.getAttribute("e"); 
    						String Gender = eElement.getAttribute("gender");
    						String M = eElement.getAttribute("m");
    						String Name = eElement.getAttribute("name");
    						
    						String male= "M";
    						String female = "F";
    						if(Gender.equals(male))
    						{
    							genderinfo = "Gender : Male ";
    						}
    						else if(Gender.equals(female))
    						{
    							genderinfo = "Gender : Female ";
    						}
    						name = "Name : "+Name+" ";
    						dateofbirth = "Date Of Birth : "+ Dob +"";
    						
    						System.out.println("Dob - "+ Dob);  
    						System.out.println("E - "+ eElement.getAttribute("e"));  
    						System.out.println("Gender - "+ eElement.getAttribute("gender"));  
    						System.out.println("M - "+ eElement.getAttribute("m"));  
    						System.out.println("Name - "+ eElement.getAttribute("name"));  
    					} 
    				}
    				
    				
    				

    				NodeList nodeList1 = doc.getElementsByTagName("Poa");
    				for (int itr1 = 0; itr1 < nodeList1.getLength(); itr1++)   
    				{  
    					Node node1 = nodeList1.item(itr1);  
    					System.out.println("\nTag Name :" + node1.getNodeName());  
    					if (node1.getNodeType() == Node.ELEMENT_NODE)   
    					{  
    						Element eElement = (Element) node1;
    						String Careof = eElement.getAttribute("careof");
    						String Country = eElement.getAttribute("country");
    						String District = eElement.getAttribute("dist");
    						String House = eElement.getAttribute("house");
    						String Landmark = eElement.getAttribute("landmark");
    						String Loc = eElement.getAttribute("loc");
    						String Pc = eElement.getAttribute("pc");
    						String Po = eElement.getAttribute("po");
    						String State = eElement.getAttribute("state");
    						String Street = eElement.getAttribute("street");
    						String Subdist = eElement.getAttribute("subdist");
    						String Vtc = eElement.getAttribute("vtc");
    						
    						careof = "Care of : "+ Careof + " ";
    						
    						address0 = "| Full Address : ";
    						
    						address1 = House + " ," + Landmark + " ," +Street + " ," + Loc + " ";
    						address2 = "Subdist : "+Subdist + " ," + Vtc + " ,PINCODE : " + Pc ;
    						address3 = "Post Office : "+Po+ " , District : " + District + " ," + State + " ," + Country + "";
    						System.out.println("Careof - "+ eElement.getAttribute("careof"));  
    						System.out.println("Country - "+ eElement.getAttribute("country"));  
    						System.out.println("District - "+ eElement.getAttribute("dist"));  
    						System.out.println("House - "+ eElement.getAttribute("house"));
    						System.out.println("Landmark - "+ eElement.getAttribute("landmark"));
    						System.out.println("Loc - "+  eElement.getAttribute("loc"));
    						System.out.println("Pc - "+ eElement.getAttribute("pc"));
    						System.out.println("Po - "+ eElement.getAttribute("po")) ; 
    						System.out.println("State - "+ eElement.getAttribute("state"));
    						System.out.println("Street - "+ eElement.getAttribute("street"));
    						System.out.println("Subdist: "+ eElement.getAttribute("subdist"));  
    						System.out.println("Vtc: "+ eElement.getAttribute("vtc"));  
    					
    					}  
    				}
    				
    				
    				
    				PDDocument document = new PDDocument();
    				//Creating a blank page
    			    PDPage blankPage = new PDPage();
    			       
    			      //Adding the blank page to the document
    			    document.addPage( blankPage );
    			    
    		    	document.save("C:\\lokesh_data\\aadhar.pdf");
    		    	document.close();
    		    	
    		    	File filenew = new File("C:\\lokesh_data\\aadhar.pdf");
    		    	
    		    	PDDocument doc2 = PDDocument.load(filenew);
    		    	//Creating a PDF Document
    		        PDPage page = doc2.getPage(0);
    		        
    		        PDImageXObject pdImage = PDImageXObject.createFromFile("C:\\lokesh_data\\output.png", doc2);
    		        
    		        PDPageContentStream contentStream = new PDPageContentStream(doc2, page);
    		        
    		        
    		        contentStream.drawImage(pdImage, 70, 250);
    		        
    		      //Begin the Content stream 
    		        contentStream.beginText(); 
    		         
    		        //Setting the font to the Content stream
    		        contentStream.setFont( PDType1Font.TIMES_ROMAN, 16 );
    		         
    		        //Setting the leading
    		        contentStream.setLeading(14.5f);
    		        
    		        //Setting the position for the line
    		        contentStream.newLineAtOffset(25, 725);
    		        
    		        
    		        
    		        contentStream. showText(name);
    		        contentStream.newLine();
    		        contentStream.newLine();
    		        contentStream. showText(careof);
    		        contentStream.newLine();
    		        contentStream.newLine();
    		        contentStream. showText(dateofbirth);
    		        contentStream.newLine();
    		        contentStream.newLine();
    		        contentStream. showText(genderinfo);
    		        contentStream.newLine();
    		        contentStream.newLine();

    		        contentStream. showText(address0);
    		        contentStream.newLine();
    		        contentStream.newLine();
    		        
    		        contentStream. showText(address1);
    		        contentStream.newLine();
    		        contentStream.newLine();
    		        contentStream. showText(address2);
    		        contentStream.newLine();
    		        contentStream.newLine();
    		        contentStream. showText(address3);
    		        contentStream.newLine();
    		        contentStream.newLine();
    		       
    		        //Ending the content stream
    		        contentStream.endText();
    		               
    		        System.out.println("Content added");
    		       

    		        //Closing the content stream
    		        contentStream.close();

    		        //Saving the document
    		        doc2.save(new File("C:\\lokesh_data\\new1.pdf"));
    		              
    		        //Closing the document
    		        doc2.close();  
    						    						
    	}
    	catch (Exception e)   
    	{  
    		e.printStackTrace();  
    	}

	}
	
	
}

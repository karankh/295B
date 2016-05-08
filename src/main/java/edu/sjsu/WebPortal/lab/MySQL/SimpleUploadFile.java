package edu.sjsu.WebPortal.lab.MySQL;


import org.springframework.web.multipart.MultipartFile;


public class SimpleUploadFile {

 
    private MultipartFile file;
    private String documentType;
    private String documentName;
    private String documentCategory;
    private String documentDescp;
   
   
 
    public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getDocumentCategory() {
		return documentCategory;
	}

	public void setDocumentCategory(String documentCategory) {
		this.documentCategory = documentCategory;
	}

	public String getDocumentDescp() {
		return documentDescp;
	}

	public void setDocumentDescp(String documentDescp) {
		this.documentDescp = documentDescp;
	}

	public MultipartFile getFile() {
        return file;
    }
 
    public void setFile(MultipartFile file) {
        this.file = file;
    }

	
    
}

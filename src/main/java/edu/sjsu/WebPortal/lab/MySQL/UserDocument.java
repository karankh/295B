package edu.sjsu.WebPortal.lab.MySQL;



public class UserDocument {

    private String documentType;
    private String documentName;
    private String documentCategory;
    private String documentDescp;

	private String userid;
	private String docid;
	private String isPdf;
    
    public String getIsPdf() {
		return isPdf;
	}

	public void setIsPdf(String isPdf) {
		this.isPdf = isPdf;
	}

	private byte[] content;
	   
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

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getDocid() {
		return docid;
	}

	public void setDocid(String docid) {
		this.docid = docid;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	
    


}

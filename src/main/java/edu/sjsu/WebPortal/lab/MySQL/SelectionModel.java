package edu.sjsu.WebPortal.lab.MySQL;



public class SelectionModel {

    String [] platforms;
    
    String [] parameters;
    
    String load;

	public String getLoad() {
		return load;
	}

	public void setLoad(String load) {
		this.load = load;
	}

	public String[] getParameters() {
		return parameters;
	}

	public void setParameters(String[] parameters) {
		this.parameters = parameters;
	}

	public String[] getPlatforms() {
		return platforms;
	}

	public void setPlatforms(String[] platforms) {
		this.platforms = platforms;
	}
}

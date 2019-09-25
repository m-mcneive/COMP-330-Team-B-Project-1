
public class Clue {

	private Boolean relationship; // determines relation between elements as true or false
	private String name1, type1;
	private String name2, type2;
	private String name3, type3;
	private String name4, type4;
	private String name5, type5;
	private String error = null;
	private int counter = 0; // makes note of how many elements are being impacted
	
	public void setRelationship(Boolean relationship) {
		this.relationship = relationship;
	}
	public Boolean getRelationship () {
		return relationship;
	}
	
	public int getCounter() {
		return counter;	
	}
	
	public void setName(int choice, String name) {
	  switch(choice) {
	  
	  case 1:	
	  name1 = name;
	  counter++;
	  break;
	  
	  case 2:
	  name2 = name;
	  counter++;
	  break;
	  
	  case 3:
	  name3 = name;
	  counter++;
	  break;
	  
	  case 4:
	  name4 = name;
	  counter++;
	  break;
	  
	  case 5:
	  name5 = name;
	  counter++;
	  break;
	  
	  default:
	  System.out.println("Incorrect entry or name limit has been exceeded...");
	  break;
	  }
	}
	
	public void setType(int choice, String type) {
		  switch(choice) {
		  
		  case 1:	
		  type1 = type;
		  break;
		  
		  case 2:
		  type2 = type;
		  break;
		  
		  case 3:
		  type3 = type;
		  break;
		  
		  case 4:
		  type4 = type;
		  break;
		  
		  case 5:
		  type5 = type;
		  break;
		  
		  default:
		  System.out.println("Incorrect entry or type limit has been exceeded...");
		  break;
		  }
	}
	
	public String getName(int choice) {
		  switch(choice) {
		  
		  case 1:	
		  return name1;
		  
		  
		  case 2:
		  return name2;
		  
		  
		  case 3:
		  return name3;
		 
		  
		  case 4:
		  return name4;
		  
		  
		  case 5:
		  return name5;
		  
		  
		  default:
		  System.out.println("Incorrect entry: returning null value ...");
		  return error;
		  }
	}
	
	public String getType(int choice) {
		  switch(choice) {
		  
		  case 1:	
		  return type1;
		  
		  
		  case 2:
		  return type2;
		  
		  
		  case 3:
		  return type3;
		 
		  
		  case 4:
		  return type4;
		  
		  
		  case 5:
		  return type5;
		  
		  
		  default:
		  System.out.println("Incorrect entry: returning null value ...");
		  return error;
		  }
	}
}

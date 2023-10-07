package laboratoria;



import java.security.InvalidParameterException;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
//@SessionScoped
public class GeneratorBB {

	private String shape;
	private String size;
	private Character character;
	private String result;
	
	@Inject
	FacesContext ctx;
	
	




	public String getResult() {
		
		return result;
	}



	public void setResult(String result) {
		this.result = result;
	}



	public String getShape() {
		return shape;
	}



	public void setShape(String shape) {
		this.shape = shape;
	}



	public String getSize() {
		return size;
	}



	public void setSize(String size) {
		this.size = size;
	}



	public Character getCharacter() {
		return character;
	}



	public void setCharacter(Character character) {
		this.character = character;
	}



	private boolean generate() {
		try {
			int size = Integer.parseInt(this.size);
			
			switch (shape) {
				case "square":
					result = generateSquare(size);
					break;
				case "stairs":
					result = generateStairs(size);
					break;
				case "triangle":
					result = generateTriangle(size);
					break;
				case "rhombus":
					result = generateRhombus(size);
					break;
					
				default:
					throw new InvalidParameterException();
			}

			

			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
			return true;
		} catch (Exception e) {
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas przetwarzania parametrów", null));
			return false;
		}
	}
	 
	

	public String actionGenerate() {
		if (generate()) {
			return "index";
		}
		return null;
	}

	
	public String info() {
		return "info";
	}
	
	private String generateSquare(Integer shapesize){
	    StringBuilder stringBuilder = new StringBuilder();
		for(int i = 1; i<=shapesize; i++){
            for(int j = 0; j<shapesize; j++){
                stringBuilder.append(character+"  ");   
            }    
            stringBuilder.append('\n');
	    }
		return stringBuilder.toString();
	}

	private String generateStairs(Integer shapesize){
	    StringBuilder stringBuilder = new StringBuilder();
		for(int i = 1; i<=shapesize; i++){
            for(int j = 0; j<i; j++){ 
            	stringBuilder.append(character);
            }    
            stringBuilder.append('\n');
	    }
	    return stringBuilder.toString();
	}
	private String generateTriangle(Integer shapesize){
	    StringBuilder stringBuilder = new StringBuilder();
		for(int i = 1; i<=shapesize; i++){
            for(int j = 0; j<(shapesize-i); j++){
            	stringBuilder.append("  ");
            }

            for(int j = 0; j<i; j++){
            	stringBuilder.append(character);
            	stringBuilder.append(character);

            }    
            stringBuilder.append('\n');
	    }
		return stringBuilder.toString();
	}

	private String generateRhombus(Integer shapesize){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(generateTriangle(shapesize));

	    for(int i = 0; i<=shapesize; i++){
            for(int j = 0; j<i; j++){
            	stringBuilder.append("  ");
            }
            for(int j = 0; j<(shapesize-i); j++){
            	stringBuilder.append(character);
            	stringBuilder.append(character);

            }    
            stringBuilder.append('\n');
	    }
	    
	    return stringBuilder.toString();
	}
	
	
}

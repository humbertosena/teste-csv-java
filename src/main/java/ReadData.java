import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			Path currentRelativePath = Paths.get(".");
			String s = currentRelativePath.toAbsolutePath().toString();
			
			File file = new File(s + "//resources//cidades.csv");
			FileReader fr = new FileReader(file);
		    BufferedReader br = new BufferedReader(fr);
		    String line;
		    while((line = br.readLine()) != null){
		            System.out.println(line);
		    }
		    br.close();
		    fr.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

	}

}

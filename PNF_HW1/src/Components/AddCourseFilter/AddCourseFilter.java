package Components.AddCourseFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import Framework.CommonFilterImpl;

public class AddCourseFilter extends CommonFilterImpl{
    @Override
    public boolean specificComputationForFilter() throws IOException {
    	int checkBlank = 5; 
        int numOfBlank = 0;
        int idx = 0;
        byte[] buffer = new byte[1000];
        int byte_read = 0;
        String student = "";
        String[] lines; // string을 저장할 배열 
        
        while (true) {
            while ((byte_read = in.read()) != '\n' && byte_read != -1) {
                if (byte_read == ' ') numOfBlank++;
                if (byte_read != -1) buffer[idx++] = (byte) byte_read;
            }
            
            student = new String(buffer, 0, idx, StandardCharsets.UTF_8);
            lines = student.split("\n");
                        
            for(String str: lines) {
            	if(!str.contains("12345")) System.out.println(str);
            }
            
            if (byte_read == -1) break;
            idx = 0;
            numOfBlank = 0;
        } 
        return true;
    }
}
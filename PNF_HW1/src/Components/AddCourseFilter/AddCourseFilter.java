package Components.AddCourseFilter;

import java.io.IOException;
import Framework.CommonFilterImpl;

public class AddCourseFilter extends CommonFilterImpl{
    @Override
    public boolean specificComputationForFilter() throws IOException {
    	int checkBlank = 5; 
        int numOfBlank = 0;
        int idx = 0;
        byte[] buffer = new byte[64];
        boolean is12345 = false;    
        int byte_read = 0;
        
        while(true) {          
            while(byte_read != '\n' && byte_read != -1) {
            	byte_read = in.read();  
                if(byte_read == ' ') numOfBlank++;  
                if(byte_read != -1) buffer[idx++] = (byte)byte_read;
                if(numOfBlank == checkBlank && 
                		buffer[idx-6] == '1' && 
                		buffer[idx-5] == '2' &&
                		buffer[idx-4] == '3' &&
                		buffer[idx-3] == '4' &&
                		buffer[idx-2] == '5' 
                		) is12345 = true;              
            }   
            
            
            if(!is12345) {   
            	for(int i = 0; i<idx; i++) {
            		System.out.println((char)buffer[i]);
            	}            	
            }

            if(is12345) {
            	for (int i = 0; i < idx; i++) {
                    out.write((char) buffer[i]);
                }           	
            }
            
            if (byte_read == -1) return true;
            idx = 0;
            numOfBlank = 0;
            byte_read = '\0';
        	is12345 = false; 
        }
    } 
}
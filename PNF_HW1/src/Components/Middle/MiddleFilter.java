/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Components.Middle;

import java.io.IOException;
import Framework.CommonFilterImpl;

public class MiddleFilter extends CommonFilterImpl{
    @Override
    public boolean specificComputationForFilter() throws IOException {
    	int checkBlank = 4; 
        int numOfBlank = 0;
        int idx = 0;
        byte[] buffer = new byte[64];
        boolean isCS = false;    
        int byte_read = 0;
        
        while(true) {          
        	// check "CS" on byte_read from student information
            while(byte_read != '\n' && byte_read != -1) {
            	byte_read = in.read(); // 여기서 한 바이트씩 읽는다. 
                if(byte_read == ' ') numOfBlank++; // 빈칸을 읽는다. 
                if(byte_read != -1) buffer[idx++] = (byte)byte_read;
                if(numOfBlank == checkBlank && buffer[idx-3] == 'C' && buffer[idx-2] == 'S') isCS = true;
             // C, S가 있는지 확인한다. 이 프로젝트는 CS만 남기고 다 지운다.
             // 여기서 읽고 CS가 포함되어있지 않으면 버린다. 
             // C,S가 있으면 sink 
            }      
            if(isCS == true) {
                for(int i = 0; i<idx; i++) 
                    out.write((char)buffer[i]);
                isCS = false;
            }
            if (byte_read == -1) return true;
            idx = 0;
            numOfBlank = 0;
            byte_read = '\0';
        }
    }  
}

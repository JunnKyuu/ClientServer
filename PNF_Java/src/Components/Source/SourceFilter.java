/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Components.Source;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import Framework.CommonFilterImpl;

public class SourceFilter extends CommonFilterImpl{
    private String sourceFile;
    
    public SourceFilter(String inputFile){
        this.sourceFile = inputFile;
    }    
    @Override
    public boolean specificComputationForFilter() throws IOException {
        int byte_read;    
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(new File(sourceFile)));
        // 파일이름을 한 바이트씩 읽는다. 
        // rmi에서는 interface에 함수가 정해져있었는데, PF에서는 범용적이게 만들기 위해서 byte 단위로 흐른다. 
        // 자바가 pipe에 1바이트씩 읽게 미리 코딩을 해놓았다.
        // "60222126 이준규"가 있다면 6,0,2,2 이런식으로 한 바이트씩 읽는다. 빈 칸도 읽는다. 
       
        // PF에서 정해버리면 pip를 꽂는데 힘들다.
        // 1비트는 2개의 01이 붙어있고, 64비트라고 하면 이게 64개가 있다는 것이다. 
        // 숫자 하나, 알파벳 1개가 1바이트이다.
        
        while(true) {
            byte_read = br.read();
            if (byte_read == -1) return true;
            out.write(byte_read);
        }
    }
}

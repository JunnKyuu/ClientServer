package Components.AddCourseFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import Framework.CommonFilterImpl;

public class AddCourseFilter extends CommonFilterImpl {
    @Override
    public boolean specificComputationForFilter() throws IOException {
        int idx = 0;
        byte[] buffer = new byte[64];
        int byte_read = 0;
        String student = "";
        StringBuilder result = new StringBuilder();

        while (true) {
            while ((byte_read = in.read()) != '\n' && byte_read != -1) {
                if (byte_read != -1) buffer[idx++] = (byte) byte_read;
            }

            student = new String(buffer, 0, idx, StandardCharsets.UTF_8); // byte -> String
            String[] studentArr = student.split("\n"); // String add to array 

            for (int i = 0; i < studentArr.length; i++) {
            	// add 12345
            	if (!studentArr[i].contains("12345") && studentArr[i].length()>0) {
        	        studentArr[i] = studentArr[i].substring(0, studentArr[i].length()-1);
            	    studentArr[i] += " 12345";
            	}
                result.append(studentArr[i]).append("\n");
            }
            if (byte_read == -1) break;
            idx = 0;
        }

        // write
        byte[] resultBytes = result.toString().getBytes(StandardCharsets.UTF_8);
        out.write(resultBytes);

        return true;
    }
}
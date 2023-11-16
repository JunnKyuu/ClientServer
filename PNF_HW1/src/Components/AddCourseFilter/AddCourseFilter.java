package Components.AddCourseFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import Framework.CommonFilterImpl;

public class AddCourseFilter extends CommonFilterImpl {
    @Override
    public boolean specificComputationForFilter() throws IOException {
        int checkBlank = 5;
        int numOfBlank = 0;
        int idx = 0;
        byte[] buffer = new byte[1000];
        int byte_read = 0;
        String student = "";
        StringBuilder resultBuilder = new StringBuilder();

        while (true) {
            while ((byte_read = in.read()) != '\n' && byte_read != -1) {
                if (byte_read == ' ') numOfBlank++;
                if (byte_read != -1) buffer[idx++] = (byte) byte_read;
            }

            student = new String(buffer, 0, idx, StandardCharsets.UTF_8);

            String[] lines = student.split("\n");

            for (int i = 0; i < lines.length; i++) {
            	if (!lines[i].contains("12345")) {
            	    if (lines[i].length() > 0) {
            	        lines[i] = lines[i].substring(0, lines[i].length() - 1);
            	    }
            	    lines[i] += " 12345";
            	}

                resultBuilder.append(lines[i]).append("\n");
            }

            if (byte_read == -1) break;
            idx = 0;
            numOfBlank = 0;
        }

        byte[] resultBytes = resultBuilder.toString().getBytes(StandardCharsets.UTF_8);
        out.write(resultBytes);

        return true;
    }
}
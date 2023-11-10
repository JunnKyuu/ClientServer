package Framework;

import java.io.EOFException;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Vector;

public abstract class CommonFilterImpl implements CommonFilter {
	protected PipedInputStream in = new PipedInputStream();// 들어오는 파이프 
	protected PipedOutputStream out = new PipedOutputStream(); // 나가는 파이프 

	public void connectOutputTo(CommonFilter nextFilter) throws IOException {
		out.connect(nextFilter.getPipedInputStream()); // 연결 
	}
	public void connectInputTo(CommonFilter previousFilter) throws IOException {
		in.connect(previousFilter.getPipedOutputStream()); // 연결 
	}
	public PipedInputStream getPipedInputStream() {
		return in;
	}
	public PipedOutputStream getPipedOutputStream() {
		return out;
	}
	
	abstract public boolean specificComputationForFilter() throws IOException;
	// Implementation defined in Runnable interface for thread
	// abstract: 부모에서 선언만 하고, 자식에서 구현을 해야한다. 
	public void run() {
		// 자바에서 thread를 실행시키려면 run함수를 써야함. main() 같은 느낌 
		try {
			specificComputationForFilter();
			// 지금은 선언만 되어있어서 자식에서 선언된 것을 실행한다. 
		} catch (IOException e) {
			if (e instanceof EOFException) return;
			else System.out.println(e);
		} finally {
			closePorts();
		}
	}
	private void closePorts() {
		try {
			out.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

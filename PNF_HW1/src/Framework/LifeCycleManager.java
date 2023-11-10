package Framework;

import Components.AddCourseFilter.AddCourseFilter;
import Components.DeptFilter.DeptFilter;
import Components.Sink.SinkFilter;
import Components.Source.SourceFilter;

public class LifeCycleManager {
    public static void main(String[] args) {
        try {
            CommonFilter sourceFilter = new SourceFilter("Students.txt");
            CommonFilter sinkFilter = new SinkFilter("Output.txt");
            CommonFilter deptFilter = new DeptFilter();
            CommonFilter addCourseFilter = new AddCourseFilter();
            
//            sourceFilter.connectOutputTo(deptFilter);
//            deptFilter.connectOutputTo(sinkFilter);
            
            sourceFilter.connectOutputTo(deptFilter);
            deptFilter.connectOutputTo(addCourseFilter);
            addCourseFilter.connectOutputTo(sinkFilter);

            Thread sourceThread = new Thread(sourceFilter);
            Thread sinkThread = new Thread(sinkFilter);
            Thread deptThread = new Thread(deptFilter);
            Thread addCourseThread = new Thread(addCourseFilter);

            sourceThread.start();
            sinkThread.start();
            deptThread.start();
            addCourseThread.start();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

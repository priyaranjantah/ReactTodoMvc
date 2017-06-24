package org.maven.todomvc.DataDrivenTesting;

import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 6/24/2017.
 */
public class TestRunner {
    public static void main(String[] args) {
        TestNG runner = new TestNG();
        List<String> list = new ArrayList<String>();
        list.add("D:\\IdeaProjects\\ReactTodoMvc\\testng.xml");
        runner.setTestSuites(list);
        runner.run();
    }
}

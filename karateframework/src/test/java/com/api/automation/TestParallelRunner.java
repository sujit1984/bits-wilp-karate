package com.api.automation;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.intuit.karate.Results;
import com.intuit.karate.Runner.Builder;

import cucumber.api.CucumberOptions;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
//import com.intuit.karate.junit5.Karate.Test;

//@CucumberOptions(plugin = {"json:target/cucumber/cucumber.json"})
//@KarateOptions(tags = "~@ignore")
public class TestParallelRunner {
	
	@Test
	public void testParallel() {
		
		//String outputDir = "target//surefire-reports";
		Builder testRun = new Builder();
		testRun.path("classpath:com/api/automation/Features").outputCucumberJson(true).tags("~@ignore");
	    Results results = testRun.parallel(3);
		System.out.println("Total Features ==> " + results.getFeaturesTotal());
		System.out.println("Total Scenarios ==> " + results.getScenariosTotal());
		System.out.println("Total Scenarios ==> " + results.getScenariosPassed());
		System.out.println("Total Scenarios ==> " + results.getScenariosFailed());
	    generateReport(results.getReportDir());

		
		//Assertions.assertEquals(0, results.getFailCount(), "There are some Failed Scenarios");
		

	}
	
	public static void generateReport(String reportDirLocation) {
		File reportDir=new File(reportDirLocation);		
		Collection<File> jsonFiles = FileUtils.listFiles(reportDir, new String[] {"json"}, true);
		//jsonFiles.add(File("cucumber-report.json"));
		List<String> jsonPaths = new ArrayList<>();
		
		//jsonFiles.add("cucumber-report-2.json");
		jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));
		Configuration config = new Configuration(new File("target"), "Cucumber Report");
		ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
		reportBuilder.generateReports();
		
		
		}


}

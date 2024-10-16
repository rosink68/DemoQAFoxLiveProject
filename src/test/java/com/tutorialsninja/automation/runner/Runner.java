package com.tutorialsninja.automation.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"classpath:FeatureFiles/Login.feature",
							 "classpath:FeatureFiles/Register.feature"},
				 glue = {"classpath:com.tutorialsninja.automation.stepdef"},
				 plugin = {"pretty",
						   "html:target/cucumber_html_report.html"})
public class Runner {

}

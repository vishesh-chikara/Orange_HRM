package com.orangeHRM_Tests;

import org.testng.annotations.Test;
import com.orangeHRM_Base.BaseClassHRM;

public class DummyClass extends BaseClassHRM {
	
	@Test
	public void Dummy ()
	{
		String Title =driver.getTitle();
		System.out.println(driver.getTitle());
		assert Title.equals("OrangeHRM") : "Test Failed  -Title is not macthing" ;
		System.out.println("Test Passed - Title is passed");
	}

}

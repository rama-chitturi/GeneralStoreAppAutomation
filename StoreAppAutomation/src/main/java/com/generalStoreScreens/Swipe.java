package com.generalStoreScreens;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Rectangle;

import com.base.TestBase;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Swipe extends TestBase{
	
	public void swipeScreen(String dir) {
		
	    System.out.println("swipeScreen(): dir: '" + dir + "'"); // always log your actions

	    // Animation default time:
	    //  - Android: 300 ms
	    //  - iOS: 200 ms
	    // final value depends on your app and could be greater
	    final int ANIMATION_TIME = 200; // ms

	    final int PRESS_TIME = 200; // ms

	    int edgeBorder = 10; // better avoid edges
	    PointOption pointOptionStart, pointOptionEnd;

	    // init screen variables
	
	    Dimension dims = driver.manage().window().getSize();
	   
	    // init start point = center of screen
	    pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);

	    switch (dir) {
	        case "DOWN": // center of footer
	            pointOptionEnd = PointOption.point(dims.width / 2, dims.height - edgeBorder);
	            break;
	        case "UP": // center of header
	            pointOptionEnd = PointOption.point(dims.width / 2, edgeBorder);
	            break;
	        case "LEFT": // center of left side
	            pointOptionEnd = PointOption.point(edgeBorder, dims.height / 2);
	            break;
	        case "RIGHT": // center of right side
	            pointOptionEnd = PointOption.point(dims.width - edgeBorder, dims.height / 2);
	            break;
	        default:
	            throw new IllegalArgumentException("swipeScreen(): dir: '" + dir + "' NOT supported");
	    }

	    // execute swipe using TouchAction
	    try {
	        new TouchAction(driver)
	                .press(pointOptionStart)
	                // a bit more reliable when we add small wait
	                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
	                .moveTo(pointOptionEnd)
	                .release().perform();
	        System.out.println("Swipe done");
	    } catch (Exception e) {
	        System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
	        return;
	    }
	    
	   

	    // always allow swipe action to complete
	    try {
	        Thread.sleep(ANIMATION_TIME);
	    } catch (InterruptedException e) {
	        // ignore
	    }
	}


	public void eleSwipe(MobileElement el, String dir)
	{
		
		    // Animation default time:
		    //  - Android: 300 ms
		    //  - iOS: 200 ms
		    // final value depends on your app and could be greater
		    final int ANIMATION_TIME = 200; // ms

		    final int PRESS_TIME = 150; // ms

		    int edgeBorder=0;
		    PointOption pointOptionStart, pointOptionEnd;
		  
 
		    // init screen variables
		    Rectangle rect = el.getRect();
		    // sometimes it is needed to configure edgeBorders
		    // you can also improve borders to have vertical/horizontal
		    // or left/right/up/down border variables
		 
		    switch (dir) {
		        case "DOWN": // from up to down
		            pointOptionStart = PointOption.point(rect.x + rect.width / 2,
		                    rect.y + edgeBorder);
		            pointOptionEnd = PointOption.point(rect.x + rect.width / 2,
		                    rect.y + rect.height - edgeBorder);
		            break;
		        case "UP": // from down to up
		            pointOptionStart = PointOption.point(rect.x + rect.width / 2,
		                    rect.y + rect.height - edgeBorder);

		            pointOptionEnd = PointOption.point(rect.x + rect.width / 2,
		                    rect.y + edgeBorder); 
		            break;
		        case "LEFT": // from right to left
		            pointOptionStart = PointOption.point(rect.x + rect.width - edgeBorder,
		                    rect.y + rect.height / 2);
		            pointOptionEnd = PointOption.point(rect.x + edgeBorder,
		                    rect.y + rect.height / 2);
		            break;
		        case "RIGHT": // from left to right
		            pointOptionStart = PointOption.point(rect.x + edgeBorder,
		                    rect.y + rect.height / 2);
		            pointOptionEnd = PointOption.point(rect.x + rect.width - edgeBorder,
		                    rect.y + rect.height / 2);
		            break;
		        default:
		            throw new IllegalArgumentException("swipeElementAndroid(): dir: '" + dir + "' NOT supported");
		    }

		    // execute swipe using TouchAction
		    try {
		        new TouchAction(driver)
		                .press(pointOptionStart)
		                // a bit more reliable when we add small wait
		                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
		                .moveTo(pointOptionEnd)
		                .release().perform();
		    } catch (Exception e) {
		        System.err.println("swipeElementAndroid(): TouchAction FAILED\n" + e.getMessage());
		        return;
		    }

		    // always allow swipe action to complete
		/*    try {
		        Thread.sleep(ANIMATION_TIME);
		    } catch (InterruptedException e) {
		        // ignore
		    } */
		}

	}


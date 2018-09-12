package com.k2.WidgetAssembly;

import static org.junit.Assert.*;

import java.io.StringWriter;
import java.io.Writer;
import java.lang.invoke.MethodHandles;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.WidgetAssembly.AWidget;
import com.k2.WidgetAssembly.WidgetAssemblyNode;
import com.k2.WidgetAssembly.sample.sampleWidget.SampleWidget;
import com.k2.WidgetAssembly.sample.sampleWidget.SampleWidgetData;
import com.k2.WidgetAssembly.sample.sampleWidget.SampleWidgetInterface;
import com.k2.WidgetAssembly.sample.widgetA.WidgetA;
import com.k2.WidgetAssembly.sample.widgetB.WidgetB;




public class ContainedWidgetTests {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
		

	
	@Test
	public void createTest() {
		
		AWidget<SampleWidgetInterface> w = SampleWidget.create();
		
		WidgetAssemblyNode cw = WidgetAssemblyNode.rootNode(null, SampleWidgetInterface.class, w);
		
		assertNotNull(cw);
		
		assertNull(cw.getContainerAlias());
		assertEquals(w, cw.getWidget());
		assertEquals("", cw.getFieldAlias());
		
		
	}
	
	
	
	
	
	
	
}

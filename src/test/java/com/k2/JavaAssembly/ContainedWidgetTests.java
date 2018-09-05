package com.k2.JavaAssembly;

import static org.junit.Assert.*;

import java.io.StringWriter;
import java.io.Writer;
import java.lang.invoke.MethodHandles;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.JavaAssembly.sample.sampleWidget.SampleWidget;
import com.k2.JavaAssembly.sample.sampleWidget.SampleWidgetData;
import com.k2.JavaAssembly.sample.sampleWidget.SampleWidgetInterface;
import com.k2.JavaAssembly.sample.widgetA.WidgetA;
import com.k2.JavaAssembly.sample.widgetB.WidgetB;




public class ContainedWidgetTests {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
		

	
	@Test
	public void createTest() {
		
		AWidget<SampleWidgetInterface> w = SampleWidget.create();
		
		ContainedWidget cw = ContainedWidget.create(Object.class, "Container", w, "alias");
		
		assertNotNull(cw);
		
		assertEquals("Container", cw.getContainerAlias());
		assertEquals(w, cw.getWidget());
		assertEquals("alias", cw.getFieldAlias());
		
		
	}
	
	
	
	
	
	
	
}

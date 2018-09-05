package com.k2.WidgetAssembly;

import static org.junit.Assert.*;

import java.io.StringWriter;
import java.io.Writer;
import java.lang.invoke.MethodHandles;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.WidgetAssembly.AWidget;
import com.k2.WidgetAssembly.sample.sampleWidget.SampleWidget;
import com.k2.WidgetAssembly.sample.sampleWidget.SampleWidgetData;
import com.k2.WidgetAssembly.sample.sampleWidget.SampleWidgetInterface;
import com.k2.WidgetAssembly.sample.widgetA.WidgetA;
import com.k2.WidgetAssembly.sample.widgetB.WidgetB;




public class WidgetTests {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
		

	
	@Test
	public void assembleWidgetsTest() {
		
		WidgetA wa = WidgetA.create();
		WidgetB wb = WidgetB.create();
		
		AWidget<SampleWidgetInterface> root = SampleWidget.create()
				.bind("A", wa, "widgetA")
				.bind("A", wb, "widgetB")
				.bind("B", wa, "widgetAs")
				.bind("B", wb, "widgetBs");
		
		
		assertEquals(2, root.getContainedWidgets("A").size());
		assertEquals(2, root.getContainedWidgets("B").size());
		
		assertEquals("A", root.getContainedWidgets("A").get(0).getContainerAlias());
		assertEquals("A", root.getContainedWidgets("A").get(1).getContainerAlias());
		assertEquals(wa, root.getContainedWidgets("A").get(0).getWidget());
		assertEquals(wb, root.getContainedWidgets("A").get(1).getWidget());
		assertEquals("widgetA", root.getContainedWidgets("A").get(0).getFieldAlias());
		assertEquals("widgetB", root.getContainedWidgets("A").get(1).getFieldAlias());
		
		assertEquals("B", root.getContainedWidgets("B").get(0).getContainerAlias());
		assertEquals("B", root.getContainedWidgets("B").get(1).getContainerAlias());
		assertEquals(wa, root.getContainedWidgets("B").get(0).getWidget());
		assertEquals(wb, root.getContainedWidgets("B").get(1).getWidget());
		assertEquals("widgetAs", root.getContainedWidgets("B").get(0).getFieldAlias());
		assertEquals("widgetBs", root.getContainedWidgets("B").get(1).getFieldAlias());
		
		
	}
	
	
	
	
	
	
	
}

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
		SampleWidget sw = SampleWidget.create();
		
		WidgetAssembly<SampleWidgetInterface> assembly = WidgetAssembly.create(sw);
		assembly.root()
				.bind("A", wa, "widgetA").up()
				.bind("A", wb, "widgetB").up()
				.bind("B", wa, "widgetAs").up()
				.bind("B", wb, "widgetBs").up();
		
		
		assertEquals(2, assembly.root().getContainedNodes("A").size());
		assertEquals(2, assembly.root().getContainedNodes("B").size());
		
		assertEquals("A", assembly.root().getContainedNodes("A").get(0).getContainerAlias());
		assertEquals("A", assembly.root().getContainedNodes("A").get(1).getContainerAlias());
		assertEquals(wa, assembly.root().getContainedNodes("A").get(0).getWidget());
		assertEquals(wb, assembly.root().getContainedNodes("A").get(1).getWidget());
		assertEquals("widgetA", assembly.root().getContainedNodes("A").get(0).getFieldAlias());
		assertEquals("widgetB", assembly.root().getContainedNodes("A").get(1).getFieldAlias());
		
		assertEquals("B", assembly.root().getContainedNodes("B").get(0).getContainerAlias());
		assertEquals("B", assembly.root().getContainedNodes("B").get(1).getContainerAlias());
		assertEquals(wa, assembly.root().getContainedNodes("B").get(0).getWidget());
		assertEquals(wb, assembly.root().getContainedNodes("B").get(1).getWidget());
		assertEquals("widgetAs", assembly.root().getContainedNodes("B").get(0).getFieldAlias());
		assertEquals("widgetBs", assembly.root().getContainedNodes("B").get(1).getFieldAlias());
		
		
	}
	
	
	
	
	
	
	
}

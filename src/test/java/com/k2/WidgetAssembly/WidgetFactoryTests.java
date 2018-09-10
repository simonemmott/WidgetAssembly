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
import com.k2.WidgetAssembly.sample.widgetA.WidgetAInterface;
import com.k2.WidgetAssembly.sample.widgetB.WidgetB;
import com.k2.WidgetAssembly.sample.widgetB.WidgetBInterface;
import com.k2.WidgetFactory.WidgetFactory;




public class WidgetFactoryTests {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
		

	
	@Test
	public void createWidgetFactory() {	
		WidgetFactory wf = WidgetFactory.create();
		assertNotNull(wf);	
	}
	
	@Test
	public void registerWidget() {
		WidgetFactory wf = WidgetFactory.create();
		
		wf.register(WidgetA.class, "alias");
		
		AWidget<WidgetAInterface> w = wf.get(WidgetAInterface.class, "alias");
		
		assertNotNull(w);
		assertEquals(WidgetA.class, w.getClass());
	}
	
	@Test
	public void registerAnotatedWidget() {
		WidgetFactory wf = WidgetFactory.create();
		
		wf.register(WidgetA.class);
		
		AWidget<WidgetAInterface> w = wf.get(WidgetAInterface.class, "sample");
		
		assertNotNull(w);
		assertEquals(WidgetA.class, w.getClass());
		
	}
	
	@Test
	public void registerPackageScan() {
		WidgetFactory wf = WidgetFactory.create("com.k2.WidgetAssembly.sample");
				
		AWidget<WidgetAInterface> wa = wf.get(WidgetAInterface.class, "sample");
		
		assertNotNull(wa);
		assertEquals(WidgetA.class, wa.getClass());
		
		AWidget<WidgetBInterface> wb = wf.get(WidgetBInterface.class, "sample");
		
		assertNotNull(wb);
		assertEquals(WidgetB.class, wb.getClass());
		
	}
	
	
	
	
}

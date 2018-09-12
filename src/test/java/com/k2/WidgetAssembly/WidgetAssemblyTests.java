package com.k2.WidgetAssembly;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.k2.WidgetAssembly.AWidget;
import com.k2.WidgetAssembly.WidgetAssembly;
import com.k2.WidgetAssembly.sample.sampleWidget.SampleTemplate;
import com.k2.WidgetAssembly.sample.sampleWidget.SampleWidget;
import com.k2.WidgetAssembly.sample.sampleWidget.SampleWidgetData;
import com.k2.WidgetAssembly.sample.sampleWidget.SampleWidgetInterface;
import com.k2.WidgetAssembly.sample.widgetA.WidgetA;
import com.k2.WidgetAssembly.sample.widgetA.WidgetAData;
import com.k2.WidgetAssembly.sample.widgetA.WidgetAInterface;
import com.k2.WidgetAssembly.sample.widgetB.WidgetB;
import com.k2.WidgetAssembly.sample.widgetB.WidgetBData;
import com.k2.WidgetAssembly.sample.widgetB.WidgetBInterface;
import com.k2.WidgetFactory.WidgetFactory;




public class WidgetAssemblyTests {
	
   static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
		

	
	@Test
	public void createAssemblyWithWidgetTest() {
		WidgetAssembly<SampleWidgetInterface> wa = WidgetAssembly.create(SampleWidget.create());
		assertNotNull(wa);
	}
	
	@Test
	public void extractRootWigetTest() {
		WidgetAssembly<SampleWidgetInterface> wa = WidgetAssembly.create(SampleWidget.create());
		AWidget<SampleWidgetInterface> w = wa.root().getWidget();
		assertNotNull(w);
	}
	
	@Test
	public void widgetOutput() throws IOException {
		WidgetAssembly<SampleWidgetInterface> wa = WidgetAssembly.create(SampleWidget.create());
		
		SampleWidgetInterface swi = new SampleWidgetData("Sample", "Widget");
		
		Writer sw = wa.output(swi, new StringWriter());
		
		String expected = ""
				+ "SAMPLE WIDGET\n"
				+ "Sample(Widget)\n"
				+ "Container A [\n"
				+ "]\n"
				+ "Container B [\n"
				+ "]\n";
		
		assertEquals(expected, sw.toString());
		
	}

	@Test
	public void setIndentTest() {
		WidgetAssembly<SampleWidgetInterface> wa = WidgetAssembly.create(SampleWidget.create());
		wa.setIndentStr("    ");
		
		assertEquals("", wa.getIndent());
		
		wa.indent();
		assertEquals("    ", wa.getIndent());
		
		wa.indent();
		assertEquals("        ", wa.getIndent());
		
		wa.setIndentStr("\t");
		assertEquals("\t\t", wa.getIndent());
		
		wa.outdent();
		assertEquals("\t", wa.getIndent());

		assertEquals("    ", wa.setIndentStr("    ").getIndent());
		
	}
	
	
	@Test
	public void widgetAssembleyOutput() throws IOException {

		
		WidgetA wa = WidgetA.create();
		WidgetB wb = WidgetB.create();
		
		WidgetAssembly<SampleWidgetInterface> a = WidgetAssembly.create(SampleWidget.create());
		a.root()
				.bind("A", wa, "widgetA").up()
				.bind("A", wb, "widgetB").up()
				.bind("B", wa, "widgetAs").up()
				.bind("B", wb, "widgetBs").up();
		
		SampleWidgetInterface swi = new SampleWidgetData("Sample", "Widget");
		swi.setWidgetA(new WidgetAData("widgetA", 1L));
		swi.setWidgetB(new WidgetBData("widgetB", 1L));
		List<WidgetAInterface> widgetAs = new ArrayList<WidgetAInterface>();
		widgetAs.add(new WidgetAData("widgetA1", 1L));
		widgetAs.add(new WidgetAData("widgetA2", 2L));
		widgetAs.add(new WidgetAData("widgetA3", 3L));
		swi.setWidgetAs(widgetAs);
		List<WidgetBInterface> widgetBs = new ArrayList<WidgetBInterface>();
		widgetBs.add(new WidgetBData("widgetB1", 1L));
		widgetBs.add(new WidgetBData("widgetB2", 2L));
		swi.setWidgetBs(widgetBs);
		
		
		
		Writer sw = a.output(swi, new StringWriter());
		
		String expected = ""
				+ "SAMPLE WIDGET\n"
				+ "Sample(Widget)\n"
				+ "Container A [\n"
				+ "    WIDGET A\n"
				+ "    widgetA[1]\n"
				+ "    WIDGET B\n"
				+ "    widgetB[1]\n"
				+ "]\n"
				+ "Container B [\n"
				+ "    WIDGET A\n"
				+ "    widgetA1[1]\n"
				+ "    WIDGET A\n"
				+ "    widgetA2[2]\n"
				+ "    WIDGET A\n"
				+ "    widgetA3[3]\n"
				+ "    WIDGET B\n"
				+ "    widgetB1[1]\n"
				+ "    WIDGET B\n"
				+ "    widgetB2[2]\n"
				+ "]\n";
		
		assertEquals(expected, sw.toString());


	}
	
	@Test
	public void templateAssembleyOutput() throws IOException {

		
		WidgetA wa = WidgetA.create();
//		WidgetB wb = WidgetB.create();
		
		WidgetFactory wf = WidgetFactory.create("com.k2.WidgetAssembly.sample");
		
		ATemplate<SampleWidgetInterface> template = new SampleTemplate(wf);
		
		WidgetAssembly<SampleWidgetInterface> a = WidgetAssembly.create(template);

		a.root()
				.bind("C", wa, "widgetAs").up();
//				.bind("A", wb, "widgetB").up()
//				.bind("B", wa, "widgetAs").up()
//				.bind("B", wb, "widgetBs").up();
		
		SampleWidgetInterface swi = new SampleWidgetData("Sample", "Widget");
		swi.setWidgetA(new WidgetAData("widgetA", 1L));
		swi.setWidgetB(new WidgetBData("widgetB", 1L));
		List<WidgetAInterface> widgetAs = new ArrayList<WidgetAInterface>();
		widgetAs.add(new WidgetAData("widgetA1", 1L));
		widgetAs.add(new WidgetAData("widgetA2", 2L));
		widgetAs.add(new WidgetAData("widgetA3", 3L));
		swi.setWidgetAs(widgetAs);
		List<WidgetBInterface> widgetBs = new ArrayList<WidgetBInterface>();
		widgetBs.add(new WidgetBData("widgetB1", 1L));
		widgetBs.add(new WidgetBData("widgetB2", 2L));
		swi.setWidgetBs(widgetBs);
		
		
		
		Writer sw = a.output(swi, new StringWriter());
		
		String expected = ""
				+ "SAMPLE WIDGET\n"
				+ "Sample(Widget)\n"
				+ "Container A [\n"
				+ "    WIDGET A\n"
				+ "    widgetA[1]\n"
				+ "    WIDGET B\n"
				+ "    widgetB[1]\n"
				+ "]\n"
				+ "Container B [\n"
				+ "    WIDGET A\n"
				+ "    widgetA1[1]\n"
				+ "    WIDGET A\n"
				+ "    widgetA2[2]\n"
				+ "    WIDGET A\n"
				+ "    widgetA3[3]\n"
//				+ "    WIDGET B\n"
//				+ "    widgetB1[1]\n"
//				+ "    WIDGET B\n"
//				+ "    widgetB2[2]\n"
				+ "]\n";
		
		assertEquals(expected, sw.toString());


	}
	
	
	
	
	
	
	
	
	
}

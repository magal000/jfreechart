/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2020, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 * [Oracle and Java are registered trademarks of Oracle and/or its affiliates. 
 * Other names may be trademarks of their respective owners.]
 *
 * --------------------------
 * YIntervalRendererTest.java
 * --------------------------
 * (C) Copyright 2003-2020, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 25-Mar-2003 : Version 1 (DG);
 * 20-Feb-2007 : Extended the testEquals() checks (DG);
 * 17-May-2007 : Added testGetLegendItemSeriesIndex() (DG);
 * 22-Apr-2008 : Added testPublicCloneable() (DG);
 * 26-May-2008 : Extended testEquals() (DG);
 *
 */

package org.jfree.chart.renderer.xy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.jfree.chart.TestUtils;
import org.junit.jupiter.api.Test;

import se.malmin.chart.JFreeChart;
import se.malmin.chart.LegendItem;
import se.malmin.chart.annotations.XYTextAnnotation;
import se.malmin.chart.axis.NumberAxis;
import se.malmin.chart.labels.IntervalXYItemLabelGenerator;
import se.malmin.chart.labels.StandardXYItemLabelGenerator;
import se.malmin.chart.labels.StandardXYSeriesLabelGenerator;
import se.malmin.chart.labels.StandardXYToolTipGenerator;
import se.malmin.chart.plot.XYPlot;
import se.malmin.chart.renderer.xy.YIntervalRenderer;
import se.malmin.chart.ui.Layer;
import se.malmin.chart.urls.StandardXYURLGenerator;
import se.malmin.chart.util.PublicCloneable;
import se.malmin.data.xy.YIntervalSeries;
import se.malmin.data.xy.YIntervalSeriesCollection;

/**
 * Tests for the {@link YIntervalRenderer} class.
 */
public class YIntervalRendererTest {

    /**
     * Check that the equals() method distinguishes all fields.
     */
    @Test
    public void testEquals() {
        YIntervalRenderer r1 = new YIntervalRenderer();
        YIntervalRenderer r2 = new YIntervalRenderer();
        assertEquals(r1, r2);

        // the following fields are inherited from the AbstractXYItemRenderer
        r1.setSeriesItemLabelGenerator(0, new StandardXYItemLabelGenerator());
        assertFalse(r1.equals(r2));
        r2.setSeriesItemLabelGenerator(0, new StandardXYItemLabelGenerator());
        assertTrue(r1.equals(r2));

        r1.setDefaultItemLabelGenerator(new StandardXYItemLabelGenerator());
        assertFalse(r1.equals(r2));
        r2.setDefaultItemLabelGenerator(new StandardXYItemLabelGenerator());
        assertTrue(r1.equals(r2));

        r1.setSeriesToolTipGenerator(0, new StandardXYToolTipGenerator());
        assertFalse(r1.equals(r2));
        r2.setSeriesToolTipGenerator(0, new StandardXYToolTipGenerator());
        assertTrue(r1.equals(r2));

        r1.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
        assertFalse(r1.equals(r2));
        r2.setDefaultToolTipGenerator(new StandardXYToolTipGenerator());
        assertTrue(r1.equals(r2));

        r1.setURLGenerator(new StandardXYURLGenerator());
        assertFalse(r1.equals(r2));
        r2.setURLGenerator(new StandardXYURLGenerator());
        assertTrue(r1.equals(r2));

        r1.addAnnotation(new XYTextAnnotation("X", 1.0, 2.0), Layer.FOREGROUND);
        assertFalse(r1.equals(r2));
        r2.addAnnotation(new XYTextAnnotation("X", 1.0, 2.0), Layer.FOREGROUND);
        assertTrue(r1.equals(r2));

        r1.addAnnotation(new XYTextAnnotation("X", 1.0, 2.0), Layer.BACKGROUND);
        assertFalse(r1.equals(r2));
        r2.addAnnotation(new XYTextAnnotation("X", 1.0, 2.0), Layer.BACKGROUND);
        assertTrue(r1.equals(r2));

        r1.setDefaultEntityRadius(99);
        assertFalse(r1.equals(r2));
        r2.setDefaultEntityRadius(99);
        assertTrue(r1.equals(r2));

        r1.setLegendItemLabelGenerator(new StandardXYSeriesLabelGenerator(
                "{0} {1}"));
        assertFalse(r1.equals(r2));
        r2.setLegendItemLabelGenerator(new StandardXYSeriesLabelGenerator(
                "{0} {1}"));
        assertTrue(r1.equals(r2));

        r1.setLegendItemToolTipGenerator(new StandardXYSeriesLabelGenerator());
        assertFalse(r1.equals(r2));
        r2.setLegendItemToolTipGenerator(new StandardXYSeriesLabelGenerator());
        assertTrue(r1.equals(r2));

        r1.setLegendItemURLGenerator(new StandardXYSeriesLabelGenerator());
        assertFalse(r1.equals(r2));
        r2.setLegendItemURLGenerator(new StandardXYSeriesLabelGenerator());
        assertTrue(r1.equals(r2));

        r1.setAdditionalItemLabelGenerator(new IntervalXYItemLabelGenerator());
        assertFalse(r1.equals(r2));
        r2.setAdditionalItemLabelGenerator(new IntervalXYItemLabelGenerator());
        assertTrue(r1.equals(r2));

    }

    /**
     * Two objects that are equal are required to return the same hashCode.
     */
    @Test
    public void testHashcode() {
        YIntervalRenderer r1 = new YIntervalRenderer();
        YIntervalRenderer r2 = new YIntervalRenderer();
        assertTrue(r1.equals(r2));
        int h1 = r1.hashCode();
        int h2 = r2.hashCode();
        assertEquals(h1, h2);
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        YIntervalRenderer r1 = new YIntervalRenderer();
        YIntervalRenderer r2 = (YIntervalRenderer) r1.clone();
        assertTrue(r1 != r2);
        assertTrue(r1.getClass() == r2.getClass());
        assertTrue(r1.equals(r2));

        // check independence
        r1.setSeriesItemLabelGenerator(0, new StandardXYItemLabelGenerator());
        assertFalse(r1.equals(r2));
        r2.setSeriesItemLabelGenerator(0, new StandardXYItemLabelGenerator());
        assertTrue(r1.equals(r2));

        r1.setSeriesToolTipGenerator(0, new StandardXYToolTipGenerator());
        assertFalse(r1.equals(r2));
        r2.setSeriesToolTipGenerator(0, new StandardXYToolTipGenerator());
        assertTrue(r1.equals(r2));

        r1.addAnnotation(new XYTextAnnotation("ABC", 1.0, 2.0),
                Layer.FOREGROUND);
        assertFalse(r1.equals(r2));
        r2.addAnnotation(new XYTextAnnotation("ABC", 1.0, 2.0),
                Layer.FOREGROUND);
        assertTrue(r1.equals(r2));

        r1.addAnnotation(new XYTextAnnotation("ABC", 1.0, 2.0),
                Layer.BACKGROUND);
        assertFalse(r1.equals(r2));
        r2.addAnnotation(new XYTextAnnotation("ABC", 1.0, 2.0),
                Layer.BACKGROUND);
        assertTrue(r1.equals(r2));

    }

    /**
     * Verify that this class implements {@link PublicCloneable}.
     */
    @Test
    public void testPublicCloneable() {
        YIntervalRenderer r1 = new YIntervalRenderer();
        assertTrue(r1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        YIntervalRenderer r1 = new YIntervalRenderer();
        YIntervalRenderer r2 = (YIntervalRenderer) TestUtils.serialised(r1);
        assertEquals(r1, r2);
    }

    /**
     * A check for the datasetIndex and seriesIndex fields in the LegendItem
     * returned by the getLegendItem() method.
     */
    @Test
    public void testGetLegendItemSeriesIndex() {
        YIntervalSeriesCollection d1 = new YIntervalSeriesCollection();
        YIntervalSeries s1 = new YIntervalSeries("S1");
        s1.add(1.0, 1.1, 1.2, 1.3);
        YIntervalSeries s2 = new YIntervalSeries("S2");
        s2.add(1.0, 1.1, 1.2, 1.3);
        d1.addSeries(s1);
        d1.addSeries(s2);

        YIntervalSeriesCollection d2 = new YIntervalSeriesCollection();
        YIntervalSeries s3 = new YIntervalSeries("S3");
        s3.add(1.0, 1.1, 1.2, 1.3);
        YIntervalSeries s4 = new YIntervalSeries("S4");
        s4.add(1.0, 1.1, 1.2, 1.3);
        YIntervalSeries s5 = new YIntervalSeries("S5");
        s5.add(1.0, 1.1, 1.2, 1.3);
        d2.addSeries(s3);
        d2.addSeries(s4);
        d2.addSeries(s5);

        YIntervalRenderer r = new YIntervalRenderer();
        XYPlot plot = new XYPlot(d1, new NumberAxis("x"),
                new NumberAxis("y"), r);
        plot.setDataset(1, d2);
        /*JFreeChart chart =*/ new JFreeChart(plot);
        LegendItem li = r.getLegendItem(1, 2);
        assertEquals("S5", li.getLabel());
        assertEquals(1, li.getDatasetIndex());
        assertEquals(2, li.getSeriesIndex());
    }

}

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
 * -------------------------------
 * AbstractXYItemRendererTest.java
 * -------------------------------
 * (C) Copyright 2004-2020, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 06-Oct-2004 : Version 1 (DG);
 * 24-Nov-2006 : Added cloning tests (DG);
 *
 */

package org.jfree.chart.renderer.xy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import se.malmin.chart.labels.StandardXYItemLabelGenerator;
import se.malmin.chart.labels.StandardXYSeriesLabelGenerator;
import se.malmin.chart.labels.StandardXYToolTipGenerator;
import se.malmin.chart.renderer.xy.AbstractXYItemRenderer;
import se.malmin.chart.renderer.xy.StandardXYItemRenderer;
import se.malmin.chart.renderer.xy.XYBarRenderer;
import se.malmin.data.Range;
import se.malmin.data.xy.XYDataset;
import se.malmin.data.xy.XYSeries;
import se.malmin.data.xy.XYSeriesCollection;

/**
 * Tests for the {@link AbstractXYItemRenderer} class.
 */
public class AbstractXYItemRendererTest {

    /**
     * Creates a test dataset.
     *
     * @return A test dataset.
     */
    private XYDataset createDataset1() {
        XYSeries series = new XYSeries("Series");
        series.add(1.0, 1.0);
        series.add(2.0, 2.0);
        series.add(3.0, 3.0);
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        return dataset;
    }

    private static final double EPSILON = 0.0000000001;

    /**
     * Some checks for the findDomainBounds() method.
     */
    @Test
    public void testFindDomainBounds() {
        AbstractXYItemRenderer renderer = new StandardXYItemRenderer();

        // check the bounds of a simple dataset
        XYDataset dataset = createDataset1();
        Range r = renderer.findDomainBounds(dataset);
        assertEquals(1.0, r.getLowerBound(), EPSILON);
        assertEquals(3.0, r.getUpperBound(), EPSILON);

        // check that a null dataset returns null bounds
        assertTrue(renderer.findDomainBounds(null) == null);
    }

    /**
     * Some checks for the findRangeBounds() method.
     */
    @Test
    public void testFindRangeBounds() {
        AbstractXYItemRenderer renderer = new StandardXYItemRenderer();
        // check that a null dataset returns null bounds
        assertTrue(renderer.findRangeBounds(null) == null);
    }

    /**
     * Check that the legendItemLabelGenerator is cloned.
     */
    @Test
    public void testCloning_LegendItemLabelGenerator() throws CloneNotSupportedException {
        StandardXYSeriesLabelGenerator generator
                = new StandardXYSeriesLabelGenerator("Series {0}");
        XYBarRenderer r1 = new XYBarRenderer();
        r1.setLegendItemLabelGenerator(generator);
        XYBarRenderer r2 = (XYBarRenderer) r1.clone();
        assertTrue(r1 != r2);
        assertTrue(r1.getClass() == r2.getClass());
        assertTrue(r1.equals(r2));

        // check that the generator has been cloned
        assertTrue(r1.getLegendItemLabelGenerator()
                != r2.getLegendItemLabelGenerator());
    }

    /**
     * Check that the legendItemToolTipGenerator is cloned.
     */
    @Test
    public void testCloning_LegendItemToolTipGenerator() 
            throws CloneNotSupportedException {
        StandardXYSeriesLabelGenerator generator
                = new StandardXYSeriesLabelGenerator("Series {0}");
        XYBarRenderer r1 = new XYBarRenderer();
        r1.setLegendItemToolTipGenerator(generator);
        XYBarRenderer r2 = (XYBarRenderer) r1.clone();

        assertTrue(r1 != r2);
        assertTrue(r1.getClass() == r2.getClass());
        assertTrue(r1.equals(r2));

        // check that the generator has been cloned
        assertTrue(r1.getLegendItemToolTipGenerator()
                != r2.getLegendItemToolTipGenerator());
    }

    /**
     * Check that the legendItemURLGenerator is cloned.
     */
    @Test
    public void testCloning_LegendItemURLGenerator() 
            throws CloneNotSupportedException {
        StandardXYSeriesLabelGenerator generator
                = new StandardXYSeriesLabelGenerator("Series {0}");
        XYBarRenderer r1 = new XYBarRenderer();
        r1.setLegendItemURLGenerator(generator);
        XYBarRenderer r2 = (XYBarRenderer) r1.clone();
        assertTrue(r1 != r2);
        assertTrue(r1.getClass() == r2.getClass());
        assertTrue(r1.equals(r2));

        // check that the generator has been cloned
        assertTrue(r1.getLegendItemURLGenerator()
                != r2.getLegendItemURLGenerator());
    }
    
    @Test
    public void testEquals_ObjectList() {
        XYBarRenderer r1 = new XYBarRenderer();
        r1.setSeriesItemLabelGenerator(0, new StandardXYItemLabelGenerator());
        XYBarRenderer r2 = new XYBarRenderer();
        r2.setSeriesItemLabelGenerator(0, new StandardXYItemLabelGenerator());
        assertEquals(r1, r2);
        r2.setSeriesItemLabelGenerator(1, new StandardXYItemLabelGenerator("X"));
        assertNotEquals(r1, r2);
    }

    @Test
    public void testEquals_ObjectList2() {
        XYBarRenderer r1 = new XYBarRenderer();
        r1.setSeriesToolTipGenerator(0, new StandardXYToolTipGenerator());
        XYBarRenderer r2 = new XYBarRenderer();
        r2.setSeriesToolTipGenerator(0, new StandardXYToolTipGenerator());
        assertEquals(r1, r2);
        r2.setSeriesToolTipGenerator(1, new StandardXYToolTipGenerator());
        assertNotEquals(r1, r2);
    }

}

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
 * ----------------------------------
 * GroupedStackedBarRendererTest.java
 * ----------------------------------
 * (C) Copyright 2004-2020, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 08-Jul-2004 : Version 1 (DG);
 * 23-Apr-2008 : Added testPublicCloneable() (DG);
 *
 */

package org.jfree.chart.renderer.category;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.jfree.chart.TestUtils;
import org.junit.jupiter.api.Test;

import se.malmin.chart.JFreeChart;
import se.malmin.chart.axis.CategoryAxis;
import se.malmin.chart.axis.NumberAxis;
import se.malmin.chart.plot.CategoryPlot;
import se.malmin.chart.renderer.category.GroupedStackedBarRenderer;
import se.malmin.chart.util.PublicCloneable;
import se.malmin.data.KeyToGroupMap;
import se.malmin.data.Range;
import se.malmin.data.category.DefaultCategoryDataset;

/**
 * Tests for the {@link GroupedStackedBarRenderer} class.
 */
public class GroupedStackedBarRendererTest {

    /**
     * Test that the equals() method distinguishes all fields.
     */
    @Test
    public void testEquals() {
        GroupedStackedBarRenderer r1 = new GroupedStackedBarRenderer();
        GroupedStackedBarRenderer r2 = new GroupedStackedBarRenderer();
        assertTrue(r1.equals(r2));
        assertTrue(r2.equals(r1));

        // map
        KeyToGroupMap m1 = new KeyToGroupMap("G1");
        m1.mapKeyToGroup("S1", "G2");
        r1.setSeriesToGroupMap(m1);
        assertFalse(r1.equals(r2));
        KeyToGroupMap m2 = new KeyToGroupMap("G1");
        m2.mapKeyToGroup("S1", "G2");
        r2.setSeriesToGroupMap(m2);
        assertTrue(r1.equals(r2));
    }

    /**
     * Confirm that cloning works.
     */
    @Test
    public void testCloning() throws CloneNotSupportedException {
        GroupedStackedBarRenderer r1 = new GroupedStackedBarRenderer();
        GroupedStackedBarRenderer r2 = (GroupedStackedBarRenderer) r1.clone();
        assertTrue(r1 != r2);
        assertTrue(r1.getClass() == r2.getClass());
        assertTrue(r1.equals(r2));
    }

    /**
     * Check that this class implements PublicCloneable.
     */
    @Test
    public void testPublicCloneable() {
        GroupedStackedBarRenderer r1 = new GroupedStackedBarRenderer();
        assertTrue(r1 instanceof PublicCloneable);
    }

    /**
     * Serialize an instance, restore it, and check for equality.
     */
    @Test
    public void testSerialization() {
        GroupedStackedBarRenderer r1 = new GroupedStackedBarRenderer();
        GroupedStackedBarRenderer r2 = (GroupedStackedBarRenderer) 
                TestUtils.serialised(r1);
        assertEquals(r1, r2);
    }

    /**
     * Draws the chart with a {@code null} info object to make sure that
     * no exceptions are thrown (particularly by code in the renderer).
     */
    @Test
    public void testDrawWithNullInfo() {
        try {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.addValue(1.0, "S1", "C1");
            dataset.addValue(2.0, "S1", "C2");
            dataset.addValue(3.0, "S2", "C1");
            dataset.addValue(4.0, "S2", "C2");
            GroupedStackedBarRenderer renderer
                    = new GroupedStackedBarRenderer();
            CategoryPlot plot = new CategoryPlot(dataset,
                    new CategoryAxis("Category"), new NumberAxis("Value"),
                    renderer);
            JFreeChart chart = new JFreeChart(plot);
            /* BufferedImage image = */ chart.createBufferedImage(300, 200,
                    null);
        }
        catch (NullPointerException e) {
            fail("No exception should be thrown.");
        }
    }

    /**
     * Some checks for the findRangeBounds() method.
     */
    @Test
    public void testFindRangeBounds() {
        GroupedStackedBarRenderer r = new GroupedStackedBarRenderer();
        assertNull(r.findRangeBounds(null));

        // an empty dataset should return a null range
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        assertNull(r.findRangeBounds(dataset));

        dataset.addValue(1.0, "R1", "C1");
        assertEquals(new Range(0.0, 1.0), r.findRangeBounds(dataset));

        dataset.addValue(-2.0, "R1", "C2");
        assertEquals(new Range(-2.0, 1.0), r.findRangeBounds(dataset));

        dataset.addValue(null, "R1", "C3");
        assertEquals(new Range(-2.0, 1.0), r.findRangeBounds(dataset));

        KeyToGroupMap m = new KeyToGroupMap("G1");
        m.mapKeyToGroup("R1", "G1");
        m.mapKeyToGroup("R2", "G1");
        m.mapKeyToGroup("R3", "G2");
        r.setSeriesToGroupMap(m);

        dataset.addValue(0.5, "R3", "C1");
        assertEquals(new Range(-2.0, 1.0), r.findRangeBounds(dataset));

        dataset.addValue(5.0, "R3", "C2");
        assertEquals(new Range(-2.0, 5.0), r.findRangeBounds(dataset));
    }

}
